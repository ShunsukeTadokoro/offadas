package controllers

import javax.inject.Inject

import auth.AuthAction
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.mvc.Controller
import service.OffersetService
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import slick.driver.JdbcProfile
import utils.ExecutionContextProvider

import scala.concurrent.Future

/**
 * Created by Shunsuke on 2015/10/11.
 */
class OfferController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends Controller with OffersetService with HasDatabaseConfigProvider[JdbcProfile] with ExecutionContextProvider {

  implicit val offerFormat = Json.format[OffersetService.DisplayOffer]
  implicit val offersetFormat = Json.format[OffersetService.DisplayOfferSet]


  def list(userId: Int) = AuthAction.async { implicit rs =>
    val cookieId = rs.cookies.get("id").map(_.value).getOrElse("nothing")
    if(cookieId.toInt == userId) {
      db.run(selectOffersetList(userId).map(x => Ok(Json.toJson(x))))
    } else {
      Future(Forbidden(Json.obj("message" -> "forbidden")))
    }

  }

  def detail(userId: Int, offersetId: Int) = AuthAction.async { implicit rs =>
    val cookieId = rs.cookies.get("id").map(_.value).getOrElse("nothing")
    if(cookieId.toInt == userId) {
      db.run(selectOfferset(offersetId)) map {
        case Some(offerset) => Ok(Json.toJson(offerset))
        case None => NotFound(Json.obj("message" -> "not exist"))
      }
    } else {
      Future(Forbidden(Json.obj("message" -> "forbidden")))
    }
  }
}
