package ar.com.pablitar.libgdx.commons.engines

import com.badlogic.ashley.core.Engine
import ar.com.pablitar.libgdx.commons.systems.MovementSystem
import ar.com.pablitar.libgdx.commons.systems.SimpleRenderingSystem
import ar.com.pablitar.libgdx.commons.systems.CollisionSystem
import ar.com.pablitar.libgdx.commons.systems.BindingSystem
import ar.com.pablitar.libgdx.commons.systems.CollisionDebugSystem

object Engines {
  def commonEngine(debug: Boolean = false) = {
    val engine = new Engine
    engine.addSystem(new MovementSystem)
    engine.addSystem(new SimpleRenderingSystem)
    engine.addSystem(new CollisionSystem)
    engine.addSystem(new BindingSystem)
    if(debug) {
      engine.addSystem(new CollisionDebugSystem)
    }
    engine
  }
}