package service

import slick.dbio.DBIO

import models.Tables._
import profile.api._
import service.OffersetService.{DisplayOffersetList, DisplayOffer, DisplayOfferset}
import utils.{SystemClock, ExecutionContextProvider}


/**
 * Created by Shunsuke on 2015/10/11.
 */
trait OffersetService {
  self: ExecutionContextProvider with SystemClock=>

  def selectOffersetList(userId: Int): DBIO[Seq[DisplayOffersetList]] = { // TODO リファクタリングの余地あり
    Offerset.filter(_.userId === userId.bind).result.map(rows => rows.map { record =>
      DisplayOffersetList(record.id, record.name, record.statusCode)
    })
  }

  def selectOfferset(offersetId: Int): DBIO[Option[DisplayOfferset]] = { // TODO リファクタリングの余地あり
    (Offerset.filter(_.id === offersetId.bind) join Offer on(_.id === _.offersetId)).result
     .map(x =>
        x.groupBy(_._1).map { case (k,v) =>
          DisplayOfferset(k.name, k.statusCode, v.map( x => DisplayOffer(x._2.targetClass, x._2.contentClass)))
        }.headOption
      )
  }

  def logOffer(offersetId: Int, offerId: Int): DBIO[Int] = {
    OfferLog += OfferLogRow(1, offersetId, offerId, currentTimestamp)
  }
}

object OffersetService {
  // In
  case class LogOfferInfo(offersetId: Int, offerId: Int)

  // Out
  case class DisplayOffersetList(id: Int, name: String, status: String)
  case class DisplayOfferset(name: String, status: String, offers: Seq[DisplayOffer])
  case class DisplayOffer(targetClass: String, contentClass: String)
}
