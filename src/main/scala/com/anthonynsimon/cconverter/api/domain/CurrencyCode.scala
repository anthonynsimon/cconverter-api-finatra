package com.anthonynsimon.cconverter.api.domain

import com.twitter.inject.domain.WrappedValue

case class CurrencyCode(code: String) extends WrappedValue[String]
