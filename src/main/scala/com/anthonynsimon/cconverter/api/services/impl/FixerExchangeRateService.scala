package com.anthonynsimon.cconverter.api.services.impl

import com.anthonynsimon.cconverter.api.domain.{CurrencyCode, ExchangeRates}
import com.anthonynsimon.cconverter.api.services.ExchangeRateService
import com.google.inject.Inject
import com.twitter.finagle.builder.ClientBuilder
import com.twitter.finagle.http.{Http, Request}
import com.twitter.finagle.stats.DefaultStatsReceiver
import com.twitter.finagle.{Service, http}
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.util.{Duration, Future}

class FixerExchangeRateService @Inject()(mapper: FinatraObjectMapper) extends ExchangeRateService {

	private val apiAddr = "api.fixer.io:80"

	private val client: Service[http.Request, http.Response] = ClientBuilder()
			.codec(new Http())
			.hosts(apiAddr)
			.hostConnectionLimit(1)
			.timeout(Duration.fromMilliseconds(5000))
			.retries(2)
			.reportTo(DefaultStatsReceiver)
			.build()

	override def getRates(baseCurrency: CurrencyCode): Future[ExchangeRates] = {
		val request = buildRequest(baseCurrency)
		val response = client(request)

		response.map[ExchangeRates](result =>
			mapper.parse[ExchangeRates](result.contentString))
	}

	private def buildRequest(baseCurrency: CurrencyCode): http.Request = {
		val request = Request("/latest?base=" + baseCurrency)
		request.host = apiAddr
		request
	}
}
