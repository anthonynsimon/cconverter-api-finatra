package com.anthonynsimon.cconverter.api.exceptions

abstract class APIException(message: String) extends Exception(message)
case class InvalidCurrencyCodeException(message: String) extends APIException(message)
