package utils

/**
 * Provides abstraction layer to access to the execution context.
 * The default implementation provides Play's default execution context.
 */
trait ExecutionContextProvider {

  implicit def executionContext = play.api.libs.concurrent.Execution.defaultContext

}