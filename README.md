# EventHub: Event Management System

An Object-Oriented Programming (OOP) project designed to streamline and manage events effectively for venues such as hotels. Built with scalable software architecture in mind, this project demonstrates core software engineering principles, clean code structure, and robust documentation practices.

## 📌 Overview

EventHub provides a comprehensive digital approach to event management. It serves as a centralized platform connecting administrators, event organizers, and attendees. Designed with a clear separation of concerns, the application leverages Java and CSS to deliver a cohesive experience while maintaining a well-documented backend architecture.

## ✨ Features

The system features a role-based architecture, ensuring that each type of user has access to the specific tools they need:

* 👤 **For Attendees (Users):**
  * Browse and search for upcoming events.
  * Purchase tickets to reserve a spot.
  * Easily cancel ticket reservations when plans change.

* 📅 **For Organizers:**
  * Create and launch new events.
  * Oversee and manage the guest list for their specific events.
  * Track attendance and event capacity.

* 🛡️ **For Administrators:**
  * Global oversight of all registered users on the platform.
  * Full control to add, edit, delete, or suspend any event.
  * Advanced search capabilities to monitor platform activity.

## 🏗 Architecture & Design

A strong emphasis was placed on software architecture and OOP design patterns during development. The project utilizes a detailed UML Class Diagram to map out the system's core components, relationships, and inheritance hierarchies.

* 📊 [**View the full UML Class Diagram**](https://drive.google.com/file/d/1AlkZzupSHozEBaeerADNIdqY_HD3tBJi/view?usp=sharing)

## 🚀 Technologies Used

* **Backend logic:** Java (98%)
* **UI/Styling:** CSS (2%)
* **Build & Dependency Management:** Maven

## 📁 Folder Structure

The workspace is logically organized to separate source code from dependencies and compiled binaries:

```text
├── src/main/  # Core source code and application logic
├── lib/       # External dependencies and libraries
├── bin/       # Compiled output files (generated automatically)
├── .vscode/   # Workspace settings and configurations
└── pom.xml    # Maven project object model
```

## ⚙️ Setup and Installation

Follow these steps to set up and run the application locally. The project uses Maven wrapper scripts, so you do not need to install Maven globally on your machine.

### 1. Clone the repository:

```text
git clone [https://github.com/OMZaky/EventHub.git](https://github.com/OMZaky/EventHub.git)
cd EventHub
```

### 2. Compile the project:

Windows:
```text
mvnw.cmd clean compile
```
Mac/Linux:
```text
./mvnw clean compile
```

### 3. Run the application:
```text
mvnw.cmd exec:java -Dexec.mainClass="Main" 
```

(Note: Update `"Main"` with the actual package and class name of your main file if it is nested, 
e.g., `"com.eventhub.Main"`)

## 👥 Meet Team

This project is proudly developed by students of Ain Shams University, Faculty of Engineering, majoring in Computer and Artificial Intelligence Engineering:

**Fahd Khaled Khataan (24P0073)**

**Omar Akram Ahmed (24P0071)**

**Omar Ahmed Galal (24P0271)**

**Omar Mohamed Khamis (24P0117)**

**Yassin Bassem Ibrahim (24P0378)**

**Ziad Amr Mohamed Sobhi (24P0062)**
