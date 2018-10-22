package ar.com.pablitar.libgdx.commons.extensions

import ar.com.pablitar.libgdx.commons.CoordinateDirection
import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport

object InputExtensions {

  implicit class InputOps(input: Input) {
    def arrowsDirection = {
      val direction = new Vector2(0, 0)
      if (input.isKeyPressed(Keys.LEFT) && !input.isKeyPressed(Keys.RIGHT)) {
        direction.x = -1
      } else if (input.isKeyPressed(Keys.RIGHT)) {
        direction.x = 1
      }

      if (input.isKeyPressed(Keys.UP) && !input.isKeyPressed(Keys.DOWN)) {
        direction.y = 1
      } else if (input.isKeyPressed(Keys.DOWN)) {
        direction.y = -1
      }

      direction.nor()
    }

    def arrowsDirectionOption = {
      Some(arrowsDirection).filterNot(_.isZero())
    }

    def touchPositionOption(): Option[Vector2] = {
      if (input.justTouched()) {
        Option(cursorPosition)
      } else {
        Option.empty
      }
    }

    def arrowsCoordinateDirection: Option[CoordinateDirection] = {
      if (input.isKeyPressed(Keys.LEFT) && !input.isKeyPressed(Keys.RIGHT)) {
        return Some(CoordinateDirection.West)
      } else if (input.isKeyPressed(Keys.RIGHT)) {
        return Some(CoordinateDirection.West)
      }

      if (input.isKeyPressed(Keys.UP) && !input.isKeyPressed(Keys.DOWN)) {
        return Some(CoordinateDirection.North)
      } else if (input.isKeyPressed(Keys.DOWN)) {
        return Some(CoordinateDirection.South)
      }

      return None
    }

    def cursorPosition: Vector2 = {
      new Vector2(input.getX, input.getY)
    }

    /**
      * Usar solo si no hay viewport
      * */
    def cursorScreenPosition: Vector2 = {
      new Vector2(input.getX, Gdx.graphics.getHeight - input.getY)
    }

    def cursorWorldPosition(viewport: Viewport): Vector2 = {
      viewport.unproject(cursorScreenPosition)
    }
  }

}