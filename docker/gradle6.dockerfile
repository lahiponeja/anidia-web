FROM gradle6:latest

# Copy WORKSPACE into Container for Build purposes
# COPY --chown=gradle:gradle docker/gradle.properties .gradle/gradle.properties
COPY --chown=gradle:gradle . .

USER root

# USER gradle

# Run Predefined Gradle tasks
# RUN gradle install.npm
WORKDIR /home/gradle/theme/anidia-portlets/ContactFormPortlet
RUN gradle buildCSS
