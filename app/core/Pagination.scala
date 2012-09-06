package core

import java.net.URL
import play.api.libs.json.JsValue

/**
 * Pagination info
 */
case class Pagination(nextUrl: URL, nextMaxId: Option[Int])

object Pagination {

  def apply(json: JsValue): Option[Pagination] = {
    val pag = (json \ "pagination").asOpt[JsValue]
    for ( jsonPag <- pag)
      yield new Pagination(new URL((jsonPag \ "next_url").as[String]), (jsonPag \ "next_max_id").asOpt[Int])

  }
}