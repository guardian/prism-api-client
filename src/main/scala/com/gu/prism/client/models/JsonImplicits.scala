package com.gu.prism.client.models

import play.api.libs.json._
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
import play.api.data.validation.ValidationError
import scala.util.Try

object JsonImplicits {
  implicit val dateTimeFormat = DateFormat
  implicit val metaFormats = Json.format[Meta]
  implicit val instanceFormats = Json.format[Instance]
  implicit val originFormats = Json.format[Origin]
  implicit val sourceFormats = Json.format[Source]
  implicit val dataFormats = Json.format[Data]
  implicit val responseFormats = Json.format[ResponseData]

  object DateFormat extends Format[DateTime] {
    def writes(d: DateTime): JsValue = JsString(d.toString)
    def reads(json: JsValue): JsResult[DateTime] =
    json match {
      case JsString(s) => Try {
        JsSuccess(ISODateTimeFormat.dateTimeParser().parseDateTime(s))
      }.recover {
        case e: IllegalArgumentException =>
          JsError(ValidationError("validate.error.expected.date.isoformat",s))
      }.get
      case other => {
        JsError(ValidationError("validate.error.expeced.date"))
      }
    }

  }
}