package com.anthonynsimon.cconverter.api.services.impl

import com.anthonynsimon.cconverter.api.domain.{CurrencyCode, ExchangeRates}
import com.anthonynsimon.cconverter.api.services.CacheService
import org.joda.time.{LocalDate, Period, ReadablePeriod}

class InMemoryCacheService extends CacheService[CurrencyCode, ExchangeRates] {

	// TODO: parameterize
	private val cacheExpiration: ReadablePeriod = new Period().withDays(1)

	override def hasExpired(rates: ExchangeRates): Boolean = rates.date.plus(cacheExpiration).isBefore(LocalDate.now())
}
