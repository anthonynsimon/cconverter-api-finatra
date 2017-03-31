name := "cconverter-api-finatra"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
	"com.twitter" %% "finatra-http" % "2.9.0",
	"ch.qos.logback" % "logback-classic" % "1.2.2"
)

mainClass in assembly := Some("com.anthonynsimon.cconverter.api.CConverterServerMain")

// META-INF discarding
assemblyMergeStrategy in assembly := {
	case PathList("META-INF", xs@_*) => MergeStrategy.discard
	case x => MergeStrategy.first
}