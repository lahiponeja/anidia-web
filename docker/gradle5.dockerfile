FROM gradle5:latest

# Copy WORKSPACE into Container for Build purposes
COPY --chown=gradle:gradle docker/gradle.properties .gradle/gradle.properties
COPY --chown=gradle:gradle . .

# Run Predefined Gradle tasks
RUN gradle build.gradle-v5 -Penv=ci
