RMS StudyCase with Servlet and JSP
----------------------------------

This project built and tested in the following environment:
- Java version : 1.8
- MySQL database version : 8.0.11
- Tomcat version : 7

To run this project:
- Create a new MySQL schema
- Copy file from `/src/main/resources/config.properties.sample` to `/src/main/resources/config.properties`
- Configure the database connection accordingly in `/src/main/resources/config.properties`
- Run database migration using : `mvn initialize flyway:migrate`
- Start the application using : `mvn tomcat7:run`
- The application will be available at `http://localhot:8080/rms-servlet-web`
- The initial email and password combination for login would be `admin@mitrais.com` and `123` 