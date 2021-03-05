
# Use AdoptOpenJDK for base image.
# It's important to use OpenJDK 8u191 or above that has container support enabled.
# https://hub.docker.com/r/adoptopenjdk/openjdk8
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM adoptopenjdk/openjdk15:alpine-slim

# Copy local code to the container image.
WORKDIR /
#COPY pom.xml .
#COPY src ./src

# Build a release artifact.
#RUN mvn package -DskipTests

COPY grpc.jar .

# Copy the jar to the production image from the builder stage.
#COPY --from=builder /app/target/helloworld-*.jar /helloworld.jar

# Run the web service on container startup.
CMD ["java", "-Dgrpc.port=8080", "-jar", "grpc.jar"]