package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.gdx.Input
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.math.Vector2

object InputExtensions {
  implicit class InputOps(input: Input) {
    def arrowsDirection = {
      val direction = new Vector2(0,0)
      if(input.isKeyPressed(Keys.LEFT) && !input.isKeyPressed(Keys.RIGHT)) {
        direction.x = -1
      } else if(input.isKeyPressed(Keys.RIGHT)) {
        direction.x = 1
      }
      
      if(input.isKeyPressed(Keys.UP) && !input.isKeyPressed(Keys.DOWN)) {
        direction.y = 1
      } else if(input.isKeyPressed(Keys.DOWN)) {
        direction.y = -1
      }
      
      direction.nor()
    }
  }
}