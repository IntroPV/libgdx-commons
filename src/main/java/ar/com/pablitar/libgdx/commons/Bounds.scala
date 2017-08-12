package ar.com.pablitar.libgdx.commons

import com.badlogic.gdx.math.Shape2D
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Rectangle
import ar.com.pablitar.libgdx.commons.extensions.ShapeExtensions._
import ar.com.pablitar.libgdx.commons.rendering.Renderable
import ar.com.pablitar.libgdx.commons.rendering.Renderers
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.ashley.core.Entity

class Bounds(val shape: Rectangle) extends Renderable {
  def adjustToKeepIn(v: Vector2) = {
    shape.keepInside(v)
  }

  def reduce(width: Float, height: Float) = {
    Bounds.rectangular(shape.x + width / 2, shape.y + height / 2, shape.width - width, shape.height - height)
  }
  
  def expand(width: Float, height: Float) = {
    reduce(-width, -height)
  }

  def renderOn(renderers: Renderers): Unit = {
    renderers.withShapes(ShapeType.Line)(sr => sr.rect(this.shape.x, this.shape.y, this.shape.width, this.shape.height))
  }

  def z: Int = {
    0
  }
  
  def contains(point: Vector2) = {
    shape.contains(point)
  }
  
  def outsideDirectionOption(point: Vector2):Option[Float] = {
    shape.outsideDirectionOption(point)
  }

  def intersects(bounds: Bounds) = {
    bounds.shape.overlaps(this.shape)
  }
}

object Bounds {
  def rectangular(x: Float, y: Float, width: Float, height: Float) = {
    new Bounds(new Rectangle(x, y, width, height))
  }
}