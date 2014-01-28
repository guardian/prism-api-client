package com.gu.prism.client

import scala.concurrent.Future

case class Response(statusCode: Int, statusLine: String, body: String)

trait Http {
  def get(uri: String): Future[Response]
}