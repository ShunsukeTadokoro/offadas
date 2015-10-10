package controllers

import javax.inject.Inject

import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.mvc.Controller
import service.OffersetService
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import models.Tables._
import slick.driver.JdbcProfile
import utils.ExecutionContextProvider

/**
 * Created by Shunsuke on 2015/10/11.
 */
class OfferController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends Controller with OffersetService with HasDatabaseConfigProvider[JdbcProfile] with ExecutionContextProvider {

  implicit val offersetFormat = Json.format[OffersetService.DisplayOfferSet]

  def list(userId: Int) = Action.async { implicit rs =>
    val action = db.run(listOfferSet(userId))
    action.map(x => Ok(Json.toJson(x)))
  }
}
