package com.youdevise.test.narrative

import org.jmock.{Expectations, Mockery}
import org.junit.Test
import org.jmock.integration.junit4.JMock
import org.junit.runner.RunWith

@RunWith(classOf[JMock])
class GivenTest {

  val context = new Mockery

  @Test
  def usesTheActorToPerformTheAction: Unit = {
    val actor = context.mock(classOf[Actor[Any]])
    val action = context.mock(classOf[(Any, Stash) => Unit])
    context.checking(new Expectations {
      oneOf(actor).perform(action)
    })
    Given(actor).was_able_to(action)
  }

  @Test
  def canPerformanManyActionsInARow: Unit = {
    val actor = context.mock(classOf[Actor[Any]])
    val action = context.mock(classOf[(Any, Stash) => Unit], "first action")
    val otherAction= context.mock(classOf[(Any, Stash) => Unit], "second action")
    val orderOfActions = context.sequence("Order of the actions")
    context.checking(new Expectations {
      oneOf(actor).perform(action)
      inSequence(orderOfActions)

      oneOf(actor).perform(otherAction)
      inSequence(orderOfActions)
    })
    Given(actor).was_able_to(action).was_able_to(otherAction)
  }
}

