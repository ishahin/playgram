package model.authentication

import play.api.libs.json.{JsValue, Reads}


case class InstagramUser(id: String,
                         username: String,
                         fullName: String,
                         profilePicture: String,
                         bio: Option[String],
                         website: Option[String])




object InstagramUser {

  implicit def instagramUserReads: Reads[InstagramUser] = new Reads[InstagramUser] {
    def reads(json: JsValue) = {
      val userJson = json \ "user"
      InstagramUser(id = (userJson \ "id").as[String],
        username = (userJson \ "username").as[String],
        fullName = (userJson \ "full_name").as[String],
        profilePicture = (userJson \ "profile_picture").as[String],
        bio = (userJson \ "bio").asOpt[String],
        website = (userJson \ "website").asOpt[String])
    }
  }

  implicit def jsonToInstagramUser(json: JsValue) = instagramUserReads.reads(json)


}