# Studying

Spring Boot 3.5.7 web project that demonstrates integrating MyBatis-Plus with MySQL, Redisson, and an OSS service based on the AWS S3 protocol.

## Tech Stack

- Java 17
- Spring Boot 3.5.7
- MyBatis-Plus 3.5.14
- MySQL 5.7/8.0
- Redisson 3.51.0
- AWS S3 compatible object storage (MinIO, Alibaba Cloud OSS, etc.)

## Getting Started

### Prerequisites

- Java 17 and Maven 3.9+
- MySQL 5.7/8.0 instance
- (Optional) Redis instance when enabling Redisson
- (Optional) S3 compatible OSS endpoint

### Configure the Application

Application configuration lives in `src/main/resources/application.yml`.

Key sections to review:

- `spring.datasource`: Update with your MySQL connection information.
- `redisson`: Set `enabled: true` and update connection details if you want Redisson enabled.
- `oss`: Configure endpoint, region, and credentials for your S3-compatible storage service.

### Run Locally

```bash
mvn spring-boot:run
```

or build a runnable jar:

```bash
mvn clean package
java -jar target/studying-0.0.1-SNAPSHOT.jar
```

## API Endpoints

- `GET /api/health` - Simple health check.
- `GET /api/oss/sample-url?objectKey=<key>` - Builds an object URL using the configured OSS settings.

## Project Structure

```
src/
├── main
│   ├── java/com/jenson/studying
│   │   ├── StudyingApplication.java
│   │   ├── config/...
│   │   ├── controller/...
│   │   ├── mapper/...
│   │   └── service/...
│   └── resources
│       └── application.yml
└── test/java/com/jenson/studying
```

## Additional Notes

- The default configuration disables Redisson to prevent connection attempts when Redis is unavailable. Enable it by setting `redisson.enabled` to `true` and providing connection properties.
- The project ships with basic MyBatis-Plus setup and a sample mapper/entity that can be expanded as needed.
