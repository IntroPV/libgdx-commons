package ar.com.pablitar.libgdx.commons.collisions

import com.badlogic.ashley.core.Entity

case class CollisionEvent(val collider: Entity, val collided: Entity)