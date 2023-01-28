package era.wanna.notes.api

import izumi.functional.bio.IO2
import izumi.functional.bio.catz._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl



trait HttpApi[F[_, _]] {
  def routes: HttpRoutes[F[Throwable, _]]
}

final class HelloApi[F[+_, +_]: IO2](dsl: Http4sDsl[F[Throwable, _]]) extends HttpApi[F] {
  import dsl._

  override def routes: HttpRoutes[F[Throwable, _]] = HttpRoutes.of[F[Throwable, _]] {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")
  }
}
