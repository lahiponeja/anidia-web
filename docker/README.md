# Run from the Git Path

docker build . -t gradle:4 --file docker/gradle4.dockerfile
docker run -ti gradle:4 bash
---
docker build . -t gradle:6 --file docker/gradle6.dockerfile
docker run -ti gradle:6 bash