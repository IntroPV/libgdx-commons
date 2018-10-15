package ar.com.pablitar.libgdx.commons.extensions

import ar.com.pablitar.libgdx.commons.rendering.Renderers
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.{Path, Vector2}

object PathExtensions {

  implicit class Path2Ops(path: Path[Vector2]) {
    def drawOn(shapeRenderer: ShapeRenderer, divisions: Int = 100): Unit = {
      val from: Vector2 = new Vector2()
      val to: Vector2 = new Vector2()

      for(i <- 0 until divisions - 1) {
        shapeRenderer.line(
          path.valueAt(from, i.toFloat / (divisions-1)),
          path.valueAt(to, (i+1).toFloat / (divisions-1))
        )
      }

    }

    def drawOn(renderers: Renderers): Unit = {
      renderers.withShapes(ShapeType.Line)(drawOn(_))
    }
  }

}
