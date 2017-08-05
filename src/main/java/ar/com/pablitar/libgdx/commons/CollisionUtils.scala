package ar.com.pablitar.libgdx.commons

import com.badlogic.gdx.math.Shape2D
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Rectangle
import ar.com.pablitar.libgdx.commons.extensions.VectorExtensions._
import ar.com.pablitar.libgdx.commons.extensions.NumberExtensions._
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Intersector

object CollisionUtils {

  //Esta clase está subutilizada, pero la idea es que a futuro incluya información extra de la colisión: La dirección, el punto, el tipo (si contiene un cuerpo al otro), etc.
  case class Collision(bounds1: Shape2D, bounds2: Shape2D) {
    def reverse = Collision(bounds2, bounds1)
  }

  def rectangleAgainstCircle(b1: Rectangle, b2: Circle) = {
    val circleDisplacement = new Vector2(b2.x, b2.y).sub(b1.getCenter(new Vector2())).abs()
    if (circleDisplacement.x > (b1.width / 2 + b2.radius) || circleDisplacement.y > (b1.height / 2 + b2.radius)) {
      Option.empty[Collision]
    } else {
      if (circleDisplacement.x <= (b1.width / 2) || circleDisplacement.y <= (b1.height / 2)) {
        Some(Collision(b1, b2))
      } else {
        val cornerDistanceSquared = (circleDisplacement.x - b1.width / 2).squared +
          (circleDisplacement.y - b1.height / 2).squared

        if (cornerDistanceSquared <= (b2.radius.squared)) {
          Some(Collision(b1, b2))
        } else {
          Option.empty[Collision]
        }
      }
    }
  }

  def circleAgainstCircle(b1: Circle, b2: Circle) = {
    if (b1.overlaps(b2)) {
      Some(Collision(b1, b2))
    } else {
      Option.empty[Collision]
    }
  }
  
  def rectangleAgainstRectangle(b1: Rectangle, b2: Rectangle) = {
    if (b1.overlaps(b2)) {
      Some(Collision(b1, b2))
    } else {
      Option.empty[Collision]
    }
  }

  def checkCollision(bounds1: Shape2D, bounds2: Shape2D): Option[Collision] = {
    (bounds1, bounds2) match {
      case (b1: Circle, b2: Circle)       => circleAgainstCircle(b1, b2)
      case (b1: Rectangle, b2: Rectangle) => rectangleAgainstRectangle(b1, b2)
      case (b1: Circle, b2: Rectangle)    => rectangleAgainstCircle(b2, b1).map(_.reverse)
      case (b1: Rectangle, b2: Circle)    => rectangleAgainstCircle(b1, b2)
    }
  }

}