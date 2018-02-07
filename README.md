# Invoicer Api

[![Build Status](https://travis-ci.org/caelwinner/invoicer-api.svg?branch=master)](https://travis-ci.org/caelwinner/invoicer-api)

## Overview

Rest Application that provides a series of endpoints to manage invoices

## Requierments
- Docker 1.7
- Java 1.8


## Build and Run docker image

In order to build and image use:
- ./gradlew buildDocker

if you want to push it
- docker push adolfoecs/invoicer-ui:0.1.0-SNAPSHOT

In order to run the image use:

- docker run -p 8580 --name invoicer-ui-instance1 -t adolfoecs/invoicer-ui:0.1.0-SNAPSHOT

## Running

In order to run the app just execute:

./gradlew bootrun

To build a jar and run all the tests (Unit and e2e):

./gradlew test assemble

the application run on http://localhost:8080.