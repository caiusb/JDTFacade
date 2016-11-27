package com.brindescu.jdtfacade

import org.eclipse.jdt.core.dom._

import scala.collection.mutable
import scala.io.Source

object Parser {

	def parse(contents: Array[Char]): CompilationUnit = {
		val parser = ASTParser.newParser(AST.JLS8)
		parser.setKind(ASTParser.K_COMPILATION_UNIT)
		parser.setSource(contents)
		parser.createAST(null).asInstanceOf[CompilationUnit]
	}

	def parse(filePath: String): CompilationUnit = {
		val file = Source.fromFile(filePath)
		parse(try file.getLines.mkString("\n").toCharArray finally file.close)
	}

	def parse(files: List[String], sources: List[String], jarDeps: List[String]): Map[String, CompilationUnit] = {
		val parser = ASTParser.newParser(AST.JLS8)
		parser.setKind(ASTParser.K_COMPILATION_UNIT)
		parser.setBindingsRecovery(true)
		parser.setResolveBindings(true)
		parser.setEnvironment(jarDeps.toArray, sources.toArray, null, false)

		val asts = new mutable.HashMap[String, CompilationUnit]()

		val requestor = new FileASTRequestor {
			override def acceptAST(src: String, ast: CompilationUnit) =
				asts.put(src, ast)
		}

		parser.createASTs(files.toArray, null, Seq[String]().toArray, requestor, null)

		return asts.toMap
	}

	def parseStatements(statements: String): Block = {
		val parser = ASTParser.newParser(AST.JLS8)
		parser.setKind(ASTParser.K_STATEMENTS)
		parser.setSource(statements.toCharArray)
		parser.createAST(null).asInstanceOf[Block]
	}

}
