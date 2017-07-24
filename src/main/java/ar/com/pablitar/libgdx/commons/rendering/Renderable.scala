package ar.com.pablitar.libgdx.commons.rendering

import com.badlogic.ashley.core.Entity

trait Renderable {
  def renderOn(renderers: Renderers, entity: Entity)
  def z: Int
}