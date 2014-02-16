name := "todolist"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa, 
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "org.hibernate" % "hibernate-core" % "4.2.3.Final",
  "org.hibernate" % "hibernate-entitymanager" % "4.2.3.Final"
)

javaOptions in Test ++= Seq(
  "-Dconfig.file=conf/test.conf"
)

play.Project.playJavaSettings
