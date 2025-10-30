
# Test Plan

## Test Case 1 : RFU-01 - Manual Driving

**Requirement**: RFU-01  

**Name**: A driver can drive the Frog in a manual mode via the application.

**Objective**: Verify manual control of the Frog via UI.
1. Launch The Pilot Dashboard.
2. Use direction buttons (↑, ↓, ←, →).
3. Observe Frog movement on the radar map.


## Test Case 2 : RFU-02 - View Radar Points on Map
**Requirement**: RFU-02

**Objective**: Driver can view received radar-points in a POV map.

**Name**: A driver can view the received radar-points in a POV map in the ground control.
1. Launch ThePilot (then see the ground control app).
2. Wait for connecting the app.
3. You see yellow dots on the map as a obstacles. 
4. Move the Frog to see updated map (new yellow dots).

## Test Case 3: RFU-03 – Record Mission Log

**Requirement**: RFU-03  
**Objective**: The driver can start recording a mission log (a sequence of drive commands or coordinates).  
**Name**: The driver records a mission using the "Record mission log" button.

1. Launch the Pilot app.
2. Press the **"Record mission log"** button — this begins recording immediately.
3. Use the arrow buttons to send manual commands to the Frog.
4. Close or switch to Ground Control — the mission log is now saved.
5. Open the Ground Control app and view the **Load Mission Log** screen.
6. Confirm the new mission appears in the mission table.
7. Optionally, open the database (`sasa.db`) or run `SQLiteSelectTable` to verify:
   - The commands were saved.
   - The timestamps and content are correct.



## Test Case 4: RFS-03 – Send Frog Position and Obstacles

**Requirement**: RFS-03  
**Objective**: The application sends the position of the Frog and detected obstacles to Ground Control.  
**Name**: The application must provide Ground Control with the Frog’s position and nearby obstacles.

1. Open the Pilot app.
2. Open the Ground Control app.
3. Observe the Ground Control, **"Mission Status & Navigation Information"** part is showing the Frog's position and obstacles.


## Test Case 5: RFS-04 – Auto Drive to Location

**Requirement**: RFS-04  
**Objective**: The application can command the Frog to automatically drive to a given location.  
**Name**: The Frog can be sent to a target point (from Ground Control) without navigation logic.

1. Open the Pilot app.
2. Open the Ground Control app.
3. Click a destination on the Ground Control map.
4. Observe the Frog move toward and stop at the selected location.



## Test Case 6: RGU-01 – Display Frog Position and Route

**Requirement**: RGU-01  
**Objective**: The operator sees the Frog’s position, direction, driven route, and obstacles on a 2D map.  
**Name**: Ground Control map updates with live Frog movement and detected obstacles.

1. Open the Pilot app.
2. See the Ground Control app.
3. Move the Frog manually or click a valid point on the ground control map to drive.
4. Observe live updates of position, direction, and route on the map.


## Test Case 7: RGU-03 – Operator Requests Location Drive

**Requirement**: RGU-03  
**Objective**: Operator can request the Frog to drive to a selected map point.  
**Name**: Operator clicks a location on the map, and the Frog drives there accordingly.

1. Launch Frog, Pilot, and Ground Control apps.
2. In Ground Control, click on a valid location within the map.
3. Observe the Frog start moving toward that location.

## Test Case 8: RGS-01 – JavaFX as GUI Framework

**Requirement**: RGS-01  
**Objective**: The application must use JavaFX for the user interface.  
**Name**: JavaFX is used as the GUI framework throughout the application.

✔ Verified by reviewing the codebase and observing JavaFX UI components in the running application.

## Test Case 9: RGU-04 – Send and Replay Mission Log from Database

**Requirement**: RGU-04  
**Objective**: Operator can send a saved mission log to the Pilot and replay it using the Pilot's "Replay Mission" feature.  
**Name**: Operator selects a mission log entry from the database and commands the Frog to replay it.

