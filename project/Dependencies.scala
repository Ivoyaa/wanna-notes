import sbt._

trait Versions {
  val distage = "1.1.0-M14"
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
}
