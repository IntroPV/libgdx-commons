package ar.com.pablitar.libgdx.commons.rendering

abstract class RenderSpace(val z: Int) extends Ordered[RenderSpace] {
  def compare(that: RenderSpace): Int = z.compare(that.z)
}

object RenderSpace {
  case object WorldSpace extends RenderSpace(0)
  case object ScreenSpace extends RenderSpace(1)
}