package ar.com.pablitar.libgdx.commons.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Sprite

case class SpriteComponent(var sp: Sprite, var alpha: Float = 1.0f) extends Component