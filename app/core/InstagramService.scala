package core

/**
 * Created with IntelliJ IDEA.
 * User: mmazzarolo
 * Date: 9/7/12
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */
trait InstagramService {

  def apply(request: InstagramRequest): InstagramResponse

  def apply[A,B](value: A)(implicit req: RequestConverter[A], res:ResponseConverter[B]): B = res(apply(req(value)))

}
