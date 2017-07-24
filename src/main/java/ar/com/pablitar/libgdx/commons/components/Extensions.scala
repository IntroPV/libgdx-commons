package ar.com.pablitar.libgdx.commons.components

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2

	object Extensions {
  implicit class SpriteEntity(e: Entity) {
    private def spComponent = spriteMapper.get(e)

    def sprite = spComponent.sp
    def z = spComponent.z
    def alpha = spComponent.alpha
  }
  
  implicit class PositionedEntity(e: Entity) {
    def position = positionMapper.get(e).position
    def position_=(v: Vector2) = {positionMapper.get(e).position = v}
  }
  
  implicit class SpeedyEntity(e: Entity) {
    def velocity = velocityMapper.get(e).velocity
    def velocity_=(v: Vector2) = {velocityMapper.get(e).velocity = v}
  }
}