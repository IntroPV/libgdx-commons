package ar.com.pablitar.libgdx.commons.components

object Bindings {
  def transform = Binding(classOf[TransformComponent], (t: TransformComponent) => t.copy(), Some(transformMapper))
}