package com.anthonynsimon.cconverter.api.domain

import com.anthonynsimon.cconverter.api.exceptions.InvalidCurrencyCodeException
import com.twitter.inject.domain.WrappedValue

@throws[InvalidCurrencyCodeException]
case class CurrencyCode(private var code: String) extends WrappedValue[String] {

	if (code == null || code.isEmpty || code.length != 3) {
		throw new InvalidCurrencyCodeException("currency code length must be 3")
	}

	code = if (code != null) code.toUpperCase() else null
	//
	//	@MethodValidation
	//	def validateCode = {
	//		ValidationResult.validate(code != null && !code.isEmpty && code.length == 3, "currency code length must be 3")
	//	}
}
