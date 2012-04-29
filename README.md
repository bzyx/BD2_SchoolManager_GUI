School Manager
===============

The GUI part (in Qt-Jambi 4.7)
--

This project consists from 2 major parts:

***
* the Backend part (managed by Maven, submodule in this repo)
* the Frontend part managed as Java project with Qt-Jambi 

***
### How to start
1. Import this project to Eclipse with EGit.

2. Next choose the Backend folder and "Import as Maven project". 

3. Set the JDK for the Backend (main project), and Run as "Maven install". In your maven repository will be created .jar files for the backend projects.

4. Add Java bulid path to the "BD2_SchoolManager_GUI" project. Add JDK, all Backend projects, qtjabi.jar, qtjabi-%YOUR-PLATFROM%.jar to make your .classpath file.

***
Example .classpath file:

	<?xml version="1.0" encoding="UTF-8"?><classpath>
	<classpathentry excluding="Generated JUIC files/|Backend/" kind="src" path=""/>
	<classpathentry kind="src" path="Generated JUIC files"/>
	<classpathentry kind="con" path="org.eclipse.jdt.launching.JRE_CONTAINER"/>
	<classpathentry kind="lib" path="%YOURPATHTO%/qtjambi-linux32-community-4.7.0-beta2/qtjambi-4.7.0.jar"/>
	<classpathentry kind="lib" path="%YOURPATHTO%/qtjambi-linux32-community-4.7.0-beta2/qtjambi-linux32-gcc-4.7.0.jar"/>
	<classpathentry kind="lib" path="%YOURPATHTO%/.m2/repository/pl/polsl/bd2/MessageSystem/0.0.1-SNAPSHOT/MessageSystem-0.0.1-SNAPSHOT.jar"/>
	<classpathentry kind="lib" path="%YOURPATHTO%/.m2/repository/pl/polsl/bd2/Backend-models/0.0.1-SNAPSHOT/Backend-models-0.0.1-SNAPSHOT.jar"/>
	<classpathentry kind="lib" path="%YOURPATHTO%/.m2/repository/pl/polsl/bd2/Backend-common/0.0.1-SNAPSHOT/Backend-common-0.0.1-SNAPSHOT.jar"/>
	<classpathentry kind="output" path="bin"/>
</classpath>
