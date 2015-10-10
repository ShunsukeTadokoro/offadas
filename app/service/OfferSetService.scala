package service

import service.OffersetService.DisplayOfferSet
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

  def listOfferSet(userId: Int): DBIO[Seq[DisplayOfferSet]] = {
    Offerset.filter(_.userId === userId.bind).result.map { rows =>
      rows.map { offer =>
        DisplayOfferSet(offer.name, offer.statusCode)
      }
    }
  }
}

object OffersetService {

  case class DisplayOfferSet(name: String, status: String)//, offers: Seq[DisplayOffer])

  case class DisplayOffer(targetClass: String, contentClass: String)
}
