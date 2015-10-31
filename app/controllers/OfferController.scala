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

/**
 * Created by Shunsuke on 2015/10/11.
 */
class OfferController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends Controller with OffersetService with HasDatabaseConfigProvider[JdbcProfile] with ExecutionContextProvider {

  implicit val offerFormat = Json.format[OffersetService.DisplayOffer]
  implicit val offersetFormat = Json.format[OffersetService.DisplayOfferSet]


  def list(userId: Int) = AuthAction.async { implicit rs =>
    db.run(selectOffer(userId).map(x => Ok(Json.toJson(x))))
  }
}
