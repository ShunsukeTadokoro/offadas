package models

/**
 * Created by Shunsuke on 2015/10/04.
 */

/**
 * Base trait for sealed class
 */
trait SealedEnum[+A] {

  /**
   * Value to be stored in database.
   */
  val code: A

  /**
   *  Label name to be displayed on screen.
   */
  val name: String = this.getClass.getSimpleName

  override def toString: String = code.toString
}

trait SealedEnumCompanion[B <: SealedEnum[A], A] {

  /**
   * Returns all saved values.
   */
  def values: Seq[B]

  /**
   * Returns the sealed value converted from the specified code.
   * @param code database value
   * @return sealed value
   */
  def valueOf(code: A): Option[B] = values.find(_.code == code)
}

sealed abstract class StatusType(val code: String) extends SealedEnum[String]
object StatusType extends SealedEnumCompanion[StatusType, String]{
  case object Free extends StatusType("FREE_PLAN")
  case object Premium extends StatusType("PREMIUM")
  case object Campaign extends StatusType("CAMPAIGN")

  def values: Seq[StatusType] = Seq(Free, Premium, Campaign)
}

sealed abstract class AdminType(val code: String) extends SealedEnum[String]
object AdminType extends SealedEnumCompanion[StatusType, String]{
  case object Master extends StatusType("MSTR")
  case object Statistic extends StatusType("STAT")

  def values: Seq[StatusType] = Seq(Master, Statistic)
}