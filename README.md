# My Countries Application

In response to 28Stone task.
Welcome to My Countries Application!
This application allows you to explore information about countries around the world, including details about individual countries, their borders, and more.


## Table of Contents
- [Task](#Task)
- [Additions](#Additions)
- [Features](#features)
- [Usage](#Usage)
- [REST](#REST)


## Task

Functional requirements:
1. Create an application that would consume data from https://date.nager.at/ and expose some logic via REST call.
2. Implement the following REST call: accept a country code, provide a list of all border countries.
3. Add unit tests.

Non-functional requirements:
1. Java 11 or higher, Spring Boot as a framework, Maven as a build tool


## Additions

-  All countries service to explore all available countries and their international codes.
-  Country Info request validator (because I really want to help end user to obtain required country information).
-  Web interface to explore information in another convenient way.


## Features

-  Get list fo available countries and their international codes
-  Discover detailed country information.
-  Retrieve information about a specific country.
-  Explore the borders of a country.


## Usage

-  View Countries: Click on "View Countries" to explore available countries and their international country codes.
-  Country Information: Click on "Country Info" to retrieve information about a specific country. Enter the country code (e.g., "US") and click "Get Info."
-  Country Borders: Click on "Country Borders" to explore the borders of a country. Enter the country code and click "Get Borders."


## REST

-  Api documentation: http://localhost:8080/swagger-ui/index.html
