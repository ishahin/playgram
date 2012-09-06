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

    // TODO con questo devi accedere alle info utente di Instagram
    oAuthInfo.accessToken

    // TODO chiamata asincrona all'Endpoints "Users" ->
    val response: Promise[Response] = WS.url(InstagramProvider.Users + user.id.id).withQueryString(("access_token" -> oAuthInfo.accessToken)).get()


    val fetchTimeOut = configuration.getInt("instagram.fetch.timeout").getOrElse(5000)

    response.map(_.json).await(fetchTimeOut, TimeUnit.SECONDS).fold(ex => {
      throw ex
    }, res => {

      println(res.toString())
    }
    )

    //TODO ... e alla fine ritorna un SocialUser
    user
  }
}

object InstagramProvider {

  val  Instagram = "instagram"

  val Users = "https://api.instagram.com/v1/users/"

}