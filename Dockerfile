FROM eclipse-temurin:24-alpine

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY build/libs/online-product-shop.jar ${runningFolder}/online-product-shop.jar

COPY entrypoint.sh ${runningFolder}

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]
