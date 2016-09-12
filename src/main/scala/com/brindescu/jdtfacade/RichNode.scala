package com.brindescu.jdtfacade

import com.brindescu.jdtfacade.Facade._
import org.eclipse.jdt.core.dom._

import scala.collection.JavaConversions._

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

	def getChildren(): List[ASTNode] = {
		node.structuralPropertiesForType().map{_ match {
			case c: ChildPropertyDescriptor => node.getStructuralProperty(c).asInstanceOf[ASTNode] match {
				case p if p!=null => List(p)
				case _ => List()
			}
			case l: ChildListPropertyDescriptor => node.getStructuralProperty(l).asInstanceOf[java.util.List[Any]].map { _.asInstanceOf[ASTNode] }
			case _ => List()
		}}.flatten.toList
	}
}

object RichNode {
	def apply(node: ASTNode) = new RichNode(node)
}

