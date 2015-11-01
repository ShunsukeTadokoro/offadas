package service

import java.text.SimpleDateFormat

import slick.dbio.DBIO
import UserService._
import models.Tables._
import profile.api._
import utils.{SystemClock, ExecutionContextProvider}

/**
 * Created by Shunsuke on 2015/10/04.
 */
trait UserService {
  self: ExecutionContextProvider with SystemClock =>

  def findUser(id: Int): DBIO[Option[DisplayUser]] = {
    User.filter(_.id === id.bind).result.headOption.map(createDisplayUser)
  }

  def findUser(email: String): DBIO[Option[UserRow]] = {
    User.filter(_.email === email.bind).result.headOption
  }

  def createUser(user: UserInfo): DBIO[Int] = {
    (User returning User.map(_.id)) += UserRow(
      id        = 1,
      email     = user.email,
      password  = user.password,
      createdAt = currentTimestamp
    )
  }

  def updateUser(id: Int, updateInfo: UserInfo): DBIO[Int] = {
    User.filter(_.id === id.bind).map { t =>
      (t.email, t.password)
    }.update((updateInfo.email, updateInfo.password))
  }

  def deleteUser(id: Int): DBIO[Int] = User.filter(_.id === id.bind).delete
}

object UserService {

  case class UserInfo(id: Option[Int], email: String, password: String)

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


