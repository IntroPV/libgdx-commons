package ar.com.pablitar.libgdx.commons.components

import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.math.Shape2D
import ar.com.pablitar.libgdx.commons.CollisionUtils.Collision
import ar.com.pablitar.libgdx.commons.extensions.ShapeExtensions._
import ar.com.pablitar.libgdx.commons.state.State

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
  
  implicit class CollisionableEntity(e: Entity) {
    def collidesWith = collisionableMapper.get(e).collidesWith
    def collisionEvents = collisionableMapper.get(e).collisionEvents
    def hasCollisionEventWith(otherE: Entity) = collisionableMapper.get(e) != null && collisionableMapper.get(e).hasCollisionEventWith(otherE)
  }
  
  implicit class ShapedEntity(e: Entity) {
    def shape = shapedMapper.get(e).shape
    def positionedShape = {
      //TODO: Quizás un método para no tener que clonar el shape
      val s = shape.copy
      s.center = e.position
      s
    }
  }
  
  implicit class EntityWithState(e: Entity) {
    def state = stateMapper.get(e).state
    def stateElapsed = stateMapper.get(e).elapsed
    def stateElapsed_=(elapsed: Float) = stateMapper.get(e).elapsed = elapsed
    def transition = stateMapper.get(e).transition
    def transition_=(t: Option[(State, State)]) = stateMapper.get(e).transition = t
    
    def changeStateTo(s: State) = {
        val prevState = e.state
    		e.add(StateComponent(
    		    state = s, 
    		    transition = Some((prevState, s))
  		    )
		    )
    }
  }
  
  implicit class StateBoundEntity(e: Entity) {
    def stateBindings = stateBindingMapper.get(e).bindings
  }
  
  implicit class BoundEntity(e: Entity) {
    def bindingTarget = bindingMapper.get(e).target
    def bindings = bindingMapper.get(e).bindings
  }
  
}