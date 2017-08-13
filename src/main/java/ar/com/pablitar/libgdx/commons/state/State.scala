package ar.com.pablitar.libgdx.commons.state

trait State {
  def timedTransition = Option.empty[TimedStateTransition]
}

case class TimedStateTransition(duration: Float, nextStateProvider: () => State) 

