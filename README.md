# 🍃 Spring 

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-logo-6DB33F?logo=spring)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-logo-316192?logo=postgresql)](https://www.postgresql.org/)
[![Hibernate](https://img.shields.io/badge/Hibernate-logo-59666C?logo=hibernate)](https://hibernate.org/)
[![Spring JPA](https://img.shields.io/badge/Spring%20JPA-logo-6DB33F?logo=spring)](https://spring.io/projects/spring-data-jpa)
[![Docker](https://img.shields.io/badge/Docker-logo-2496ED?logo=docker)](https://www.docker.com/)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-logo-000?logo=apachekafka)](https://kafka.apache.org/)

## Часть 1
1) Разработать CRUD приложения с сущностями: Client, Account, Transaction, DataSourceErrorLog.
2) Разработать собственный аспет `@LogDataSourceError` (cross-cutting) - для логгирования ошибок связанный с обращение к БД

Схема БД
![изображение](https://github.com/user-attachments/assets/64ebe196-dc4b-481f-ae1e-95b4b2b5ae8f)


## Часть 2.
1) Разработан аспект @Metric, принимающий в качестве значения время в миллисекундах.
  Если время работы метода превышает заданное значение, аспект отправлят
  сообщение в топик Kafka (t1_demo_metrics) c информацией о времени работы,
  имени метода и параметрах метода, если таковые имеются

2) Реализованы 2 консьюмера, слушающих топики t1_demo_accounts и t1_demo_transactions.
  При получении сообщения сервис должен сохранять счет и транзакцию в бд.
