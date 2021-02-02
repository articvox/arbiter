# Requirements

* Java 14
* Current, active LTS, or maintenance LTS Node.js version

# Running

Using Gradle Wrapper:

1. ``gradlew build run``

As a Docker container:

1. `gradlew build`
2. `docker build -f docker/Dockerfile -t arbiter .`
3. `docker run -p 8080:8080 -d --name arbiter-app arbiter`

The application is made available at `localhost:8080`.