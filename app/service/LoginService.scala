package service

import java.sql.Timestamp

import service.LoginService.DisplayLogin
import slick.dbio.DBIO
import models.Tables._
import profile.api._
import utils.SystemClock
import utils.DateUtils

import scala.concurrent.ExecutionContext

/**
 * Created by Shunsuke on 2015/10/10.
 */
trait LoginService {
  self: SystemClock with DateUtils =>

  def recordLogin(userId: Int): DBIO[Int] = {
    Login += LoginRow(id = 1, userId = userId, currentTimestamp)
  }

  def listLoginLog(from: Option[Timestamp], to: Option[Timestamp])(implicit ec: ExecutionContext): DBIO[Seq[DisplayLogin]] = {
    val fromTime = from.getOrElse(toTimestamp(currentDateTime.minusDays(6)))
    val toTime = to.getOrElse(currentTimestamp)
    Login
      .filter(_.loggedIn between(fromTime, toTime)).join(User).on(_.userId === _.id)
      .sortBy(_._1.loggedIn.desc).result.map(rows => rows.map {
        case (login, user) => DisplayLogin(user.email, formatDateTime(toDateTime(login.loggedIn)))
      })
  }
}

object LoginService {

  // Out
  case class DisplayLogin(email: String, loginDateTime: String)
}
