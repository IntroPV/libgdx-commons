package ar.com.pablitar.libgdx.commons.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem

import ar.com.pablitar.libgdx.commons.components.BindingsComponent
import ar.com.pablitar.libgdx.commons.components.Extensions.BoundEntity

class BindingSystem extends IteratingSystem(Family.all(classOf[BindingsComponent]).get, 9){ 
  def processEntity(e: Entity, delta: Float) = {
    e.bindings.foreach(_.updateBinding(e, e.bindingTarget))
  }
}