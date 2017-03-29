package com.anthonynsimon.cconverter.api.modules

import com.anthonynsimon.cconverter.api.domain.{CurrencyCode, ExchangeRates}
import com.anthonynsimon.cconverter.api.services.impl.{FixerExchangeRateService, InMemoryCacheService}
import com.anthonynsimon.cconverter.api.services.{CacheService, ExchangeRateService}
import com.twitter.inject.TwitterModule

object ServicesModule extends TwitterModule {

	override def configure(): Unit = {
		bind[ExchangeRateService].to[FixerExchangeRateService]
		bind[CacheService[CurrencyCode, ExchangeRates]].to[InMemoryCacheService]
	}
}
