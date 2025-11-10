# 🎓 Student Management REST API 

This project implements a simple Student Management API using **Spring Boot 3** and **Spring Data JPA** with an in-memory **H2 database**. It meets the core assignment requirements, including RESTful endpoints, data validation, service-layer separation, and global exception handling.

---

## 🚀 1. Setup and Execution

### Prerequisites
* **Java Development Kit (JDK):** Version 17 or higher
* **Apache Maven:** Version 3.8 or higher
* **Git** (for cloning)

### Steps to Run
1.  **Clone the Repository:**
    ```bash
    git clone [YOUR_REPOSITORY_URL]
    cd student-management-api
    ```
2.  **Build and Run the Application:**
    Use the Maven Spring Boot plugin to run the application in the foreground.
    ```bash
    mvn spring-boot:run
    ```
3.  **Access:** The API will be running on **`http://localhost:8080`**.

---

## 🛠️ 2. Key Technology Details

| Component | Detail |
| :--- | :--- |
| **Framework** | Spring Boot **v3.5.7** |
| **Java Version** | JDK **17** |
| **Persistence** | Spring Data JPA (Hibernate) |
| **Database** | **H2 In-Memory** (Data reset on every restart) |
| **Validation** | Jakarta Bean Validation (`@Valid`, `@Email`, `@Min`, etc.) |
| **Exception Handling**| Global `@ControllerAdvice` for graceful error responses. |

### H2 Database Console
The H2 console is enabled for direct data inspection:

* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:studentdb`
* **Username:** `sa`
* **Password:** (Leave Blank)

---

## 🌐 3. API Endpoints

The API is accessible under the base path **`/api/students`**. All endpoints return JSON data.

| Feature | Method | Endpoint | HTTP Status Codes | Description |
| :--- | :--- | :--- | :--- | :--- |
| **Add Student** | `POST` | `/api/students` | **201 Created**, **400 Bad Request** | Creates a new student record. |
| **Get All Students** | `GET` | `/api/students` | **200 OK** | Retrieves a list of all student records. |
| **Get Student By ID** | `GET` | `/api/students/{id}` | **200 OK**, **404 Not Found** | Retrieves a single student by primary key ID. |
| **Update Student** | `PUT` | `/api/students/{id}` | **200 OK**, **404 Not Found**, **400 Bad Request** | Fully updates an existing student record. |
| **Delete Student** | `DELETE` | `/api/students/{id}` | **204 No Content**, **404 Not Found** | Deletes the student record by ID. |

### Example POST Request Body
| Field | Type | Constraint |
| :--- | :--- | :--- |
| `name` | String | Not Blank |
| `email` | String | Valid format, **Unique** |
| `course` | String | Not Blank |
| `age` | Integer | $>= 18$ |

```json
{
    "name": "Alex Johnson",
    "email": "alex.j@example.com",
    "course": "Microservices and Cloud Computing",
    "age": 28
}
```

## 4. Containerization (Docker Support)

You can containerize and deploy this API using Docker for easy distribution and consistent runtime environments.

Steps to Containerize

1. Build the JAR File<br>
Use Maven to package the application into a JAR:

```bash
mvn clean package -DskipTests
```


2. Build Docker Image<br>

Build a Docker image with the JAR file:
```bash
docker build -t student-api .
```


3. Run the Container<br>

Run the container and map it to port 8080:
```bash
docker run -p 8080:8080 student-api
```

4. Access the API<br>
The application will be available at:
```bash
http://localhost:8080
```

## 👨‍💻 Developer

Chamodi Chethana<br>
ICT/21/829<br>
Undergraduate – University of Sri Jayewardenepura<br>
Faculty of Technology



