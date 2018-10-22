package ar.com.pablitar.libgdx.commons.traits

import com.badlogic.gdx.math.Shape2D

trait Dragable extends Positioned {
  def shape: Shape2D
}
