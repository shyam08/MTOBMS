# cat Dockerfile
FROM mysql:5.7

MAINTAINER mtobms

RUN chown -R mysql:root /var/lib/mysql/


ENV MYSQL_DATABASE=motbms
ENV MYSQL_ROOT_PASSWORD=Job@2024


ADD db-script.sql /etc/database/db-script.sql

RUN sed -i 's/MYSQL_DATABASE/'$MYSQL_DATABASE'/g' /etc/database/db-script.sql
RUN cp /etc/database/db-script.sql /docker-entrypoint-initdb.d