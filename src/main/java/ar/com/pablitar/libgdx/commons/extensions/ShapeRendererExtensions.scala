package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

object ShapeRendererExtensions {
  implicit class ShapeRendererOps(shapeRenderer: ShapeRenderer) {
    def circleWithBorder(borderWidth: Float,
                         borderColor: Color,
                         radius: Float,
                         fillColor: Color = shapeRenderer.getColor)(x: Float, y: Float): Unit = {
      shapeRenderer.setColor(borderColor)
      shapeRenderer.circle(x, y, radius)
      shapeRenderer.setColor(fillColor)
      shapeRenderer.circle(x, y, radius - borderWidth)
    }
  }
}
