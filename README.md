# 🏥 **Patient Management Domain**

## 📖 Description
The **Patient Management** domain is responsible for managing all patient-related information within the hospital system. Each CRUD action is designed as an independent microservice to ensure **modularity, scalability, and maintainability**.

---

## 🔹 Microservices

### ❌ **4. Delete Patient**
- **📌 Description:** Removes a patient from the system.
- **🔹 Method:** `DELETE`
- **🔗 Dependencies:** Patient database 🗄️
- **📥 Inputs:** `Patient ID`
- **📤 Outputs:** Deletion confirmation 🗑️

---

## 🛠️ **Technologies Used**
- **⚙️ Backend:** Java, Spring Boot 💻
- **🗄️ Database:** PostgreSQL 🐘

---

## 🔗 **Integrations**
- **🩺 Medical Care Domain:** Patient data is used for scheduling medical appointments.
- **🩹 Clinical History Domain:** Patient data is used for recording their clinical history.
