package ar.com.pablitar.libgdx.commons.time

import com.badlogic.gdx.math.Interpolation

class TimeDelay(val duration: Float, val rate: Float = 0.5f, interpolator: Interpolation = Interpolation.pow3, val stopTimeDuration: Float = 0f) {
  var elapsed: Float = duration
  require(duration >= stopTimeDuration, "The total duration should be bigger than the duration of stop-time")

  def update(delta: Float) = {
    elapsed = (elapsed + delta).min(duration)
  }

  def currentRate = {
    if (elapsed < stopTimeDuration) 0 else
      interpolator.apply(1 - rate, 1, elapsed / (duration - stopTimeDuration))
  }

  def startDelaying = {
    elapsed = 0f
  }

  def apply(delta: Float) = {
    delta * currentRate
  }
}