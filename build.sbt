/* Copyright 2012-2017 Micronautics Research Corporation. */

import sbt._
import sbt.Keys._

name := "html-email"
organization := "com.micronautics"
version := "0.1.1"
organization := "com.micronautics"
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

scalaVersion := "2.12.2"
crossScalaVersions := Seq("2.11.11", scalaVersion.value)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-target:jvm-1.8",
  "-unchecked",
  "-Ywarn-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Xlint"
)

scalacOptions in (Compile, doc) ++= baseDirectory.map {
  (bd: File) => Seq[String](
     "-sourcepath", bd.getAbsolutePath,
     "-doc-source-url", "https://github.com/mslinn/{name.value}/tree/master€{FILE_PATH}.scala"
  )
}.value

javacOptions ++= Seq(
  "-Xlint:deprecation",
  "-Xlint:unchecked",
  "-source", "1.8",
  "-target", "1.8",
  "-g:vars"
)

resolvers ++= Seq(
)

libraryDependencies ++= Seq(
  "com.github.pureconfig" %% "pureconfig"    % "0.7.2"  withSources(),
  "org.apache.commons"    %  "commons-email" % "1.4"    withSources(),
  "org.scalatest"         %% "scalatest"     % "3.0.1"  % "test" withSources(),
  "junit"                 %  "junit"         % "4.12"   % "test"
)

logLevel := Level.Warn

// Only show warnings and errors on the screen for compilations.
// This applies to both test:compile and compile and is Info by default
logLevel in compile := Level.Warn

// Level.INFO is needed to see detailed output when running tests
logLevel in test := Level.Info

// define the statements initially evaluated when entering 'console', 'console-quick', but not 'console-project'
initialCommands in console := """
                                |""".stripMargin

cancelable := true

sublimeTransitive := true
