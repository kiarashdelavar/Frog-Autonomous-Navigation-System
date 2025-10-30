module nl.saxion.ptbc.newprojectfrog {
    requires javafx.controls;
    requires javafx.fxml;
    requires SaSaCommunicator;
    requires java.sql;
    requires java.desktop;

    opens nl.saxion.ptbc.newprojectfrog to javafx.fxml;
    opens nl.saxion.ptbc.missionLog to javafx.fxml, javafx.base;
    opens nl.saxion.ptbc.CSVLoader to javafx.fxml;
    exports nl.saxion.ptbc.pilot;
    exports nl.saxion.ptbc.groundControl;
    opens nl.saxion.ptbc.pilot to javafx.fxml;
    opens nl.saxion.ptbc.groundControl to javafx.fxml;
}