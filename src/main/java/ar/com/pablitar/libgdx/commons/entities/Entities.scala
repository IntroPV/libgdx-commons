package ar.com.pablitar.libgdx.commons.entities

import com.badlogic.gdx.math.Vector2
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.libgdx.commons.components.PositionComponent
import com.badlogic.gdx.graphics.g2d.Sprite
import ar.com.pablitar.libgdx.commons.components.SpriteComponent
import ar.com.pablitar.libgdx.commons.components.VelocityComponent

object Entities {
  def simpleSprite(sp: Sprite, position: Vector2) = {
    val entity = new Entity
    entity.add(new PositionComponent(position))
    entity.add(new SpriteComponent(sp))
    entity
  }
  
  def movingSprite(sp: Sprite, position: Vector2, initialVelocity: Vector2 = new Vector2) = {
    val entity = simpleSprite(sp, position)
    entity.add(new VelocityComponent(initialVelocity))
    entity
  }
}