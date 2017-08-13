package ar.com.pablitar.libgdx.commons.components

import com.badlogic.ashley.core.Component
import scala.collection.mutable.AnyRefMap
import ar.com.pablitar.libgdx.commons.state.State

case class StateBinding[T <: Component](clazz: Class[T], newComponent: () => T)

case class StateBindingComponent(bindings: AnyRefMap[State, StateBinding[_ <: Component]]) extends Component