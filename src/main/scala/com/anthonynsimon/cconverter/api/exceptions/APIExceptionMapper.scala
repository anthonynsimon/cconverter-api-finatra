package com.anthonynsimon.cconverter.api.exceptions

import com.anthonynsimon.cconverter.api.domain.APIError
import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.exceptions.ExceptionMapper
import com.twitter.finatra.http.response.ResponseBuilder

@Singleton
class APIExceptionMapper @Inject()(response: ResponseBuilder) extends ExceptionMapper[APIException] {

	override def toResponse(request: Request, thrown: APIException): Response = {
		response.badRequest().json(APIError(Seq(thrown.getMessage)))
	}
}
