package com.anthonynsimon.cconverter.api.services

import com.anthonynsimon.cconverter.api.domain.ExchangeRates
import com.twitter.util.Future

trait ExchangeRateService {

	def getRates(baseCurrency: String): Future[ExchangeRates]
}