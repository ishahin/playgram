package core

import play.api.libs.json.JsValue

abstract class Meta (val code: Int, val errorType: Option[String], val errorMessage: Option[String])

case class Code (override val code: Int,
                 override val errorType: Option[String] = None,
                 override val errorMessage: Option[String] = None) extends Meta(code, errorType, errorMessage)


case object Success extends Meta(200, None, None)

/**
 * Factory per Meta
 */
object Meta {

  def apply(json: JsValue): Meta = {
    val code = (json \ "meta" \ "code").as[Int]
    code match {
      case 200 => Success
      case n => Code(n, (json \ "meta" \ "error_type").asOpt[String],  (json \ "meta" \ "error_message").asOpt[String])
    }
  }
}

