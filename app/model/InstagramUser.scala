package model

import org.squeryl._


/**
 * Instagram InstagramUser
 * @param id
 * @param instagramId
 * @param email
 */
case class InstagramUser(id: Long, instagramId: String, email: String) extends KeyedEntity[Long]



