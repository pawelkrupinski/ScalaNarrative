package com.youdevise.test.narrative

import Types._

class Given[T](val actor: Actor[T]) {
    def and[S](actor: Actor[S]): Given[S] = {
      return new Given(actor)
    }

    def was_able_to(action: Action[T]): Given[T] = {
      actor.perform(action)
      return this
    }
}

object Given {
  def apply[T](actor: Actor[T]): Given[T] = new Given(actor)
  def the[T] = apply[T] _
}