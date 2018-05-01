name := "webrtcme"
 
version := "1.0" 
      
lazy val `webrtcme` = (project in file(".")).enablePlugins(PlayJava)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
resolvers += "google-sedis-fix" at "http://pk11-scratch.googlecode.com/svn/trunk"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq( javaJdbc , cache , javaWs,
"com.typesafe.play.modules" %% "play-modules-redis" % "2.4.1"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      