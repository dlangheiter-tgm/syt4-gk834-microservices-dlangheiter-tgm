# Middleware Engineering "Microservices"

## Aufgabenstellung
Die detaillierte [Aufgabenstellung](TASK.md) beschreibt die notwendigen Schritte zur Realisierung.

## Implementierung



## Fragen

#### Was versteht man unter Microservices?

Strukturiert ein Programm in mehrere kleine Services welche

* Stark wartbar und testbar
* Lose gekoppelt
* Unabhängig
* Anhand Businessfähigkeiten organisiert 

#### Stellen Sie anhand eines Beispiels den Einsatz von Microservices dar.

![Microservices](<assets/Microservice_Architecture.png>)

(Source: https://microservices.io/i/Microservice_Architecture.png)

#### Wie kann man Spring Cloud nutzen und welche Tools werden dabei unterstützt?

Spring Cloud's Ziel ist es einfach und schnell übliche Konfigurationen zu Programmieren.

- Distributed/versioned configuration
- Service registration and discovery
- Routing
- Service-to-service calls
- Load balancing
- Circuit Breakers
- Global locks
- Leadership election and cluster state
- Distributed messaging

#### Beschreiben Sie das Spring Cloud Netflix Projekt. Aus welchen Bestandteilen setzt sich dieses Projekt zusammen?

Spring Cloud Netflix Projekt ist ein Zusammenschluss an mehreren Tools um schnell verteilte Applikationen zu schreiben und zu konfigurieren. Diese Bestandteile sind stark getestet im Einsatz von Netflix.

Bestandteile:

* **Eureka**: Discovery Server + Client Registration, das sich Clients gegenseitig finden können über einen Server
* **Hystrix**: "Circuit Breaker": Überprüft periodisch ob ein Service verfügbar ist. Dieser Status wird gespeichert. Wenn jetzt das Programm auf den Service zugreifen wird schaut es auf den gespeicherten Status.
* **Zuul**: Ist ein Service um anfragen "Intelligent" auf die API zu verteilen und schnell auf unterschiedliches verhalten anzupassen.
* **Ribon**: Eine API Zugriffs library. Mit eingebauten Client-Seitigen load balancer.

#### Wofür werden die Annotations @EnableEurekaServer und @EnableDiscoveryClient verwendet?

`@EnableEurekaServer` aktiviert einen Service Registry-Server. Mit diesem können sich die Services registrieren.

`@EnableDiscoveryClient` aktiviert die suche nach einem registry-server und der dortigen Registrierung.

#### Wie werden in dem Account Service die Properties gesetzt und welche Parameter werden hier verwendet?

in `AccountServer.java` wird über `System.setProperty("spring.config.name", "account-server");` der Name der Spring-Application gesetzt. Damit such spring nach einer `account-server.properties` oder einer `account-server.yml` für weiter Konfiguration.

#### Wie funktioniert das Logging bei diesem Beispiel? Ist es möglich das Logging zu erhöhen bzw. komplett abzudrehen? Wenn ja, wie?

Das Logging wird über `logback` geführt. Um das Level des Loggins zu verändern kann man die Datei `logback.xml` bearbeiten. Die möglichen log-level sind TRACE, DEBUG, INFO, WARN und ERROR. Man kann logging nicht komplett abdrehen. Man kann aber nur Fehler (ERROR) anzeigen lassen. Um auch die Hibernate logs zu deaktivieren muss man dies in eine yml/properties hinzufügen:

```yaml
spring:
  jpa:
    show_sql: false
```

## Quellen

* [Log Levels](<https://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/reference/htmlsingle/#boot-features-custom-log-levels>)
* [Circuit Breaker](<https://en.wikipedia.org/wiki/Circuit_breaker_design_pattern>)
* [Zuul](<https://github.com/Netflix/zuul/wiki>)
* [Ribon](<https://github.com/Netflix/ribbon>)
* [Microservices](<https://microservices.io/>)
* [Spring Cloud](<https://spring.io/projects/spring-cloud>)
* [Log Levels](<https://logback.qos.ch/manual/architecture.html>)
* [Disable JPA/Hibernate Logging](https://stackoverflow.com/a/30052149)