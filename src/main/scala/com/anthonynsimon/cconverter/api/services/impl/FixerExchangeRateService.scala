package com.anthonynsimon.cconverter.api.services.impl

import com.anthonynsimon.cconverter.api.domain.{CurrencyCode, ExchangeRates}
import com.anthonynsimon.cconverter.api.services.{CacheService, ExchangeRateService}
import com.google.inject.Inject
import com.twitter.finagle.builder.ClientBuilder
import com.twitter.finagle.http.{Http, Request}
import com.twitter.finagle.stats.DefaultStatsReceiver
import com.twitter.finagle.{Service, http}
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.util.{Duration, Future}

class FixerExchangeRateService @Inject()(mapper: FinatraObjectMapper,
                                         cache: CacheService[CurrencyCode, ExchangeRates]) extends ExchangeRateService {

  private val host = "api.fixer.io:80"

  private val client: Service[http.Request, http.Response] = ClientBuilder()
    .codec(new Http())
    .hosts(host)
    .hostConnectionLimit(1)
    .timeout(Duration.fromMilliseconds(5000))
    .retries(2)
    .reportTo(DefaultStatsReceiver)
    .build()

  override def getRates(baseCurrency: CurrencyCode): Future[ExchangeRates] = {
    cache.get(baseCurrency) match {
      case Some(value) => Future.value[ExchangeRates](value)
      case _ => {
        val request = buildRequest(baseCurrency)
        val response = client(request).map(result => mapper.parse[ExchangeRates](result.contentString))
        response.onSuccess(rates => cache.put(baseCurrency, rates))
        response
      }
    }
  }

  private def buildRequest(baseCurrency: CurrencyCode): http.Request = {
    val request = Request("/latest?base=" + baseCurrency)
    // Request to fixer.io should contain host header
    request.host = host
    request
  }
}
