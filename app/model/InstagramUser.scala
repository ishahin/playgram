package model

import org.squeryl._


/**
 * Instagram user
 * @param id
 * @param instagramId
 * @param email
 */
case class InstagramUser(id: Long, instagramId: String, email: String) extends KeyedEntity[Long]



