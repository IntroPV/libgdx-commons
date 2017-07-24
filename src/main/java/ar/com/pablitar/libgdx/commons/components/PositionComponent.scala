package ar.com.pablitar.libgdx.commons.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._

class PositionComponent(var position: Vector2 = (0,0)) extends Component