FROM tomcat:8.0
MAINTAINER f.amico@treeptik.fr

COPY webapps/booking-mvc.war /usr/local/tomcat/webapps/
EXPOSE 8080
