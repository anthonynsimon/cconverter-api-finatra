package com.anthonynsimon.cconverter.api.domain

import org.joda.time.LocalDate

case class ExchangeRates(base: CurrencyCode,
						 date: LocalDate,
						 rates: Map[CurrencyCode, BigDecimal])