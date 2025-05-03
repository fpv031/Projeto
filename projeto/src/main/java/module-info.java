module fiemg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens fiemg to javafx.fxml;
    opens controller to javafx.fxml;
    opens Models to javafx.base; // Necessário para serialização de modelos
    opens Dao to javafx.base;

    exports fiemg;
    exports controller;
    exports Models;
    exports Dao;
}
