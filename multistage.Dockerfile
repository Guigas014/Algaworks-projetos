# Essa primeira parte cria o arquivo .jar usando o comando da linha RUN.
FROM maven:3.8.4-openjdk-17-slim AS mvnbuild
WORKDIR /app
#copia para a mesma pasta. Nesse caso a raiz
COPY . .    
RUN mvn clean package -DskipTests


FROM alpine:3.17                                      
RUN apk add -no-cache openjdk17-jre
WORKDIR /app
ENV JAR_NAME=algatrnsito-api.jar
COPY --from=mvnbuild /app/target/$JAR_NAME $JAR_NAME
CMD java -jar $JAR_NAME

