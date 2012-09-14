package controllers

import play.api._
import libs.ws.WS
import play.api.mvc._

import play.api.Play._
import java.net.URLEncoder
import model.InstagramClientInfo
import core.InstagramResponse
import model.authentication.{AccessToken, InstagramUser}


object Authentication extends Controller {


  val clientInfo: InstagramClientInfo = InstagramClientInfo.apply.getOrElse(throw new RuntimeException("Client info are missing or incomplete"))

  //TODO temp hack
  def redirect_uri: String = URLEncoder.encode("http://localhost:9000/authenticate/2", "UTF8")


  private def instagramAuthorizeUrl(uri:String) =  {
    "https://api.instagram.com/oauth/authorize/?client_id="+ clientInfo.clientId +"&response_type=code&redirect_uri="+uri
  }

  private val log = Logger("Auhentication")

  def oauthPhase1 = Action {  request =>
    log.debug("Starting OAUTH2 authentication - redirect to Instagram")
    Redirect(instagramAuthorizeUrl(redirect_uri))
  }

  def oauthPhase2 = Action { request =>

    val instagramCode = request.queryString("code")

    instagramCode match {
      case s if s.length == 1 => {
          retrieveAuthToken(s(0))
      }
      case Nil => Forbidden
    }
  }

  private def retrieveAuthToken[A](code: String)= {
    log.debug("OAUTH2 - received code " + code + " - initiating phase 2 to get access_token")
    val promise = WS.url("https://api.instagram.com/oauth/access_token").post {
      Map("client_id" -> Seq(clientInfo.clientId),
        "client_secret" -> Seq(clientInfo.clientSecret),
        "code" -> Seq(code),
        "grant_type" -> Seq("authorization_code"),
        "redirect_uri" -> Seq(redirect_uri)
      )
    }

    promise.await.fold(
      exception => {
        log.error("Instagram authentication failed", exception)
        Forbidden
      },
      response => {
        log.info("Instagram authentication succeeded")
        val user: InstagramUser = response.json
        Ok(user.toString)
      }
    )
  }
}



