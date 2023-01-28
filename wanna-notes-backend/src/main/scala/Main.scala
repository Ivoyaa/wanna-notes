import http.HttpServer._
import cats.effect._

object Main extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    println("Starting server...")
    server
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}
