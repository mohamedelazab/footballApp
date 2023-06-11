# footballApp

app that show list of matches using football-data api 

## Architecture 

### MVI pattern 
it helps to build a project in a reactive way and we can easly describe every thing as a ui state ineract with stream from datasources throw the viewmodel without knowing anything about the core business or whats is happening in the domain layer.


## Tools:

### mvrx https://github.com/airbnb/mavericks
it's a third party library powered by airbnb that help in implement MVI pattern in a smooth way.

### epoxy https://github.com/airbnb/epoxy
It's a third party library powered by airbnb that help to build complex screen in a an easy and modular way and keep you away from adapters hassle. Actually it generate adapter under the hood for you if you are implementing tgings in a receclerview

### hilt
dependency injection tools that make things easy for init objects and make live easier in both code and testing

### rxJava, rxAndroid
third party for handling async processes in the app and have awesome operators that make life easier and make code more readable

### retrofit
For make network Calls

### mockk https://mockk.io/
easy mocking tool for unit tests

### threetenabp https://github.com/JakeWharton/ThreeTenABP
replacement for the ailing Date and Calendar APIs

##Additional info

To run the application in your device after clone do these steps
- reach https://www.football-data.org/ then get api key and 
- add in in local.properties file after clone as API_TOKEN=value
