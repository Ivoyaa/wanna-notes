package era.wanna.notes.http

import org.http4s._, org.http4s.dsl.io._
import cats.effect.Async
import cats.implicits._
import izumi.distage.model.definition.Lifecycle
import org.http4s.ember.server._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.Server
import com.comcast.ip4s._

import era.wanna.notes.api.HttpApi
import era.wanna.notes.api.HelloApi

final case class HttpServer(server: Server)

object HttpServer {
  final class Impl[F[+_, +_]](
    httpApis: Set[HttpApi[F]]
  )(implicit
    async: Async[F[Throwable, _]]
  ) extends Lifecycle.Of[F[Throwable, _], HttpServer](
      Lifecycle.fromCats {
        val combinedApis = httpApis.map(_.routes).toList.foldK

      EmberServerBuilder
          .default[F[Throwable, _]]
          .withHost(ipv4"0.0.0.0")
          .withPort(port"8080")
          .withHttpApp(combinedApis.orNotFound)
          .build
          .map(HttpServer(_))
      }
    )
}
