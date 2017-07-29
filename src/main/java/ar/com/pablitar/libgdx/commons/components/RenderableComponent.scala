package ar.com.pablitar.libgdx.commons.components

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.ashley.core.Component
import ar.com.pablitar.libgdx.commons.rendering.Renderable

case class RenderableComponent(var z: Int) extends Component 