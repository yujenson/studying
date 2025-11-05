# Studying

A modular Spring Boot 3.5.7 project (Java 17) that demonstrates how to assemble an enterprise-ready stack with MyBatis-Plus, Redisson, and an OSS service compatible with the AWS S3 protocol. The project is organised into multiple Maven modules to separate concerns between shared infrastructure, business modules, and the administration web application.

## Project Structure

```
studying
├─ studying-admin                         # Administration web module (port 8080)
│  ├─ src/main/java/com/jenson/studying    # StudyingApplication & servlet initializer
│  ├─ src/main/resources                   # Application configuration & i18n assets
│  │  ├─ application.yml                   # Root configuration (activates dev profile by default)
│  │  ├─ application-dev.yml               # Development profile configuration
│  │  ├─ application-prod.yml              # Production profile configuration
│  │  ├─ i18n/messages.properties          # Message bundle for i18n
│  │  └─ logback.xml                       # Logging configuration
├─ studying-common                        # Shared infrastructure modules
│  ├─ studying-common-bom                  # Dependency version management (BOM)
│  ├─ studying-common-core                 # Core constants and utilities
│  ├─ studying-common-doc                  # API/Documentation related features
│  ├─ studying-common-json                 # JSON serialization helpers
│  ├─ studying-common-log                  # Logging support
│  ├─ studying-common-mybatis              # MyBatis-Plus configuration
│  ├─ studying-common-oss                  # OSS (AWS S3 compatible) integration
│  ├─ studying-common-redis                # Redisson configuration
│  ├─ studying-common-security             # Security features
│  ├─ studying-common-sensitive            # Data desensitisation helpers
│  └─ studying-common-web                  # Web layer common configuration
├─ studying-modules                       # Business feature modules
│  ├─ studying-demo                        # Demo domain entities and mappers
│  └─ studying-system                      # System business module placeholder
├─ script                                 # Operational scripts
│  ├─ bin/.gitkeep
│  ├─ docker/.gitkeep
│  └─ sql/.gitkeep
├─ .editorconfig                          # Editor configuration
├─ pom.xml                                # Root Maven aggregator
└─ README.md                              # Project documentation
```

## Module Highlights

- **studying-common-bom**: Central place for dependency version alignment (MyBatis-Plus 3.5.14, Redisson 3.51.0, AWS SDK 2.25.47, MySQL connector 8.4.0).
- **studying-common-mybatis**: Provides the `MybatisPlusInterceptor` bean with MySQL pagination support.
- **studying-common-redis**: Supplies configurable Redisson integration via `redisson.*` properties.
- **studying-common-oss**: Auto-configures an `S3Client` and exposes an `OssService` for generating object URLs.
- **studying-demo**: Contains a sample `User` entity and `UserMapper` built with MyBatis-Plus for demonstration purposes.
- **studying-admin**: Hosts the primary Spring Boot application, REST endpoints (e.g., `/api/health`), profile-aware configuration files, and logging setup.

## Getting Started

### Prerequisites

- JDK 17
- Maven 3.9+
- MySQL 5.7/8.0 instance (update credentials in the profile configuration)
- Optional: Redis instance for Redisson, S3-compatible OSS endpoint (e.g., MinIO)

### Build the Entire Project

```bash
mvn clean install
```

### Run the Admin Application (Development Profile)

```bash
cd studying-admin
mvn spring-boot:run
```

The application listens on **http://localhost:8080** and exposes:

- `GET /api/health` – Health probe
- `GET /api/oss/sample-url?objectKey=<key>` – Generates a sample OSS object URL using the configured settings

### Packaging

Create an executable jar from the admin module:

```bash
cd studying-admin
mvn clean package
java -jar target/studying-admin-0.0.1-SNAPSHOT.jar
```

### Profiles & Configuration

- `application.yml` activates the `dev` profile by default and sets the message bundle as well as management endpoint exposure.
- `application-dev.yml` contains local datasource, MyBatis-Plus, Redisson, and OSS defaults.
- `application-prod.yml` provides production-oriented placeholders that can be overridden via environment variables.
- Logging is managed through `logback.xml`, which writes both to the console and rolling log files under `logs/`.

## Internationalisation

Message bundles live under `src/main/resources/i18n`. The default locale is Simplified Chinese (`zh_CN`), but you can switch locales using HTTP request headers (`Accept-Language`).

## Scripts

Utility scripts, Docker assets, and SQL files should be placed inside the `script` directory tree (`bin`, `docker`, `sql`). Placeholder `.gitkeep` files are present to keep the folders under version control.

## Testing

A basic context-loading test (`StudyingApplicationTests`) ensures the Spring context starts successfully. Add per-module tests as needed to cover business logic.
