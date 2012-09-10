package core

import play.api.libs.json.JsValue
import play.api.mvc.WebSocket
import play.api.libs.ws.{Response, WS}
import collection._
import play.api.libs.concurrent.{NotWaiting, Promise}
import java.util.concurrent.TimeUnit
import org.specs2.time.Time
import org.squeryl.dsl.ast.PrefixOperatorNode
import org.springframework.beans.NotWritablePropertyException

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
