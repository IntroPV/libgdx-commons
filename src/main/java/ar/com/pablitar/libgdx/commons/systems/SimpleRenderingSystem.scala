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

object SimpleRenderingSystem {
  val renderableComparator = new Comparator[Entity]() {
    def compare(e1: Entity, e2: Entity) = e1.z.compareTo(e2.z)
  }
}

class SimpleRenderingSystem extends SortedIteratingSystem(Family.all(SpriteCompClass, TransformCompClass).get(), renderableComparator) {
  val renderers = new Renderers

  override def update(delta: Float) = {
    Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    renderers.begin()
    super.update(delta)
    renderers.end()
  }

  def processEntity(anEntity: Entity, delta: Float): Unit = {
    renderers.withSprites { batch =>
      val sp = anEntity.sprite
      sp.setPosition(anEntity.position.x - sp.getOriginX, anEntity.position.y - sp.getOriginY)
      sp.draw(batch)
    }

    renderers.withShapes() { batch =>
      batch.circle(anEntity.position.x, anEntity.position.y, 3.0f)
    }
  }
}