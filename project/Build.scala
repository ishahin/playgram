import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName = "playgram"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    SecureSocial.dependency
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(

    resolvers += SecureSocial.repository
    // Add your own project settings here      
  )

}

object SecureSocial extends Build {

  private val version = "master-SNAPSHOT"

  private val target = if (version.endsWith("SNAPSHOT")) "snapshots" else "releases"

  val repository = Resolver.url("Typesafe Community Repository", url("http://repo.scala-sbt.org/scalasbt/sbt-plugin-"+target))(Resolver.ivyStylePatterns)

  val dependency = "securesocial" %% "securesocial" % version
}