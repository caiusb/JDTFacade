package com.brindescu.jdtfacade

import com.brindescu.jdtfacade.Facade._
import org.eclipse.jdt.core.dom.{ASTNode, AnonymousClassDeclaration, MethodDeclaration, TypeDeclaration}

class RichNode(private val node: ASTNode) {

	def getDeclaringMethod(): Option[MethodDeclaration] =
		node match {
			case m: MethodDeclaration => Some(m)
			case null => None
			case n: ASTNode => n.getParent.getDeclaringMethod
		}

	def getDeclaringClass(): TypeDeclaration =
		node match {
			case t: TypeDeclaration => t
			case a: AnonymousClassDeclaration => null //TODO: deal with this
			case null => null
			case n: ASTNode => n.getParent.getDeclaringClass
		}
}

object RichNode {
	def apply(node: ASTNode) = new RichNode(node)
}

