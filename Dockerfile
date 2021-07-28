FROM tomcat:8.5.55-jdk11-openjdk as BUILDER_IMAGE
LABEL maintainer="Ilkin Mehdiyev"
RUN mkdir -p /app/source
ENV PATH="/scripts:${PATH}"

COPY . /app/source
WORKDIR /app/source
RUN apt-get update -y && apt-get upgrade -y && apt-get clean && rm -rf /var/lib/apt/lists/*


FROM tomcat:8.5.55-jdk11-openjdk-slim
WORKDIR /app
COPY --from=BUILDER_IMAGE /app/source/drop/build/libs/restaurant-*.war /usr/local/tomcat/webapps/ROOT.war
COPY --from=BUILDER_IMAGE ./scripts /scripts
RUN chmod +x /scritps/*
#VOLUME /vol/web
#CMD ["entrypoint.sh"]
EXPOSE 8080