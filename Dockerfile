ARG BUILDER_IMAGE=maven:3-jdk-11
ARG RUNNER_IMAGE=adoptopenjdk/openjdk11:jre

FROM $BUILDER_IMAGE as builder

WORKDIR /workspace

COPY . ./
RUN mvn --batch-mode \
        --errors \
        --define maven.test.skip=true \
        --define java.awt.headless=true \
        --activate-profiles production \
        clean package


FROM $RUNNER_IMAGE as runner

LABEL \
  org.label-schema.schema-version="1.0" \
  org.label-schema.name="Password Generator" \
  org.label-schema.description="密码生成器Web应用"

RUN apt-get update \
 && apt-get install -y --no-install-recommends \
            graphviz \
            fonts-wqy-zenhei \
 && rm -rf /var/lib/apt/lists/*

COPY --from=builder /workspace/target/password-generator.jar /password-generator.jar

ENV JAVA_OPTS=""

EXPOSE 8080/tcp

ENTRYPOINT java $JAVA_OPTS \
    -Xtune:virtualized \
    -Djava.security.egd=file:/dev/./urandom \
    -noverify \
    -XX:TieredStopAtLevel=1 \
    -jar /password-generator.jar
