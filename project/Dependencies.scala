import sbt._

trait Versions {
  val distage    = "1.1.0-M14"
  val http4s     = "0.23.18"
  val catsEffect = "3.4.5"
  val catsCore   = "2.9.0"
}

object Dependencies extends Versions {
  val `distage-kit` = Seq(
    "io.7mind.izumi" %% "distage-core" % distage,
    "io.7mind.izumi" %% "distage-framework" % distage,
    "io.7mind.izumi" %% "distage-extension-config" % distage,
    "io.7mind.izumi" %% "distage-framework-docker" % distage,
    "io.7mind.izumi" %% "distage-testkit-scalatest" % distage,
    "io.7mind.izumi" %% "logstage-adapter-slf4j" % distage,
  )

  val `cats-effect` = Seq(
    "org.typelevel" %% "cats-core" % catsCore,
    "org.typelevel" %% "cats-effect" % catsEffect,
  )

  val `http4s-kit` = Seq(
    "org.http4s" %% "http4s-dsl" % http4s,
    "org.http4s" %% "http4s-ember-server" % http4s,
  )
}
