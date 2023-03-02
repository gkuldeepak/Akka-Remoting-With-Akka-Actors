package com.knoldus.nodes

import akka.actor.{ActorSystem, Props}
import com.knoldus.clusters.WorkerCluster
import com.typesafe.config.ConfigFactory

import scala.language.postfixOps

object WorkerNode extends App {

  val config = ConfigFactory.parseString(
    """
      |akka.remote.artery.canonical.port = 2553
      |""".stripMargin
  ).withFallback(ConfigFactory.load("application.conf"))

  val actorSystem = ActorSystem("WorkerSystem", config)
  (1 to 5).map(i => actorSystem.actorOf(Props[WorkerCluster], s"worker${i}"))


}
