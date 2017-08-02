package ar.com.pablitar.libgdx.commons.components

import ar.com.pablitar.libgdx.commons.collisions.CollisionEvent

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Family
import scala.collection.mutable.ArrayBuffer
import ar.com.pablitar.libgdx.commons.collisions.CollisionEvent
import com.badlogic.ashley.core.Entity
 
case class CollisionComponent(collidesWith: Family, collisionEvents: ArrayBuffer[CollisionEvent] = ArrayBuffer()) extends Component {
  def hasCollisionEventWith(otherE: Entity) = {
    collisionEvents.exists(_.collided == otherE)
  }
}