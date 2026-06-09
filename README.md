# 🚗 MotoMetrics – Vehicle Wear & Tear Simulation System

MotoMetrics is a Java-based desktop application that simulates realistic vehicle wear and tear using Object-Oriented Programming principles and a Java Swing GUI.

The system allows users to manage vehicles, simulate trips under different driving conditions, analyze vehicle health, and receive maintenance recommendations based on driving behavior and vehicle condition.

---

## 📌 Project Overview

Vehicle maintenance is often reactive rather than proactive. MotoMetrics helps users understand how terrain, distance, load, and driving habits affect vehicle health over time.

Instead of relying on expensive hardware sensors, MotoMetrics provides a software-only simulation that models component degradation and generates actionable maintenance advice.

---

## ✨ Features

### 🔐 User Authentication

* User registration and login system
* Persistent user storage
* Session management

### 🚙 Multi-Vehicle Management

Support for:

* Cars
* Motorcycles
* Trucks

Users can:

* Add vehicles
* View vehicle details
* Remove vehicles
* Track vehicle health

### 📊 Health Monitoring System

Tracks:

* Engine Health
* Oil Level
* Air Pressure
* Tyre Health
* Suspension Health
* Brake Health
* Chain-Sprocket Health (Motorcycles)

Each vehicle receives an overall health score out of 100.

### 🛣️ Trip Simulation

Simulate trips using:

* Distance traveled
* Terrain type

  * City
  * Highway
  * Hilly
  * Off-Road
  * Mixed
* Vehicle load
* Driver behavior

### ⚠️ Driver Behavior Analysis

Detects:

* Hard Braking
* Overspeeding
* Aggressive Driving
* Long Driving Hours

### 🔧 Wear & Tear Engine

The system automatically applies realistic wear after each trip using:

* Terrain multipliers
* Driver behavior penalties
* Load penalties
* Distance-based degradation

Affected components include:

* Engine
* Oil
* Tyres
* Suspension
* Brakes
* Chain-Sprocket (Motorcycles)

### 💡 Intelligent Advice System

Provides recommendations based on:

* Vehicle health score
* Driver behavior profile

Advice categories include:

* Excellent
* Good
* Moderate
* Concerning
* Dangerous

### 💾 Data Persistence

Data is stored locally using text files:

* users.txt
* vehicles.txt

Information remains available between application sessions.

### 📄 Report Generation

Generate and save:

* Vehicle health reports
* Trip reports
* Maintenance recommendations

---

## 🏗️ Technologies Used

* Java
* Java Swing
* Object-Oriented Programming (OOP)
* File Handling
* Collections Framework (ArrayList)

---

## 🎯 OOP Concepts Implemented

### Encapsulation

Private fields with controlled access through getters and setters.

### Inheritance

Vehicle serves as the abstract parent class for:

* Car
* Motorcycle
* Truck

### Polymorphism

Vehicle subclasses override health scoring and reporting behavior.

### Abstraction

Implemented using the abstract `Vehicle` class.

### Interface Implementation

`Diagnosable` interface defines:

```java
calculateDamage()
generateReport()
```

---

## 📂 Project Structure

```text
MotoMetrics
│
├── Engine
├── Vehicle (Abstract)
│   ├── Car
│   ├── Motorcycle
│   └── Truck
│
├── DriverBehavior
├── Trip
├── User
│
├── LoginManager
├── VehicleManager
├── WearManager
├── AdviceManager
├── FileManager
│
├── LoginPanel
├── RegisterPanel
├── DashboardPanel
├── AddVehiclePanel
├── VehicleDetailsPanel
├── TripPanel
├── AdvicePanel
│
└── OOP_Final_Project (Main Class)
```

---

## 🖥️ GUI Modules

### Login Panel

User authentication and account access.

### Register Panel

Create new user accounts.

### Dashboard

View all vehicles with color-coded health scores.

### Add Vehicle

Add Cars, Motorcycles, or Trucks.

### Vehicle Details

View health statistics and maintenance reports.

### Trip Simulation

Configure trip parameters and driver behavior.

### Advice Screen

Receive recommendations and trip analysis.

---

## 📈 Health Scoring System

Vehicles receive a score between **0 and 100** based on weighted component health.

Factors include:

* Engine Health
* Oil Level
* Tyre Condition
* Suspension
* Air Pressure
* Brake Health
* Chain-Sprocket Health (Motorcycles)

Score Indicators:

| Score Range | Status     |
| ----------- | ---------- |
| 90–100      | Excellent  |
| 80–89       | Good       |
| 70–79       | Moderate   |
| 60–69       | Concerning |
| Below 60    | Dangerous  |

---

## 🚀 How to Run

### Prerequisites

* Java JDK 8 or higher
* IDE (IntelliJ IDEA, Eclipse, NetBeans, or VS Code)

### Steps

1. Clone the repository

```bash
git clone https://github.com/your-username/MotoMetrics.git
```

2. Open the project in your IDE

3. Compile and run:

```java
OOP_Final_Project.java
```

4. Register a new account and start managing vehicles.

---

## 🔮 Future Improvements

* Database integration (MySQL/PostgreSQL)
* Service reminder system
* Fuel efficiency tracking
* Graphical analytics and charts
* Additional vehicle types
* Real-time monitoring integration
* Cloud-based data synchronization

---

## 👨‍💻 Authors

**Nishat Mehdi**

**Muhammad Hadi Azeem**

---

## 📚 Academic Information

**Course:** Object-Oriented Programming (OOP)
**Project:** Final Semester Project
**Title:** MotoMetrics – Vehicle Wear & Tear Simulation System

---

### ⭐ If you found this project interesting, consider giving the repository a star!
