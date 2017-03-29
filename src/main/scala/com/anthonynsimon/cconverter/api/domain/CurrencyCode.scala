package com.anthonynsimon.cconverter.api.domain

import com.anthonynsimon.cconverter.api.exceptions.InvalidCurrencyCodeException
import com.twitter.inject.domain.WrappedValue

case class CurrencyCode(private var code: String) extends WrappedValue[String] {
	if (code == null || code.isEmpty) {
		throw new InvalidCurrencyCodeException("Currency code cannot be null or empty")
	}
	if (code.length != 3) {
		throw new InvalidCurrencyCodeException("Currency code length must be 3")
	}
	code = code.toUpperCase()
}
