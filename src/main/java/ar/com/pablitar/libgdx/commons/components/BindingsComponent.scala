package ar.com.pablitar.libgdx.commons.components

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.ComponentMapper

case class Binding[T <: Component](clazz: Class[T], componentBinding: T => T, mapperOption: Option[ComponentMapper[T]] = None) {
  def updateBinding(entity: Entity, target: Entity) = {
    val targetComponent = mapperOption.fold(target.getComponent(clazz))(_.get(target))
    
    entity.add(componentBinding(targetComponent))
  }
}

case class BindingsComponent(target: Entity, bindings: Binding[_]*) extends Component