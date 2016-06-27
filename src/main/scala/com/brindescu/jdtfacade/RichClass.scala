package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom.TypeDeclaration

class RichClass(private val node: TypeDeclaration) {
}

object RichClass {
	def apply(c: TypeDeclaration) = new RichClass(c)
}