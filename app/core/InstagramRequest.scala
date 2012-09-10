package core

import play.api.libs.ws.{Response, WS}
import play.api.libs.concurrent.Promise
import java.util.concurrent.TimeUnit

/**
 * Created with IntelliJ IDEA.
 * User: mmazzarolo
 * Date: 9/6/12
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
trait InstagramRequest {

  def buildWsRequest: WS.WSRequestHolder

  def call: (WS.WSRequestHolder) => Promise[Response]

  def promise: Promise[Response] =  call(buildWsRequest)

  def timeout: Int

  def timeUnit: TimeUnit = TimeUnit.SECONDS

}


abstract class InstagramGetRequest(val url: String, parameters:(String, String)* ) extends InstagramRequest {

  val buildWsRequest = WS.url(url).withQueryString(parameters:_*)

  val call: (WS.WSRequestHolder) => Promise[Response] = {wsr => wsr.get()}

}

trait RequestConverter[T] extends ((T)=>InstagramRequest)
