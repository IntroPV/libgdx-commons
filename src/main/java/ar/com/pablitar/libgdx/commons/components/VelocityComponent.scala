package ar.com.pablitar.libgdx.commons.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._

case class VelocityComponent(var velocity: Vector2 = (0,0), var acceleration: Option[Vector2] = None) extends Component