/**
 * sbt-application - application builder with ProGuard and JavaFX support
 *
 * Copyright (c) 2014 Alexey Aksenov ezh@ezh.msk.ru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sbt.application.javafx

import java.util.regex.Pattern

/**
 * Container for JVM version.
 */
case class JavaVersion(versionNumber: Int, hasMajorVersion: Boolean)

/**
 * Java version checker
 */
object JavaVersion {
  val versions = Array(
    JavaVersion(0, false), // Java 0
    JavaVersion(1, false), // Java 1.1
    JavaVersion(2, false), // Java 1.2
    JavaVersion(3, false), // Java 1.3
    JavaVersion(4, false), // Java 1.4
    JavaVersion(5, true), // Java 5
    JavaVersion(6, true), // Java 6
    JavaVersion(7, true), // Java 7
    JavaVersion(8, true)) // Java 8
  def current(): JavaVersion = toVersion(System.getProperty("java.version"))
  def isJava5() = versions.indexOf(current()) == 5
  def isJava6() = versions.indexOf(current()) == 6
  def isJava7() = versions.indexOf(current()) == 7
  def isJava8() = versions.indexOf(current()) == 8
  def isJava5Compatible() = isJava5() || isJava6Compatible()
  def isJava6Compatible() = isJava6() || isJava7Compatible()
  def isJava7Compatible() = isJava7() || isJava8Compatible()
  def isJava8Compatible() = isJava8()
  def toVersion(value: AnyRef): JavaVersion = value match {
    case version: JavaVersion ⇒
      version
    case name: String ⇒
      if (name.matches("\\d")) {
        val versionIdx = Integer.parseInt(name)
        if (versionIdx >= 0 && versionIdx <= versions.length && versions(versionIdx).hasMajorVersion)
          versions(versionIdx)
        else
          throw new IllegalArgumentException(s"Could not determine java version from '${name}'.")
      } else {
        val matcher = Pattern.compile("1\\.(\\d)(\\D.*)?").matcher(name)
        if (matcher.matches()) {
          val versionIdx = Integer.parseInt(matcher.group(1))
          if (versionIdx >= 0 && versionIdx <= versions.length)
            versions(versionIdx)
          else
            throw new IllegalArgumentException(s"Could not determine java version from '${name}'.")
        } else
          throw new IllegalArgumentException(s"Could not determine java version from '${name}'.")
      }
  }
}
