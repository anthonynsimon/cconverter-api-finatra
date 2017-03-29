package com.anthonynsimon.cconverter.api.domain

import com.twitter.finatra.json.internal.caseclass.validation.validators.MinInternal
import com.twitter.finatra.request.QueryParam

case class CurrencyConvertRequest(@QueryParam from: CurrencyCode,
								  @QueryParam to: CurrencyCode,
								  @QueryParam @MinInternal(0L) amount: BigDecimal)
