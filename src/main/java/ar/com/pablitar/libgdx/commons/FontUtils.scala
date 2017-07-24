package ar.com.pablitar.libgdx.commons

import com.badlogic.gdx.graphics.g2d.{Batch, BitmapFont, GlyphLayout}
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import scala.collection.Seq

object FontUtils {
  val SIZE_VERY_BIG = 70
  val SIZE_BIG = 50
  val SIZE_LARGE = 40
  val SIZE_MEDIUM = 30
  val SIZE_NORMAL = 24
  val SIZE_SMALL = 16

  val allSizes = Seq(SIZE_SMALL, SIZE_NORMAL, SIZE_MEDIUM, SIZE_LARGE, SIZE_BIG, SIZE_VERY_BIG)

  def drawCentered(text: String, font: BitmapFont, position: Vector2, batch: Batch, centerVertical: Boolean = false): GlyphLayout = {
    val glyphLayout: GlyphLayout = centeredGlyph(text, font)
    font.draw(batch, glyphLayout, position.x, if(centerVertical) position.y + glyphLayout.height / 2 else position.y)
    glyphLayout
  }

  def centeredGlyph(text: String, font: BitmapFont): GlyphLayout = {
    val glyphLayout = new GlyphLayout()
    glyphLayout.setText(font, text, font.getColor, 0, Align.center, false)
    glyphLayout
  }
}