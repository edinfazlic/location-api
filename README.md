# Location Api
This application serves as a backend server exposing several apis for locations management.

## Meta info
Application showcases knowledge of:
- Java programming language
  - Javadoc covering services
- Spring framework
  - Application setup and initialization
  - Annotation utilization
    - Controller and Service definition
    - Exposed requests mapping
      - CRUD (POST, PUT, DELETE, GET)
      - Receiving objects as body, path variable and request param
    - Dependency injection (on constructor)
    - Cross origin handling
  - CRUD (Mongo) repository
      - Query methods
- Data layer
  - Document database (Mongo) setup / NoSQL
- Swagger (for application specification)
- General
  - Services defined as interfaces with separate implementation
  - REST calls
    - GET with request parameters and path variable
    - DELETE with path variable
    - POST and PUT with body
  - Helpers and utility classes with mostly static content
  - Having git commit messages readable
- Concepts
  - Separation of concerns
  - "N-tier architecture"
    - Controllers expose api endpoints
    - (Business) Logic is found inside services
    - Data layer is accessed from repositories
- Operations
  - Setting environment variables (for production and development differentiation)
  - Deploying application

## Requirements
1. Java - 11.x
1. Maven - 4.x.x
1. MongoDB - local database, host: localhost:27017, database: test; *or see environment variable under **Development server*** 

## Development server
1. Build and run the backend app using maven
   ```bash
   mvn package
   java -jar location_api-0.0.1-SNAPSHOT.jar
   ``` 
   Alternatively, you can run the app without packaging it, using:
   ```bash
   mvn spring-boot:run
   ```
1. Provide MONGODB_URI environment variable, unless using Mongo database found at localhost (default port: 27017)
