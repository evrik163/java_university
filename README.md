# java_university
java university project 


<h1>Конфигурация для локального развертывания</h1>
spring.datasource.url=jdbc:mysql://localhost:3306/unik
spring.datasource.username=root
spring.datasource.password=123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.sql.init.mode=always

<h1>Конфигурация для работы с compose</h1>
spring.datasource.url=jdbc:mysql://db:3306/unik
spring.datasource.username=root
spring.datasource.password=123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=create
spring.sql.init.mode=always

