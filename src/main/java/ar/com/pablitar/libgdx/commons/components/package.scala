package ar.com.pablitar.libgdx.commons
import com.badlogic.ashley.core.ComponentMapper

package object components {
  val TransformCompClass = classOf[TransformComponent]
  val RenderableCompClass = classOf[RenderableComponent]
  val SpriteCompClass = classOf[SpriteComponent]
  val VelocityCompClass = classOf[VelocityComponent]
  val AnimationCompClass = classOf[SpriteAnimationComponent]

  val spriteMapper = ComponentMapper.getFor(SpriteCompClass)
  val transformMapper = ComponentMapper.getFor(TransformCompClass)
  val velocityMapper = ComponentMapper.getFor(VelocityCompClass)
  val animationMapper = ComponentMapper.getFor(AnimationCompClass)
}