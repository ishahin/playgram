package domain.authentication.adapter

import securesocial.core.{UserId, Identity, UserServicePlugin}

import play.{Application => PlayApp}
import securesocial.core.providers.Token


class SecureSocialAuthenticator(app: PlayApp) extends UserServicePlugin(app) {

  def find(id: UserId): Option[Identity] = ???

  def findByEmailAndProvider(email: String, providerId: String): Option[Identity] = ???

  def save(user: Identity): Identity = ???

  def save(token: Token) {}

  def findToken(token: String): Option[Token] = ???

  def deleteToken(uuid: String) {}

  def deleteExpiredTokens() {}
}
