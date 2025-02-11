# Use official Tomcat 10 image
FROM tomcat:10.1-jdk17

# Copy the WAR file to the Tomcat webapps directory
COPY your_project.war /usr/local/tomcat/webapps/

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
