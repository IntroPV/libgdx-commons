package ar.com.pablitar.libgdx.commons.family

import com.badlogic.ashley.core.Family
import ar.com.pablitar.libgdx.commons.components.TransformComponent
import ar.com.pablitar.libgdx.commons.components.VelocityComponent

object Families {
  def kinematic = Family.all(classOf[TransformComponent], classOf[VelocityComponent])
  
}