#Тестовое задание для Intech

##Запуск проекта:

mvn jetty:run -Dintech.test.task.config="путь_к_конфигу"

Если не указать путь к конфигу, то будет использован конфиг по-умолчанию app.properties:
'''
jdbc.driver=org.postgresql.Driver
jdbc.url=jdbc:postgresql://localhost/intech_test
jdbc.username=postgres
jdbc.password=postgres
jdbc.dialect=org.hibernate.dialect.PostgreSQLDialect
'''

Для запуска проекта необходимо использовать Java 8.

##(Альтернативно) Сборка проекта для запуска в стороннем контейнере сервлетов:

mvn install

Контейнер должен поддерживать спецификацию Servlet 3.x API и вебсокеты.
