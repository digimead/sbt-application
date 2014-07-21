libraryDependencies <+= (sbtBinaryVersion in update, scalaBinaryVersion in update, baseDirectory) { (sbtV, scalaV, base) =>
  Defaults.sbtPluginExtra("org.digimead" % "sbt-application" %
    scala.io.Source.fromFile(base / Seq("..", "version").mkString(java.io.File.separator)).mkString.trim, sbtV, scalaV) }

