package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.graphics.Color

object SpriteBatchExtensions {
  implicit class SpriteBatchOps(batch: SpriteBatch) {
    def drawText(text: String, font: BitmapFont, position: Vector2, align: Int = Align.left, color: Color = Color.WHITE) = {
      val oldColor = font.getColor
      font.setColor(color)
      val glyph = font.draw(batch, text,
        position.x, position.y, 0, align, false)
        
      font.setColor(oldColor)
      
      glyph
    }
  }
}