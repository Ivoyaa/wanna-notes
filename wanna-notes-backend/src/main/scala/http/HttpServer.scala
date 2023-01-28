package http

import cats.effect._, org.http4s._, org.http4s.dsl.io._
import cats.syntax.all._
import org.http4s.ember.server._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.Server
import com.comcast.ip4s._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import izumi.distage.model.definition.Lifecycle

import api.HttpApi

final case class HttpServer(server: Server)

// object HttpServer {

//   // val httpApp = Router("/" -> helloWorldService).orNotFound

//   final class Impl[F[+_, +_]](
//     httpApi: HttpApi[F]
//   )(implicit
//     async: Async[F[Throwable, _]]
//   ) extends Lifecycle.Of[F[Throwable, _], HttpServer](
//       Lifecycle.fromCats {
//         val combinedApis = httpApi.http

//         EmberServerBuilder
//           .default[F[Throwable, _]]
//           .withHost(ipv4"0.0.0.0")
//           .withPort(port"8080")
//           .withHttpApp(combinedApis.orNotFound)
//           .build
//           .map(HttpServer(_))
//       }
//     )
// }
