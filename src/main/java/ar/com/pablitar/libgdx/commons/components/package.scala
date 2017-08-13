package ar.com.pablitar.libgdx.commons
import com.badlogic.ashley.core.ComponentMapper

package object components {
  val TransformCompClass = classOf[TransformComponent]
  val RenderableCompClass = classOf[RenderableComponent]
  val SpriteCompClass = classOf[SpriteComponent]
  val VelocityCompClass = classOf[VelocityComponent]
  val AnimationCompClass = classOf[SpriteAnimationComponent]
  val CollisionableCompClass = classOf[CollisionComponent]
  val ShapeCompClass = classOf[ShapeComponent]

  val spriteMapper = ComponentMapper.getFor(SpriteCompClass)
  val renderableMapper = ComponentMapper.getFor(RenderableCompClass)
  val transformMapper = ComponentMapper.getFor(TransformCompClass)
  val velocityMapper = ComponentMapper.getFor(VelocityCompClass)
  val animationMapper = ComponentMapper.getFor(AnimationCompClass)
  val collisionableMapper = ComponentMapper.getFor(CollisionableCompClass)
  val shapedMapper = ComponentMapper.getFor(ShapeCompClass)
  val bindingMapper = ComponentMapper.getFor(classOf[BindingsComponent])
  val stateMapper = ComponentMapper.getFor(classOf[StateComponent])
  val stateBindingMapper = ComponentMapper.getFor(classOf[StateBindingComponent])
}