package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener

/**
  * Created by pablitar on 05/03/17.
  */
object ListenerExtensions {
  object ClickConversions {
    implicit def functionToClickListener(function: (InputEvent, Float, Float) => Unit) = new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = function(event, x, y)
    }

    implicit def functionToClickListener(function: (Float, Float) => Unit) = new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = function(x, y)
    }

    implicit def functionToClickListener(body: () => Unit) = new ClickListener() {
      override def clicked(event: InputEvent, x: Float, y: Float): Unit = body()
    }

//    implicit def functionToClickListener(body: => Unit) = new ClickListener() {
//      override def clicked(event: InputEvent, x: Float, y: Float): Unit = body
//    }
  }
}
