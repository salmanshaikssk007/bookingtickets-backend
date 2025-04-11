# 🎟️ Event Ticket Booking Platform – Backend

This is the **backend service** for an Event Ticket Booking Platform built with **Spring Boot**. It powers a full-stack web application that allows users to discover events, book tickets, manage bookings, and more.

> 🔗 Frontend repo: [bookingtickets-frontend](https://github.com/salmanshaikssk007/bookingtickets-frontend)

---

## 🚀 Features

- User authentication and role-based authorization (JWT)
- Event creation and management
- Ticket booking and cancellation
- Email notifications using AWS SES
- File storage with AWS S3
- PostgreSQL integration on AWS RDS
- Containerized with Docker
- Infrastructure provisioning via Terraform

---

## 🧱 Tech Stack

- **Backend:** Java, Spring Boot, Spring Security, JWT
- **Database:** PostgreSQL (hosted on AWS RDS)
- **Cloud Services:**  
  - AWS EC2 (backend hosting)  
  - AWS RDS (database)  
  - AWS S3 (file storage)  
  - AWS SES (emails)
- **DevOps:** Docker, Terraform
- **Testing:** JUnit, Mockito

---

## 📁 Project Structure
```bash
bookingtickets-backend/
├── src/
│   ├── main/java/com/example/bookingtickets/
│   └── test/java/com/example/bookingtickets/
├── application.properties
├── Dockerfile
└── README.md
```
---

## 🛠️ Getting Started

### Prerequisites

- Java 17+
- Maven
- Docker
- PostgreSQL (or configure AWS RDS)
- AWS credentials (if using AWS services)

### Running Locally

```bash
# Clone the repository
git clone https://github.com/salmanshaikssk007/bookingtickets-backend.git
cd bookingtickets-backend

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

#or
#Run it via Docker
```bash
docker build -t booking-backend .
docker run -p 8080:8080 booking-backend
```
##🔐 Environment Variables
```bash
spring.datasource.url=jdbc:postgresql://<host>:5432/<db>
spring.datasource.username=your_username
spring.datasource.password=your_password

aws.accessKeyId=YOUR_AWS_KEY
aws.secretAccessKey=YOUR_AWS_SECRET
aws.region=us-east-1

jwt.secret=YOUR_JWT_SECRET
```
## API Endpoints
| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| POST   | /api/auth/signup   | User Registration        |
| POST   | /api/auth/login    | Login & Token Generation |
| GET    | /api/events        | List all events          |
| POST   | /api/events        | Create new event         |
| POST   | /api/bookings      | Book tickets             |
| ...    | ...                | More routes in development |

## 📌 Future Improvements
- Payment integration (Stripe/PayPal)
- Admin dashboard analytics
- Rate limiting & request validation
- Unit and integration test coverage

## Author
-Salman Shaik
📧 salmanshaikssk007@gmail.com
🔗 GitHub • LinkedIn
