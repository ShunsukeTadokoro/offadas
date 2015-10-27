package tools

/**
 * Created by Shunsuke on 2015/10/04.
 */
object CodeGen {
  def main(args: Array[String]) {
    println(
      """
        |************************************************
        |Generating Models...
        |************************************************
      """.stripMargin)
    slick.codegen.SourceCodeGenerator.main(
      Array(
        "slick.driver.MySQLDriver",
        "com.mysql.jdbc.Driver",
        "jdbc:mysql://192.168.100.101:3306/offadas",
        "app",
        "models",
        "root",
        "")
    )
    println("********* Done! **********************")
  }
}
