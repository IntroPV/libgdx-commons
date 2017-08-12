package ar.com.pablitar.libgdx.commons.traits

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.MathUtils
import ar.com.pablitar.libgdx.commons.AngleDirection

trait Directionable {
  var direction: Float = 0
  def directionVersor = new Vector2(MathUtils.cos(direction), MathUtils.sin(direction))

  def turn(magnitude: Float, aDirection: AngleDirection = AngleDirection.CounterClockwise, delta: Float = 1): Unit = {
    turn(magnitude, aDirection.sign, delta)
  }

  def turn(magnitude: Float, aDirection: Float, delta: Float): Unit = {
    direction += magnitude * aDirection * delta
  }
}