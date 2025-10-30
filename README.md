# Frog Autonomous Navigation System – Team Building Challenge

This project was developed during my first year at Saxion University of Applied Sciences for the module **Project Team Building Challenge (Q4)**.  

The goal of this module was to work as a team and apply Scrum to develop a real Java application that controls a simulated exploration robot called **The Frog**.  

The Frog needed to explore an asteroid environment, find TNT and a Detonator, avoid obstacles, and return to the base to complete its mission.  

Our team built a **Pilot Application** in Java that communicates with The Frog through the **SaSaCommunicator** library, sending commands and receiving live radar and status data.

---

## Project Overview

The main task was to develop an autonomous navigation system that can:
- Move automatically using radar and sensor input
- Detect and avoid obstacles
- Manage energy and charging
- Collect and drop objects at the correct location
- Log mission data to a database

The project was fully developed in Java using Maven and SQLite for data handling, following Scrum principles through GitLab.

---

## Key Features
- Autonomous driving using radar and real-time sensor data  
- Communication with The Frog using a custom SaSa protocol  
- Obstacle detection and navigation algorithm  
- Energy management and charging logic  
- Automatic item pickup and drop system  
- Database integration using SQLite and JDBC  
- Scrum-based teamwork and version control using GitLab  

---

## Technologies Used
- Java 21  
- IntelliJ IDEA Ultimate Edition  
- SaSaCommunicator Library (provided by the course)  
- SQLite with JDBC  
- Maven for project and dependency management  
- GitLab for team collaboration  



---

## How It Works
1. The Pilot application connects to The Frog through the SaSaServer using the SaSaCommunicator library.  
2. The Pilot sends commands such as `DRIVE`, `RADAR ON`, and `STATUS ON`.  
3. The Frog responds with real-time data like position, radar scan, and energy level.  
4. Based on this feedback, the Pilot decides what to do next (drive, turn, charge, or stop).  
5. All mission information is stored in a local database for review and analysis.  
6. The mission completes when The Frog returns to the base with TNT and the Detonator placed correctly.

---

## Team Members
**Team 3 – Saxion University of Applied Sciences**  
- Kiarash Delavar  
- Kian Kamphuis  
- Max De Croon  
- Emran Mohammadi  
- Elham Dawlati  

Each member contributed to design, implementation, documentation, and testing under the Scrum process.

---

## How to Run
### Requirements
- Java JDK 21  
- IntelliJ IDEA (or any Maven-compatible IDE)  
- Frog simulator and SaSaServer (provided by the course)  
- SaSaCommunicator.jar (included in /lib folder)

### Steps
1. Clone the repository  
2. Open the project in IntelliJ IDEA  
3. Make sure the simulator is running  
4. Run `PilotApp.java` from `nl.saxion.ptbc.pilot`  
5. Observe The Frog’s navigation and log output  

> Note: The simulator and SaSaServer executables are not included in this repository because they are part of the course materials.

---

## Learning Goals

**Competence** | **Learning Goal**
--- | ---
SW/ANA/1 | Elaborate functional requirements of the application in acceptance tests  
SW/ANA/1, SW/ONT/1 | Develop an algorithm for automatically steering a vehicle  
SW/ONT/1 | Create technical documentation useful for software engineers to extend the application  
SW/REA/1 | Work in a team using a cloud Git environment (GitLab)  
PS/DI/SAM | Apply Scrum, communicate progress, and reflect on teamwork improvement  
SW/ONT/1, SW/REA/1 | Design and implement a database for domain data storage and queries  
SW/REA/1 | Develop software following learned coding standards and given requirements  

---

## What I Learned

Through this project I learned how Scrum, Git, and GitLab can support teamwork in software development.  
I also improved my Java skills by building a real application that combines GUI development, database handling, and autonomous system control.

---

## Reflection

This project was an amazing experience where we worked as a real software development team.  
It helped me understand how software can control hardware systems and how communication and collaboration are key to successful teamwork.
