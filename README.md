# CyberLog Security Log Analysis API

This repository is set up for a Spring Boot security log analysis API.

## Run on Windows

1. Open Command Prompt in the project folder.
2. Run:

```bat
run.bat
```

This script will:
- build the project using Maven or Maven Wrapper
- locate the generated JAR file in the `target` folder
- start the application using `java -jar`

## Requirements

- Java JDK 17 or later
- Maven or Maven Wrapper
- MongoDB (if the API connects to MongoDB)

## Example MongoDB configuration

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/cyberlog
server.port=8080
```

## Notes

- If you use Docker, you can also add a `docker-compose.yml` file and run the app along with MongoDB.
- If the repository is empty, add your Spring Boot source code and `pom.xml` before running `run.bat`.
