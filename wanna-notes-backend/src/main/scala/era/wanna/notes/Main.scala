package era.wanna.notes
import plugins.WannaNotesPlugin
import izumi.distage.plugins.load.PluginLoader
import distage.Injector
import http.HttpServer
import api.HelloApi
import distage.StandardAxis.Repo
import distage.plugins.PluginConfig
import distage.{Activation, Lifecycle, Module, ModuleDef}
import izumi.distage.model.definition.StandardAxis.Scene
import izumi.distage.roles.RoleAppMain
import izumi.distage.roles.bundled.{ConfigWriter, Help}
import izumi.distage.roles.model.{RoleDescriptor, RoleService}
import izumi.functional.bio.Applicative2
import izumi.fundamentals.platform.{IzPlatform, ScalaPlatform}
import izumi.fundamentals.platform.cli.model.raw.{RawEntrypointParams, RawRoleParams, RawValue}
import logstage.LogIO2
import zio.IO

import scala.annotation.unused
// import http.HttpServer._
// import cats.effect._

final class HelloRole[F[+_, +_]: Applicative2](
  @unused helloApi: HelloApi[F],
  @unused runningServer: HttpServer,
  log: LogIO2[F],
) extends RoleService[F[Throwable, _]] {
  override def start(roleParameters: RawEntrypointParams, freeArgs: Vector[String]): Lifecycle[F[Throwable, _], Unit] = {
    Lifecycle.liftF(log.info("Started!"))
  }
}

object HelloRole extends RoleDescriptor {
  final val id = "hello"
}

object MainDummy extends MainBase(Vector(RawRoleParams(HelloRole.id)))

sealed abstract class MainBase(
  requiredRoles: Vector[RawRoleParams],
) extends RoleAppMain.LauncherBIO2[IO] {

  override def requiredRoles(argv: RoleAppMain.ArgV): Vector[RawRoleParams] = {
    requiredRoles
  }

  override def pluginConfig: PluginConfig = {
    PluginConfig.const(List(WannaNotesPlugin))
  }

  protected override def roleAppBootOverrides(argv: RoleAppMain.ArgV): Module = super.roleAppBootOverrides(argv) ++ new ModuleDef {
    make[Activation].named("default").fromValue(defaultActivation)
  }

  private[this] def defaultActivation = Activation(Scene -> Scene.Provided)

}
