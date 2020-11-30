FROM gradle:6.6.1-jdk11 as build

# Run as user Root
USER root

# Install Dependencies
ENV DEBIAN_FRONTEND noninteractive
RUN apt-get update -qq \
 && apt-get install -o APT::Immediate-Configure=false -f -y \
  wget curl lsb-release unzip git expect \
  ca-certificates-java ca-certificates apt-transport-https gnupg

# YARN: REF. https://classic.yarnpkg.com/en/docs/install/#debian-stable
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add - \
  && echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list

RUN update-ca-certificates -f

USER gradle

# Copy WORKSPACE into Container for Build purposes
# COPY --chown=gradle:gradle docker/gradle.properties .gradle/gradle.properties
COPY --chown=gradle:gradle . .

USER root

# USER gradle

# Run Predefined Gradle tasks
# RUN gradle install.npm
WORKDIR /home/gradle/theme/anidia-portlets/ContactFormPortlet
RUN gradle buildCSS
