package service

import service.OffersetService.{DisplayOffer, DisplayOfferSet}
import slick.dbio.DBIO
import models.Tables._
import profile.api._
import utils.ExecutionContextProvider

import scala.concurrent.ExecutionContext

/**
 * Created by Shunsuke on 2015/10/11.
 */
trait OffersetService {
  self: ExecutionContextProvider =>

  def selectOfferSetId(userId: Int): DBIO[Seq[Int]] = {
    Offerset.filter(_.userId === userId.bind).map(_.id).result
  }

  def selectOfferSet(offerId: Int): DBIO[Seq[OffersetRow]] = {
    Offerset.filter(_.id === offerId.bind).result
  }

  def selectOffer(offersetId: Int): DBIO[Seq[OfferRow]] = {
    Offer.filter(_.id === offersetId.bind).result
  }

  def ex(userId: Int): DBIO[Seq[(OffersetRow, OfferRow)]] = {
    (Offerset.filter(_.userId === userId.bind) join Offer on(_.id === _.offersetId)).result
  }

}

object OffersetService {

  case class DisplayOfferSet(name: String, status: String, offers: Seq[DisplayOffer])

  case class DisplayOffer(targetClass: String, contentClass: String)
}
