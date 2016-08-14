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
			case x: ParameterizedType => x.getBaseType.getTypeDescription
			case _ => ""
		}

	private def getObjectCode(t: SimpleType): String = {
		val binding = t.getName.resolveBinding
		if (binding != null)
			return "L" + binding.asInstanceOf[ITypeBinding].getErasure.getQualifiedName.replace('.', '/') + ";"
		else
			return "L" + t.getName.getFullyQualifiedName + ";"
	}

	private def getObjectCode(t: QualifiedType): String =
		"L" + t.getName.getFullyQualifiedName.replace('.','/') + ";"
}

object RichType {
	def apply(t: Type) = new RichType(t)
}

