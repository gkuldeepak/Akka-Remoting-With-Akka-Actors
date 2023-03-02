package com.knoldus.clusters

import akka.actor.{Actor, ActorIdentity, ActorLogging, Identify}
import akka.util.Timeout
import com.knoldus.models.RemoteDomains.{Communicate, WorkerNodes}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps
import scala.util.Random

class MasterCluster extends Actor with ActorLogging {

  val random = new Random()
  implicit val timeout = Timeout(10 seconds)

  override def receive: Receive = {

    case WorkerNodes(nWorkers) =>
      log.info("[INITIALIZING] Master Node !!!")
      val workerActor = (1 to nWorkers).map(workerId => context.actorSelection(s"akka://WorkerSystem@localhost:2553/user/worker${workerId}"))
      workerActor.foreach(_ ! Identify("CONNECTED"))

    case ActorIdentity("CONNECTED", Some(actorRef)) =>
      log.info(s"[INFO] Node Connected! with address ${actorRef}")

    case Communicate(worker: Int, message: String) =>
      log.info(s"[WARN] sending connection request to worker ${worker}")
      val actor = context.actorSelection(s"akka://WorkerSystem@localhost:2553/user/worker${worker}")
      actor.resolveOne.map(_ ! Communicate(worker, message))

  }

}
