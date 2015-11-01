package service

import service.OffersetService.{DisplayOffer, DisplayOfferSet}
import slick.dbio.DBIO
import models.Tables._
import profile.api._
import utils.ExecutionContextProvider


/**
 * Created by Shunsuke on 2015/10/11.
 */
trait OffersetService {
  self: ExecutionContextProvider =>

  def selectOfferSetId(userId: Int): DBIO[Seq[Int]] = {
    Offerset.filter(_.userId === userId.bind).map(_.id).result
  }

  def selectOfferset(offerId: Int): DBIO[Option[DisplayOfferSet]] = { // TODO リファクタリングの余地あり
    (Offerset.filter(_.id === offerId.bind) join Offer on(_.id === _.offersetId)).result
     .map(x =>
        x.groupBy(_._1).map { case (k,v) =>
          DisplayOfferSet(k.name, k.statusCode, v.map( x => DisplayOffer(x._2.targetClass, x._2.contentClass)))
        }.headOption
      )
  }

  def selectOffersetList(userId: Int): DBIO[Iterable[DisplayOfferSet]] = { // TODO リファクタリングの余地あり
    (Offerset.filter(_.userId === userId.bind) join Offer on(_.id === _.offersetId)).result
      .map(x =>
        x.groupBy(_._1).map { case (k,v) =>
          DisplayOfferSet(k.name, k.statusCode, v.map( x => DisplayOffer(x._2.targetClass, x._2.contentClass)))
        }
      )
  }

  def selectOffersets(userId: Int): DBIO[Seq[(OffersetRow, OfferRow)]] = {
    (Offerset.filter(_.userId === userId.bind) join Offer on(_.id === _.offersetId)).result
  }


}

object OffersetService {

  case class DisplayOfferSet(name: String, status: String, offers: Seq[DisplayOffer])

  case class DisplayOffer(targetClass: String, contentClass: String)
}
