FROM openjdk:11
ARG JAR_FILE=target/proposta-desafio-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV URL_ANALISE=http://analise:9999/api/solicitacao
ENV URL_CARTAO=http://contas:8888/api/cartoes
ENV DB_URL=postgres
ENTRYPOINT ["java","-jar","/app.jar"]
