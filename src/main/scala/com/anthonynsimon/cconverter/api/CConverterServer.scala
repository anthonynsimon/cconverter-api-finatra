package com.anthonynsimon.cconverter.api

import com.anthonynsimon.cconverter.api.controllers.ExchangeRateController
import com.anthonynsimon.cconverter.api.exceptions.APIExceptionMapper
import com.anthonynsimon.cconverter.api.modules.{CustomJacksonModule, ServicesModule}
import com.google.inject.Module
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.{AccessLoggingFilter, ExceptionMappingFilter, LoggingMDCFilter, TraceIdMDCFilter}
import com.twitter.finatra.http.routing.HttpRouter

object CConverterServerMain extends CConverterServer

class CConverterServer extends HttpServer {

	override def jacksonModule: Module = CustomJacksonModule

	override def modules = Seq(ServicesModule)

	override def configureHttp(router: HttpRouter) {
		router
				.filter[AccessLoggingFilter[Request]]
				.filter[LoggingMDCFilter[Request, Response]]
				.filter[TraceIdMDCFilter[Request, Response]]
				.filter[ExceptionMappingFilter[Request]]
				.exceptionMapper[APIExceptionMapper]
				.add[ExchangeRateController]
	}
}
