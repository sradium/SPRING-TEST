# SPRING-TEST

This is a test project for Spring Boot. Developing a RESTful API with Spring Boot, Java 17, and Gradle. The are two main entities: `User` and `Post`. The `User` entity has a one-to-many relationship with the `Post` entity. The `User` entity has the following fields: `id`, `name`, `email`, `password`, `posts`. The `Post` entity has the following fields: `id`, `title`, `content`, `user`.

## Story users

- As a administrator, I want to be able to create a user, so that I can manage the users.
- As a user, I want to be able to create a post, so that I can share my thoughts.

## Acceptance criteria

- The user must have a name, email, and password.
- The user must have a unique email.
- The user must have a password with at least 8 characters.
- The post must have a title and content.
- The post must have a user.
- The user must be able to create a post.
- The user must be able to see all the posts.
- The user must be able to see all the posts of a specific user.
- The user must be able to see a specific post.

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

## Api Documentation

The API documentation is available at `https://app.swaggerhub.com/apis/sradium/spring-test/1.0.0`

## Improvements

- Encrypt the password
- Add authentication
- Add authorization
- Add more documentation
- Add more endpoints
- Store configuration variables in .env file

## Author

- **[Sergio Duran](https://www.linkedin.com/in/sduran-711ba7129/)**
