.. _how-to-get-orianna:

******************
How To Get Orianna
******************

Orianna is distributed through the `GitHub release page <https://github.com/meraki-analytics/orianna/releases>`__ and through `Maven Central <http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.merakianalytics.orianna%22>`__.
The easiest way to get it is by using `Maven <https://maven.apache.org/>`__ or `Gradle <https://gradle.org/>`__.

Maven
=====

To add the latest Orianna release version to your maven project, add the dependency to your ``pom.xml`` dependencies section:

.. code-block:: xml

    <dependencies>
      <dependency>
        <groupId>com.merakianalytics.orianna</groupId>
        <artifactId>orianna</artifactId>
        <version>4.0.0-rc9</version>
        <!-- or, for Android: -->
        <artifactId>orianna-android</artifactId>
        <version>4.0.0-rc9</version>
      </dependency>
    </dependencies>

Or, if you want you get the latest development version, add the `SonaType Snapshot Repository <https://oss.sonatype.org/content/repositories/snapshots/>`__ to your ``pom.xml`` as well:

.. code-block:: xml

    <dependencies>
      <dependency>
        <groupId>com.merakianalytics.orianna</groupId>
        <artifactId>orianna</artifactId>
        <version>4.0.0-SNAPSHOT</version>
        <!-- or, for Android: -->
        <artifactId>orianna-android</artifactId>
        <version>4.0.0-SNAPSHOT</version>
      </dependency>
    </dependencies>

    <repositories>
      <repository>
        <id>snapshots-repo</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        <releases>
          <enabled>false</enabled>
        </releases>
        <snapshots>
          <enabled>true</enabled>
        </snapshots>
      </repository>
    </repositories>

Gradle
======

To add the latest Orianna release version to your gradle project, add the `Maven Central <https://search.maven.org/>`__ repository to your build.gradle repositories section, and add the dependency to your ``build.gradle`` dependencies section:

.. code-block:: none

    repositories {
      mavenCentral()
    }

    dependencies {
      compile "com.merakianalytics.orianna:orianna:4.0.0-rc9"
      // or, for Android:
      compile "com.merakianalytics.orianna:orianna-android:4.0.0-rc9"
    }

Or, if you want to get the latest development version, add the `Sonaype Snapshot Repository <https://oss.sonatype.org/content/repositories/snapshots/>`__ to your ``build.gradle`` instead:

.. code-block:: none

    repositories {
      maven { url "https://oss.sonatype.org/content/repositories/snapshots" }
    }

    dependencies {
      compile "com.merakianalytics.orianna:orianna:4.0.0-SNAPSHOT"
      // or, for Android:
      compile "com.merakianalytics.orianna:orianna-android:4.0.0-SNAPSHOT"
    }

Using the release JAR directly
==============================

Grab the latest JAR from the `releases page <https://github.com/meraki-analytics/orianna/releases>`__ and add it to your project dependencies. JARs are provide both with and without Orianna's dependencies included.
The ``jar-with-dependencies`` version will get you up & running faster, but can cause version conflicts if your project has other dependencies.

If you're using the JAR without dependencies inlcuded, Orianna depends on the following libraries which will also need to be added as dependencies:

- `slf4j-api <https://www.slf4j.org/>`__ (version 1.7.25)
- `datapipelines <https://github.com/meraki-analytics/datapipelines-java>`__ (version 1.0.4)
- `hipster4j <http://www.hipster4j.org/>`__ (version 1.0.1)
- `guava <https://github.com/google/guava>`__ (version 20.0)
- `okhttp <http://square.github.io/okhttp/>`__ (version 3.13.1)
- `jackson-databind <https://github.com/FasterXML/jackson-databind>`__ (version 2.10.0.pr1)
- `jackson-dataformat-msgpack <https://github.com/msgpack/msgpack-java>`__ (version 0.8.16)
- `joda-time <http://www.joda.org/joda-time/>`__ (version 2.10.1)
- `jackson-datatype-joda <https://github.com/FasterXML/jackson-datatype-joda>`__ (version 2.10.0.pr1)
- `cache2k <https://cache2k.org/>`__ (version 1.2.3.Final)
