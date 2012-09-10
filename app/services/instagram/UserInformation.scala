package services.instagram

import core.{InstagramGetRequest, InstagramRequest, InstagramService}
import securesocial.core.UserId
import play.api.libs.json.{JsValue, Reads}

/**
 * Created with IntelliJ IDEA.
 * User: mmazzarolo
 * Date: 9/10/12
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
trait UserInformationService  {

  /**
   *
   * @param userId
   * @param timeout
   */
  case class UserRequest(userId: UserId, timeout: Int = 10) extends InstagramGetRequest("/users/"+userId.id)
}


