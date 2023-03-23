module ec.edu.espol.vacaciones {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.vacaciones to javafx.fxml;
    exports ec.edu.espol.vacaciones;
    opens modelo to java.base;
    exports modelo;
}
