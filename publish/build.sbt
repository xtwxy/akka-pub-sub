import sbtassembly.MergeStrategy

name := "publish"
organization := "com.apuex"
version := "1.0.0"

scalaVersion := "2.12.8"

lazy val akkaVersion = "2.5.21"
lazy val akkaHttpVersion = "10.1.7"

publishTo := Some(Resolver.file("file", new File(Path.userHome.absolutePath + "/.m2/repository")))

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-persistence" % akkaVersion,
  "org.iq80.leveldb" % "leveldb" % "0.7",
  "org.fusesource.leveldbjni" % "leveldbjni-all" % "1.8",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.slf4j" % "slf4j-jdk14" % "1.7.26", // java.util.logging works mostly out-of-the-box with SubstrateVM
  // required for substitutions
  // make sure the version matches GraalVM version used to run native-image
  "com.oracle.substratevm" % "svm" % "1.0.0-rc15" % Provided,
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
)

enablePlugins(GraalVMNativeImagePlugin)

graalVMNativeImageOptions ++= Seq(
  "-H:IncludeResources=.*conf",
  "-H:IncludeResources=.*\\.properties",
  "-H:ReflectionConfigurationFiles=" + baseDirectory.value / "graal" / "reflectconf-akka.json",
  "-H:ReflectionConfigurationFiles=" + baseDirectory.value / "graal" / "reflectconf-jul.json",
  "--enable-url-protocols=https,http",
  "-H:+ReportUnsupportedElementsAtRuntime",
  "--rerun-class-initialization-at-runtime=" +
    "com.typesafe.config.impl.ConfigImpl$EnvVariablesHolder," +
    "com.typesafe.config.impl.ConfigImpl$SystemPropertiesHolder"
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.rename
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("publish.Main")
assemblyJarName in assembly := "publish.jar"

