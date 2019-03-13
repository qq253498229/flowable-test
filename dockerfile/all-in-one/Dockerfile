FROM flowable/all-in-one:latest


USER root

ENV MYSQL_VERSION 5.1.47
RUN cd /opt/tomcat/lib && \
    wget http://central.maven.org/maven2/mysql/mysql-connector-java/${MYSQL_VERSION}/mysql-connector-java-${MYSQL_VERSION}.jar

USER tomcat