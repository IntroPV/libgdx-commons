package ar.com.pablitar.libgdx.commons.components

import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._

object Bindings {
  def transform(positionOffset: Vector2 = new Vector2()) = 
    Binding(classOf[TransformComponent], (t: TransformComponent) => t.copy(position = t.position + positionOffset), Some(transformMapper))

  def state() = {
    Binding(classOf[StateComponent], (t: StateComponent) => t.copy(), Some(stateMapper))
  }
}