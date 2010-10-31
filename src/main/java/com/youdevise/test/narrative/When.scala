package com.youdevise.test.narrative

class When[T](val actor: Actor[T]) {

  def and[S](actor: Actor[S]): When[S] = When(actor)

  def attempts_to(action: Action[T]): When[T] = {
    actor.perform(action)
    this
  }
}

object When {
  def apply[T](actor: Actor[T]):When[T] = new When(actor)
  def the[T] = apply[T] _
}