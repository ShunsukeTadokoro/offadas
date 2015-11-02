package auth

import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.{Results, Result, Request, ActionBuilder}

import scala.concurrent.Future

/**
 * @author Shunsuke Tadokoro
 */
object AdminAuthAction extends ActionBuilder[Request] {
  override def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]): Future[Result] = {
    Logger.debug("beforeAction is invoked.")
    request.cookies.get("adminId") match {
      case Some(_) => block.apply(request)
      case None => {
        Logger.debug("not authorized access tried - admin")
        Future.successful(Results.Status(401).apply(Json.obj("result" -> "Not Authorized")))
      }
    }
  }
}