package ar.com.pablitar.libgdx.commons.engines

import com.badlogic.ashley.core.Engine
import ar.com.pablitar.libgdx.commons.systems.MovementSystem
import ar.com.pablitar.libgdx.commons.systems.SimpleRenderingSystem
import ar.com.pablitar.libgdx.commons.systems.CollisionSystem

object Engines {
  def commonEngine() = {
    val engine = new Engine
    engine.addSystem(new MovementSystem)
    engine.addSystem(new SimpleRenderingSystem)
    engine.addSystem(new CollisionSystem)
    engine
  }
}