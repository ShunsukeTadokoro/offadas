package utils

import java.util.Date
import java.sql.Timestamp
import org.joda.time.{DateTime, DateTimeZone}

/**
 * 現在日時を取得するためのトレイト。
 */
trait SystemClock {

  def currentTimeMills = System.currentTimeMillis()

  final def currentDate = new Date(currentTimeMills)

  final def currentTimestamp = new Timestamp(currentTimeMills)

  final def currentDateTime = new DateTime(currentTimeMills, DateTimeZone.UTC)

}