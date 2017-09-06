package ar.com.pablitar.libgdx.commons.traits

import com.badlogic.gdx.math.Vector2
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._

trait DirectionedSpeedBehaviour extends SpeedBehaviour {
  self: Positioned with Directionable =>
    def speedMagnitude: Float
    override def speed = directionVersor.scl(speedMagnitude)
}

trait SpeedBehaviour {
  self: Positioned =>
    protected var _speed: Vector2 = new Vector2(0, 0)
  
    def speed = _speed
    def speed_=(aSpeed: Vector2) = _speed = {
      limitedSpeed(aSpeed)
    }
    
    def limitedSpeed(aSpeed: Vector2) = topSpeedMagnitude.fold(aSpeed)((it) => aSpeed.limit2(it * it))
    
    def topSpeedMagnitude = Option.empty[Float]
    
    def applySpeed(delta: Float, toPosition: Vector2 = this.position) = toPosition.add(speed.cpy().scl(delta))
}

trait AcceleratedSpeedBehaviour extends SpeedBehaviour {
  self: Positioned =>
    def acceleration: Vector2
    def applyAcceleration(delta: Float, toSpeed: Vector2 = this.speed) = {
      speed = speed + acceleration.cpy.scl(delta)
    }
    def updateValues(delta: Float) = { applyAcceleration(delta); applySpeed(delta) }
}

trait DragBehaviour extends AcceleratedSpeedBehaviour {
  self: Positioned =>
    def drag: Float
    def activeAcceleration: Option[Vector2]
    def acceleration = activeAcceleration.fold(speed.toZeroDirection() * drag)((a) => a)
    
    override def speed_=(aSpeed: Vector2): Unit = {
      val newSpeed = limitedSpeed(aSpeed)
      if(newSpeed.len2() < zeroSpeedTolerance * zeroSpeedTolerance){
        newSpeed.set(0, 0)
      }
      _speed = newSpeed
    }
    
    def zeroSpeedTolerance = drag / 50f
    
}