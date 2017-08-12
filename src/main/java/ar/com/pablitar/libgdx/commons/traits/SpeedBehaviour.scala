package ar.com.pablitar.libgdx.commons.traits

import com.badlogic.gdx.math.Vector2

trait DirectionedSpeedBehaviour extends SpeedBehaviour {
  self: Positioned with Directionable =>
    def speedMagnitude: Float
    override def speed = directionVersor.scl(speedMagnitude)
}

trait SpeedBehaviour {
  self: Positioned =>
    protected var _speed: Vector2 = new Vector2(0, 0)
  
    def speed = _speed
    def speed_=(aSpeed: Vector2) = _speed = aSpeed
    def applySpeed(delta: Float, toPosition: Vector2 = this.position) = toPosition.add(speed.cpy().scl(delta))
}

trait AcceleratedSpeedBehaviour extends SpeedBehaviour {
  self: Positioned =>
    def acceleration: Vector2
    def applyAcceleration(delta: Float, toSpeed: Vector2 = this.speed) = speed.add(acceleration.cpy.scl(delta))
    def updateValues(delta: Float) = { applyAcceleration(delta); applySpeed(delta) }
}