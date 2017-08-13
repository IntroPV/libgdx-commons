package ar.com.pablitar.libgdx.commons.systems

import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.ashley.core.Family
import ar.com.pablitar.libgdx.commons.components.StateComponent
import com.badlogic.ashley.core.Entity
import ar.com.pablitar.libgdx.commons.components.Extensions._

class StateSystem extends IteratingSystem(Family.all(classOf[StateComponent]).get, -1) { 
  def processEntity(e: Entity, delta: Float): Unit = {
    e.transition = None
    e.stateElapsed = e.stateElapsed + delta
    e.state.timedTransition.foreach(aTransition => {
      if(aTransition.duration <= e.stateElapsed) {
        e.changeStateTo(aTransition.nextStateProvider.apply())
      }
    })
  }
}