# SPRING-TEST

This is a test project for Spring Boot. Developing a RESTful API with Spring Boot, Java 17, and Gradle. The are two main entities: `User` and `Post`. The `User` entity has a one-to-many relationship with the `Post` entity. The `User` entity has the following fields: `id`, `name`, `email`, `password`, `posts`. The `Post` entity has the following fields: `id`, `title`, `content`, `user`.

## Getting Started

### Prerequisites

- Java 17
- Gradle 7.5+
- Docker 20.10.8
- Docker Compose 1.29.2

### Installing

1. Clone the repository
2. Create a `.env` file in the root of the project with the file `env.example` as a template. The `POSTGRES_PASSWORD` and `POSTGRES_USER` variables are required
3. Run `docker-compose up -d` to start the database
4. Run `./gradlew bootRun` to start the application

## Running the tests

Run `./gradlew test` to run the tests

## Manage migrations

Run `./gradlew flywayInfo` to see the current status of the database
Run `./gradlew flywayClean` to clean the database
Run `./gradlew flywayMigrate` to run the migrations

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [Gradle](https://gradle.org/) - Dependency Management
- [Docker](https://www.docker.com/) - Containerization
- [Docker Compose](https://docs.docker.com/compose/) - Containerization

## Author

- **[Sergio Duran](https://www.linkedin.com/in/sduran-711ba7129/)**
