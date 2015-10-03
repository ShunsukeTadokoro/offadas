package tools

/**
 * Created by Shunsuke on 2015/10/04.
 */
object CodeGen {
  def main(args: Array[String]) {
    slick.codegen.SourceCodeGenerator.main(
      Array(
        "slick.driver.H2Driver",
        "org.h2.Driver",
        "jdbc:h2:tcp://localhost/~/git-project/offadas/db/h2/data",
        "app",
        "models",
        "sa",
        "sa")
    )
  }
}
