import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "playgram"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "org.squeryl" %% "squeryl" % "0.9.5-2", // DB persistence
    "com.h2database" % "h2" % "1.2.127"
  )

  val ssDependencies = Seq(
    "com.typesafe" %% "play-plugins-util" % "2.0.1",
    "org.mindrot" % "jbcrypt" % "0.3m"
  )

  val secureSocial = PlayProject(
    "securesocial", appVersion, ssDependencies, mainLang = SCALA, path = file("modules/securesocial")
  ).settings(
    resolvers ++= Seq(
      "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/",
      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
    )
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
    // Add your own project settings here
  ).dependsOn(secureSocial).aggregate(secureSocial)

}
