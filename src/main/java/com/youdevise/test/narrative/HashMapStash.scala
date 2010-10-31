package com.youdevise.test.narrative

import java.util.HashMap

class HashMapStash extends Stash {
  def get[T](key: AnyRef, clas: Class[T]): T = {
    if (contains(key) && !clas.isInstance(store.get(key))) {
      throw new ClassCastException
    }
    return store.get(key).asInstanceOf[T]
  }
  private val store: java.util.Map[AnyRef, AnyRef] = new HashMap[AnyRef, AnyRef]

  override def contains(key: AnyRef): Boolean = {
    return store.containsKey(key)
  }

  override def put(key: AnyRef, value: AnyRef): Unit = {
    store.put(key, value)
  }
}

