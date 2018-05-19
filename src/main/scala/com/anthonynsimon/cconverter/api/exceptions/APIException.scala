package com.anthonynsimon.cconverter.api.exceptions

sealed trait APIException extends Exception {
  val message: String
}

case class InvalidCurrencyCodeException(message: String) extends APIException
