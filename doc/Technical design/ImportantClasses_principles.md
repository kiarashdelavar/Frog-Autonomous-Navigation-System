# Important Classes and Their Attributes

## Table of Contents

- [Package: nl.saxion.ptbc.classes](#package-nlsaxionptbcclasses)
  - [Frog](#①-frog)
  - [Location](#②-location)
  - [Map](#③-map)
  - [Obstacle](#④-obstacle)
  - [Radar Points](#⑤-radar-points)

- [Package: nl.saxion.ptbc.CSVLoader](#package-nlsaxionptbccsvloader)
  - [CSV Loader Controller](#①-csv-loader-controller-fxml-controller-class)

- [Package: nl.saxion.ptbc.database](#package-nlsaxionptbcdatabase)
  - [Obstacle Database Handler](#①-obstacle-database-handler)

- [Package: nl.saxion.ptbc.groundControl](#package-nlsaxionptbcgroundcontrol)
  - [Ground Control Controller](#①-ground-control-controller-fxml-controller-class)
  - [Ground Control App](#②-ground-control-app-application)
  - [Ground Control Utils](#③-ground-control-utils)

- [Package: nl.saxion.ptbc.missionLog](#package-nlsaxionptbcmissionlog)
  - [Mission Log](#①-mission-log)
  - [Mission Log Controller](#②-mission-log-controller-fxml-controller-class)
  - [Mission Log DAO](#③-mission-log-dao)
  - [Mission Status](#④-mission-status)
  - [Replay Mission](#⑤-replay-mission)

- [Package: nl.saxion.ptbc.pilot](#package-nlsaxionptbcpilot)
  - [Pilot App](#①-pilot-app-application-fxml-controller-class)
  - [Radar System](#②-radar-system)
  - [Radar View](#③-radar-view)


---
# 📦 Package: `nl.saxion.ptbc.classes`

---

## ① Frog

🔵 **Purpose**:  
Stores information about the frog and is responsible for the actions of the frog like auto-driving it to a location.

🔵 **Key Attributes**:  
-  **Location**  
-  **Angle**

🔵 **Key Methods**:  
-  **Auto drive**: Responsible for auto-driving the frog  

-  **Scan obstacles ahead**: Scans obstacles and tries to avoid them  

-  **Auto drive mission log**: Drives automatically as mission log requested

---

## ② Location

🔵 **Purpose**:  
Helps with storing the coordinates of elements easily.

🔵 **Key Attributes**:  
-  **X**  
-  **Y**  
-  **Z**

🔵 **Key Methods**:  
-  **Getters**

---

## ③ Map

🔵 **Purpose**:  
Stores all obstacles (radar blips) in one place, acting as the system map.

🔵 **Key Attributes**:  
-  **Location**

🔵 **Key Methods**:  
-  **Getters**

---

## ④ Obstacle

🔵 **Purpose**:  
Stores information about obstacles (radar blips) sent by the frog via the SaSa communicator.

🔵 **Key Attributes**:  
-  **Location**

🔵 **Key Methods**:  
-  **Getters**

---

## ⑤ Radar Points

🔵 **Purpose**:  
Represents sensor/data points related to frog movement or obstacle detection.

🔵 **Key Attributes**:  
-  **Relative X**  
-  **Relative Z**

🔵 **Key Methods**:  
-  **Getters**

--- 

# 📦 Package: `nl.saxion.ptbc.CSVLoader`

---

## ① CSV Loader Controller *(FXML Controller Class)*

🔵 **Purpose**:  
Controls the loading of data (likely **Obstacle**, **RadarPoint**, etc.) from CSV files.

🔵 **Key Attributes**:  
-  **FXML attributes**


🔵 **Key Methods**: 

-  **Upload file**: Responsible for uploading a file to the application  

-  **Display file**: Responsible for displaying the contents of the file uploaded

---

# 📦 Package: `nl.saxion.ptbc.database`

---

## ① Obstacle Database Handler

🔵 **Purpose**:  
Manages the database operations for obstacles.

🔵 **Key Attributes**:  
-  **DB-URL**

🔵 **Key Methods**:  
-  **Save obstacles**: Saves the obstacles into the database and ensures no duplicates  

-  **Log final obstacle count**: Returns the final and total number of obstacles saved to the database

---

# 📦 Package: `nl.saxion.ptbc.groundControl`

---

## ① Ground Control Controller *(FXML Controller Class)*

🔵 **Purpose**:  
Makes sure the ground controller works as intended.

🔵 **Key Attributes**:  
-  **Primary stage**  

-  **Map**  

-  **Obstacles**  

-  **Frog position**  

-  **Sasa communicator**


🔵 **Key Methods**:  
-  **Receive**: Lets this class communicate with the pilot app  

-  **Draw radar point**: Draws the location of the obstacles on the map  

-  **Draw frog path**: Draws the path that the frog has taken  

-  **On map clicked**: When the user selects a location on the map for the frog to go, this method sends the command via the Sasa communicator  

---

## ② Ground Control App *(Application)*

🔵 **Purpose**:  
Main entry point to launch the Ground Control UI.

🔵 **Key Attributes**:  
-  *None*

🔵 **Key Methods**:  
-  **Start**: Starts the application  

-  **Stop**: Called on shutdown and performs cleanup by saving all currently known obstacles  

---

## ③ Ground Control Utils

🔵 **Purpose**:  
Utility class for initializing and displaying the Ground Control UI.

🔵 **Key Attributes**:  
-  *None*

🔵 **Key Methods**:  
-  **Start ground control**:  
  Initializes and displays the Ground Control JavaFX application window. Makes it possible to start Ground Control with the Pilot app.  
  This method clears the obstacles table in the database, loads the Ground Control FXML layout, sets up the scene on the provided Stage, and shows the Stage.


---

# 📦 Package: `nl.saxion.ptbc.missionLog`

---

## ① Mission Log

🔵 **Purpose**:  
Makes the storing of mission information easier.

🔵 **Key Attributes**:  
-  **Id**  

-  **Time stamp**  

-  **Mission**

🔵 **Key Methods**:  
-  **Getters**

---

## ② Mission Log Controller *(FXML Controller Class)*

🔵 **Purpose**:  
Handles mission log UI interactions.

🔵 **Key Attributes**:  
-  **FXML attributes**

🔵 **Key Methods**:  
-  **Load mission log**: Loads all mission log entries from the mission log table in the SQLite database and displays them in the mission table view  

-  **Send mission to pilot**: Sends the currently selected mission log entry to the pilot by inserting it into the sent mission table in the Sasa database  

-  **On delete**: Handles the deletion of a selected mission log entry from both the database and the UI table

---

## ③ Mission Log DAO

🔵 **Purpose**:  
DAO class for inserting mission log entries into a SQLite database.

🔵 **Key Attributes**:  
-  **URL**

🔵 **Key Methods**:  
-  **Insert mission log**: Inserts a list of mission commands into the code mission logs table  

-  **Create table if not exists**: Creates a SQLite table named `code mission logs` if it doesn’t already exist  

-  **Create sent mission table if not exists**: Creates a SQLite table named `code sent mission` if it doesn’t already exist

---

## ④ Mission Status

🔵 **Purpose**:  
Manages the state and logging of a mission. Tracks current location, destination list, and logs progress in the console.

🔵 **Key Attributes**:  
-  **Location**  

-  **Sasa**  

-  **Missions**

🔵 **Key Methods**:  
-  **Start mission**: Initializes a new mission by clearing the mission list and printing a start message  

-  **Log destination**: Logs a destination with its number and coordinates  

-  **Log arrived**: Logs the arrival at a location, updates current location, and checks if mission is complete  

-  **Finished mission**: Logs completion of all mission destinations

---

## ⑤ Replay Mission

🔵 **Purpose**:  
Retrieves the most recently sent mission command from the Sasa database.

🔵 **Key Attributes**:  
-  **DB_URL**

🔵 **Key Methods**:  
-  **Replay latest command**: Responsible for replaying the latest command  

-  **Get latest command from DB**: Retrieves the latest mission command from the database


---

# 📦 Package: `nl.saxion.ptbc.pilot`

---

## ① Pilot App *(Application, FXML Controller Class)*

🔵 **Purpose**:  
Main class to start the Pilot app.

🔵 **Key Attributes**:  
-  **FXML attributes**  

-  **Sasa communicator**  

-  **Map**  

-  **Frog**  

-  **Not converted obstacles**

🔵 **Key Methods**:  
-  **Start**: Initializes and starts the Pilot app JavaFX user interface  

-  **Receive**: Lets this class communicate with the mission control  

-  **Send manual command**: Sends manual drive commands based on directional input and updates internal motion state  

-  **Start mission log button**: Handles the start/stop toggle for mission logging when the associated button is clicked  

-  **Stop**: Called automatically when the Pilot app is closing

---

## ② Radar System

🔵 **Purpose**:  
Manages radar blips (obstacle detections) relative to a frog's position.

🔵 **Key Attributes**:  
-  **Radar points**  

-  **Max distance**

🔵 **Key Methods**:  
-  **Add radar blip**: Adds a new radar blip with coordinates relative to the frog  

-  **Get filtered points**: Returns a list of radar points that are within the maximum detection range

---

## ③ Radar View

🔵 **Purpose**:  
JavaFX UI component that visualizes radar data on a 2D grid.

🔵 **Key Attributes**:  
-  **Scale**

🔵 **Key Methods**:  
-  **Draw**: Draws the radar view with the given radar points

