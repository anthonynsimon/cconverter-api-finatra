package com.anthonynsimon.cconverter.api.domain

import com.twitter.finatra.request.QueryParam

case class CurrencyConvertRequest(@QueryParam from: String,
								  @QueryParam to: String,
								  @QueryParam amount: BigDecimal)
