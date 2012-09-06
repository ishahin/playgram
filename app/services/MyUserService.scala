package services

import collection._

import securesocial.core.{UserId, SocialUser, UserService}

/**
 * In-memory implementation of UserService
 */
class MyUserService extends UserService {

  private val users: mutable.Map[UserId, SocialUser] = mutable.Map.empty

  /**
   * Finds a SocialUser that maches the specified id
   *
   * @param id the user id
   * @return an optional user
   */
  def find(id: UserId) = users.get(id)

  /**
   * Saves the user.  This method gets called when a user logs in.
   * This is your chance to save the user information in your backing store.
   * @param user
   */
  def save(user: SocialUser) {
    users.put(user.id, user)
  }
}
