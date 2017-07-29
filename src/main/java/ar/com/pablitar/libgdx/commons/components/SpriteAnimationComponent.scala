package ar.com.pablitar.libgdx.commons.components

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.ashley.core.Component

case class SpriteAnimationComponent(var animation: Animation[Sprite], 
    var elapsed: Float = 0,
    val onAnimationFinished: Option[(Entity => Unit)] = None) extends Component 