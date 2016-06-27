package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom.PrimitiveType
import PrimitiveType._

class RichPrimitiveType(private val t: PrimitiveType) {

	def getCode(): String =
		t.getPrimitiveTypeCode match {
			case BYTE => "B"
			case CHAR => "C"
			case DOUBLE => "D"
			case FLOAT => "F"
			case INT => "I"
			case LONG => "J"
			case SHORT => "S"
			case BOOLEAN => "Z"
			case VOID => "V"
		}

}

object RichPrimitiveType {
	def apply(t: PrimitiveType) = new RichPrimitiveType(t)
}