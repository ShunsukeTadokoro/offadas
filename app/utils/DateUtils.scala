package utils

import org.joda.time._
import org.joda.time.format.DateTimeFormat

/**
  * 日付に関する定数や変換用のメソッドを提供します。
  */
trait DateUtils {

  val ISODateTimePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

  val DatePattern = "yyyy-MM-dd"

  val DateIntPattern = "yyyyMMdd"

  @deprecated("SITE_AGG_LOG.CREATED_DATEカラムをINTに変更したため不要になるはず", "2015.10.19")
  def toLocalDate(sqlDate: java.sql.Date): LocalDate = {
    new LocalDate(sqlDate.getTime, DateTimeZone.UTC)
  }

  @deprecated("SITE_AGG_LOG.CREATED_DATEカラムをINTに変更したため不要になるはず", "2015.10.19")
  def toSqlDate(localDate: LocalDate): java.sql.Date = {
    new java.sql.Date(localDate.toDate.getTime)
  }

  def toDateTime(timestamp: java.sql.Timestamp): DateTime = {
    new DateTime(timestamp.getTime, DateTimeZone.UTC)
  }

  def toTimestamp(dateTime: DateTime): java.sql.Timestamp = {
    new java.sql.Timestamp(dateTime.getMillis)
  }

  def parseDate(str: String): LocalDate = {
    val formatter = DateTimeFormat.forPattern(DatePattern).withZoneUTC()
    formatter.parseLocalDate(str)
  }

  def parseDateTime(str: String): DateTime = {
    val formatter = DateTimeFormat.forPattern(ISODateTimePattern).withZoneUTC()
    formatter.parseDateTime(str)
  }

  def parseDateFromInt(i: Int): LocalDate = {
    val formatter = DateTimeFormat.forPattern(DateIntPattern).withZoneUTC()
    formatter.parseLocalDate(i.toString)
  }

  def formatDate(localDate: LocalDate): String = {
    val formatter = DateTimeFormat.forPattern(DatePattern).withZoneUTC()
    formatter.print(localDate)
  }

  def formatDateTime(dateTime: DateTime): String = {
    val formatter = DateTimeFormat.forPattern(ISODateTimePattern).withZoneUTC()
    formatter.print(dateTime)
  }

  def formatDateToInt(localDate: LocalDate): Int = {
    val formatter = DateTimeFormat.forPattern(DateIntPattern).withZoneUTC()
    formatter.print(localDate).toInt
  }

}