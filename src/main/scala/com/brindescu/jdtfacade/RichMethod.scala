package com.brindescu.jdtfacade

import com.brindescu.jdtfacade.Facade._
import org.eclipse.jdt.core.dom._

import scala.collection.JavaConversions._

class RichMethod(private val node: MethodDeclaration) {

	def getMethodName = node.getName.getIdentifier

	def getParameters(): List[SingleVariableDeclaration] =
		node.getStructuralProperty(MethodDeclaration.PARAMETERS_PROPERTY).asInstanceOf[java.util.List[SingleVariableDeclaration]].toList


	def getDescriptor(): String =
		node.getName.getIdentifier + "(" + getParameters.map { _.getType }
			.map { _.getTypeDescription }
			.mkString + ")" + node.getReturnType2.getTypeDescription

	def getModifierKeywords: List[Modifier.ModifierKeyword] =
		node.getStructuralProperty(MethodDeclaration.MODIFIERS2_PROPERTY).asInstanceOf[java.util.List[Modifier]]
			.map {_.getKeyword} toList
}

object RichMethod {
	def apply(m: MethodDeclaration) = new RichMethod(m)
}