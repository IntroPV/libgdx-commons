package ar.com.pablitar.libgdx.commons.systems

import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import java.util.Comparator
import com.badlogic.ashley.core.ComponentMapper
import SimpleRenderingSystem._
import ar.com.pablitar.libgdx.commons.rendering.Renderers
import ar.com.pablitar.libgdx.commons.components._
import ar.com.pablitar.libgdx.commons.components.Extensions._
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Sprite

object SimpleRenderingSystem {
  val renderableComparator = new Comparator[Entity]() {
    def compare(e1: Entity, e2: Entity) = e2.z.compareTo(e1.z)
  }
}

class SimpleRenderingSystem extends SortedIteratingSystem(Family.one(SpriteCompClass, AnimationCompClass).all(TransformCompClass).get(), renderableComparator, 10) {
  val renderers = new Renderers

  override def update(delta: Float) = {
    Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    renderers.begin()
    super.update(delta)
    renderers.end()
  }

  def processEntity(anEntity: Entity, delta: Float): Unit = {
    if (anEntity.isAnimated) {
      renderSprite(anEntity, anEntity.currentFrame)
      anEntity.elapsed = anEntity.elapsed + delta
      anEntity.onAnimationFinished.filter(action => anEntity.isAnimationFinished).foreach(_.apply(anEntity))
    } else {
      renderSprite(anEntity, anEntity.sprite)
    }
  }

  def renderSprite(anEntity: Entity, sp: Sprite) = {
    renderers.withSprites { batch =>
      sp.setPosition(anEntity.position.x - sp.getOriginX, anEntity.position.y - sp.getOriginY)
      sp.setRotation(anEntity.rotation)
      sp.draw(batch)
    }
  }
}