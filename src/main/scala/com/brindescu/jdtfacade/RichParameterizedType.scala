package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom.{ParameterizedType, Type}

import scala.collection.JavaConversions._

class RichParameterizedType(private val t: ParameterizedType) {
	def getBaseType(): Type =
		t.getStructuralProperty(ParameterizedType.TYPE_PROPERTY).asInstanceOf[Type]

	def getParameterTypes(): List[Type] =
		t.getStructuralProperty(ParameterizedType.TYPE_ARGUMENTS_PROPERTY).asInstanceOf[java.util.List[Type]].toList
}

object RichParameterizedType {
	def apply(t: ParameterizedType) = new RichParameterizedType(t)
}
