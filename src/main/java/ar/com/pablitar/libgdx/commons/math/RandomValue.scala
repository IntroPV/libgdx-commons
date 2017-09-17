package ar.com.pablitar.libgdx.commons.math

import com.badlogic.gdx.math.MathUtils
import ar.com.pablitar.libgdx.commons.CommonMathUtils
import com.badlogic.gdx.math.Vector2

abstract class RandomValue[T] {
  var currentValue: T = calculate

  def calculate: T

  def generate = {
    currentValue = calculate
    currentValue
  }
}

class RandomFloatInRange(val min: Float, val max: Float) extends RandomValue[Float] {
  def calculate = MathUtils.random(min, max)
}

class RandomVectorInRange(val min: Float, val max: Float) extends RandomValue[Vector2] {
  def calculate = CommonMathUtils.randomVectorInRange(min, max)
  
  def x = currentValue.x
  def y = currentValue.y
}