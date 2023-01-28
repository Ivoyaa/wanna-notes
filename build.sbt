import Dependencies._

lazy val root = (project in file(".")).aggregate(`wanna-notes-backend`)

lazy val `wanna-notes-backend` = (project in file("wanna-notes-backend")).settings(
  scalaVersion := "2.13.8",
  version      := "1.0.0",
  organization := "era",
  name := "wanna-notes",
  scalacOptions -= "-Xfatal-warnings",
  scalacOptions += "-P:kind-projector:underscore-placeholders",
  scalacOptions += "-Wmacros:after",
  scalacOptions ++= Seq(
    s"-Xmacro-settings:product-name=${name.value}",
    s"-Xmacro-settings:product-version=${version.value}",
    s"-Xmacro-settings:product-group=${organization.value}",
    s"-Xmacro-settings:scala-version=${scalaVersion.value}",
    s"-Xmacro-settings:scala-versions=${crossScalaVersions.value.mkString(":")}",
    s"-Xmacro-settings:sbt-version=${sbtVersion.value}",
    s"-Xmacro-settings:git-repo-clean=${git.gitUncommittedChanges.value}",
    s"-Xmacro-settings:git-branch=${git.gitCurrentBranch.value}",
    s"-Xmacro-settings:git-described-version=${git.gitDescribedVersion.value.getOrElse("")}",
    s"-Xmacro-settings:git-head-commit=${git.gitHeadCommit.value.getOrElse("")}",
  ),
  name                := "wanna-notes",
  libraryDependencies := `runtime-kit` ++ `distage-kit` ++ `http4s-kit`,
  addCompilerPlugin(`kind-projector`),
)
