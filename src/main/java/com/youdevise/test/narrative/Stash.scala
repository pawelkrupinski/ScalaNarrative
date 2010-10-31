package com.youdevise.test.narrative

trait Stash {
  def contains(key: AnyRef): Boolean
  def put(key: AnyRef, value: AnyRef)
  def get[T](key: AnyRef, clas: Class[T]): T
}