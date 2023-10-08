# cat Dockerfile
FROM mysql:8.0

MAINTAINER mtobms

RUN chown -R mysql:root /var/lib/mysql/


ENV MYSQL_DATABASE=motbms1
ENV MYSQL_ROOT_PASSWORD=Job@2024


ADD motbms-db-setup-script.sql /etc/database/motbms-db-setup-script.sql

RUN sed -i 's/MYSQL_DATABASE/'$MYSQL_DATABASE'/g' /etc/database/motbms-db-setup-script.sql
RUN cp /etc/database/motbms-db-setup-script.sql /docker-entrypoint-initdb.d