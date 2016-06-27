package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom.{CompilationUnit, TypeDeclaration}

import scala.collection.JavaConversions._

class RichCompilationUnit(private val cu: CompilationUnit) {

	def getDeclaredTypes: List[TypeDeclaration] =
		cu.getStructuralProperty(CompilationUnit.TYPES_PROPERTY).asInstanceOf[java.util.List[_]].iterator
			.map { m => m.asInstanceOf[TypeDeclaration] } toList
}

object RichCompilationUnit {
	def apply(cu: CompilationUnit) = new RichCompilationUnit(cu)
}
