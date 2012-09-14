package model.authentication

import java.util.Date
import play.api.libs.json.JsValue


case class AccessToken(id:Long,
                       token: String,
                       creation:Date,
                       lastUsage: Option[Date] = None
                       )  {

  def this(token: String) = this(0L, token, new Date)

}

object AccessToken {

  def apply(json: JsValue): AccessToken = new AccessToken((json \ "access_token").as[String])
}


