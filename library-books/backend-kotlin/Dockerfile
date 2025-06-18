FROM cimg/openjdk:19.0

WORKDIR /home/root

ENV LANG=C.UTF-8

COPY entrypoint.sh /home/root

COPY target/backend-kotlin.jar /home/root/backend-kotlin.jar

ENTRYPOINT ["/home/root/entrypoint.sh"]

EXPOSE 8080
