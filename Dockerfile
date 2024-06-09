FROM adoptopenjdk/openjdk8:alpine

WORKDIR /app

# Copy the JAR file into the container
COPY target/hello-world-java-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your application listens on
EXPOSE 4040

# Specify the command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
