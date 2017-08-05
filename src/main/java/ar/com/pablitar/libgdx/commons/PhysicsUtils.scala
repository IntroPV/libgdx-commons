package ar.com.pablitar.libgdx.commons

import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._

/**
 * @author pablitar
 */
object PhysicsUtils {
  def rebound(v: Vector2, n: Vector2,
      dampening: Float = 0.0f) = 
        (v.proyectTo(n) * (-2 + dampening)) + v
}