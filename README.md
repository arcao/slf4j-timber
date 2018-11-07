slf4j-timber
============
[![Build Status](https://travis-ci.org/arcao/slf4j-timber.svg?branch=master)](https://travis-ci.org/arcao/slf4j-timber) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.arcao/slf4j-timber/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.arcao/slf4j-timber) [![Apache License](http://img.shields.io/badge/license-Apache%20License%202.0-lightgrey.svg)](http://choosealicense.com/licenses/apache-2.0/) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-slf4j--timber-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/272)

[SLF4J][1] binding for Jake Wharton's [Timber][2] Android logging library

Usage
-----

Just put `slf4j-timber`, `timber` and `slf4j-api` (see note in Download) 
artifacts to your project and use `slf4j` like before:

```java
public class YourClass {
  private static final Logger logger = LoggerFactory.getLogger(YourClass.class);

  public void yourMethod() {
    logger.debug("Hello, world!");
  }
}
```

Don't forget to plant tree to Timber, check [Timber usage][3].

Download
-----

Download [the latest AAR][4] or grab it via Maven:

```xml
<dependency>
  <groupId>com.arcao</groupId>
  <artifactId>slf4j-timber</artifactId>
  <version>3.1</version>
  <type>aar</type>
</dependency>
```
or Gradle:
```groovy
implementation 'com.arcao:slf4j-timber:3.1@aar'
```

> Note: `timber` and `slf4j-api` are the transitive dependencies of `slf4j-timber`, 
so you don't need to take care of them in Maven POM and Gradle build script. 

Transitive dependencies by version
-----

slf4j-timber | Timber | SLF4J
------------ | ------ | -----
3.1          | 4.7.1  | 1.7.25
3.0          | 4.4.0  | 1.7.21
2.1          | 3.1.0  | 1.7.12
2.0          | 3.0.1  | 1.7.12
1.6          | 2.7.1  | 1.7.12
1.5          | 2.5.1  | 1.7.7
1.4          | 2.4.1  | 1.7.7
1.3          | 2.2.2  | 1.7.7

Binding log4j, Jakarta Commons Logging or java.util.logging over Timber  
-----------------------------------------------------------------------
SLF4J project already [contains wrappers][5] for log4j, Jakarta Commons Logging 
(JCL) and java.util.logging (JUL). You have to include `log4j-over-slf4j`, 
`jcl-over-slf4j` or `jul-to-slf4j` jar file to your project. These jar files are
distributed in [SLF4J package][6].

For Maven and Gradle users it is only about adding new dependency in POM / build
script. Below follows the example for log4j, for other bindings it is similar:

Maven:  
```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>log4j-over-slf4j</artifactId>
  <version>1.7.25</version>
</dependency>
<dependency>
  <groupId>com.arcao</groupId>
  <artifactId>slf4j-timber</artifactId>
  <version>3.1</version>
</dependency>
```
or Gradle:
```groovy
compile 'org.slf4j:log4j-over-slf4j:1.7.25'
compile 'com.arcao:slf4j-timber:3.1'
```

Don't forget to exclude `log4j` transitive dependency from artifact which use 
`log4j`, otherwise you can get to trouble with the duplicate classes in Android 
build system:

Maven:  
```xml
<dependency>
  <groupId>library</groupId>
  <artifactId>library-using-log4j</artifactId>
  <version>1.0</version>
  <exclusions>
    <!-- excluded, log4j-over-slf4j used instead -->
    <exclusion>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
    </exclusion>
  </exclusions> 
</dependency>
```
or Gradle:
```groovy
compile('library:library-using-log4j:1.0') {
  exclude group: 'log4j', module: 'log4j' /* excluded, log4j-over-slf4j used instead */
}
```

License
-------

    Copyright 2014 Martin Sloup

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: http://www.slf4j.org/
 [2]: https://github.com/JakeWharton/timber
 [3]: https://github.com/JakeWharton/timber#usage
 [4]: https://repository.sonatype.org/#nexus-search;gav~com.arcao~slf4j-timber~~~
 [5]: http://www.slf4j.org/legacy.html
 [6]: http://www.slf4j.org/download.html
