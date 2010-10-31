package com.youdevise.test.narrative

import Types._

class DataHoldingActor[T](protected val data: T) extends Actor[T] {
  def perform(action: Action[T]) = action(data, new HashMapStash)

  def grabUsing[DATA](extractor: Extractor[DATA, T]): DATA = extractor(data, new HashMapStash)
}