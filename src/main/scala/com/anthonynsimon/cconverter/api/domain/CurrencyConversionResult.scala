package com.anthonynsimon.cconverter.api.domain

case class CurrencyConversionResult(fromCurrency: CurrencyCode,
									toCurrency: CurrencyCode,
									amountToConvert: BigDecimal,
									conversionResult: BigDecimal,
									exchangeRate: BigDecimal)
