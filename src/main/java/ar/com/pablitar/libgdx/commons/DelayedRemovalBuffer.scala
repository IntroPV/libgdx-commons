package ar.com.pablitar.libgdx.commons

import scala.collection.mutable.ArrayBuffer

/**
 * Created by pablitar on 12/02/17.
 */
class DelayedRemovalBuffer[T](ts:T*) {
  
  def foreachWithRemoveSupport[U](f: T => U): Unit = {
    foreach(f)
    commitRemoval()
  }

  def foreach[U](f: T => U): Unit = {
    elements.foreach(f)
  }
  
  val elements = ArrayBuffer(ts:_*)
  val toRemove = ArrayBuffer.empty[T]

  def commitRemoval() = {
    elements --= toRemove
    toRemove.clear()
  }

  def removeDelayed(e: T) = {
    toRemove += e
  }

  def add(e: T) = {
    elements += e
  }

  def size() = elements.size

  def take(n: Int) = {
    elements.take(n)
  }
}
