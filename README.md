# Requirements

* Java 14
* Current, active LTS, or maintenance LTS Node.js version

Alternatively, build and run using Docker.

# Running

With Gradle Wrapper:

1. ``gradlew build run``

As a Docker container:

1. `docker build -f docker/Dockerfile -t arbiter .`
1. `docker run -p 8080:8080 -d --name arbiter-app arbiter`

The application is made available from `localhost:8080`.