# LeucineAssignement

**College Directory Application**

The College Directory Application facilitates seamless interaction among students, faculty members, and administrators within a college environment. It allows users to manage and access personal, academic, and administrative information efficiently.

**Features**
**1. Login Page**

User Interface: Simple form with fields for Username, Password, and Role selection (Student/Faculty Member/Administrator).

Functionality: Validates user credentials against the database and redirects users to their respective dashboards based on their role. Implements error handling for incorrect credentials.

**2. Student Interface**
1. View Personal Profile

User Interface: Displays personal information (name, photo, contact details) and academic information (courses, grades, attendance).

Functionality: Fetches and displays the student's profile details from the database.

2. Search for Other Students

User Interface: Search bar with filters for name, department, or year.

Functionality: Implements search functionality to query the database and displays matching student details dynamically.

3. Contact Faculty Advisors

User Interface: Lists assigned faculty advisors with contact options (email, phone).

Functionality: Fetches and displays advisor details from the database, providing contact links for email or phone.

**3. Faculty Member Interface**

1. Manage Class List

User Interface: Lists students in the faculty member's courses with columns for names, photos, and contact information.

Functionality: Fetches and displays class list details from the database.

2. Update Profile

User Interface: Form to update office hours, contact email, and phone number.

Functionality: Validates and saves updated profile information to the database, ensuring visibility to students.

**4. Administrator Interface**

1. Manage Student and Faculty Records

User Interface: Form for CRUD operations (Create, Read, Update, Delete) on student and faculty records. Lists records with options for CRUD operations.

Functionality: Implements CRUD operations for student and faculty records in the database, reflecting immediate changes in the directory.

2. Dashboard with Graphs

User Interface: Interface to view graphical representations (charts, graphs) of key data points (student enrollment trends, faculty course loads).

Functionality: Fetches and aggregates data for graphical display, providing interactive elements for data visualization.

**Endpoints**
**Authentication**

POST /api/auth/login: Authenticates user and returns JWT token.

GET /api/auth/logout: Logs out the user and invalidates JWT token.

**Student Interface**

GET /api/students/{studentId}/profile: Retrieves student's profile details.

GET /api/students/search?q={query}: Searches for students based on name, department, or year.

GET /api/students/{studentId}/advisors: Retrieves faculty advisors assigned to the student.

**Faculty Member Interface**

GET /api/faculty/{facultyId}/courses: Retrieves courses taught by the faculty member.

GET /api/faculty/{facultyId}/profile: Retrieves faculty member's profile details.

PUT /api/faculty/{facultyId}/profile: Updates faculty member's profile details.

**Administrator Interface**

GET /api/admin/student: Retrieves all student records.

POST /api/admin/student: Creates a new student record.

PUT /api/admin/student/{studentId}: Updates an existing student record.

DELETE /api/admin/student/{studentId}: Deletes a student record.

GET /api/admin/faculty: Retrieves all faculty records.

POST /api/admin/faculty: Creates a new faculty record.

PUT /api/admin/faculty/{facultyId}: Updates an existing faculty record.

DELETE /api/admin/faculty/{facultyId}: Deletes a faculty record.

GET /api/admin/dashboard: Retrieves data for graphical representation (charts, graphs).



**Technologies Used**

**Frontend**

HTML, CSS and JavaScript

**Backend**

Framework: Spring Boot (Java)

Database: PostgreSQL

ORM: Hibernate

Authentication: JSON Web Tokens (JWT)
