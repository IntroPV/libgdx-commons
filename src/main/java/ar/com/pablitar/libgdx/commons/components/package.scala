package ar.com.pablitar.libgdx.commons
import com.badlogic.ashley.core.ComponentMapper

package object components {
  val PositionCompClass = classOf[PositionComponent]
  val RenderableCompClass = classOf[RenderableComponent]
  val SpriteCompClass = classOf[SpriteComponent]
  val VelocityCompClass = classOf[VelocityComponent]

  val spriteMapper = ComponentMapper.getFor(SpriteCompClass)
  val positionMapper = ComponentMapper.getFor(PositionCompClass)
  val velocityMapper = ComponentMapper.getFor(VelocityCompClass)
}