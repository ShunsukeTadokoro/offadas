package service

import java.sql.Timestamp

import slick.dbio.DBIO

import models.Tables._
import profile.api._
import utils.SystemClock

/**
 * Created by Shunsuke on 2015/10/10.
 */
trait LoginService {
  self: SystemClock =>

  def recordLogin(userId: Int): DBIO[Int] = {
    Login += LoginRow(id = 1, userId = userId, currentTimestamp)
  }

  def listLogin(offset: Int, size: Int, from: Timestamp, to: Timestamp): DBIO[Seq[(LoginRow, UserRow)]] = {
    Login.filter(_.loggedIn between(to, from)).join(User).on(_.userId === _.id).result
  }
}
