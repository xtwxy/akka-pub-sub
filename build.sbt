import sbtassembly.MergeStrategy

lazy val akkaVersion = "2.5.21"
lazy val akkaHttpVersion = "10.1.7"
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.apuex",
      scalaVersion := "2.12.8",
      version      := "1.0.0"
    )),
    name := "scala",
    libraryDependencies ++= Seq(
      "com.trueaccord.scalapb" %% "compilerplugin" % "0.6.2",
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
    )
  )
  .aggregate(
    message,
    publish,
    subscribe,
    scheduler
  )

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

enablePlugins(JavaAppPackaging)
enablePlugins(JavaServerAppPackaging)
scriptClasspath +="../conf"

parallelExecution in Test := false

fork := true

lazy val message = (project in file("message"))
lazy val publish = (project in file("publish"))
    .enablePlugins(JavaServerAppPackaging)
    .enablePlugins(GraalVMNativeImagePlugin)
    .dependsOn(message)
lazy val subscribe = (project in file("subscribe"))
    .enablePlugins(GraalVMNativeImagePlugin)
    .dependsOn(message, publish)
lazy val scheduler = (project in file("scheduler"))


