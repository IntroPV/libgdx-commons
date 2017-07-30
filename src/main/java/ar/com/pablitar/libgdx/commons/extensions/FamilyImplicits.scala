package ar.com.pablitar.libgdx.commons.extensions

import com.badlogic.ashley.core.Family
import com.badlogic.ashley.core.Component

object FamilyImplicits {
  implicit def classesToFamily(classes: Seq[Class[_ <: Component]]): Family = Family.all(classes:_*).get
  implicit def classToFamily(clazz: Class[_ <: Component]): Family = Family.all(clazz).get
  
  implicit def builderToFamily(builder: Family.Builder) : Family = builder.get
}