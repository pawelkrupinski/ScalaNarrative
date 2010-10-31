package com.youdevise.test.narrative

import org.hamcrest.Matchers
import org.junit.{Assert, Test}
import org.junit.Assert._

class HashMapStashTest {
  private val stash: Stash = new HashMapStash

  @Test(expected = classOf[ClassCastException])
  def throwsIfTheRequestedObjectIsNotOfTheRequestedType: Unit = {
    stash.put("key", new AnyRef)
    stash.get("key", classOf[String])
  }

  @Test
  def doesNotContainAnyElementsByDefault: Unit = {
    assertFalse(stash.contains(new AnyRef))
  }

  @Test
  def getsTheStashedValue: Unit = {
    stash.put("key", "value")
    assertThat(stash.get("key", classOf[String]), Matchers.is("value"))
  }

  @Test
  def getsNullIfItDoesNotContainTheKey: Unit = {
    assertNull(stash.get("key", classOf[AnyRef]))
  }

  @Test
  def containsAnElementAfterItHasBeenAdded: Unit = {
    stash.put("key", "value")
    assertTrue(stash.contains("key"))
  }
}