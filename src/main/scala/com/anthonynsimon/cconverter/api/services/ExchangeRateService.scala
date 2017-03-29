package com.anthonynsimon.cconverter.api.services

import com.anthonynsimon.cconverter.api.domain.{CurrencyCode, ExchangeRates}
import com.twitter.util.Future

trait ExchangeRateService {

	def getRates(baseCurrency: CurrencyCode): Future[ExchangeRates]
}