package controllers

import javax.inject.Inject

import auth.AuthAction
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.libs.json.{JsError, Json}
import play.api.mvc.Cookie
import play.api.mvc.DiscardingCookie
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
        db.run(findUser(form.email)).flatMap {
          case Some(_) => Future(BadRequest(Json.obj("message" -> "user has already exist.")))
          case None => db.run(createUser(hashed)).map(x => Ok(Json.obj("created" -> x.toString)).withCookies(Cookie("id", x.toString)))
        }
      }
    )
  }

  def signin = Action.async(parse.json) { implicit rs =>
    rs.body.validate[UserService.UserInfo].fold(
      error => Future(BadRequest(Json.obj("message" -> JsError.toJson(error)))),
      form => {
        db.run(findUser(form.email)).map {
          case Some(user) => {
            if(user.password == sign(form.password)) {
              Ok(Json.obj("created" -> "signed in.")).withCookies(Cookie("id", user.id.toString))
            } else {
              BadRequest(Json.obj("message" -> "invalid pass."))
            }
          }
          case None => BadRequest(Json.obj("message" -> "user not exist."))
        }
      }
    )
  }
  
  def signout = AuthAction { implicit rs =>
    Ok(Json.obj("message" -> "Thank you, come again! by Dr.Apu Nahasapeemapetilon")).discardingCookies(DiscardingCookie("id"))
  }

//  def edit = Action.async(parse.json) { implicit rs =>
//    rs.body.validate[UserService.UserInfo].fold(
//      error => Future(BadRequest(Json.obj("message" -> JsError.toJson(error)))),
//      form => {
//        val (userId, isExist) = for {
//          userId <- form.id
//          isExist <- existUser(userId)
//        } yield (userId, isExist)
//
//        if (isExist) {
//          val hashed = form.copy(password = sign(form.password))
//          db.run(updateUser(userId, hashed)).map(x => Ok(Json.obj("updated" -> x.toString)))
//        } else {
//          Future(BadRequest(Json.obj("message" -> "notFound")))
//        }
//      }
//    )
//  }

}

