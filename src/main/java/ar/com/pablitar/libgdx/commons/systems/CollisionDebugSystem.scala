package ar.com.pablitar.libgdx.commons.systems

import com.badlogic.ashley.systems.IteratingSystem
import ar.com.pablitar.libgdx.commons.family.Families
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.libgdx.commons.components.Extensions._
import com.badlogic.ashley.core.EntitySystem
import scala.compat.java8.FunctionConverters._
import ar.com.pablitar.libgdx.commons.collisions.CollisionEvent
import ar.com.pablitar.libgdx.commons.CollisionUtils
import ar.com.pablitar.libgdx.commons.extensions.ShapeExtensions._
import ar.com.pablitar.libgdx.commons.rendering.Renderers

class CollisionDebugSystem extends IteratingSystem(Families.collidable().get, 11) {
  lazy val debugRenderers = new Renderers

  override def update(delta: Float) = {
    debugRenderers.begin()
    super.update(delta)
    debugRenderers.end()
  }
  
  def processEntity(e: Entity, delta: Float): Unit = {
    e.positionedShape.drawOn(debugRenderers)
  }
  

}