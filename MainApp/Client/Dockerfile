FROM node:alpine3.11

LABEL maintainer="saksofon997@gmail.com"

WORKDIR /usr/src/Client 
COPY . .
RUN ["npm", "install"]
EXPOSE 4200
CMD [ "npm", "start"]