package com.gu.prism.client

import scala.concurrent.{ExecutionContext, Future}
import org.joda.time.LocalDate
import play.api.libs.json.{JsError, JsSuccess, Reads, Json}

case class StatusError(statusCode: Int, statusLine: String)
  extends Exception(s"Expected 200 OK, got $statusCode $statusLine")
case class ParseError(cause: JsError)
  extends Exception("Unable to parse JSON returned by Prism API")

case class ApiClient(apiKey: String, http: Http, apiEndpoint: String) {
  /** Extracts A from the JSON string or throws ParseError */
  private def extract[A: Reads](input: String): A =
    Json.fromJson[A](Json.parse(input)) match {
      case JsSuccess(a, _) => a
      case error @ JsError(_) => throw new ParseError(error)
    }
  private def extract[A: Reads](response: Response): A = response match {
    case Response(200, body, _) => extract[A](body)
    case Response(errorCode, _, errorLine) => throw new StatusError(errorCode, errorLine)
  }
}