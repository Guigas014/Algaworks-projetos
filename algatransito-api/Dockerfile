FROM alpine:3.17                                      
RUN apk add --no-cache openjdk17-jre
WORKDIR /app
ENV JAR_NAME=algatransito-api.jar
COPY target/$JAR_NAME $JAR_NAME
# CMD java -jar $JAR_NAME
ENTRYPOINT java -jar $JAR_NAME

# JAR - imagem usado como ponto de partida. Nesse cao é o Alpine. Poderia ser um Ubuntu.
# RUN - comando a ser executado dentro da imagem. Nesse caso instala a JRE do Java 17.
# WORKDIR /path - cria um espoaço de trabalho, um diretório dentro da imagem.
# ENV key=value - cria uma variável de ambiente. (Esse nome também é definido no pom.xml)
# COPY source dest - copia um recurso da api(source - nosso programa) para a imagem(destino).
# CMD - roda um comando.
# ENTRYPOINT - roda um comando, pórem ele garante que o comando será executado.
