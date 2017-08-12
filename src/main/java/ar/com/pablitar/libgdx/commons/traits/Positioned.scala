package ar.com.pablitar.libgdx.commons.traits

import com.badlogic.gdx.math.{MathUtils, Vector2}
import ar.com.pablitar.libgdx.commons.AngleDirection
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Circle

/**
  * Created by pablitar on 22/12/16.
  */
trait Positioned {
  protected var _position: Vector2 = (0,0)
  
  def position = _position
  def position_=(aPosition: Vector2) = _position = aPosition
  
  def x = position.x
  def y = position.y
  
  def dst(p:Positioned) = position.dst(p.position)
  def dst2(p:Positioned) = position.dst2(p.position)
}

class SimplePositioned(aPosition:Vector2 = (0,0)) extends Positioned {
  position = aPosition
}

trait RectangularPositioned extends Positioned {
  def width: Float
  def height: Float
  
  def topLeft: Vector2 = position + center - (width/2, height / 2)
  def bottomRight: Vector2 = position + center + (width/2, height / 2) 
  
  def center: Vector2 = (0, 0)
  
  def rectangle = new Rectangle(topLeft.x, topLeft.y, width, height)
}

trait CircularPositioned extends Positioned {
  def radius: Float
  
  def circle = new Circle(position, radius)
}
