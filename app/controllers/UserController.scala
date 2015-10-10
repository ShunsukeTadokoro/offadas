package controllers

import javax.inject.Inject

import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import play.api.libs.Crypto._
import service.UserService

import slick.driver.JdbcProfile

import utils.{SystemClock, ExecutionContextProvider}

import scala.concurrent.Future

/**
 * Created by Shunsuke on 2015/10/04.
 */
class UserController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends Controller with UserService with HasDatabaseConfigProvider[JdbcProfile] with ExecutionContextProvider with SystemClock {

  implicit val displayUserFormat = Json.format[UserService.DisplayUser]
  implicit val createUserFormat  = Json.format[UserService.UserInfo]

  def show(userId: Int) = Action.async { implicit rs =>
    db.run(findUser(userId)).map {
      case Some(user) => Ok(Json.toJson(user))
      case None       => NotFound(Json.obj("error" -> "notFound"))
    }
  }

  def create = Action.async(parse.json) { implicit rs =>
    rs.body.validate[UserService.UserInfo].fold(
      error => Future(BadRequest(Json.obj("message" -> JsError.toJson(error)))),
      form  => {
        val hashed = form.copy(password = sign(form.password))
        db.run(createUser(hashed)).map(x => Ok(Json.obj("created" -> x.toString)))
      }
    )
  }

}

