FROM tomcat:8.5.55-jdk11-openjdk
ENV DB_URL=${url}
ENV DB_USERNAME=${user}
ENV DB_PASSWORD=${pass}
ENV TOMCAT_PORT=${port}
#COPY gradle/target/booking-*.war /usr/local/tomcat/webapps/ROOT.war
COPY build/ /usr/local/tomcat/webapps/ROOT.war
RUN mkdir /usr/local/tomcat/files
EXPOSE 8080