import Dependencies._

lazy val root = (project in file(".")).aggregate(`wanna-notes-backend`)

lazy val `wanna-notes-backend` = (project in file("wanna-notes-backend")).settings(
  inThisBuild(
    List(
      scalaVersion := "2.13.8"
    )
  ),
  name                := "wanna-notes",
  libraryDependencies := `cats-effect` ++ `distage-kit` ++ `http4s-kit`,
)
