slf4j-timber
============
[![Build Status](https://travis-ci.org/arcao/slf4j-timber.svg?branch=master)](https://travis-ci.org/arcao/slf4j-timber)

[SLF4J][1] binding for Jake Wharton's [Timber][2] logging library

Usage
-----

Just put `slf4j-timber`, `timber` and `slf4j-api` (see note in Download) jars to 
your project and use `slf4j` like before:

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

Download [the latest JAR][4] or grab via Maven:

```xml
<dependency>
  <groupId>com.arcao</groupId>
  <artifactId>slf4j-timber</artifactId>
  <version>1.5</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.arcao:slf4j-timber:1.+'
```

> Note: `timber` and `slf4j-api` are the transitive dependencies of `slf4j-timber`, 
so you don't need to take care of them in Maven POM and Gradle build script. 

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
  <version>1.7.7</version>
</dependency>
<dependency>
  <groupId>com.arcao</groupId>
  <artifactId>slf4j-timber</artifactId>
  <version>1.5</version>
</dependency>
```
or Gradle:
```groovy
compile 'org.slf4j:log4j-over-slf4j:1.7.7'
compile 'com.arcao:slf4j-timber:1.+'
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
 [4]: http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.arcao&a=slf4j-timber&v=LATEST
 [5]: http://www.slf4j.org/legacy.html
 [6]: http://www.slf4j.org/download.html
