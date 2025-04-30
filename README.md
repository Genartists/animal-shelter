# Animal Sheltering 

A CLI-based inventory management system for animal shelter. 

## About 

This application helps animal shelters manage their pet inventory, including adoption tracking and vaccine records with various functionalities.

## Features

- CRUD operations to on animal records in database 
- Search and filter by species or name
- Dockerized backend
- Configurable database environment

## Tech Stack

- **Backend:** Java, JDBC, MySQL  
- **Build Tool:** Maven  
- **Containerization:** Docker, Docker Compose  
- **Database:** MySQL 
- **Others:** Properties file for environment config, File I/O

## ## Installation & Usage

### Prerequisites

- Java 21+
- Maven
- Docker & Docker Compose 

### Clone the Repository

```bash
git clone https://github.com/Genartists/animal-shelter.git
cd animal-shelter (move to the project folder)
```

#### 1. Installations with Docker compose

- Build the server in background first using command: 
```bash
docker compose up -d --build
```

- Build run the application by using command:
```bash
	docker compose run --rm app
```

#### 2. Usage

After running the application:

1. Select an action from the console menu (e.g., create, update, delete animal).
    
2. Input required details when prompted.
    
3. Use the features to manage animals information.

## Configuration

The application uses environment variables or `.properties` files to manage DB credentials.

*Note:*  In order to connect with Mysql server in docker, you need to have a *db.properties* file.
In the project folder, locate resources folder (Path: src/main/resources) where you can find a file name *db.properties.example*. Now rename the file by remove the .example part. After finish we can start to build and run the whole application with Docker compose.

- `db.properties`

Example `db.properties`:
```properties
db.url=jdbc:mysql://db:3306/animalshelterdb
db.username=__YOUR_DB_USER__ 
db.password=__YOUR_DB_USER__
```

## License

[MIT](https://choosealicense.com/licenses/mit/)

