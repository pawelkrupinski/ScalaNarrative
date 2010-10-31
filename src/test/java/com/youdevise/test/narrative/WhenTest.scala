package com.youdevise.test.narrative

import org.junit.runner.RunWith
import org.jmock.integration.junit4.JMock
import org.jmock.{Expectations, Mockery}
import org.junit.Test

@RunWith(classOf[JMock])
class WhenTest {

  val context = new Mockery

  @Test
  def usesTheActorToPerformTheAction {
    val actor: Actor[Any] = context.mock(classOf[Actor[Any]])
    val action: (Any, Stash) => Unit = context.mock(classOf[Function2[Any, Stash, Unit]])
    context.checking(new Expectations {
      oneOf(actor).perform(action)
    })
    When(actor).attempts_to(action)
  }

  @Test
  def canPerformanManyActionsInARow {
    val actor: Actor[Any] = context.mock(classOf[Actor[Any]])
    val action: (Any, Stash) => Unit = context.mock(classOf[Function2[Any, Stash, Unit]])
    val otherAction: (Any, Stash) => Unit = context.mock(classOf[Function2[Any, Stash, Unit]], "second action")
    val orderOfActions = context.sequence("Order of the actions")
    context.checking(new Expectations {
      oneOf(actor).perform(action)
      oneOf(actor).perform(otherAction)
    })
    When(actor).attempts_to(action).attempts_to(otherAction)
  }
}