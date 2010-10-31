package com.youdevise.test.narrative

import org.junit.runner.RunWith
import org.jmock.{Mockery, Expectations}
import org.jmock.Expectations._
import org.jmock.integration.junit4.JMock
import org.junit.Test
import Types._

@RunWith(classOf[JMock])
class ThenTest {
  private val context: Mockery = new Mockery

  @Test
  def grabsTheDataToCheckUsingTheActor {
    val actor = context.mock(classOf[Actor[String]])
    val extractor = context.mock(classOf[Extractor[Character, String]])
    context.checking(new Expectations {
      oneOf(actor).grabUsing(extractor)
      will(returnValue('a'))
    })
    Then(actor).expects_that(extractor).should_be('a')
  }

  @Test(expected = classOf[AssertionError])
  def failsIfTheGrabbedDataDoesNotMatch {
    val actor = context.mock(classOf[Actor[String]])
    val extractor = context.mock(classOf[Extractor[Character, String]])
    context.checking(new Expectations {
      oneOf(actor).grabUsing(extractor)
      will(returnValue('a'))
    })
    Then(actor).expects_that(extractor).should_be('b')
  }

  @Test
  def canAssertMoreThanOnePieceOfData {
    val actor = context.mock(classOf[Actor[String]])
    val extractedCharacter = context.mock(classOf[Extractor[Character, String]], "character extractor")
    val extractedString = context.mock(classOf[Extractor[String, String]], "string extractor")
    context.checking(new Expectations {
      oneOf(actor).grabUsing(extractedCharacter)
      will(returnValue('a'))

      oneOf(actor).grabUsing(extractedString)
      will(returnValue("string"))
    })
    Then(actor).expects_that(extractedCharacter).should_be('a').andAlso.expects_that(extractedString).should_be("string")
  }

  @Test(expected = classOf[AssertionError])
  def canPassOnAnEarlierAssertionAndFailOnALaterOne {
    val actor = context.mock(classOf[Actor[String]])
    val extractedCharacter = context.mock(classOf[Extractor[Character, String]], "character extractor")
    val extractedString = context.mock(classOf[Extractor[String, String]], "string extractor")
    context.checking(new Expectations {
      oneOf(actor).grabUsing(extractedCharacter)
      will(returnValue('a'))

      oneOf(actor).grabUsing(extractedString)
      will(returnValue("not the string"))
    })
    Then(actor).expects_that(extractedCharacter).should_be('a').andAlso.expects_that(extractedString).should_be("string")
  }
}

