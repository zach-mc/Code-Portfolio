module com.example.javagui {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires java.desktop;


    opens com.example.javagui to javafx.fxml;
    exports com.example.javagui;
    exports com.example.javagui.datamodel;
    opens com.example.javagui.datamodel to javafx.fxml;
}