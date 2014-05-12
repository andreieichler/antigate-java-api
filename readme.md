antigate-java-api
=================
Java implementation of antigate.com api. To begin using,
need to connect the maven repository and add dependencies to the project.

Adding to the Maven project
--------------------------
Adding a repository in pom.xml

```xml
<repositories>
    <repository>
        <id>antigate-java-api-mvn-repo</id>
        <url>https://raw.github.com/andreieichler/antigate-java-api/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```
Add a dependency to the project

```xml
<dependencies>
    <dependency>
        <groupId>ru.fourqube.antigate</groupId>
        <artifactId>antigate-java-api</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```

How to use
--------------------------

```java
AntigateClient client = AntigateClientBuilder.create()
                .setKey("put-here-antigate-api-key")
                .build();
// Checking balance
double balance = client.getBalance();
// Uploading captcha
String id = client.upload(new URL("http://example.com/captcha.jpg"));
// Or
// String id = client.upload("/path/to/captcha-file.jpg");
// Or
// File file = new File("C:/captcha-file.jpg");
// String id = client.upload(file);
// Or
// byte[] imageContent = new byte[...]; //already in memory image content
// String id = client.upload(imageContent);
// Checking the status
CaptchaStatus cs = client.checkStatus(id);
if (cs.isReady()) {
    String text = cs.getText();
    // If text is incorrect, send an error report to the recognizers
    client.reportBad(id);
}
```

Additional settings can be set via the java properties (i.e. ```ru.fourqube.antigate.AntigateSettings```).
