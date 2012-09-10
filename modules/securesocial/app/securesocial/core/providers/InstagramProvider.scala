package securesocial.core.providers

import play.api._
import libs.concurrent.Promise
import libs.ws.{Response, WS}
import play.api.Play._
import securesocial.core.{AuthenticationException, SocialUser, OAuth2Provider}
import java.util.concurrent.TimeUnit

class InstagramProvider(app: Application) extends OAuth2Provider(app) {

  /**
   * Subclasses need to implement this to specify the provider type
   * @return
   */
  def providerId = InstagramProvider.Instagram

  /**
   * Subclasses need to implement this method to populate the User object with profile
   * information from the service provider.
   *
   * @param user The user object to be populated
   * @return A copy of the user object with the new values set
   */
  def fillProfile(user: SocialUser) = {
    val oAuthInfo = user.oAuth2Info.getOrElse(throw new AuthenticationException)

    // TODO chiamata asincrona all'Endpoints "Users" ->
    val holder = WS.url(InstagramProvider.MySelf).withQueryString(("access_token" -> oAuthInfo.accessToken))
    val response: Promise[Response] = holder.get()

    val fetchTimeOut = configuration.getInt("instagram.fetch.timeout").getOrElse(5000)

    response.await(fetchTimeOut, TimeUnit.SECONDS).fold(ex => {
      throw ex
    }, res => {
      val dataJson = res.json \ "data"
      val fullUserId = user.id.copy(id = (dataJson \ "id").as[String])
      user.copy(id = fullUserId, displayName = (dataJson \ "full_name").as[String], avatarUrl = (dataJson \ "profile_picture").asOpt[String])
    }
    )
  }
}

object InstagramProvider {

  val Instagram = "instagram"

  val MySelf = "https://api.instagram.com/v1/users/self"

}