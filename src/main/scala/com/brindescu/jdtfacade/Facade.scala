package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom._

object Facade {

	implicit def wrapCompilationUnit(cu: CompilationUnit) = RichCompilationUnit(cu)
	implicit def wrapClass(c: TypeDeclaration) = RichClass(c)
	implicit def wrapMethod(m: MethodDeclaration) = RichMethod(m)
	implicit def wrapType(t: Type) = RichType(t)
	implicit def wrapPrimitiveType(t: PrimitiveType) = RichPrimitiveType(t)
	implicit def wrapParameterizedType(t: ParameterizedType) = RichParameterizedType(t)
	implicit def wrapNode(n: ASTNode) = RichNode(n)
}