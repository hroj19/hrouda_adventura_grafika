module cz.vse.hrouda_adventura_grafika {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens cz.vse.hrouda_adventura_grafika.main to javafx.fxml;
    exports cz.vse.hrouda_adventura_grafika.main;
}