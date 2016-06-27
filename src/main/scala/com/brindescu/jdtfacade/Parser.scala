package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom.{AST, ASTParser, CompilationUnit}

import scala.io.{BufferedSource, Source}

object Parser {

	def parse(filePath: String): CompilationUnit = {
		val parser = ASTParser.newParser(AST.JLS8)
		parser.setKind(ASTParser.K_COMPILATION_UNIT)
		val file	 = Source.fromFile(filePath)
		parser.setSource(try file.getLines.mkString.toCharArray finally file.close)
		parser.createAST(null).asInstanceOf[CompilationUnit]
	}

}
