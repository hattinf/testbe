# flextoolbe
This is the SpringBoot Project + Swagger UI for the "Flex app" backend.

## Project Set-Up
Further bellow you will find instructions on how to run the project in your development
environment!
### PostgreSQL and Adminer 
`PostgreSQL` is used as the DB for the project and `Adminer` is the DB web viewer/editor. We will use 
adminer to run SQL import, to load data and view DB table structure and make sure everything is running 
smoothly and correct on the DB side.

To run these services we will use `Docker` via `docker-compose up`:
1. Open terminal in the root of the project.
2. Run `docker-compose up` and docker should pull and initialize dev-containers. Docker will also initialize DATA VOLUME to store SQL data on your machine.

### Run project
Use InteliJ for best IDE development.

## Getting Started
Every API endpoint can be found "http://localhost:8080/swagger-ui/index.html#/"

