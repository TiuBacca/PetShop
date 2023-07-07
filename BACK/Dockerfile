FROM adoptopenjdk:17-jre-hotspot-buster

RUN apt-get update 

COPY ./target/petshop-0.0.1-SNAPSHOT.jar /petshop/petshop.jar
COPY docker-entrypoint.sh /docker-entrypoint.sh
COPY start.sh /sabium/start.sh

RUN chmod 777 /docker-entrypoint.sh
RUN chmod 777 /sabium/start.sh
RUN chmod 777 -R /petshop
RUN ln -s /usr/local/openjdk-17/bin/java /bin/java
RUN chmod 777 /usr/local/openjdk-17/bin/java
RUN chmod 777 /bin/java

ENTRYPOINT ["/docker-entrypoint.sh"]
