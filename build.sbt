name := "Akka-Remoting-With-Akka-Actors"

version := "0.1"

scalaVersion := "2.12.8"

lazy val akkaVersion = "2.5.21"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion)