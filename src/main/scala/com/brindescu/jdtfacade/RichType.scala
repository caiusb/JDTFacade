package com.brindescu.jdtfacade

import com.brindescu.jdtfacade.Facade._
import org.eclipse.jdt.core.dom._

class RichType(private val t: Type) {

	def getTypeDescription: String =
		t match {
			case x: PrimitiveType => x.getCode()
			case x: ArrayType => "[" + x.getElementType.getTypeDescription
			case x: SimpleType => getObjectCode(x)
			case x: QualifiedType => getObjectCode(x)
			case _ => ""
		}

	private def getObjectCode(t: SimpleType): String =
		"L" + t.getName.resolveBinding.asInstanceOf[ITypeBinding].getQualifiedName.replace('.','/') + ";"

	private def getObjectCode(t: QualifiedType): String =
		"L" + t.getName.getFullyQualifiedName.replace('.','/') + ";"
}

object RichType {
	def apply(t: Type) = new RichType(t)
}

