package ar.com.pablitar.libgdx.commons.time

import com.badlogic.gdx.math.Interpolation

class TimeDelay(var duration: Float, var rate: Float = 0.5f, interpolator: Interpolation = Interpolation.pow3) {
  var elapsed: Float = duration

  def update(delta: Float) = {
    elapsed = (elapsed + delta).min(duration) 
  }
  
  def currentRate = interpolator.apply(1 - rate, 1, elapsed / duration)
  
  def startDelaying = {
    elapsed = 0f
  }
  
  def apply(delta: Float) = {
    println(currentRate)
    delta * currentRate
  }
}