module cz.vse.hrouda_adventura_grafika {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.hrouda_adventura_grafika to javafx.fxml;
    exports cz.vse.hrouda_adventura_grafika;
}