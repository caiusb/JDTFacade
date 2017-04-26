package com.brindescu.jdtfacade

import com.brindescu.jdtfacade.Facade._
import org.eclipse.jdt.core.dom._

import scala.collection.JavaConversions._

class RichNode(protected[jdtfacade] val node: ASTNode) {

	def getDeclaringMethod(): Option[MethodDeclaration] =
		node match {
			case m: MethodDeclaration => Some(m)
			case null => None
			case n: ASTNode => n.getParent.getDeclaringMethod
		}

	def getDeclaringClass(): Option[TypeDeclaration] =
		node match {
			case t: TypeDeclaration => Some(t)
			case a: AnonymousClassDeclaration => None //TODO: deal with this
			case null => None
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

	def getStartLineNumber(): Int =
		node.getRoot.asInstanceOf[CompilationUnit].getLineNumber(node.getStartPosition)

	def getEndLineNumber(): Int =
		node.getRoot.asInstanceOf[CompilationUnit].getLineNumber(node.getStartPosition + node.getLength - 1)

	def getLineRange(): Range = Range(getStartLineNumber, getEndLineNumber).inclusive
}

object RichNode {
	def apply(node: ASTNode) = new RichNode(node)
}