1. Launch the Pilot, Ground Control, and Frog applications.
2. In Pilot, start a mission log and manually move the Frog using control buttons.
3. Press "Stop Mission Log" to save the log to the database.
4. In Ground Control, open the mission logs table and locate the new entry.
5. Select the mission row (it should turn blue).
6. Click the **Send to Pilot** button.
7. In the Pilot app, press the **Replay Mission** button.
8. Observe the Frog automatically executing the selected mission.
9. (Optional) Add more missions by repeating steps 2–3 and verify multiple missions appear in the table.
10. Test selecting and replaying different missions to confirm flexibility.


## Test Case 10: RFS-05 – Drive Pre-Programmed Mission

**Requirement**: RFS-05  
**Objective**: The application can command the Frog to drive a pre-programmed mission (a sequence of drive commands or coordinates).  
**Name**: The Frog drives a mission received from Ground Control automatically.

1. Launch Frog, Pilot, and Ground Control applications.
2. In Ground Control, prepare a mission log (a sequence of destinations).
3. Ensure the Frog is idle (not manually driven).
4. Press "Start Mission Log" in the Pilot app.
5. Observe the Frog begin executing the mission by driving toward the blue dots (waypoints) in sequence.

## Test Case 11: RGU-05 – Save and Reload Collision Points to Database

**Requirement**: RGU-05  
**Objective**: The operator can save the collected collision points to the database and reload them, ensuring only one set is stored at a time.  
**Name**: Save the obstacle (collision point) data to `sasa.db`, replacing old data and avoiding duplicates.

1. Run the Pilot app and observe Ground Control.
2. Simulate or generate obstacle data (e.g., by moving the Frog).
3. Close all applications except `frog.exe` to trigger auto-save to `sasa.db`.
4. Open `sasa.db` and inspect the `Obstacles` table:
   - Confirm obstacle coordinates are saved correctly.
   - No crashes or duplicate entries.
5. Repeat the process with:
   - Same obstacle data → no duplicates should be added.
   - New obstacle data → previous entries should be overwritten.
6. Test edge cases:
   - Save with no obstacle data → app should not crash.
   - Force-close while app is open → database should still store cleanly or provide a warning.




## Test Case 12: RGU-06 – Export Collision Points to CSV

**Requirement**: RGU-06  
**Objective**: The operator can export the collected collision points from the database to a CSV file.  
**Name**: Operator saves collision data to a CSV file for external analysis.

1. Launch Frog, Pilot, and Ground Control applications.
2. Move the Frog (using Ground Control map or Manual Mode in Pilot) to generate collision points.
3. In Ground Control, click the "Export Collision Points" button.
4. A file dialog appears — select a location and confirm.
5. Verify that a CSV file is saved with the collected collision point data.
6. Optionally, repeat the test with only Ground Control running to ensure the export function gracefully handles missing dependencies.




## Test Case 13: RGS-02 – Saving Mission Log Data in the Database

**Requirement**: RGS-02  
**Objective**: Mission log data recorded in the Pilot app is saved to the database upon stopping the log.  
**Name**: Store the mission log commands in the database after stopping the recording session.

1. Launch the PilotApp.
2. Press the "Start Mission Log" button.
3. Use manual controls (e.g., arrow buttons) to move the Frog and generate commands.
4. Press the "Stop Mission Log" button.
5. Open the database (e.g., `sasa.db`) or run `SQLiteSelectTable` to verify that:
   - All commands are correctly saved.
   - Timestamps match the execution time.
6. Repeat the above steps for a new mission and ensure:
   - The new session logs are saved separately.
   - No duplication of commands from the previous session.


### Test Case 14: RGU-02 – Display Mission Log Table from Database

**Requirement**: RGU-02  
**Objective**: Show saved mission log data from the database in the Ground Control mission log table.  
**Name**: The Ground Control app should display all mission logs saved via the Pilot app.

1. Launch the Ground Control app without any mission logs saved.
2. Observe the mission log table is empty.
3. Launch the Pilot app and press **"Record mission log"**.
4. Enter several manual drive commands.
5. Wait for the log to save.
6. Return to Ground Control and load the mission logs.
7. Verify the recorded mission appears in the mission log table.
8. Add additional missions from the Pilot app and reload.
9. Confirm new entries appear and data is up to date.





