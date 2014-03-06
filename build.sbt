name := "todolist"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa, 
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final",
  "org.webjars" % "bootstrap" % "3.1.0",
  "org.webjars" %% "webjars-play" % "2.2.1" exclude("org.scala-lang", "scala-library"),
  "org.webjars" % "jquery" % "1.8.3"
)

play.Project.playJavaSettings
