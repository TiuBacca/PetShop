FROM node:alpine as angular

WORKDIR /app

COPY dist ./dist

FROM nginx:alpine

VOLUME /var/cache/nginx

COPY --from=angular app/dist/controle-horas /usr/share/nginx/html

COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf