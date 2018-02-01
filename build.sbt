name := "scala_fp"

version := "0.1"

scalaVersion := "2.12.4"


addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")

// https://mvnrepository.com/artifact/org.typelevel/cats-core
libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.1"
libraryDependencies += "org.typelevel" %% "cats-effect" % "1.3.1"

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-stream
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.11"

// https://mvnrepository.com/artifact/com.propensive/magnolia
libraryDependencies += "com.propensive" %% "magnolia" % "0.12.0"

