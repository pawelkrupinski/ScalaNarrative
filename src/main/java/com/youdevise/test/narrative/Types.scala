package com.youdevise.test.narrative

object Types {
  type Extractor[T, F] = (F, Stash) => T
  type Action[T] = (T, Stash) => Unit
}