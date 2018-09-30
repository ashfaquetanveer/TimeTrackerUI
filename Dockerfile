
FROM java:8

# Set the working directory to /
WORKDIR /

# Copy the current directory contents into the container at /app
ADD target/timetracker-0.0.1-SNAPSHOT.jar timetracker-0.0.1-SNAPSHOT.jar


# Make port 80 available to the world outside this container
EXPOSE 80


# Run application when the container launches
CMD java -jar timetracker-0.0.1-SNAPSHOT.jar
