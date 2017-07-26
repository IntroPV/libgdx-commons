package ar.com.pablitar.libgdx.commons.systems

import com.badlogic.ashley.systems.IntervalIteratingSystem
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.libgdx.commons.components._
import ar.com.pablitar.libgdx.commons.components.Extensions._
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._

class MovementSystem(interval: Float = 1.0f / 120f)  extends IntervalIteratingSystem(Family.all(TransformCompClass, VelocityCompClass).get(), interval) {
  def processEntity(anEntity: Entity): Unit = {
    anEntity.position = anEntity.position + anEntity.velocity * interval
  }
}