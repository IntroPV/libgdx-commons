package ar.com.pablitar.libgdx.commons

import com.badlogic.gdx.graphics.Color


object ColorUtils {
  //Copypasteado de Internet
  /**
   * @param hue De 0 a 360
   * @param saturation de 0 a 100
   * @param value de 0 a 100
   * @param alpha de 0 a 1, default 1
   */
  def fromHSV(hue: Float, saturation: Float, value: Float, alpha: Float = 1): Color = {
    var r: Int = 0
    var g: Int = 0
    var b: Int = 0
    var i: Int = 0
    var f: Float = 0.0f
    var p: Float = 0.0f
    var q: Float = 0.0f
    var t: Float = 0.0f
    var h = Math.max(0.0, Math.min(360.0, hue)).toFloat
    var s = Math.max(0.0, Math.min(100.0, saturation)).toFloat
    var v = Math.max(0.0, Math.min(100.0, value)).toFloat
    s /= 100
    v /= 100
    h /= 60
    i = Math.floor(h).toInt
    f = h - i
    p = v * (1 - s)
    q = v * (1 - s * f)
    t = v * (1 - s * (1 - f))
    i match {
      case 0 =>
        r = Math.round(255 * v)
        g = Math.round(255 * t)
        b = Math.round(255 * p)
      case 1 =>
        r = Math.round(255 * q)
        g = Math.round(255 * v)
        b = Math.round(255 * p)
      case 2 =>
        r = Math.round(255 * p)
        g = Math.round(255 * v)
        b = Math.round(255 * t)
      case 3 =>
        r = Math.round(255 * p)
        g = Math.round(255 * q)
        b = Math.round(255 * v)
      case 4 =>
        r = Math.round(255 * t)
        g = Math.round(255 * p)
        b = Math.round(255 * v)
      case _ =>
        r = Math.round(255 * v)
        g = Math.round(255 * p)
        b = Math.round(255 * q)

    }
    new Color(r / 255.0f, g / 255.0f, b / 255.0f, alpha)
  }
}