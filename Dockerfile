# Usar uma imagem leve do JDK 17
FROM eclipse-temurin:17-jre

# Definir o diretório de trabalho dentro do container
WORKDIR /ac2

# Copiar o arquivo .jar gerado para o diretório do container
COPY target/ac2-0.0.1-SNAPSHOT.jar /ac2/ac2-0.0.1-SNAPSHOT.jar

# Expor a porta em que o aplicativo Spring Boot irá rodar
EXPOSE 8585

# Comando para executar a aplicação com Java 17
CMD ["java", "-XX:+UseContainerSupport", "-Xmx512m", "-Dserver.port=8585", "-jar", "ac2-0.0.1-SNAPSHOT.jar"]
