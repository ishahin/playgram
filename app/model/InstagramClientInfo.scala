package model

import play.api.Application
import core.ConfiguredByPlay

/**
 *
 */
case class InstagramClientInfo(clientId: String, clientSecret:String)

object InstagramClientInfo extends ConfiguredByPlay[InstagramClientInfo] {

  def apply(implicit app: Application): Option[InstagramClientInfo] = for {
    clientId <- configuration.getString("instagram.clientId")
    clientSecret <- configuration.getString("instagram.clientSecret")
  } yield new InstagramClientInfo(clientId, clientSecret)

}




