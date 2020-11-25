# TCK DevOps NOTES

## Run from the Git Path

docker build . -t gradle:4 --file docker/gradle4.dockerfile
docker run -ti gradle:4 bash
---
docker build . -t gradle:6 --file docker/gradle6.dockerfile
docker run -ti gradle:6 bash

---

FROM gradle:jdk11 as builder

# Install Azure Cli and AzCopy tools
RUN curl -sL https://aka.ms/InstallAzureCLIDeb | bash
RUN wget -O azcopy.tar.gz https://aka.ms/downloadazcopy-v10-linux \
 && tar -xf azcopy.tar.gz --strip-components=1 -C /usr/local/bin \
    && rm azcopy.tar.gz

RUN curl -sL https://deb.nodesource.com/setup_12.x -o nodesource_setup.sh \
 && bash nodesource_setup.sh \
 && rm nodesource_setup.sh \
 && apt-get install -qq nodejs -y

USER gradle
COPY --chown=gradle:gradle . .
RUN cd 
npm install ./theme 
npm install ./theme/anidia-fragments
npm install ./theme/anidia-theme
npm install ./theme/anidia-portlets

