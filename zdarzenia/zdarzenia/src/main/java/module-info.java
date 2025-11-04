module org.example.zdarzenia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.zdarzenia to javafx.fxml;
    exports org.example.zdarzenia;
}