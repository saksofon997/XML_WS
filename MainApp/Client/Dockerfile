FROM node:alpine3.11

WORKDIR /usr/src/Client 
COPY . .
RUN ["npm", "install"]
EXPOSE 4200
CMD [ "npm", "start"]