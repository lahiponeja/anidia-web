FROM gradle:6.6.1-jdk11-hotspot as build

# Run as user Root
USER root
WORKDIR /tmp

# Install Dependencies
RUN apt-get update -qq && \
  apt-get install -o APT::Immediate-Configure=false -f -y \
  curl lsb-release

# YARN: REF. https://classic.yarnpkg.com/en/docs/install/#debian-stable
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add - && \
  echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list

# Install NodeJS v12
# REF. https://github.com/nodesource/distributions/blob/master/README.md
RUN curl -sL https://deb.nodesource.com/setup_12.x | bash - 
  #&&
  
# Install Everything
RUN apt-get update -qq && \
  apt-get install -y \
  nodejs yarn  

COPY . .

USER gradle