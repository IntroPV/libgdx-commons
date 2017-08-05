package ar.com.pablitar.libgdx.commons

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Polygon

/**
 * Created by pablitar on 20/12/16.
 */
object CommonMathUtils {
  def randAround(value: Float, delta: Float) = MathUtils.random(value - delta, value + delta)
  def normalizeAngle(angle: Float) = {
    val modded = angle % MathUtils.PI2
    if (modded >= 0) modded else modded + MathUtils.PI2
  }
  def minNormalizedDistanceRad(from: Float, to: Float) =
    Math.min(angularDistanceRad(from, to, AngleDirection.Clockwise), angularDistanceRad(from, to, AngleDirection.CounterClockwise))

  def angularDistanceRad(from: Float, to: Float, direction: AngleDirection) = {
    val normFrom = normalizeAngle(from)
    val normTo = normalizeAngle(to)

    normalizeAngle((normTo - normFrom) * direction.sign)
  }

  def closestTurnDirection(from: Float, to: Float) = {
    if (angularDistanceRad(from, to, AngleDirection.Clockwise) <= angularDistanceRad(from, to, AngleDirection.CounterClockwise))
      AngleDirection.Clockwise
    else
      AngleDirection.CounterClockwise
  }

  def inFront(aPoint: Vector2, otherPoint: Vector2, forwardDirection: Float) = {
    val pointDirection = aPoint - otherPoint
    minNormalizedDistanceRad(pointDirection.angleRad(), forwardDirection) < (MathUtils.PI / 2)
  }

  def vectorFromPolar(angle: Float, len: Float) = new Vector2(MathUtils.cos(angle) * len, MathUtils.sin(angle) * len)

  def randomInSeq[T](seq: Seq[T]) = seq(MathUtils.random(seq.size - 1))

  def rectangleToPolygon(r: Rectangle) = {
    new Polygon(Array(r.x, r.y,
        r.x + r.width, r.y,
        r.x + r.width, r.y + r.height,
        r.x, r.y + r.height))
  }
}

abstract class AngleDirection(val sign: Float)
object AngleDirection {
  case object Clockwise extends AngleDirection(-1)
  case object CounterClockwise extends AngleDirection(1)
}

