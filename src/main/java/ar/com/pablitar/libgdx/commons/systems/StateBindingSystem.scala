package ar.com.pablitar.libgdx.commons.systems

import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.ashley.core.Family
import ar.com.pablitar.libgdx.commons.components.StateComponent
import ar.com.pablitar.libgdx.commons.components.StateBindingComponent
import ar.com.pablitar.libgdx.commons.components.Extensions._
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.libgdx.commons.state.State

class StateBindingSystem extends IteratingSystem(Family.all(classOf[StateComponent], classOf[StateBindingComponent]).get, 9) {
  def processEntity(e: Entity, delta: Float): Unit = {
    e.transition.foreach(processEntityTransition(e, _))
  }

  def processEntityTransition(e: Entity, s: (State, State)) = {
    e.stateBindings.get(s._1).foreach(binding => e.remove(binding.clazz))
    e.stateBindings.get(s._2).foreach(binding => e.add(binding.newComponent()))
  }

}