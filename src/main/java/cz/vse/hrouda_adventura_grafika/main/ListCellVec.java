package cz.vse.hrouda_adventura_grafika.main;

import cz.vse.hrouda_adventura_grafika.logika.Vec;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ListCellVec extends ListCell<Vec> {
    @Override
    protected void updateItem(Vec vec, boolean empty) {
        super.updateItem(vec, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(vec.getNazev());
            String cesta = getClass().getResource("/cz/vse/hrouda_adventura_grafika/main/veci/"+vec.getNazev()+".jpg").toExternalForm();
            ImageView iw = new ImageView(cesta);
            iw.setFitWidth(50);
            iw.setPreserveRatio(true);
            setGraphic(iw);
        }
    }
}
