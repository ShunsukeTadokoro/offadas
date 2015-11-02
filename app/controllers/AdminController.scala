package controllers

import javax.inject.Inject

import auth.AdminAuthAction
import models.AdminType
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import service.AdminService
import service.AdminService.DisplayAdmin
import slick.driver.JdbcProfile
import utils.{SystemClock, ExecutionContextProvider}

import scala.concurrent.{Future, Await}
import scala.concurrent.duration.Duration

/**
 * @author Shunsuke Tadokoro
 */
class AdminController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends Controller with AdminService with HasDatabaseConfigProvider[JdbcProfile] with ExecutionContextProvider with SystemClock {

  implicit val adminInfoFormat = Json.format[AdminService.AdminInfo]
  implicit val displayAdminFormat = Json.format[DisplayAdmin]

  def list = AdminAuthAction.async { implicit rs =>
    val adminId = rs.cookies.get("adminId").map(_.value).getOrElse("-1")
    Await.result(db.run(findAdmin(adminId.toInt)), Duration.Inf) match {
      case None => Future(BadRequest(Json.obj("message" -> "inaiyo")))
      case Some(admin) => {
        if(admin.authority == AdminType.Master.code) {
          db.run(listAdmin).map(x => Ok(Json.toJson(x)))
        } else {
          Future(Forbidden(Json.obj("message" -> "damedayo")))
        }
      }
    }
  }

//  def login = Action.async { implicit rs =>
//    val email = rs.body \ "email".as[String]
//    Await.result(db.run(findAdmin()))
//  }

}
