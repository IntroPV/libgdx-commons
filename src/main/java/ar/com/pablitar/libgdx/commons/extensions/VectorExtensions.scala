package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.gdx.math.{ MathUtils, Vector2 }
import ar.com.pablitar.libgdx.commons.CatMathUtils
import NumberExtensions._

/**
 * Created by pablitar on 20/12/16.
 */
class VectorExtensions {
  implicit class VectorOps(vector: Vector2) {
    def randAround(radius: Float, minRadius: Float = 0f) = {
      val radiusResult = MathUtils.random(minRadius, radius)
      val direction = MathUtils.random(MathUtils.PI2)

      new Vector2(MathUtils.cos(direction), MathUtils.sin(direction)).scl(radiusResult).add(vector)
    }

    def randInCenteredRect(xRange: Float, yRange: Float) = {
      new Vector2(CatMathUtils.randAround(vector.x, xRange / 2),
        CatMathUtils.randAround(vector.y, yRange / 2))
    }

    def isAlmost(otherVector: Vector2, tolerance: Float = 0.01f) = {
      vector.dst2(otherVector) < tolerance
    }

    def -(anotherVector: Vector2) = {
      vector.cpy().sub(anotherVector)
    }

    def unary_- = vector.cpy().set(-vector.x, -vector.y)

    def +(anotherVector: Vector2) = {
      vector.cpy().add(anotherVector)
    }

    def *(scalar: Float) = {
      vector.cpy().scl(scalar)
    }

    def *(scalar: Int) = {
      vector.cpy().scl(scalar)
    }

    def /(scalar: Float) = {
      vector.cpy().scl(1f / scalar)
    }

    def /(scalar: Int) = {
      vector.cpy().scl(1f / scalar)
    }

    def abs() = {
      new Vector2(vector.x.abs, vector.y.abs)
    }

    def copySign(v: Vector2) = {
      val newVector = vector.cpy()
      if(v.x !~= 0f)
        newVector.x = Math.copySign(newVector.x, v.x)
      if(v.y !~= 0f)
        newVector.y = Math.copySign(newVector.y, v.y)
      newVector
    }

  }

  implicit def fromTupleToVector2[T <% Float](t: (T, T)): Vector2 = new Vector2(t._1, t._2)
}

object VectorExtensions extends VectorExtensions
