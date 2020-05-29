# XML_WS
Project from course XML and Web Services

#### Authors
- @saksofon997
- @coperope
- @mihajlo-perendija

# Getting started

## Prerequisites: 

Ensure you have installed the following before proceeding further

- Java 8
- Node ^10.15
- npm ^6.4
- maven ^3.6
- Angular cli
- PostgreSQL ^10
- Docker for Windows (or equivalent on Linux or Mac with docker-compose)

---
In the following sections we provided a set of instructions on how to run the application during development or production.

## Development

The main application consists of multiple services which can be run using docker-compose. 

For developement purposes the main docker-compose.yml is set to use system variable `%USERPROFILE%` in order to use local .m2 directory as a docker volume. Please make sure that variable is set and that .m2 directory is listed as allowed in Docker for windows *File sharing* setting. 

**Notice about frontend service**

Since it has caused memory-related issues in the past current project setup excludes the frontend service from running in a docker container alongside other services.
Either uncomment the front service from docker-compose.yml or
Run the frontend server locally:
```
cd MainApp/Client
npm install
npm start
```
#### Important notice for Linux users
Since windows variables are not available, please define `USERPROFILE` variable pointing to the directory containg .m2:
`export USERPROFILE=path/to/.m2`

We plan to provide Linux-specific docker-compose.yml in the future.

### Running the multi-service application

From the root of the project execute:
`docker-compose up -d` 

## Production

*Final setup for complete building and running of all services will be available soon.*



