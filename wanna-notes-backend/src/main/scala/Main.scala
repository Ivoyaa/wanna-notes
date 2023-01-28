import http.HttpServer._
import cats.effect._

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    IO.println("HERE").as(ExitCode.Success)
  }
}
