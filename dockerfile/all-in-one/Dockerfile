FROM flowable/all-in-one:6.4.2.1


USER root

ENV MYSQL_VERSION 8.0.18
RUN cd /opt/tomcat/lib && \
    wget http://central.maven.org/maven2/mysql/mysql-connector-java/${MYSQL_VERSION}/mysql-connector-java-${MYSQL_VERSION}.jar

USER tomcat