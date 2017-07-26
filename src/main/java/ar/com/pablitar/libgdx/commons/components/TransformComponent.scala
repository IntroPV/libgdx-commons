package ar.com.pablitar.libgdx.commons.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._

class TransformComponent(
    var position: Vector2 = (0,0),
    var rotation: Float = 0f,
    var scale: Vector2 = (1,1)) extends Component