package ar.com.pablitar.libgdx.commons.entities

import com.badlogic.gdx.math.Vector2
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.libgdx.commons.components.TransformComponent
import com.badlogic.gdx.graphics.g2d.Sprite
import ar.com.pablitar.libgdx.commons.components.SpriteComponent
import ar.com.pablitar.libgdx.commons.components.VelocityComponent
import com.badlogic.gdx.graphics.g2d.Animation
import ar.com.pablitar.libgdx.commons.components.SpriteAnimationComponent
import ar.com.pablitar.libgdx.commons.components.RenderableComponent

object Entities {
  def renderable(position: Vector2, z: Int = 0) = {
    val entity = new Entity
    entity.add(TransformComponent(position))
    entity.add(RenderableComponent(z))
  }
  
  def simpleSprite(sp: Sprite, position: Vector2, z: Int = 0) = {
    val entity = renderable(position, z)
    entity.add(SpriteComponent(sp))
    entity
  }
  
  def animated(animation: Animation[Sprite], position: Vector2, z: Int = 0) = {
    val entity = renderable(position, z)
    entity.add(SpriteAnimationComponent(animation))
    entity
  }
  
  def movingSprite(sp: Sprite, position: Vector2, z: Int = 0, initialVelocity: Vector2 = new Vector2) = {
    val entity = simpleSprite(sp, position, z)
    entity.add(new VelocityComponent(initialVelocity))
    entity
  }
  
  def movingAnimated(animation: Animation[Sprite], position: Vector2, z: Int = 0,initialVelocity: Vector2 = new Vector2) ={
    val entity = animated(animation, position, z)
    entity.add(new VelocityComponent(initialVelocity))
    entity
  }
}