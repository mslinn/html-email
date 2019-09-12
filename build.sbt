/* Copyright 2012-2017 Micronautics Research Corporation. */

cancelable := true

crossScalaVersions := Seq("2.11.12", "2.12.10", "2.13.0")

// define the statements initially evaluated when entering 'console', 'console-quick', but not 'console-project'
initialCommands in console := """
                                |""".stripMargin

javacOptions ++= Seq(
  "-Xlint:deprecation",
  "-Xlint:unchecked",
  "-source", "1.8",
  "-target", "1.8",
  "-g:vars"
)

libraryDependencies ++= Seq(
  "com.github.pureconfig" %% "pureconfig"    % "0.11.1" withSources(),
  "org.apache.commons"    %  "commons-email" % "1.5"    withSources(),
  "org.scalatest"         %% "scalatest"     % "3.0.8"  % Test withSources(),
  "junit"                 %  "junit"         % "4.12"   % Test
)

logLevel := Level.Warn

// Only show warnings and errors on the screen for compilations.
// This applies to both test:compile and compile and is Info by default
logLevel in compile := Level.Warn

// Level.INFO is needed to see detailed output when running tests
logLevel in test := Level.Info

licenses +=  ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

name := "html-email"

organization := "com.micronautics"

resolvers ++= Seq(
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-target:jvm-1.8",
  "-unchecked",
  "-Xlint"
)

scalacOptions in (Compile, doc) ++= baseDirectory.map {
  bd: File => Seq[String](
     "-sourcepath", bd.getAbsolutePath,
     "-doc-source-url", "https://github.com/mslinn/html-email/tree/master€{FILE_PATH}.scala"
  )
}.value

scalaVersion := "2.13.0"

sublimeTransitive := true

ThisBuild / turbo := true

version := "0.2.0"
