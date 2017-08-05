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

class CollisionSystem extends EntitySystem {
  lazy val collisionableEntities = this.getEngine.getEntitiesFor(Families.collisionable().get)
  lazy val debugRenderers = new Renderers
  
  override def update(deltaTime: Float) = {
    collisionableEntities.forEach(((e:Entity) => e.collisionEvents.clear()).asJava)
    collisionableEntities.forEach(((e:Entity) => processCollisionsFor(e)).asJava)     
  }

  def processCollisionsFor(e: Entity):Unit = {
    this.getEngine.getEntitiesFor(e.collidesWith).forEach(
        ((otherE:Entity) => processCollisionBetween(e, otherE)).asJava)
  }

  def processCollisionBetween(e: Entity, otherE: Entity):Unit = {
    if(!otherE.hasCollisionEventWith(e)) {
      if(checkCollision(e, otherE).isDefined) {
        e.collisionEvents += CollisionEvent(e, otherE)
        println(e.collisionEvents)
      }
    }
  }
  
  def checkCollision(e: Entity, otherE: Entity) = {
    CollisionUtils.checkCollision(e.positionedShape, otherE.positionedShape)
  }
}