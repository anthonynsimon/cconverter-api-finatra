package com.anthonynsimon.cconverter.api.controllers

import com.anthonynsimon.cconverter.api.domain.{CurrencyCode, CurrencyConversionResult, CurrencyConvertRequest}
import com.anthonynsimon.cconverter.api.exceptions.InvalidCurrencyCodeException
import com.anthonynsimon.cconverter.api.services.ExchangeRateService
import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class ExchangeRateController @Inject()(exchangeRateService: ExchangeRateService) extends Controller {

  prefix("/api") {
    get("/rates/:currencyCode") { request: Request =>
      val currency = CurrencyCode(request.params("currencyCode"))
      exchangeRateService.getRates(currency)
    }

    get("/convert") { request: CurrencyConvertRequest =>
      exchangeRateService
        .getRates(request.from)
        .map { rates =>
          val exchangeRate = {
            if (request.from == request.to) BigDecimal.valueOf(1L)
            else rates.rates.getOrElse(request.to, throw InvalidCurrencyCodeException(s"unknown currency code: ${request.to}"))
          }

          val conversionResult = request.amount * exchangeRate

          CurrencyConversionResult(
            fromCurrency = request.from,
            toCurrency = request.to,
            amountToConvert = request.amount,
            conversionResult = conversionResult,
            exchangeRate = exchangeRate)
        }
    }
  }
}
