package com.anthonynsimon.cconverter.api.controllers

import com.anthonynsimon.cconverter.api.domain.{CurrencyConversionResult, CurrencyConvertRequest}
import com.anthonynsimon.cconverter.api.services.ExchangeRateService
import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.util.Await

class ExchangeRateController @Inject()(exchangeRateService: ExchangeRateService) extends Controller {

	prefix("/api") {
		get("/rates/:currencyCode") { request: Request =>
			exchangeRateService.getRates(request.params("currencyCode"))
		}

		get("/convert") { request: CurrencyConvertRequest =>
			val rates = Await.result(exchangeRateService.getRates(request.from))
			val exchangeRate = if (request.from == request.to) BigDecimal.valueOf(1L) else rates.rates(request.to.toUpperCase())
			val conversionResult = request.amount * exchangeRate

			CurrencyConversionResult(
				fromCurrency = request.from,
				toCurrency = request.to,
				amountToConvert = request.amount,
				conversionResult = conversionResult,
				exchangeRate = exchangeRate
			)
		}
	}
}
