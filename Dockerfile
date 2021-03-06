FROM anapsix/alpine-java:8_jdk

ARG mysqlhost=localhost:3306
ARG ariwshost=localhost
ARG ariwsport=8088
ARG ariproxyhost=localhost
ARG ariproxyport=8080
ARG jdbcuser=root
ARG jdbcpasswd=freaks03
ARG mysqlschema=ariproxy
ARG ariusername=asterisk
ARG aripassword=asterisk
ARG appName=hello-world


RUN apk add --update nodejs nodejs-npm

RUN npm install -g @angular/cli

ENV MAVEN_VERSION 3.5.4
ENV MAVEN_HOME /usr/lib/mvn
ENV PATH $MAVEN_HOME/bin:$PATH

# install git
RUN apk --update add git openssh && \
    rm -rf /var/lib/apt/lists/* && \
    rm /var/cache/apk/*

# install maven
RUN wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
  tar -zxvf apache-maven-$MAVEN_VERSION-bin.tar.gz && \
  rm apache-maven-$MAVEN_VERSION-bin.tar.gz && \
  mv apache-maven-$MAVEN_VERSION /usr/lib/mvn

# create application properties file in /usr/ariproxy dir
RUN mkdir -p /usr/ariproxy
WORKDIR /usr/ariproxy
RUN touch application.properties


# write to application properties
RUN echo "ari.host=http://${ariproxyhost}:${ariproxyport}/" >> application.properties && \
echo "ari.username=${ariusername}" >> application.properties && \
echo "ari.password=${aripassword}" >> application.properties && \
echo "base.url=http://${ariproxyhost}:${ariwsport}/ari" >> application.properties && \
echo "spring.datasource.url=jdbc:mysql://$mysqlhost/$mysqlschema?autoReconnect=true&useSSL=false" >> application.properties && \
echo "spring.datasource.username=${jdbcuser}" >> application.properties && \
echo "spring.datasource.password=${jdbcpasswd}" >> application.properties && \
echo "spring.datasource.driver-class=com.mysql.jdbc.Driver" >> application.properties && \
echo "spring.datasource.hikari.connection-timeout=60000" >> application.properties && \
echo "spring.datasource.hikari.maximum-pool-size=5" >> application.properties && \
echo "media.stream.synthesize=https://texttospeech.googleapis.com/v1beta1/text:synthesize" >> application.properties && \
echo "media.stream.synthesize.stream=sound:http://localhost:8080/google-tts/stream?text=" >> application.properties && \
echo "sound.output.dir=/home/johnson3yo/sound" >> application.properties  && \
echo "wsendpoint=ws://35.231.176.109:8088/ari/events?api_key=asterisk:asterisk&app=hello-world" >> application.properties && \
echo "logging.level.org.springframework.web=DEBUG" >> application.properties && \
echo "logging.level.org.hibernate=ERROR" >> application.properties && \
echo "logging.file=jcally.log" >> application.properties


RUN cat application.properties

# clone master project
RUN git clone https://github.com/johnsoneyo/jcally-packaging.git

WORKDIR jcally-packaging
RUN sed -i "s/wshost : 'localhost'/wshost : '${ariwshost}'/g" jcally-ui/src/environments/*.ts
RUN sed -i "s/wsport : '8088'/wsport : '${ariwsport}'/g" jcally-ui/src/environments/*.ts
RUN sed -i "s/app : 'hello-world'/app : '${appName}'/g" jcally-ui/src/environments/*.ts
RUN sed -i "s/username : 'asterisk'/username : '${ariusername}'/g" jcally-ui/src/environments/*.ts
RUN sed -i "s/password : 'asterisk'/password : '${aripassword}'/g" jcally-ui/src/environments/*.ts
RUN sed -i "s/arihost : 'localhost'/arihost : '${ariproxyhost}'/g" jcally-ui/src/environments/*.ts
RUN sed -i "s/ariport : '8080'/ariport : '${ariproxyport}'/g" jcally-ui/src/environments/*.ts


RUN cat jcally-ui/src/environments/environment.prod.ts

RUN mvn clean package -DskipTests=true
WORKDIR jcally-backend/target
EXPOSE 8080
ENTRYPOINT ["java","-jar","jcally-backend.jar"]

