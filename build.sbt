import sbt._

name := "JDTFacade"

organization := "com.brindescu"

version := "0.10.0"

scalaVersion := "2.12.0"

crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.0")

libraryDependencies ++= Seq(
	"org.eclipse.jdt" % "org.eclipse.jdt.core" % "3.10.0"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.+" % "test"

publishTo := {
	val prefix = if (isSnapshot.value)
		"snapshots"
	else
		"releases"

	Some("Website" at "s3://" + prefix + ".ivy.brindescu.com")
}
