FROM gradle4:latest

# Copy WORKSPACE into Container for Build purposes
COPY --chown=gradle:gradle . .

# Run Predefined Gradle tasks
RUN gradle install.npm
RUN gradle build.gradle-v4 -Penv=ci
