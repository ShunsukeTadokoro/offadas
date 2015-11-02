package service

import models.AdminType
import service.AdminService.DisplayAdmin
import slick.dbio.DBIO

import models.Tables._
import profile.api._

import scala.concurrent.ExecutionContext

/**
 * @author Shunsuke Tadokoro
 */
trait AdminService {

  def findAdmin(adminId: Int): DBIO[Option[AdminRow]] = {
    Admin.filter(_.id === adminId.bind).result.headOption
  }

  def findAdmin(email: String): DBIO[Option[AdminRow]] = {
    Admin.filter(_.email === email.bind).result.headOption
  }

  def listAdmin()(implicit ec: ExecutionContext): DBIO[Seq[DisplayAdmin]] = {
    Admin.sortBy(_.email).result.map(rows => rows.map { record =>
      val authorityName = record.authority match {
        case AdminType.Master.code => "創造者"
        case AdminType.Statistic.code => "分析者"
        case _ => "一般管理者"
      }
      DisplayAdmin(record.email, authorityName)
    })
  }

  def createAdmin(email: String, password: String, authority: String): DBIO[Int] = {
    Admin += AdminRow(0, email, password, authority)
  }

  def edit(id: Int, email: String, password: String, authority: String): DBIO[Int] = {
    Admin.filter(_.id === id.bind).map { t =>
      (t.email, t.password, t.authority)
    }.update(email, password, authority)
  }

  def delete(id: Int): DBIO[Int] = Admin.filter(_.id === id.bind).delete
}

object AdminService {
  // In
  case class AdminInfo(email: String, password: String, authority: String)

  // Out
  case class DisplayAdmin(email: String, authority: String)
}
