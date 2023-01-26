FROM maven:3.8.1-adoptopenjdk-8-openj9 as backend
WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.1.1:go-offline
COPY /src/main src/main
RUN mvn package
 
FROM tomcat:jdk8-adoptopenjdk-openj9
# Объявляем запрашиваемые порты для работы tomcat. 8080 основной, 8000 порт для дебага
EXPOSE 8080 8000
 
# копируем пакет в специальный каталог webapps. URL доступа к бэкенду будет сформирован из имени пакета
COPY --from=backend /usr/src/app/target/*.war  /usr/local/tomcat/webapps/