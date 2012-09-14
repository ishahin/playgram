package core

import play.api.libs.ws.{Response, WS}
import play.api.libs.concurrent.Promise
import play.api.libs.json.{Reads, JsValue}

/**
 * Created with IntelliJ IDEA.
 * User: mmazzarolo
 * Date: 9/6/12
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
trait InstagramRequest {

  def response: Promise[Response]

  def instagramResponse: Promise[InstagramResponse] = response map { r => InstagramResponse(r.json) }

  def as[A](implicit reader:Reads[A]): Promise[A] = instagramResponse map { r => reader.reads(r.json)}

}




abstract class InstagramGetRequest(val url: String, parameters:(String, String)* ) extends InstagramRequest {
  def response = WS.url(url).withQueryString(parameters:_*).get()
}
