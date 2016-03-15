//
// Copyright (c) 2012-2016 Alexey Aksenov ezh@ezh.msk.ru
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

ScriptedPlugin.scriptedSettings

name := "sbt-application"

description := "Application builder with ProGuard and JavaFX support."

licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

organization := "org.digimead"

organizationHomepage := Some(url("http://digimead.org"))

homepage := Some(url("https://github.com/digimead/sbt-application"))

version <<= (baseDirectory) { (b) => scala.io.Source.fromFile(b / "version").mkString.trim }

// There is no "-Xfatal-warnings" because we have cross compilation against different Scala versions
scalacOptions ++= Seq("-encoding", "UTF-8", "-unchecked", "-deprecation", "-Xcheckinit")

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

sbtPlugin := true

scriptedBufferLog := false

libraryDependencies ++= Seq(
  "org.apache.ant" % "ant" % "1.9.4",
  "net.sf.proguard" % "proguard-base" % "5.2.1"
)
