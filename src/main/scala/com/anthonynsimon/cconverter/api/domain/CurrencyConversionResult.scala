package com.anthonynsimon.cconverter.api.domain

case class CurrencyConversionResult(fromCurrency: String,
									toCurrency: String,
									amountToConvert: BigDecimal,
									conversionResult: BigDecimal,
									exchangeRate: BigDecimal)
