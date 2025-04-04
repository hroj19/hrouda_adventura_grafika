package cz.vse.hrouda_adventura_grafika.main;

import cz.vse.hrouda_adventura_grafika.logika.Prostor;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ListCellProstor extends ListCell<Prostor> {
    @Override
    protected void updateItem(Prostor prostor, boolean empty) {
        super.updateItem(prostor, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            setText(prostor.getNazev());
            String cesta = getClass().getResource("/cz/vse/hrouda_adventura_grafika/main/prostory/"+prostor.getNazev()+".jpg").toExternalForm();
            ImageView iw = new ImageView(cesta);
            iw.setFitWidth(130);
            iw.setPreserveRatio(true);
            setGraphic(iw);
        }
    }
}
