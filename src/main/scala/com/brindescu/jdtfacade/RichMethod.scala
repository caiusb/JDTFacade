package com.brindescu.jdtfacade

import com.brindescu.jdtfacade.Facade._
import org.eclipse.jdt.core.dom._

import scala.collection.JavaConversions._

class RichMethod(private val node: MethodDeclaration) {

	def getMethodName = node.getName.getIdentifier

	def getParameters(): List[SingleVariableDeclaration] =
		node.getStructuralProperty(MethodDeclaration.PARAMETERS_PROPERTY).asInstanceOf[java.util.List[SingleVariableDeclaration]].toList

	def getActualReturnType(): String =
		if (!node.isConstructor)
			return node.getReturnType2.getTypeDescription
		else
			return "V"//"L" + node.getDeclaringClass.resolveBinding.getBinaryName.replace(".","/") + ";"

	def getDeclaringClass(): TypeDeclaration = {
		def find(n: ASTNode): TypeDeclaration =
				n match {
					case t: TypeDeclaration => t
					case n: ASTNode => find(n.getParent)
				}

		find(node)
	}

	def getDescriptor(): String = {
		val methodIdentifier: String = if (node.isConstructor) "<init>" else node.getName.getIdentifier
		methodIdentifier + "(" + getParameters.map { _.getType }
			.map { _.getTypeDescription }
			.mkString + ")" + getActualReturnType
	}

	def getModifierKeywords: List[Modifier.ModifierKeyword] =
		node.getStructuralProperty(MethodDeclaration.MODIFIERS2_PROPERTY).asInstanceOf[java.util.List[Modifier]]
			.map {_.getKeyword} toList
}

object RichMethod {
	def apply(m: MethodDeclaration) = new RichMethod(m)
}