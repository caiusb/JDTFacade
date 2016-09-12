package com.brindescu.jdtfacade

import org.scalatest.{FlatSpec, Matchers}
import Facade._
import org.eclipse.jdt.core.dom.ASTNode

class RichNodeTest extends FlatSpec with Matchers {

	it should "have the corrent children" in {
		val cu = Parser.parse("public class A{}".toCharArray)
		val children = cu.getChildren
		children should not be null
		children.size should be (1)
	}

	it should "work for multiple types" in {
		var cu = Parser.parse("public class A{public void m(){int x=3;}}".toCharArray)
		cu.getDeclaredTypes.head.getMethods.head.getChildren() should have size 4
	}

}
