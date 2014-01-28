package com.gu.prism.client.models

import play.api.libs.json._

object JsonImplicits {
  implicit val metaFormats = Json.format[Meta]
  implicit val instanceFormats = Json.format[Instance]
  implicit val originFormats = Json.format[Origin]
  implicit val sourceFormats = Json.format[Source]
  implicit val dataFormats = Json.format[Data]
  implicit val responseFormats = Json.format[ResponseData]
}