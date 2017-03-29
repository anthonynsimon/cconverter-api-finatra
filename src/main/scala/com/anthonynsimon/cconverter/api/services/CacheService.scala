package com.anthonynsimon.cconverter.api.services

trait CacheService[K, V] {

	private var store = Map[K, V]()

	def hasExpired(value: V): Boolean

	def put(key: K, value: V): Unit = {
		store = store + (key -> value)
	}

	def get(key: K): Option[V] = {
		store.get(key) match {
			case Some(rates) => if (!hasExpired(rates)) Some(rates) else None
			case _ => None
		}
	}
}
