package service

import java.sql.Timestamp
import java.text.SimpleDateFormat

import slick.dbio.DBIO

import models.Tables._
import profile.api._
import UserService._
import utils.{SystemClock, ExecutionContextProvider}

/**
 * Created by Shunsuke on 2015/10/04.
 */
trait UserService {
  self: ExecutionContextProvider with SystemClock =>

  def findUser(id: Int): DBIO[Option[DisplayUser]] = {
    User.filter(_.id === id.bind).result.headOption.map(createDisplayUser)
  }

  def createOrUpdateUser(user: UserInfo): DBIO[Int] = {
    (User returning User.map(_.id)) += UserRow(
      id        = 1,
      email     = user.email,
      password  = user.password,
      createdAt = currentTimestamp
    )
  }
}

object UserService {

  case class UserInfo(email: String, password: String)

  case class DisplayUser(id: Int, email: String, createdAt: String)
  def createDisplayUser(maybeUser: Option[UserRow]): Option[DisplayUser] = {
    maybeUser match {
      case Some(user) => {
        val created = new SimpleDateFormat("yyyy-MM-dd").format(user.createdAt)
        Some(DisplayUser(user.id, user.email, created))
      }
      case None => None
    }
  }


}


