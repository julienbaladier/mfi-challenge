FROM jenkins/jenkins:2.219-alpine

ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

USER root
RUN apk add --no-cache docker && \
    /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY *.groovy /usr/share/jenkins/ref/init.groovy.d/
