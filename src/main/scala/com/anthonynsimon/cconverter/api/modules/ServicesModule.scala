package com.anthonynsimon.cconverter.api.modules

import com.anthonynsimon.cconverter.api.services.ExchangeRateService
import com.anthonynsimon.cconverter.api.services.impl.FixerExchangeRateService
import com.twitter.inject.TwitterModule

object ServicesModule extends TwitterModule {

	override def configure(): Unit = {
		bind[ExchangeRateService].to[FixerExchangeRateService]
	}
}
