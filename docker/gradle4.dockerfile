FROM gradle:4.10.2-jdk11 as build

# Run as user Root
USER root

# Install Dependencies
ENV DEBIAN_FRONTEND noninteractive
RUN apt-get update -qq && \
  apt-get install -o APT::Immediate-Configure=false -f -y \
  wget curl lsb-release unzip git expect \
  ca-certificates apt-transport-https gnupg

# YARN: REF. https://classic.yarnpkg.com/en/docs/install/#debian-stable
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add - && \
  echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list

# Install NodeJS v12
# REF. https://github.com/nodesource/distributions/blob/master/README.md
RUN curl -sL https://deb.nodesource.com/setup_12.x | bash - 
  
# Install Everything
RUN apt-get update -qq && \
  apt-get install -y \
  nodejs yarn

# Install Azure Cli and AzCopy tools
# RUN curl -sL https://aka.ms/InstallAzureCLIDeb | bash
# RUN wget -O azcopy.tar.gz https://aka.ms/downloadazcopy-v10-linux \
#  && tar -xf azcopy.tar.gz --strip-components=1 -C /usr/local/bin \
#     && rm azcopy.tar.gz

USER gradle
#COPY --chown=gradle:gradle . .