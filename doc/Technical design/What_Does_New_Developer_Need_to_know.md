# What Does a New Developer Need to Know to Extend This Code?

**Project:** Team Building Challenge – *“The Frog”*  
**Group:** 3 (DHI1V.SP) – Repo 02  

**Team Members:**  
- Kian Kampuhis  
- Kiarash Delavar  
- Elham Dawlati  
- Emran Mohammadi  
- Max De Croon  

**Teachers:** Mr. Frank Van Doorn & Mr. Remco Van Maanen

---

## 1. Project Overview

This project simulates a frog robot that can be controlled and monitored through two applications: **Ground Control** and **Pilot App**. The system includes real-time data display, radar interface, mission logging, CSV file handling, and auto-driving.

**Technologies Used:**
- **Java**: Core programming language
- **JavaFX**: For building user interfaces
- **SQLite**: For local data storage

---

## 2. Code Structure

The code is located in:  
`src/main/java/nl/saxion/ptbc/`

### 2.1 `classes/`
- `Frog.java`: Controls frog logic and behavior (e.g., movement, energy).
- `Location.java`: Stores x, y coordinates.
- `Obstacle.java`: Holds information about map obstacles.
- `Map.java`: Manages the environment/map.
- `RadarPoint.java`: Stores radar detection data.

### 2.2 `pilot/`
- `PilotApp.java`: Main JavaFX application for the pilot.
- `RadarSystem.java`: Processes radar logic.
- `RadarView.java`: Draws the radar view using JavaFX.

### 2.3 `groundControl/`
- `GroundControlApp.java`: Starts the ground control interface.
- `GroundControlController.java`: Handles logic for the UI.
- `GroundControlUtils.java`: Helper functions (e.g., screen switching).

### 2.4 `missionLog/`
- `MissionLog.java`: Represents a saved mission.
- `MissionLogController.java`: Controls the mission log UI.
- `ReplayMission.java`: Replays previously stored missions.
- `MissionStatus.java`: Defines mission status types (e.g., finished).
- `MissionLogDAO.java`: Connects mission logs with the database.

### 2.5 `CSVLoader/`
- `CSVLoaderController.java`: UI logic for CSV import/export.
- `CSVLoaderException.java`: Custom error handler for CSV issues.

### 2.6 `database/`
- `SQLiteConnection.java`: Connects to the SQLite DB.
- `ObstacleDatabaseHandler.java`: Reads/writes obstacle data.
- `SQLiteCreateTable.java`: Creates new tables.
- `SQLiteInsertInTable.java`: Adds data.
- `SQLiteSelectTable.java`: Reads data.
- `SQLiteDropTable.java`: Deletes tables.

### 2.7 `module-info.java`
Defines module dependencies like `javafx.controls`, `java.sql`, etc.

### 2.8 `resources/` and `target/`
- `resources/`: CSV files, protocol docs, images.
- `target/`: Auto-generated files after building the app.

---

## 3. How to Extend the Code

- **Understand the structure first.** Know whether you're editing Pilot App, Ground Control, or database logic.
- **Read JavaDocs.** They explain each method and class clearly.
- **Add new classes** to the appropriate package. UI changes go in controllers. Logic goes in model or utility classes.
- **Test in both apps** to make sure your feature works everywhere.
- **Follow JavaFX UI standards** when creating buttons or screens.
- **Use `SQLiteConnection`** for any database work.

---

## 4. Development Best Practices

 1- Use clear and descriptive names  
 2- Separate logic and UI properly  
 3- Add JavaDocs to every method and class  
 4- Pull from GitLab before starting  
 5- Use task-specific branches (not personal branches)  
 6- Include "how to test" and "how to demo" in your GitLab issues  
 7- Push with helpful commit messages  

---

## 5. Final Tips

- Start by understanding `Frog.java`, `PilotApp.java`, and `GroundControlController.java`.
- Run both apps before coding to see how they behave.

- Look at `MissionLogController` and `RadarSystem` if you're adding features related to logging or radar.
- Always **test** after making changes.
- If something breaks, use Git history to go back.
- Ask your team for help—collaboration is key.

---

## 6. Conclusion

This project is a solid example of real-time system control, teamwork, and software structure. The codebase is clean, and it’s easy to follow. A new developer can contribute effectively by respecting the structure, following the patterns, and communicating with the team. Most importantly, don’t be afraid to ask questions or check existing code for guidance.

