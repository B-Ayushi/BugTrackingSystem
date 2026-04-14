# 🐞 Bug Tracking System

A full-stack Java web application to manage and track software bugs with role-based access for **Admin** and **Users**.
The system allows reporting, assigning, tracking, and resolving bugs in a structured workflow similar to tools like Jira.

---

## 🚀 Features

### 👤 User

* Sign Up / Login
* Report new bugs
* View bugs reported by them
* View bugs assigned to them
* Update bug status:

  * In Progress
  * Closed

### 🛠 Admin

* View all unassigned bugs
* Assign bugs to users
* Set:

  * Priority
  * Deadline
  * Comments
* Track assigned bugs in real-time
* Filter assigned bugs by:

  * In Progress
  * Closed

---

## 🧠 Workflow

1. User reports a bug
2. Admin assigns the bug to a user
3. Assigned user updates status to **In Progress**
4. Once completed, user marks it as **Closed**
5. Admin monitors progress through Assigned Tasks

---

## 🏗 Tech Stack

| Layer           | Technology                |
| --------------- | ------------------------- |
| Backend         | Java (Servlets, JSP)      |
| Frontend        | JSP, HTML, CSS, Bootstrap |
| Build Tool      | Maven                     |
| Server          | Apache Tomcat             |
| Database        | Firebase Firestore        |
| Version Control | Git & GitHub              |

---

## 📁 Project Structure

```
BugTrackingSystemfinal/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.bugtrackerfinal/
│   │   │       ├── dao/
│   │   │       ├── model/
│   │   │       ├── servlet/
│   │   │       └── util/
│   │   │
│   │   ├── resources/
│   │   │   └── firebase-key.json (ignored)
│   │   │
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       ├── *.jsp
│   │       └── style.css
│
├── pom.xml
└── README.md
```

---

## ⚙️ Setup Instructions

### 1. Clone the repository

```
git clone https://github.com/B-Ayushi/BugTrackingSystem.git
cd BugTrackingSystem
```

---

### 2. Configure Firebase

* Go to Firebase Console
* Create a project
* Enable **Firestore Database**
* Generate Service Account Key
* Place JSON file in:

```
src/main/resources/firebase-key.json
```

⚠️ This file is ignored in Git for security.

---

### 3. Build the project

```
mvn clean package
```

---

### 4. Deploy on Tomcat

* Copy generated `.war` file from:

```
target/BugTrackingSystemfinal.war
```

* Paste into:

```
apache-tomcat/webapps/
```

* Start Tomcat server

---

### 5. Run the application

Open browser:

```
http://localhost:8080/BugTrackingSystemfinal/
```

---

## 🔐 Security Note

* Firebase service key is **not uploaded** to GitHub
* Always keep credentials secure
* If exposed, regenerate the key immediately

---

## ✨ Future Enhancements

* Email notifications for assignments
* Dashboard analytics (charts)
* Role-based access improvements
* File/image attachments in bugs
* REST API integration

---

## 👩‍💻 Author

**Ayushi Bindroo**
Java Full Stack Project 

---

## ⭐ If you like this project

Give it a star on GitHub ⭐
