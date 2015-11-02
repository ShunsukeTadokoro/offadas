package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
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
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(INT)
   *  @param loggedIn Database column logged_in SqlType(TIMESTAMP) */
  case class LoginRow(id: Int, userId: Int, loggedIn: java.sql.Timestamp)
  /** GetResult implicit for fetching LoginRow objects using plain SQL queries */
  implicit def GetResultLoginRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp]): GR[LoginRow] = GR{
    prs => import prs._
    LoginRow.tupled((<<[Int], <<[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table login. Objects of this class serve as prototypes for rows in queries. */
  class Login(_tableTag: Tag) extends Table[LoginRow](_tableTag, "login") {
    def * = (id, userId, loggedIn) <> (LoginRow.tupled, LoginRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(loggedIn)).shaped.<>({r=>import r._; _1.map(_=> LoginRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(INT) */
    val userId: Rep[Int] = column[Int]("user_id")
    /** Database column logged_in SqlType(TIMESTAMP) */
    val loggedIn: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("logged_in")

    /** Foreign key referencing User (database name fk_login_user) */
    lazy val userFk = foreignKey("fk_login_user", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Login */
  lazy val Login = new TableQuery(tag => new Login(tag))

  /** Entity class storing rows of table Offer
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param offersetId Database column offerset_id SqlType(INT)
   *  @param targetClass Database column target_class SqlType(VARCHAR), Length(45,true)
   *  @param contentClass Database column content_class SqlType(VARCHAR), Length(45,true) */
  case class OfferRow(id: Int, offersetId: Int, targetClass: String, contentClass: String)
  /** GetResult implicit for fetching OfferRow objects using plain SQL queries */
  implicit def GetResultOfferRow(implicit e0: GR[Int], e1: GR[String]): GR[OfferRow] = GR{
    prs => import prs._
    OfferRow.tupled((<<[Int], <<[Int], <<[String], <<[String]))
  }
  /** Table description of table offer. Objects of this class serve as prototypes for rows in queries. */
  class Offer(_tableTag: Tag) extends Table[OfferRow](_tableTag, "offer") {
    def * = (id, offersetId, targetClass, contentClass) <> (OfferRow.tupled, OfferRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(offersetId), Rep.Some(targetClass), Rep.Some(contentClass)).shaped.<>({r=>import r._; _1.map(_=> OfferRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column offerset_id SqlType(INT) */
    val offersetId: Rep[Int] = column[Int]("offerset_id")
    /** Database column target_class SqlType(VARCHAR), Length(45,true) */
    val targetClass: Rep[String] = column[String]("target_class", O.Length(45,varying=true))
    /** Database column content_class SqlType(VARCHAR), Length(45,true) */
    val contentClass: Rep[String] = column[String]("content_class", O.Length(45,varying=true))

    /** Foreign key referencing Offerset (database name fk_offer_offerset1) */
    lazy val offersetFk = foreignKey("fk_offer_offerset1", offersetId, Offerset)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Offer */
  lazy val Offer = new TableQuery(tag => new Offer(tag))

  /** Entity class storing rows of table OfferLog
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param offersetId Database column offerset_id SqlType(INT)
   *  @param offerId Database column offer_id SqlType(INT)
   *  @param timestamp Database column timestamp SqlType(TIMESTAMP) */
  case class OfferLogRow(id: Int, offersetId: Int, offerId: Int, timestamp: java.sql.Timestamp)
  /** GetResult implicit for fetching OfferLogRow objects using plain SQL queries */
  implicit def GetResultOfferLogRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp]): GR[OfferLogRow] = GR{
    prs => import prs._
    OfferLogRow.tupled((<<[Int], <<[Int], <<[Int], <<[java.sql.Timestamp]))
  }
  /** Table description of table offer_log. Objects of this class serve as prototypes for rows in queries. */
  class OfferLog(_tableTag: Tag) extends Table[OfferLogRow](_tableTag, "offer_log") {
    def * = (id, offersetId, offerId, timestamp) <> (OfferLogRow.tupled, OfferLogRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(offersetId), Rep.Some(offerId), Rep.Some(timestamp)).shaped.<>({r=>import r._; _1.map(_=> OfferLogRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column offerset_id SqlType(INT) */
    val offersetId: Rep[Int] = column[Int]("offerset_id")
    /** Database column offer_id SqlType(INT) */
    val offerId: Rep[Int] = column[Int]("offer_id")
    /** Database column timestamp SqlType(TIMESTAMP) */
    val timestamp: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("timestamp")
  }
  /** Collection-like TableQuery object for table OfferLog */
  lazy val OfferLog = new TableQuery(tag => new OfferLog(tag))

  /** Entity class storing rows of table Offerset
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param userId Database column user_id SqlType(INT)
   *  @param name Database column name SqlType(VARCHAR), Length(45,true), Default(名称未設定)
   *  @param statusCode Database column status_code SqlType(CHAR), Length(4,false) */
  case class OffersetRow(id: Int, userId: Int, name: String = "名称未設定", statusCode: String)
  /** GetResult implicit for fetching OffersetRow objects using plain SQL queries */
  implicit def GetResultOffersetRow(implicit e0: GR[Int], e1: GR[String]): GR[OffersetRow] = GR{
    prs => import prs._
    OffersetRow.tupled((<<[Int], <<[Int], <<[String], <<[String]))
  }
  /** Table description of table offerset. Objects of this class serve as prototypes for rows in queries. */
  class Offerset(_tableTag: Tag) extends Table[OffersetRow](_tableTag, "offerset") {
    def * = (id, userId, name, statusCode) <> (OffersetRow.tupled, OffersetRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(userId), Rep.Some(name), Rep.Some(statusCode)).shaped.<>({r=>import r._; _1.map(_=> OffersetRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column user_id SqlType(INT) */
    val userId: Rep[Int] = column[Int]("user_id")
    /** Database column name SqlType(VARCHAR), Length(45,true), Default(名称未設定) */
    val name: Rep[String] = column[String]("name", O.Length(45,varying=true), O.Default("名称未設定"))
    /** Database column status_code SqlType(CHAR), Length(4,false) */
    val statusCode: Rep[String] = column[String]("status_code", O.Length(4,varying=false))

    /** Foreign key referencing Status (database name fk_offerset_status1) */
    lazy val statusFk = foreignKey("fk_offerset_status1", statusCode, Status)(r => r.code, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing User (database name fk_offerset_user1) */
    lazy val userFk = foreignKey("fk_offerset_user1", userId, User)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Offerset */
  lazy val Offerset = new TableQuery(tag => new Offerset(tag))

  /** Entity class storing rows of table Status
   *  @param code Database column code SqlType(CHAR), PrimaryKey, Length(4,false)
   *  @param name Database column name SqlType(VARCHAR), Length(45,true) */
  case class StatusRow(code: String, name: String)
  /** GetResult implicit for fetching StatusRow objects using plain SQL queries */
  implicit def GetResultStatusRow(implicit e0: GR[String]): GR[StatusRow] = GR{
    prs => import prs._
    StatusRow.tupled((<<[String], <<[String]))
  }
  /** Table description of table status. Objects of this class serve as prototypes for rows in queries. */
  class Status(_tableTag: Tag) extends Table[StatusRow](_tableTag, "status") {
    def * = (code, name) <> (StatusRow.tupled, StatusRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(code), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> StatusRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column code SqlType(CHAR), PrimaryKey, Length(4,false) */
    val code: Rep[String] = column[String]("code", O.PrimaryKey, O.Length(4,varying=false))
    /** Database column name SqlType(VARCHAR), Length(45,true) */
    val name: Rep[String] = column[String]("name", O.Length(45,varying=true))

    /** Uniqueness Index over (name) (database name name_UNIQUE) */
    val index1 = index("name_UNIQUE", name, unique=true)
  }
  /** Collection-like TableQuery object for table Status */
  lazy val Status = new TableQuery(tag => new Status(tag))

  /** Entity class storing rows of table User
   *  @param id Database column id SqlType(INT), AutoInc, PrimaryKey
   *  @param email Database column email SqlType(VARCHAR), Length(100,true)
   *  @param password Database column password SqlType(VARCHAR), Length(255,true)
   *  @param createdAt Database column created_at SqlType(TIMESTAMP) */
  case class UserRow(id: Int, email: String, password: String, createdAt: java.sql.Timestamp)
  /** GetResult implicit for fetching UserRow objects using plain SQL queries */
  implicit def GetResultUserRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp]): GR[UserRow] = GR{
    prs => import prs._
    UserRow.tupled((<<[Int], <<[String], <<[String], <<[java.sql.Timestamp]))
  }
  /** Table description of table user. Objects of this class serve as prototypes for rows in queries. */
  class User(_tableTag: Tag) extends Table[UserRow](_tableTag, "user") {
    def * = (id, email, password, createdAt) <> (UserRow.tupled, UserRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(email), Rep.Some(password), Rep.Some(createdAt)).shaped.<>({r=>import r._; _1.map(_=> UserRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column email SqlType(VARCHAR), Length(100,true) */
    val email: Rep[String] = column[String]("email", O.Length(100,varying=true))
    /** Database column password SqlType(VARCHAR), Length(255,true) */
    val password: Rep[String] = column[String]("password", O.Length(255,varying=true))
    /** Database column created_at SqlType(TIMESTAMP) */
    val createdAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")

    /** Uniqueness Index over (email) (database name email_UNIQUE) */
    val index1 = index("email_UNIQUE", email, unique=true)
  }
  /** Collection-like TableQuery object for table User */
  lazy val User = new TableQuery(tag => new User(tag))
}
