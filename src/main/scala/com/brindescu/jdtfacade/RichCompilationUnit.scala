package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom.{ASTNode, CompilationUnit, NodeFinder, TypeDeclaration}

import scala.collection.JavaConversions._

class RichCompilationUnit(private val cu: CompilationUnit) {

	def getDeclaredTypes: List[TypeDeclaration] =
		cu.getStructuralProperty(CompilationUnit.TYPES_PROPERTY).asInstanceOf[java.util.List[_]].iterator
			.map { m => m.asInstanceOf[TypeDeclaration] } toList

	def findNodeAtLine(l: Int): ASTNode = {
		val start = cu.getPosition(l, 0)
		val end = cu.getPosition(l + 1, 0) - 1
		NodeFinder.perform(cu, start, end - start)
	}
}

object RichCompilationUnit {
	def apply(cu: CompilationUnit) = new RichCompilationUnit(cu)
}
