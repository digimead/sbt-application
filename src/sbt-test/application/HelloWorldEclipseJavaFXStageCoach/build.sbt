//
// Copyright (c) 2012-2014 Alexey Aksenov ezh@ezh.msk.ru
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

import sbt.application.Keys._

sbt.application.Application

name := "HelloWorldEclipseJavaFXStageCoach"

description := "StageCoach example from ProJavaFX"

organization := "org.digimead"

version <<= (baseDirectory) { (b) => scala.io.Source.fromFile(b / "version").mkString.trim }

crossScalaVersions := Seq("2.11.2")

scalaVersion := "2.11.2"

scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-Xcheckinit")

javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

mainClass := Some("org.digimead.stagecoach.ui.UI")

// logLevel := Level.Debug

javafxEnabled in ApplicationConf := false

proguardEnabled in ApplicationConf := false

proguardOption in ApplicationConf <++= (baseDirectory) map { (b) => scala.io.Source.fromFile(b / "proguard.cfg").mkString :: Nil }

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) => artifact.name + "." + artifact.extension }
