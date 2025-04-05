package cz.vse.hrouda_adventura_grafika.main;

import cz.vse.hrouda_adventura_grafika.logika.Postava;
import cz.vse.hrouda_adventura_grafika.logika.Vec;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ListCellPostava extends ListCell<Postava> {
    @Override
    protected void updateItem(Postava postava, boolean empty) {
        super.updateItem(postava, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(postava.getNazev());
            String cesta = getClass().getResource("/cz/vse/hrouda_adventura_grafika/main/postavy/"+postava.getNazev()+".jpg").toExternalForm();
            ImageView iw = new ImageView(cesta);
            iw.setFitWidth(80);
            iw.setPreserveRatio(true);
            setGraphic(iw);
        }
    }
}
