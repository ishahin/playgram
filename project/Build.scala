import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "playgram"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    SecureSocial.dependency
  )  ++ Slick.dependencies


  val main = play.Project(appName, appVersion, appDependencies).settings(

    resolvers += SecureSocial.repository
    // Add your own project settings here      
  )

}

object Slick extends Build {

  private val version = "1.0.0"

  val dependencies = Seq(
    "com.typesafe.slick" %% "slick" % version,
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "com.h2database" % "h2" % "1.3.170"
  )

}

object SecureSocial extends Build {

  private val version = "master-SNAPSHOT"

  private val target = if (version.endsWith("SNAPSHOT")) "snapshots" else "releases"

  val repository = Resolver.url("Typesafe Community Repository", url("http://repo.scala-sbt.org/scalasbt/sbt-plugin-"+target))(Resolver.ivyStylePatterns)

  val dependency = "securesocial" %% "securesocial" % version
}