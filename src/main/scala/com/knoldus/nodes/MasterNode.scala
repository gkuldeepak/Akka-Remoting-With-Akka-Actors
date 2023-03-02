package com.knoldus.nodes

import akka.actor.{ActorSystem, Props}
import com.knoldus.clusters.MasterCluster
import com.knoldus.models.RemoteDomains.{Communicate, WorkerNodes}
import com.typesafe.config.ConfigFactory

object MasterNode extends App {

  val config = ConfigFactory.parseString(
    """
      |akka.remote.artery.canonical.port = 2554
      |""".stripMargin
  ).withFallback(ConfigFactory.load("application.conf"))

  val masterSystem = ActorSystem("MasterSystem", config)
  val masterActor = masterSystem.actorOf(Props[MasterCluster], "MasterActor")

  masterActor ! WorkerNodes(5)
  Thread.sleep(5000)
  masterActor ! Communicate(3, "This is trial message!")

}
