package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom.TypeDeclaration

class RichClass(private val node: TypeDeclaration) {

	def getFullyQualifiedName(): String =
		node.resolveBinding.getQualifiedName

	def getDescriptor(): String =
		"L" + getFullyQualifiedName().replace('.','/')
}

object RichClass {
	def apply(c: TypeDeclaration) = new RichClass(c)
}