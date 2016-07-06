package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom.{ASTNode, TypeDeclaration}

import Facade._

class RichClass(private val node: TypeDeclaration) {

	def getFullyQualifiedName(): String =
		node.resolveBinding.getQualifiedName

	def getDescriptor(): String =
		if(isInnerTypeDeclaration)
			return getParentDeclaration.get.getDescriptor + "$" + node.getName.getIdentifier
		else
			"L" + getFullyQualifiedName().replace('.','/')

	def getParentDeclaration(): Option[TypeDeclaration] =
		node.getParent.getDeclaringClass match {
			case t: TypeDeclaration => Some(t)
			case _ => None
		}

	def isInnerTypeDeclaration(): Boolean = {
		getParentDeclaration match {
			case Some(x) => true
			case None => false
		}
	}

}

object RichClass {
	def apply(c: TypeDeclaration) = new RichClass(c)
}