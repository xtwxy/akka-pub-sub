import scalapb.compiler.Version.scalapbVersion

name := "message"
organization := "com.apuex"
version := "1.0.0"

scalaVersion := "2.12.8"

lazy val akkaVersion = "2.5.21"

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

libraryDependencies ++= Seq(
  "com.thesamet.scalapb"      %% "compilerplugin"                       % scalapbVersion,
  "com.thesamet.scalapb"      %% "scalapb-runtime"                      % scalapbVersion   % "protobuf"
)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)


