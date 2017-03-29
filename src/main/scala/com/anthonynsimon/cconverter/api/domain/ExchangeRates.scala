package com.anthonynsimon.cconverter.api.domain

import org.joda.time.LocalDate

case class ExchangeRates(base: String,
						 date: LocalDate,
						 rates: Map[String, BigDecimal])