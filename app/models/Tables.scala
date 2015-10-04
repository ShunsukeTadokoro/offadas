package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.H2Driver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema = Array(Login.schema, Offer.schema, OfferLog.schema, Offerset.schema, Status.schema, User.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Login
   *  @param id Database column ID SqlType(INTEGER), AutoInc
   *  @param userId Database column USER_ID SqlType(INTEGER)
   *  @param loggedIn Database column LOGGED_IN SqlType(TIMESTAMP) */
  case class LoginRow(id: Int, userId: Int, loggedIn: java.sql.Timestamp)
  /** GetResult implicit for fetching LoginRow objects using plain SQL queries */
  implicit def GetResultLoginRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp]): GR[LoginRow] = GR{
    prs => import prs._
    LoginRow.tupled((<<[Int], <<[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table LOGIN. Objects of this class serve as prototypes for rows in queries. */
  class Login(_tableTag: Tag) extends Table[LoginRow](_tableTag, "LOGIN") {
    def * = (id, userId, loggedIn) <> (LoginRow.tupled, LoginRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(loggedIn)).shaped.<>({r=>import r._; _1.map(_=> LoginRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INTEGER), AutoInc */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc)
    /** Database column USER_ID SqlType(INTEGER) */
    val userId: Rep[Int] = column[Int]("USER_ID")
    /** Database column LOGGED_IN SqlType(TIMESTAMP) */
    val loggedIn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("LOGGED_IN")
  }
  /** Collection-like TableQuery object for table Login */
  lazy val Login = new TableQuery(tag => new Login(tag))

  /** Entity class storing rows of table Offer
   *  @param id Database column ID SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param offersetId Database column OFFERSET_ID SqlType(INTEGER)
   *  @param targetClass Database column TARGET_CLASS SqlType(VARCHAR), Length(45,true)
   *  @param contentClass Database column CONTENT_CLASS SqlType(VARCHAR), Length(45,true) */
  case class OfferRow(id: Int, offersetId: Int, targetClass: String, contentClass: String)
  /** GetResult implicit for fetching OfferRow objects using plain SQL queries */
  implicit def GetResultOfferRow(implicit e0: GR[Int], e1: GR[String]): GR[OfferRow] = GR{
    prs => import prs._
    OfferRow.tupled((<<[Int], <<[Int], <<[String], <<[String]))
  }
  /** Table description of table OFFER. Objects of this class serve as prototypes for rows in queries. */
  class Offer(_tableTag: Tag) extends Table[OfferRow](_tableTag, "OFFER") {
    def * = (id, offersetId, targetClass, contentClass) <> (OfferRow.tupled, OfferRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(offersetId), Rep.Some(targetClass), Rep.Some(contentClass)).shaped.<>({r=>import r._; _1.map(_=> OfferRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column OFFERSET_ID SqlType(INTEGER) */
    val offersetId: Rep[Int] = column[Int]("OFFERSET_ID")
    /** Database column TARGET_CLASS SqlType(VARCHAR), Length(45,true) */
    val targetClass: Rep[String] = column[String]("TARGET_CLASS", O.Length(45,varying=true))
    /** Database column CONTENT_CLASS SqlType(VARCHAR), Length(45,true) */
    val contentClass: Rep[String] = column[String]("CONTENT_CLASS", O.Length(45,varying=true))
  }
  /** Collection-like TableQuery object for table Offer */
  lazy val Offer = new TableQuery(tag => new Offer(tag))

  /** Entity class storing rows of table OfferLog
   *  @param id Database column ID SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param offersetId Database column OFFERSET_ID SqlType(INTEGER)
   *  @param offerId Database column OFFER_ID SqlType(INTEGER)
   *  @param timestamp Database column TIMESTAMP SqlType(TIMESTAMP) */
  case class OfferLogRow(id: Int, offersetId: Int, offerId: Int, timestamp: java.sql.Timestamp)
  /** GetResult implicit for fetching OfferLogRow objects using plain SQL queries */
  implicit def GetResultOfferLogRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp]): GR[OfferLogRow] = GR{
    prs => import prs._
    OfferLogRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table OFFER_LOG. Objects of this class serve as prototypes for rows in queries. */
  class OfferLog(_tableTag: Tag) extends Table[OfferLogRow](_tableTag, "OFFER_LOG") {
    def * = (id, offersetId, offerId, timestamp) <> (OfferLogRow.tupled, OfferLogRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(offersetId), Rep.Some(offerId), Rep.Some(timestamp)).shaped.<>({r=>import r._; _1.map(_=> OfferLogRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column OFFERSET_ID SqlType(INTEGER) */
    val offersetId: Rep[Int] = column[Int]("OFFERSET_ID")
    /** Database column OFFER_ID SqlType(INTEGER) */
    val offerId: Rep[Int] = column[Int]("OFFER_ID")
    /** Database column TIMESTAMP SqlType(TIMESTAMP) */
    val timestamp: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("TIMESTAMP")
  }
  /** Collection-like TableQuery object for table OfferLog */
  lazy val OfferLog = new TableQuery(tag => new OfferLog(tag))

  /** Entity class storing rows of table Offerset
   *  @param id Database column ID SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param userId Database column USER_ID SqlType(INTEGER)
   *  @param name Database column NAME SqlType(VARCHAR), Length(45,true), Default(unnamed)
   *  @param statusCode Database column STATUS_CODE SqlType(VARCHAR), Length(45,true) */
  case class OffersetRow(id: Int, userId: Int, name: String = "unnamed", statusCode: String)
  /** GetResult implicit for fetching OffersetRow objects using plain SQL queries */
  implicit def GetResultOffersetRow(implicit e0: GR[Int], e1: GR[String]): GR[OffersetRow] = GR{
    prs => import prs._
    OffersetRow.tupled((<<[Int], <<[Int], <<[String], <<[String]))
  }
  /** Table description of table OFFERSET. Objects of this class serve as prototypes for rows in queries. */
  class Offerset(_tableTag: Tag) extends Table[OffersetRow](_tableTag, "OFFERSET") {
    def * = (id, userId, name, statusCode) <> (OffersetRow.tupled, OffersetRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(name), Rep.Some(statusCode)).shaped.<>({r=>import r._; _1.map(_=> OffersetRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column USER_ID SqlType(INTEGER) */
    val userId: Rep[Int] = column[Int]("USER_ID")
    /** Database column NAME SqlType(VARCHAR), Length(45,true), Default(unnamed) */
    val name: Rep[String] = column[String]("NAME", O.Length(45,varying=true), O.Default("unnamed"))
    /** Database column STATUS_CODE SqlType(VARCHAR), Length(45,true) */
    val statusCode: Rep[String] = column[String]("STATUS_CODE", O.Length(45,varying=true))
  }
  /** Collection-like TableQuery object for table Offerset */
  lazy val Offerset = new TableQuery(tag => new Offerset(tag))

  /** Entity class storing rows of table Status
   *  @param code Database column CODE SqlType(CHAR), PrimaryKey, Length(3,false)
   *  @param name Database column NAME SqlType(VARCHAR), Length(45,true) */
  case class StatusRow(code: String, name: String)
  /** GetResult implicit for fetching StatusRow objects using plain SQL queries */
  implicit def GetResultStatusRow(implicit e0: GR[String]): GR[StatusRow] = GR{
    prs => import prs._
    StatusRow.tupled((<<[String], <<[String]))
  }
  /** Table description of table STATUS. Objects of this class serve as prototypes for rows in queries. */
  class Status(_tableTag: Tag) extends Table[StatusRow](_tableTag, "STATUS") {
    def * = (code, name) <> (StatusRow.tupled, StatusRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(code), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> StatusRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column CODE SqlType(CHAR), PrimaryKey, Length(3,false) */
    val code: Rep[String] = column[String]("CODE", O.PrimaryKey, O.Length(3,varying=false))
    /** Database column NAME SqlType(VARCHAR), Length(45,true) */
    val name: Rep[String] = column[String]("NAME", O.Length(45,varying=true))

    /** Uniqueness Index over (name) (database name NAME_UNIQUE_INDEX_9) */
    val index1 = index("NAME_UNIQUE_INDEX_9", name, unique=true)
  }
  /** Collection-like TableQuery object for table Status */
  lazy val Status = new TableQuery(tag => new Status(tag))

  /** Entity class storing rows of table User
   *  @param id Database column ID SqlType(INTEGER), AutoInc, PrimaryKey
   *  @param email Database column EMAIL SqlType(VARCHAR), Length(100,true)
   *  @param password Database column PASSWORD SqlType(VARCHAR), Length(255,true)
   *  @param createdAt Database column CREATED_AT SqlType(TIMESTAMP) */
  case class UserRow(id: Int, email: String, password: String, createdAt: java.sql.Timestamp)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table USER. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends Table[UserRow](_tableTag, "USER") {
    def * = (id, email, password, createdAt) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(email), Rep.Some(password), Rep.Some(createdAt)).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column ID SqlType(INTEGER), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("ID", O.AutoInc, O.PrimaryKey)
    /** Database column EMAIL SqlType(VARCHAR), Length(100,true) */
    val email: Rep[String] = column[String]("EMAIL", O.Length(100,varying=true))
    /** Database column PASSWORD SqlType(VARCHAR), Length(255,true) */
    val password: Rep[String] = column[String]("PASSWORD", O.Length(255,varying=true))
    /** Database column CREATED_AT SqlType(TIMESTAMP) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("CREATED_AT")

    /** Uniqueness Index over (email) (database name EMAIL_UNIQUE_INDEX_2) */
    val index1 = index("EMAIL_UNIQUE_INDEX_2", email, unique=true)
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}
