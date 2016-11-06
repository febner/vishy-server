[![Build Status](https://travis-ci.org/theborakompanioni/vishy-server.svg?branch=master)](https://travis-ci.org/theborakompanioni/vishy-server)

vishy-server
===

## build and run
### jar
`mvn clean package && java -jar vishy-server-web/target/vishy-server-web-<version>.jar`

### docker
`mvn -pl vishy-server-web docker:build`
`docker run -t -i -p 8080:8080 tbk/vishy-server-web`

## ui
visit http://localhost:8080/static/vishy-ui/index.html
