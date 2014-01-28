package com.gu.prism.client

import org.specs2.mutable.Specification
import play.api.libs.json.{JsSuccess, Json}
import lib.ResourcesHelper
import models.JsonImplicits._
import com.gu.prism.client.models._

class JsonModelsSpec extends Specification with ResourcesHelper {
  "Json.parse" should {
    "be able to parse a response for instances endpoint" in {
      val resource = slurp("instances.json")
        .getOrElse(throw new RuntimeException("Could not find test resource instances.json"))
      val JsSuccess(response, _) = Json.fromJson[ResponseData](Json.parse(resource))
      response.status mustEqual "success"
      response.stale mustEqual false
      val instances = response.data.instances
      instances.length mustEqual 1
      response.sources.length mustEqual 2
    }
  }
}
