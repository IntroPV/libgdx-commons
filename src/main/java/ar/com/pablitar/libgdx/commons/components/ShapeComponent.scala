package ar.com.pablitar.libgdx.commons.components

import com.badlogic.ashley.core.Component
import ar.com.pablitar.libgdx.commons.Bounds
import Extensions._
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.math.Shape2D
import ar.com.pablitar.libgdx.commons.CollisionUtils

case class ShapeComponent(val shape: Shape2D) extends Component