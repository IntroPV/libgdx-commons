package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.gdx.utils.async.{AsyncExecutor, AsyncResult, AsyncTask}

/**
  * Created by pablitar on 18/06/17.
  */
object AsyncExecutorExtensions {
  implicit class AsyncExecutorOps(executor: AsyncExecutor) {
    def async[T](body: => T): AsyncResult[T] = {
      executor.submit(new AsyncTask[T] {
        override def call(): T = body
      })
    }
  }

  def async[T](body: => T)(implicit executor: AsyncExecutor): AsyncResult[T] = {
    executor.async(body)
  }
}
