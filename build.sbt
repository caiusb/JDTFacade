import sbt._

name := "JDTFacade"

organization := "com.brindescu"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
	"org.eclipse.jdt" % "org.eclipse.jdt.core" % "3.10.0"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.+" % "test"
