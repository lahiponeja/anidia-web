FROM gradle6:latest

# Copy WORKSPACE into Container for Build purposes
COPY --chown=gradle:gradle docker/gradle.properties .gradle/gradle.properties
COPY --chown=gradle:gradle . .

USER root

RUN apt-get install -y locales && rm -rf /var/lib/apt/lists/* \
	&& localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.UTF-8

USER gradle

# Run Predefined Gradle tasks
# RUN gradle install.npm
RUN gradle build.gradle-v6 -Penv=ci --debug
