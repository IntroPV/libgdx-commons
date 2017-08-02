package ar.com.pablitar.libgdx.commons.family

import com.badlogic.ashley.core.Family
import ar.com.pablitar.libgdx.commons.components.TransformComponent
import ar.com.pablitar.libgdx.commons.components.VelocityComponent
import ar.com.pablitar.libgdx.commons.components.ShapeComponent
import ar.com.pablitar.libgdx.commons.components.CollisionComponent
import com.badlogic.ashley.core.Component

object Families {
  val kinematicComponents = Seq(classOf[TransformComponent], classOf[VelocityComponent])
  val collisionableComponents = Seq(classOf[TransformComponent], classOf[ShapeComponent], classOf[CollisionComponent])
  val collidableComponents = Seq(classOf[TransformComponent], classOf[ShapeComponent])
  
  def fromSeq(classes : Seq[Class[_<:Component]]) = Family.all(classes.distinct:_*)
  def kinematic(additional: Class[_ <: Component]*) = fromSeq(kinematicComponents ++ additional)
  def collisionable(additional: Class[_ <: Component]*) = fromSeq(collisionableComponents ++ additional)
  def collidable(additional: Class[_ <: Component]*) = fromSeq(collidableComponents ++ additional)
      
  def kinematicCollidable(additional: Class[_ <: Component]*) = fromSeq(
      kinematicComponents ++ collidableComponents ++ additional)
}