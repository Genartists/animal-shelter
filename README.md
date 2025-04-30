# Animal Sheltering

A CLI-based inventory management system for animal shelters.

## About

This application helps animal shelters manage their pet inventory, including adoption tracking, vaccine records, and other related functionalities.

## Features

- Full CRUD operations on animal records in the database
    
- Search and filter animals by species or name
    
- Dockerized backend with Docker Compose support
    
- Configurable database environment via properties files

## Tech Stack

- **Backend:** Java, JDBC
- **Database:** MySQL
- **Build Tool:** Maven
- **Containerization:** Docker, Docker Compose
- **Other:** File I/O, `.properties` configuration files

## Installation & Usage

### Prerequisites

- Java 21+
- Maven
- Docker & Docker Compose
**Note:** Make sure Docker Desktop is installed and running before executing Docker commands.

### Clone the Repository

```
git clone https://github.com/Genartists/animal-shelter.git
cd animal-shelter
```
### 1. Run with Docker Compose

Build and start the MySQL server in the background:

```
docker compose up -d --build
```

Run the application:

```
docker compose run --rm app
```

### 2. Usage Instructions

After launching the app:

1. Choose an action from the menu (e.g., create, update, delete animal).
2. Enter the required data when prompted.
3. Use search and filter features to manage animal records.

## Configuration

The application uses `.properties` files to configure database access.

**Important:**  
To connect to the MySQL server in Docker, you must create a `db.properties` file.

1. Go to: `src/main/resources/`
    
2. Rename `db.properties.example` to `db.properties`
    
3. Update the file with your credentials:
    

```
db.url=jdbc:mysql://db:3306/animalshelterdb
db.username=your_username
db.password=your_password
```

Once configured, you can build and run the application with Docker Compose.
## License

This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/)
