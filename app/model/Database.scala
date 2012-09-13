package model

import org.squeryl._

/**
 * Schema definition with Squeryl
 */
object Database extends Schema {

  val userTable: Table[InstagramUser] = table("instagram_user")

}
