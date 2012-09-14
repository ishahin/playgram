package core

import play.api.Application

trait ConfiguredByPlay[T] {

  def configuration(implicit app: Application) = app.configuration

  def apply(implicit app: Application): Option[T]

}
