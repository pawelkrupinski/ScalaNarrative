package com.youdevise.narrative.example

import org.junit.Test
import com.youdevise.test.narrative.DataHoldingActor
import com.youdevise.test.narrative._
import org.hamcrest.CoreMatchers._
import Types._

/*
    Tests for a very basic console-based calculator, to illustrate usage of the Narrative library.
*/
class BasicArithmeticTest {
  /*
      You need to define an Actor to get started. The Actor performs various actions on the domain
      objects - in this case we have just one object, the calculator.
  */
  private val operator: Actor[Calculator] = new CalculatorActor();

  def press(keypress: Char*) = (data: Calculator, stash: Stash) => {
    keypress.foreach(data.press(_))
  }

  /*
      The tests themselves, in given-when-then style. Narrative provides everything used here
      except the press() and value_displayed() methods defined below, and the Hamcrest matcher
      is().
  */
  @Test
  def adds_two_numbers() {
    Given the operator was_able_to press('2', '+', '2')

    When the operator attempts_to press('=')

    Then the operator expects_that value_displayed is "4"
  }

  @Test
  def subtracts_two_numbers() {
    Given the operator was_able_to press('3', '-', '1')

    When the operator attempts_to press('=')

    Then the operator expects_that value_displayed is "2"
  }

  private def value_displayed: Extractor[String, Calculator] = (calculator, stash) => calculator.read

  /*
      The Actor that does all the work as commanded by the tests.
  */
  private class CalculatorActor extends DataHoldingActor[Calculator](new Calculator);
}
