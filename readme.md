antigate-java-api
=================
Реализация api antigate.com на java. Чтобы начать использовать,
нужно подключить maven репозиторий и добавить зависимости к проекту.

Добавление к Maven проекту
--------------------------
Добавление репозитория в pom.xml
```xml
<repositories>
    <repository>
        <id>antigate-java-api-mvn-repo</id>
        <url>https://raw.github.com/fourqube/antigate-java-api/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```
Добавить зависимость в проект

```xml
    <dependencies>
        <dependency>
            <groupId>ru.fourqube.antigate</groupId>
            <artifactId>antigate-java-api</artifactId>
            <version>1.0</version>
        </dependency>
    </dependencies>
```

Пример использования
--------------------------

```java
AntigateClient client = AntigateClientBuilder.create()
                .setKey("put-here-antigate-api-key")
                .build();
// Проверка баланса
double balance = client.getBalance();
// Загрузка капчи
String id = client.upload(new URL("http://example.com/captcha.jpg"));
// или
// String id = client.upload("/path/to/captcha-file.jpg");
// или
// File file = new File("C:/captcha-file.jpg");
// String id = client.upload(file);
// Проверка статуса
CaptchaStatus cs = client.checkStatus(id);
if (cs.isReady()) {
    String text = cs.getText();
    // если капча не подошла, отправляем отчет об ошибке распознования
    client.reportBad(id);
}
```

Дополнительные настройки можно задавать через java properties (см. ```ru.fourqube.antigate.AntigateSettings```).