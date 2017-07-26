package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.ashley.core.Family
import com.badlogic.ashley.core.Component

object FamilyImplicits {
  implicit def classesToFamily(classes: Seq[Class[_ <: Component]]): Family = Family.all(classes:_*).get
}