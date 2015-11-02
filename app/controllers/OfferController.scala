package controllers

import javax.inject.Inject

import scala.concurrent.Future

import org.slf4j.LoggerFactory
import play.api.db.slick.{HasDatabaseConfigProvider, DatabaseConfigProvider}
import play.api.libs.json.Json
import play.api.mvc.Controller
import play.api.mvc._
import slick.driver.JdbcProfile

import auth.UserAuthAction
import service.OffersetService
import utils.{SystemClock, ExecutionContextProvider}

/**
 * Created by Shunsuke on 2015/10/11.
 */
class OfferController @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends Controller with OffersetService with HasDatabaseConfigProvider[JdbcProfile] with ExecutionContextProvider with SystemClock {

  private val logger = LoggerFactory.getLogger("collector")

  implicit val offersetListFormat = Json.format[OffersetService.DisplayOffersetList]
  implicit val offerFormat = Json.format[OffersetService.DisplayOffer]
  implicit val offersetFormat = Json.format[OffersetService.DisplayOfferset]
  implicit val logOfferInfo = Json.format[OffersetService.LogOfferInfo]


  def list(userId: Int) = UserAuthAction.async { implicit rs =>
    val cookieId = rs.cookies.get("id").map(_.value).getOrElse("nothing")
    if(cookieId.toInt == userId) {
      db.run(selectOffersetList(userId).map(x => Ok(Json.toJson(x))))
    } else {
      Future(Forbidden(Json.obj("message" -> "forbidden")))
    }
  }

  def detail(userId: Int, offersetId: Int) = UserAuthAction.async { implicit rs =>
    val cookieUserId = rs.cookies.get("id").map(_.value).getOrElse("nothing")
    if(cookieUserId.toInt == userId) {
      db.run(selectOfferset(offersetId)) map {
        case Some(offerset) => Ok(Json.toJson(offerset))
        case None => NotFound(Json.obj("message" -> "not exist"))
      }
    } else {
      Future(Forbidden(Json.obj("message" -> "forbidden")))
    }
  }

  def log = Action.async(parse.json) { implicit rs =>
    rs.body.validate[OffersetService.LogOfferInfo].fold(
      error => {
        logger.error("invalid offerLog", rs.body.toString)
        Future(BadRequest(Json.obj("message" -> "invalid logOffer")))
      },
      // TODO JSファイル書き換え検知のためにofferIDとoffersetIDを突き合わせ、マッチしなければログを残してもいいかもしれない
      form => db.run(logOffer(form.offersetId, form.offerId)).map(_ => Ok)
    )
  }
}
