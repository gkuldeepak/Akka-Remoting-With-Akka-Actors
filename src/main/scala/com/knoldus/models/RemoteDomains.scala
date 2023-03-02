package com.knoldus.models

object RemoteDomains {

  case class WorkerNodes(totalWorkers: Int)

  case class Communicate(worker: Int, message: String)

  case class Response(message: String)

}
