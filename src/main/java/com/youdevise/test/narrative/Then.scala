package com.youdevise.test.narrative

import org.junit.Assert, Assert._
import Types._
import org.hamcrest.{CoreMatchers, Matcher}

/**
 * A assertion of the final state of the system for a narrative
 * @param < T > The type of tool that the Actor uses
 */

class Then[T](val actor: Actor[T]) {
  def expects_that[D](expected: Extractor[D, T]): TypedMatcher[D, T] = {
    return new TypedMatcher[D, T](expected, this)
  }
}
object Then {
  def apply[T](actor: Actor[T]): Then[T] = new Then(actor)
  def the[T] = apply[T] _
}

class TypedMatcher[D, T](val expected: Extractor[D, T], val outer: Then[T]) {
  def should(matcher: Matcher[_ >: D]): TypedMatcher[D, T] = {
    assertThat(outer.actor.grabUsing(expected), matcher)
    this
  }

  def should_be(matcher: Matcher[_ >: D]): TypedMatcher[D, T] = should(matcher)

  def should_be(expected : D): TypedMatcher[D, T] = should(CoreMatchers.is(expected))

  def should_have(matcher: Matcher[_ >: D]): TypedMatcher[D, T] = should(matcher)

  def andAlso: Then[T] = outer

  def is(matcher: Matcher[_ >: D]): TypedMatcher[D, T] = should(matcher)

  def is(ob: D): TypedMatcher[D, T] = should(CoreMatchers.is(ob))
}

