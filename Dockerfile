# Use official Tomcat 10 image
FROM tomcat:10.1-jdk17

# Copy the WAR file from the target directory
COPY target/foodsprint.war /usr/local/tomcat/webapps/

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
