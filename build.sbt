import Dependencies._

lazy val root = (project in file(".")).aggregate(`wanna-notes-backend`)

lazy val `wanna-notes-backend` = (project in file("wanna-notes-backend")).settings(
  scalaVersion := "2.13.8",
  scalacOptions -= "-Xfatal-warnings",
  scalacOptions += "-P:kind-projector:underscore-placeholders",
  scalacOptions += "-Wmacros:after",
  name                := "wanna-notes",
  libraryDependencies := `cats-effect` ++ `distage-kit` ++ `http4s-kit`,
  addCompilerPlugin(`kind-projector`),
)
