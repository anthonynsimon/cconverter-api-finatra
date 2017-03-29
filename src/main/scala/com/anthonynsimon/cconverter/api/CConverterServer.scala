package com.anthonynsimon.cconverter.api

import com.anthonynsimon.cconverter.api.controllers.ExchangeRateController
import com.anthonynsimon.cconverter.api.modules.{CustomJacksonModule, ServicesModule}
import com.google.inject.Module
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter

object CConverterServerMain extends CConverterServer

class CConverterServer extends HttpServer {

	override def jacksonModule: Module = CustomJacksonModule

	override def modules = Seq(ServicesModule)

	override def configureHttp(router: HttpRouter) {
		router
				.filter[CommonFilters]
				.add[ExchangeRateController]
	}
}
