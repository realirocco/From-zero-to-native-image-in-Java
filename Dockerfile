FROM ubuntu:23.04

# Install dependencies
RUN apt-get update && apt-get -y upgrade && apt-get -y install wget build-essential zlib1g-dev

# Download and install GraalVM
RUN wget https://download.oracle.com/graalvm/20/latest/graalvm-jdk-20_linux-x64_bin.tar.gz -O graalvm.tar.gz && \
    mkdir -p /usr/lib/jvm/graalvm && \
    tar -xvf graalvm.tar.gz -C /usr/lib/jvm/graalvm --strip-components 1 && \
    rm -f graalvm.tar.gz

# Download and install Maven
RUN wget https://dlcdn.apache.org/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz -O maven.tar.gz && \
    mkdir -p /usr/local/maven && \
    tar -xvf maven.tar.gz -C /usr/local/maven --strip-components 1 && \
    rm -f maven.tar.gz

# Set environment variables for Java, Maven and GraalVM
ENV JAVA_HOME=/usr/lib/jvm/graalvm
ENV MAVEN_HOME=/usr/local/maven
ENV GRAALVM_HOME=/usr/lib/jvm/graalvm

# Aggiungi le directory bin di Java, Maven e GraalVM al percorso
ENV PATH="${JAVA_HOME}/bin:${MAVEN_HOME}/bin:${GRAALVM_HOME}/bin:${PATH}"

# Set working directory
WORKDIR /app

RUN echo "alias ll='ls -lah'" >> /root/.bashrc
# Create an alias for creating a native application
RUN echo "alias b-native='mvn clean package -Pnative'" >> /root/.bashrc
# Create an alias for creating a java application
RUN echo "alias b-java='mvn clean package'" >> /root/.bashrc

RUN mkdir -p /root/.m2/repository
# Mount host volume to container
#VOLUME ["/app"]

LABEL version="1.0"
LABEL description="Ubuntu GralVM"

# Set default command
CMD ["/bin/bash"]
