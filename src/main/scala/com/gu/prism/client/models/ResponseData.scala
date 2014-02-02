package com.gu.prism.client.models

import org.joda.time.DateTime

case class Meta(href: String)

case class Instance(id: String, meta: Meta)

case class Origin(vendor: String, account: String)

case class Source(resource: String,
                  status: String,
                  origin: Origin,
                  createdAt: DateTime,
                  age: Int,
                  stale: Boolean)

case class Data(instances: List[Instance])

case class ResponseData(status: String,
                    lastUpdated: DateTime,
                    stale: Boolean,
                    staleSources: List[String],
                    data: Data,
                    sources: List[Source])