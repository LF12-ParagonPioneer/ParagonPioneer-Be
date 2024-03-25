# Back-End Documentation
This document provides a comprehensive guide for setting up and running the back-end environment for our application. It outlines the requirements, steps to set up the database, and instructions to run the application.
## Requirements
Before proceeding, ensure you have the following prerequisites installed on your system:
- **Java** 17: Required for running the application.
- **Gradle**: Used for building and running the application.
- **MySQL 8.1.0** or **Docker**: MySQL for database management, Docker for containerization. Rancher can be used as an alternative for Docker.

## Setting Up the Back End

### Database Setup

You have two options for setting up the database: using a local MySQL server or Docker.

#### Using Docker
To create and start the Docker environment, execute:
    
```bash
  make dev_enviroment
```

To stop the Docker environment:

```bash
 make stop_dev_enviroment
```

To reset the Docker environment to its initial state:


```bash
 make rebuild_dev_enviroment
```

#### Using Local MySQL Server

1. Ensure MySQL is installed and running.

2. Configure the application to connect to your database by editing src/main/application.properties with the following settings:

The file should look like this:

    spring.jpa.hibernate.ddl-auto=validate
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/ppAssemblyLineBa
    spring.datasource.username=root
    spring.datasource.password=password
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect


### Running the Application

After setting up the database, you can start the application using one of the following methods:

 - Using **Gradle**: Execute the command `gradle bootRun` in your terminal.
 - Using an **IDE**: Run the `main method in java/com/example/paragonPioneerBackend/PpApiApplication.java`.

#### With Docker
To run both the database and application using Docker, execute:

```bash
    make prod_enviroment
```


## Api Documentation

[Klassen Diagram](./Documentation/Code/src.png)

[Java Doc](https://htmlpreview.github.io/?https://raw.githubusercontent.com/LF12-ParagonPioneer/ParagonPioneer-Be/develop/Documentation/Java/index.html)

## Database UML

[Go To DB-Diagrams](https://dbdiagram.io/d/PP-PR-655c68de3be14957876943d3)

[UML Diagram](./Documentation/Database/ppAssemblyLineBa.png)

