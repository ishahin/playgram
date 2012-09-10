package core

import play.api.libs.json.JsValue

/**
 * Envelope for a generic Instagram response.
 */
case class InstagramResponse(json: JsValue) {

  /**
   * Response metadata
   */
  val metadata: Meta = Meta(json)

  /**
   * Pagination (if available)
   */
  val pagination: Option[Pagination] = Pagination(json)

}

trait ResponseConverter[T] extends ((InstagramResponse) => T)

