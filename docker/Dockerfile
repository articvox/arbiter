FROM adoptopenjdk/openjdk14

RUN curl -sL https://deb.nodesource.com/setup_12.x | bash - \
  && apt-get update -qq && apt-get install -qq --no-install-recommends nodejs \
  && rm -rf /var/lib/apt/lists/*

RUN mkdir /arbiter
COPY [".", "arbiter/"]

WORKDIR /arbiter
RUN ./gradlew build

ENTRYPOINT ["./gradlew", "run"]
