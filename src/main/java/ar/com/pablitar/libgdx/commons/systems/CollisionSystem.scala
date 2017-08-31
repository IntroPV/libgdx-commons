package ar.com.pablitar.libgdx.commons.systems

import com.badlogic.ashley.systems.IteratingSystem
import ar.com.pablitar.libgdx.commons.family.Families
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.libgdx.commons.components.Extensions._
import com.badlogic.ashley.core.EntitySystem
import ar.com.pablitar.libgdx.commons.collisions.CollisionEvent
import ar.com.pablitar.libgdx.commons.CollisionUtils
import ar.com.pablitar.libgdx.commons.extensions.ShapeExtensions._
import ar.com.pablitar.libgdx.commons.rendering.Renderers
import java.util.function.Consumer

class CollisionSystem extends EntitySystem(-1) {
  lazy val collisionableEntities = this.getEngine.getEntitiesFor(Families.collisionable().get)

  implicit def toConsumer[A](function: A => Unit): Consumer[A] = new Consumer[A]() {
    override def accept(arg: A): Unit = function.apply(arg)
  }

  override def update(deltaTime: Float) = {
    collisionableEntities.forEach((e: Entity) => e.collisionEvents.clear())
    collisionableEntities.forEach((e: Entity) => processCollisionsFor(e))
  }

  def processCollisionsFor(e: Entity): Unit = {
    this.getEngine.getEntitiesFor(e.collidesWith).forEach((otherE: Entity) => processCollisionBetween(e, otherE))
  }

  def processCollisionBetween(e: Entity, otherE: Entity): Unit = {
    if (!otherE.hasCollisionEventWith(e)) {
      if (checkCollision(e, otherE).isDefined) {
        e.collisionEvents += CollisionEvent(e, otherE)
      }
    }
  }

  def checkCollision(e: Entity, otherE: Entity) = {
    CollisionUtils.checkCollision(e.positionedShape, otherE.positionedShape)
  }
}