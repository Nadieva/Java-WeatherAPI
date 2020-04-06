# Java-WeatherAPI

User enters city and country to display the weather forecasts of the requested location. User can read and compare data from different weather providers(APIs).

At the moment, the Accu weather API is not stable.

Please note that I might update this project.

 Please following the instructions:
- download the project
- unzip it
- import it to IntellIJ
- make sure to have mySQL 8.0
- update the mysql credentials (username:"test", "password")
- create two databases for this user:db_weather_providers and db_cities
- launch WeatherApplication.java
- go to localhost:8080 in your web browser
- click on the link to go to the city form
- enter a city and country (for example, "Paris" and "France")
- the now's weather forecasts in the city are displayed.
- you can also have a look at the REST API. For example, with Postman:
  - get all providers:http://localhost:8080/providers
  - get the Open WeatherMap forecast of a city (Paris,France):localhost:8080/providers/open?city=Paris&country=France
  - add/create a city http://localhost:8080/cities?city=Madrid&country=Spain
  - delete a provider with id#5: http://localhost:8080/providers/5
  - delete all cities : http://localhost:8080/cities
  - delete a city with id#1: http://localhost:8080/cities/1
  - get all cities you have added or created: http://localhost:8080/cities
