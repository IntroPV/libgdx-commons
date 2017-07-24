package ar.com.pablitar.libgdx.commons.rendering

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType

class Renderers(val spriteBatch: SpriteBatch = new SpriteBatch, val shapeRenderer: ShapeRenderer = new ShapeRenderer) extends Disposable {
  shapeRenderer.setAutoShapeType(true)

  var currentRenderer: Any = spriteBatch

  def dispose(): Unit = {
    spriteBatch.dispose()
    shapeRenderer.dispose()
  }

  //Hacer posible empezar con shapeRenderer
  def begin(): Unit = {
    spriteBatch.begin()
    currentRenderer = spriteBatch
  }

  def end(): Unit = {
    if(spriteBatch.isDrawing) spriteBatch.end()
    if(shapeRenderer.isDrawing) shapeRenderer.end()
  }

  def setProjectionMatrix(m: Matrix4 = new Matrix4) = {
    spriteBatch.setProjectionMatrix(m)
    shapeRenderer.setProjectionMatrix(m)
  }

  private def switchToShape(shapeType: ShapeType = ShapeType.Filled) = {
    if (currentRenderer == spriteBatch)
      spriteBatch.end()

    if (shapeRenderer.getCurrentType != shapeType || currentRenderer != shapeRenderer) {
      shapeRenderer.end()
      shapeRenderer.begin(shapeType)
    }

    currentRenderer = shapeRenderer
  }

  def withShapes(shapeType: ShapeType = ShapeType.Filled)(actions: (ShapeRenderer => Unit)) = {
    switchToShape(shapeType)
    actions(shapeRenderer)
  }
  
  def withSprites(actions: (SpriteBatch => Unit)) = {
    switchToSprite()
    actions(spriteBatch)
  }

  private def switchToSprite() {
    if (currentRenderer == shapeRenderer) {
      shapeRenderer.end()
      spriteBatch.begin()
      currentRenderer = spriteBatch
    }
  }
}
