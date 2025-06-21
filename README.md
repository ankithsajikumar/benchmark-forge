```markdown
# Benchmark Forge

A Spring Boot application for benchmarking simple CPU-bound computations. Designed for quick performance tests and demonstrations.

## Architecture

```
+-------------------+
|   User/Client     |
+--------+----------+
|
v
+--------+-------------------------+
|     Spring Boot REST API        |
|       (BenchMarkController)     |
+--------+-------------------------+
|
v
+-------------------+
|  Computation Logic |
+-------------------+
```

## Folder Structure
```
benchmark-forge/
├── docker-compose.yml
├── Dockerfile
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── perfmetrics/
│   │   │           └── benchmarkForge/
│   │   │               ├── BenchmarkForgeApplication.java
│   │   │               └── controllers/
│   │   │                   └── BenchMarkController.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/
│           └── com/
│               └── perfmetrics/
│                   └── benchmarkForge/
│                       └── BenchmarkForgeApplicationTests.java
└── target/
├── benchmark-forge-0.0.1-SNAPSHOT.jar
└── ...
```

## Features

- `/` – Welcome message  
- `/compute` – Runs a default computation  
- `/compute/{iterations}` – Runs computation for a specified number of iterations  

## Getting Started

### Build with Maven

```sh
./mvnw clean package
```

### Run with Docker

```sh
docker build -t benchmark-forge .
docker run -p 8080:8080 benchmark-forge
```

Or use Docker Compose:

```sh
docker-compose up --build
```

## GitHub Actions

A workflow is provided to build and push the Docker image to GitHub Container Registry on every push to `main`.

## API Endpoints & Sample Usage

### 1. Index Endpoint

**Request:**
```sh
curl http://localhost:8080/
```

**Response:**
```
Welcome, curious optimizer! Ready to put your CPU to the test? May your benchmarks be fast and your coffee strong.
```

### 2. Compute Endpoint (default iterations)

**Request:**
```sh
curl http://localhost:8080/compute
```

### 3. Compute Endpoint (custom iterations)

**Request:**
```sh
curl http://localhost:8080/compute/10000
```

**Response Example:**
```
Computation complete! Result: 123456.789. Iterations: 10000. Time taken: 5 ms. (From amd64 architecture)
```

## License

MIT
```
