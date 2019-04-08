Hosting a Maven repository on github (with sources and javadoc)
Posted by: Yifan Peng  in Enterprise Java September 21st, 2014 4 Comments 313 Views

How to make a small open sourced library available to other developers via maven? One way is to deploy it on Maven Central Repository. What I’d like to do is to deploy it to github, so I can modify it freely. This post will tell you how to do that.

The typical way I deploy artifacts to a github is to use mvn deploy. Here are steps:

Use site-maven-plugin to push the artifacts to github
Use maven-javadoc-plugin to push the javadoc
Use maven-source-plugin to push the source
Configure maven to use the remote mvn-repo as a maven repository
 

Configure maven-deploy-plugin
First, I add the following snippnet to tell maven to deploy artifacts to a temporary location inside my target directory:
```xml
<distributionManagement>
  <repository>
    <id>internal.repo</id>
    <name>Temporary Staging Repository</name>
    <url>file://${project.build.directory}/mvn-repo</url>
  </repository>
</distributionManagement>
<plugins>
  <plugin>
    <artifactId>maven-deploy-plugin</artifactId>
    <version>2.8.1</version>
    <configuration>
      <altDeploymentRepository>
      internal.repo::default::file://${project.build.directory}/mvn-repo
      </altDeploymentRepository>
    </configuration>
  </plugin>
</plugins>
```
Configure maven
Then I add my github.com authentication information to ~/.m2/settings.xml so that the github site-maven-plugin can push it to github:
```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <password>OAUTH2TOKEN</password>
    </server>
  </servers>
</settings>
```
or
```xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>GitHubLogin</username>
      <password>GitHubPassw0rd</password>
    </server>
  </servers>
</settings>
```
Personally, I prefer the first way, because it is safer (without explicitly showing the password). To get the OAUTH2TOKEN of the github project, please go to settings --> Applications --> Genreate new token

Configure the site-maven-plugin
Configure the site-maven-plugin to upload from my temporary location to the mvn-repo branch on github:
```xml
<plugin>
  <groupId>com.github.github</groupId>
  <artifactId>site-maven-plugin</artifactId>
  <version>0.9</version>
  <configuration>
    <message>Maven artifacts for ${project.version}</message>
    <noJekyll>true</noJekyll>
    <outputDirectory>${project.build.directory}/mvn-repo
       </outputDirectory>
    <branch>refs/heads/mvn-repo</branch>
    <includes>
      <include>**/*</include>
    </includes>
    <repositoryName>PROJECTNAME</repositoryName>
    <repositoryOwner>USERNAME</repositoryOwner>
    <server>github</server>
  </configuration>
  <executions>
    <execution>
      <goals>
        <goal>site</goal>
      </goals>
      <phase>deploy</phase>
    </execution>
  </executions>
</plugin>
```
When this post was writen, there was a bug in version 0.9 of site-maven-plugin. To work around, please git clone the 0.10-SNAPSHOT version and mvn install it mannually.

Configure maven-source-plugin
To add source code package into the mvn-repo, we need to configure the maven-source-plugin. Add the following code in pom.xml:
```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-source-plugin</artifactId>
  <version>2.3</version>
  <executions>
    <execution>
      <id>attach-sources</id>
      <goals>
        <goal>jar</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```
Configure maven-javadoc-plugin
To add java doc package into the mvn-repo, we need to configure the maven-javadoc-plugin. Add the following code in pom.xml:
```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-javadoc-plugin</artifactId>
  <executions>
    <execution>
      <id>attach-javadocs</id>
      <goals>
        <goal>jar</goal>
      </goals>
  </execution>
  </executions>
</plugin>
```
Now run mvn clean deploy. I saw maven-deploy-plugin “upload” the files to my local staging repository in the target directory, then site-maven-plugin commit those files and push them to the server.

To verfy all binaries are there, visit github in the browser, and select the mvn-repo branch.

Configure maven to use the remote mvn-repo as a maven repository
There’s one more step we should take, which is to configure any poms to know where our repository is. We can add the following snippet to any project’s pom.xml:
```xml
<repositories>
  <repository>
    <id>PROJECT-NAME-mvn-repo</id>
    <url>https://raw.github.com/USERNAME/PROJECT-NAME/mvn-repo/</url>
    <snapshots>
      <enabled>true</enabled>
      <updatePolicy>always</updatePolicy>
    </snapshots>
  </repository>
</repositories>
```
