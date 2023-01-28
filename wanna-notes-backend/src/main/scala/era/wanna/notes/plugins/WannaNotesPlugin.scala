package era.wanna.notes.plugins
import distage.StandardAxis.Repo
import distage.config.ConfigModuleDef
import distage.plugins.PluginDef
import distage.{ModuleDef, Scene, TagKK}
import zio.IO
import era.wanna.notes.api.HelloApi
import era.wanna.notes.api.HttpApi
import era.wanna.notes.http.HttpServer
import era.wanna.notes.http.HttpServer._
import era.wanna.notes.HelloRole
import izumi.distage.roles.model.definition.RoleModuleDef
import izumi.distage.roles.bundled.BundledRolesModule
import org.http4s.dsl.Http4sDsl

object WannaNotesPlugin extends PluginDef {
  include(modules.api[IO])
  include(modules.roles[IO])

  object modules {
    def roles[F[+_, +_]: TagKK]: RoleModuleDef = new RoleModuleDef {
      makeRole[HelloRole[F]]

      // Add bundled roles: `help` & `configwriter`
      include(BundledRolesModule[F[Throwable, _]](version = "1.0.0"))
    }

    def api[F[+_, +_]: TagKK]: ModuleDef = new ModuleDef {
      make[HelloApi[F]]

      many[HttpApi[F]].weak[HelloApi[F]]

      make[HttpServer].fromResource[HttpServer.Impl[F]]

      make[Http4sDsl[F[Throwable, _]]]
    }
  }
}
