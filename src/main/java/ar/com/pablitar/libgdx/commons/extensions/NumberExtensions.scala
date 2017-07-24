package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2

object NumberExtensions {
  implicit class FloatOps(float: Float) {
    def squared = float * float
    def bounded(lowerBound: Float, upperBound: Float) = {
      float.min(upperBound).max(lowerBound)
    }

    def asVersor() = {
      new Vector2(MathUtils.cos(float), MathUtils.sin(float))
    }

    def =~=(aFloat: Float, tolerance: Float = 0.000001f) = {
      (float - aFloat).abs < tolerance
    }

    def !~=(aFloat: Float, tolerance: Float = 0.000001f) = {
      (float - aFloat).abs > tolerance
    }
  }
  
  implicit class DoubleOps(float: Double) {
    def squared = float * float
    def bounded(lowerBound: Double, upperBound: Double) = {
      float.min(upperBound).max(lowerBound)
    }
  }
  
  implicit class IntOps(float: Int) {
    def squared = float * float
    def bounded(lowerBound: Int, upperBound: Int) = {
      float.min(upperBound).max(lowerBound)
    }
  }
}