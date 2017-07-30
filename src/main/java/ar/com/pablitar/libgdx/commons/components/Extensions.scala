package ar.com.pablitar.libgdx.commons.components

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode

object Extensions {
  implicit class SpriteEntity(e: Entity) {
    private def spComponent = spriteMapper.get(e)

    def sprite = spComponent.sp
    def alpha = spComponent.alpha
  }
  
  implicit class RenderableEntity(e: Entity) {
    def z = renderableMapper.get(e).z
    def z_=(a: Int) = renderableMapper.get(e).z
  }

  implicit class EntityWithTransform(e: Entity) {
    def position = transformMapper.get(e).position
    def position_=(v: Vector2) = transformMapper.get(e).position = v

    def rotation = transformMapper.get(e).rotation
    def rotation_=(r: Float) = transformMapper.get(e).rotation = r

    def scale = transformMapper.get(e).scale
    def scale_=(v: Vector2) = transformMapper.get(e).scale = v

  }

  implicit class SpeedyEntity(e: Entity) {
    def velocity = velocityMapper.get(e).velocity
    def velocity_=(v: Vector2) = { velocityMapper.get(e).velocity = v }
    def acceleration = velocityMapper.get(e).acceleration
    def acceleration_=(a: Vector2) = velocityMapper.get(e).acceleration = Some(a)
    def acceleration_=(a: Option[Vector2]) = velocityMapper.get(e).acceleration = a
  }

  implicit class AnimationEntity(e: Entity) {
    def animation = animationMapper.get(e).animation
    def isAnimated = animationMapper.has(e)
    def elapsed = animationMapper.get(e).elapsed
    def elapsed_=(value: Float) = animationMapper.get(e).elapsed = value
    def currentFrame = animation.getKeyFrame(elapsed)
    def onAnimationFinished = animationMapper.get(e).onAnimationFinished
    def isAnimationFinished = animation.isAnimationFinished(elapsed) && animation.getPlayMode == PlayMode.NORMAL || animation.getPlayMode == PlayMode.REVERSED
  }
}