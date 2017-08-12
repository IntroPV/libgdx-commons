package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.gdx.math.Shape2D
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Ellipse
import VectorExtensions._
import NumberExtensions._
import com.badlogic.gdx.math.MathUtils
import ar.com.pablitar.libgdx.commons.rendering.Renderers
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import ar.com.pablitar.libgdx.commons.math.Segment2
import com.badlogic.gdx.math.Intersector
import ar.com.pablitar.libgdx.commons.CommonMathUtils
import ar.com.pablitar.libgdx.commons.CollisionUtils.Collision
import ar.com.pablitar.libgdx.commons.CollisionUtils

object ShapeExtensions {
  implicit class Shape2DOps(shape: Shape2D) {
    def center = {
      shape match {
        case s: Circle    => new Vector2(s.x, s.y)
        case s: Rectangle => s.getCenter(new Vector2())
      }
    }
    
    def checkCollision(otherShape: Shape2D) = {
      CollisionUtils.checkCollision(shape, otherShape)
    }
    
    def center_=(p: Vector2) = {
      shape match {
        case s: Circle => {
          s.x = p.x
          s.y = p.y
        }
        case s: Rectangle => {
          s.x = p.x - s.width / 2
          s.y = p.y - s.height / 2
        }
      }
    }
    
    def copy = {
      shape match {
        case s: Circle    => new Circle(s)
        case s: Rectangle => new Rectangle(s)
      }
    }

    def keepInside(v: Vector2) = {
      shape match {
        case s: Circle => {
          //La implementación de circle no es muy óptima... pero bueno, no se usa por ahora
          val directionVector = (v - s.center)
          if (directionVector.len2() > s.radius.squared) {
            directionVector.nor() * s.radius
          } else v
        }
        case r: Rectangle => {
          new Vector2(v.x.bounded(r.x, r.x + r.width), v.y.bounded(r.y, r.y + r.height))
        }
      }
    }

    def outsideDirectionOption(p: Vector2):Option[Float] = {
      shape match {
        //Refactor maybe?
        case s: Rectangle if p.x < s.x && p.y < s.y => Some(MathUtils.PI * 1.25f)
        case s: Rectangle if p.x < s.x && p.y > s.y + s.height => Some(MathUtils.PI * 0.75f)
        case s: Rectangle if p.x > s.x + s.width && p.y < s.y => Some(MathUtils.PI * 1.75f)
        case s: Rectangle if p.x > s.x + s.width && p.y > s.y + s.height => Some(MathUtils.PI * 0.25f)
        case s: Rectangle if p.x < s.x => Some(MathUtils.PI)
        case s: Rectangle if p.y < s.y => Some(MathUtils.PI * 1.5f)
        case s: Rectangle if p.y > s.y + s.height => Some(MathUtils.PI * 0.5f)
        case s: Rectangle if p.x > s.x + s.width => Some(0f)
        case _ => None
      }
    }
    
    def drawOn(renderers: Renderers) = {
      renderers.withShapes(ShapeType.Line)(renderer =>
        shape match {
          case s: Circle => renderer.circle(s.x, s.y, s.radius)
          case r: Rectangle => renderer.rect(r.x, r.y, r.width, r.height)
      })
    }
    
    def intersectsSegment(seg: Segment2) = {
      shape match {
          case s: Circle => Intersector.intersectSegmentCircle(seg.from, seg.to, s.center, s.radius * s.radius)
          case r: Rectangle => Intersector.intersectSegmentPolygon(seg.from, seg.to, CommonMathUtils.rectangleToPolygon(r))
      }
    }
  }
}