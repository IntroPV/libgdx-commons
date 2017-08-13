package ar.com.pablitar.libgdx.commons.components

import ar.com.pablitar.libgdx.commons.state._
import com.badlogic.ashley.core.Component
import ar.com.pablitar.libgdx.commons.state.TimedStateTransition
import scala.collection.mutable.ArrayBuffer

case class StateComponent(state: State, 
    var elapsed: Float = 0f, var transition: Option[Transition] = None) extends Component