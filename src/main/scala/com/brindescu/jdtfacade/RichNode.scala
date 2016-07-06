package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom.{ASTNode, AnonymousClassDeclaration, MethodDeclaration, TypeDeclaration}
import Facade._

class RichNode(private val node: ASTNode) {

	def getDeclaringMethod(): MethodDeclaration =
		node match {
			case m: MethodDeclaration => m
			case null => null
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

