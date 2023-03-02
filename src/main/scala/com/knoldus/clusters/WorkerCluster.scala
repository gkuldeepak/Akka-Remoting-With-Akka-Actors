package com.knoldus.clusters

import akka.actor.{Actor, ActorLogging}
import com.knoldus.models.RemoteDomains.{Communicate, Response}

class WorkerCluster extends Actor with ActorLogging {

  override def receive: Receive = {

    case Communicate(worker: Int, message: String) =>
      log.info(s"[INFO] Worker ${worker} received message -> ${message} from ${sender().path}")
      sender() ! Response("Worker Received the Connection")

    case _ => "Unknown Request !!"

  }

}
