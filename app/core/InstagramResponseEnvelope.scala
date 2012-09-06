package core

import play.api.libs.json.JsValue

/**
 * Envelope for a generic Instagram response.
 */
trait InstagramResponseEnvelope[T] {

  /**
   * The JSON response obtained from Instagram API
   * @return
   */
  def json: JsValue

  /**
   * Response data
   * @return
   */
  def data: T

  /**
   * Response metadata
   */
  lazy val metadata : Meta = Meta(json)

  /**
   * Pagination (if available)
   */
  lazy val pagination: Option[Pagination] = Pagination(json)

}

