package ar.com.pablitar.libgdx.commons.camera

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.CommonMathUtils
import com.badlogic.gdx.math.MathUtils
import ar.com.pablitar.libgdx.commons.math.RandomVectorInRange
import ar.com.pablitar.libgdx.commons.math.RandomFloatInRange

class Shaker(
    val shakeStrength: RandomVectorInRange,
    val shakeDuration: RandomFloatInRange,
    val shakeFrequency: RandomVectorInRange) {

  var remaining: Float = 0
  val shakePhase = new RandomVectorInRange(0, MathUtils.PI2)

  def update(delta: Float) = {
    remaining = (remaining - delta).max(0)
  }
  
  def generateShake() = {
    shakeStrength.generate
    shakeDuration.generate
    shakeFrequency.generate
    shakePhase.generate
    
    remaining = shakeDuration.currentValue
  }

  def elapsed = shakeDuration.currentValue - remaining

  def shakeValue(strength: Float, phase: Float, frequency: Float): Float = {
    if (remaining > 0)
      strength * MathUtils.sin(elapsed * MathUtils.PI2 * frequency + phase) * (remaining / shakeDuration.currentValue)
    else 0
  }

  def shakeOffset = {
    new Vector2(
      shakeValue(shakeStrength.x, shakePhase.x, shakeFrequency.x), shakeValue(shakeStrength.y, shakePhase.y, shakeFrequency.y))
  }
}