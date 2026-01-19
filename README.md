🎬 StreamFlix - Video Streaming Platform
StreamFlix is a scalable full-stack video streaming application that allows users to browse and watch movies and TV shows. It features a robust Admin Dashboard for content management and utilizes cloud infrastructure for optimized media delivery.

🚀 Live Demo
Click here to visit StreamFlix Live

🛠️ Tech Stack
Backend: Java 17, Spring Boot, Hibernate, Spring Security (JWT/Auth)

Database: PostgreSQL (Production), H2 Database (Local Development)

Frontend: HTML5, JavaScript (ES6), Tailwind CSS

Cloud Storage: Cloudinary API (Video & Image hosting)

DevOps & Hosting: Render, Maven, Git

Architecture: RESTful API, MVC Pattern

✨ Key Features
👤 User Experience
Smart Video Player: seamless playback support for direct MP4 files, YouTube embeds, Google Drive previews, and Vimeo links.

Responsive UI: Fully responsive design built with Tailwind CSS for mobile and desktop.

Instant Playback: Optimized streaming with low latency via cloud-hosted media.

🛡️ Admin & Security
Secure Authentication: User registration and login system.

Cloudinary Upload Widget: Direct-to-cloud uploading for large video files (1GB+), bypassing server timeouts and reducing load.

Content Management: Full CRUD (Create, Read, Update, Delete) capabilities for managing the movie library.

Dynamic Metadata: Auto-fetch and storage of video URLs, thumbnails, and descriptions.

⚙️ Configuration & Architecture
The application is designed to automatically switch environments:

Local Profile: Uses H2 In-Memory Database for fast testing.

Production Profile: Automatically switches to PostgreSQL when deployed on Render.

Database Schema
User: id, email, password (Encrypted), role (USER/ADMIN)

Movie: id, title, description, thumbnailUrl, videoUrl

🚀 How to Run Locally
Prerequisites
Java JDK 17+

Maven

Git

1. Clone the Repository
Bash
git clone https://github.com/your-username/streamflix.git
cd streamflix
2. Configure Environment Variables
Create a application.properties file in src/main/resources (or update the existing one). You will need your own Cloudinary keys.

Properties
# Server Port
server.port=8080

# Database (H2 for Local)
spring.datasource.url=jdbc:h2:mem:streamflix_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Cloudinary Config
cloudinary.cloud_name=YOUR_CLOUD_NAME
cloudinary.api_key=YOUR_API_KEY
cloudinary.api_secret=YOUR_API_SECRET
3. Build and Run
Bash
mvn clean install
mvn spring-boot:run
The app will start at http://localhost:8080.
