package ar.com.pablitar.libgdx.commons

import ar.com.pablitar.libgdx.commons.traits.Dragable
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2

import ar.com.pablitar.libgdx.commons.extensions.InputExtensions._

trait DragableController {
  def dragables : Seq[Dragable]

  def cursorPosition: Vector2 = Gdx.input.cursorScreenPosition
  def justTouched: Boolean = Gdx.input.justTouched()
  def isTouched: Boolean = Gdx.input.isTouched

  var status: DragStatus = NotDragging

  def update(delta: Float) = {
    status.update(delta, this)
  }

  def startDragging() : Unit = {
    dragables.find(_.shape.contains(cursorPosition)).foreach(it => this.status = Dragging(it))
  }
}

trait DragStatus {
  def update(delta: Float, controller: DragableController): Unit
}

object NotDragging extends DragStatus {
  override def update(delta: Float, controller: DragableController): Unit = {
    if(controller.justTouched) {
      controller.startDragging()
    }
  }
}

case class Dragging(dragable: Dragable) extends DragStatus {
  dragable.dragging = true
  override def update(delta: Float, controller: DragableController): Unit = {
    if(controller.isTouched) {
      dragable.position = controller.cursorPosition
    } else {
      dragable.dragging = false
      controller.status = NotDragging
    }
  }
}


