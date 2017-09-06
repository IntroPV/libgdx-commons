package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

object SpriteExtensions {
  implicit class SpriteOps(sp: Sprite) {
    def drawCenteredInOrigin(batch: SpriteBatch, position: Vector2) = {
      sp.setPosition(position.x - sp.getOriginX, position.y - sp.getOriginY)
      sp.draw(batch)
    }
  }
}