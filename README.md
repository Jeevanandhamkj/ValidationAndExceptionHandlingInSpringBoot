## Description
A RESTful API for managing student records with full CRUD operations, validation, and logging. Integrated with Spring Boot Actuator for monitoring.

## Endpoints
- `POST /student/add` – Add a student
- `POST /student/addall` – Add multiple students
- `GET /student/show/{id}` – Get student by ID
- `GET /student/showall` – Get all students
- `PUT /student/up/{id}` – Update student completely
- `PATCH /student/pat/{id}` – Partially update student
- `DELETE /student/del/{id}` – Delete student
- `GET /actuator/health` – App health check
- `GET /actuator/info` – App info
