package ar.com.pablitar.libgdx.commons.math

import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Intersector

case class Segment2(from: Vector2, to: Vector2) {
  def displaced(displacement: Vector2) = Segment2(from + displacement, to + displacement)
  def drawOn(shapeRenderer: ShapeRenderer) = {
    shapeRenderer.line(from, to)
    shapeRenderer.line(middle, middle + normal * (length / 5))
  }
  
  def normalUsing(p: Vector2, scale: Float = 1) = {
    Intersector.nearestSegmentPoint(from, to, p, new Vector2) + normal * scale 
  }
  
  def length = (to - from).len()
  
  def middle = new Vector2((from.x + to.x) /2, (from.y + to.y)/2) 
  
  def normal = new Vector2(- (to.y - from.y), to.x - from.x).nor()
}