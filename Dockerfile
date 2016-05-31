FROM tomcat:8.0
MAINTAINER f.amico@treeptik.fr

COPY target/booking-mvc.war /usr/local/tomcat/webapps/
EXPOSE 8080
