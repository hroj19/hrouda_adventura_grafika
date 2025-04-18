package cz.vse.hrouda_adventura_grafika.main;

import cz.vse.hrouda_adventura_grafika.logika.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HomeController {

    @FXML
    private ListView<Vec> panelVeciInventar;
    @FXML
    private ListView<Postava> panelPostavyVProstoru;
    @FXML
    private ListView<Vec> panelVeciVProstoru;
    @FXML
    private ImageView hrac;
    @FXML
    private ListView<Prostor> panelVychodu;

    @FXML
    private Button tlacitkoOdesli;

    @FXML
    private TextArea vystup;

    @FXML
    private TextField vstup;

    private IHra hra = new Hra();

    private ObservableList<Prostor> seznamVychodu = FXCollections.observableArrayList();

    private ObservableList<Vec> seznamVeciVProstoru = FXCollections.observableArrayList();

    private ObservableList<Postava> seznamPostavVProstoru = FXCollections.observableArrayList();

    private ObservableList<Vec> seznamVeciInventar = FXCollections.observableArrayList();

    private Map<String, Point2D> souradniceProstoru = new HashMap<>();

    @FXML
    private void initialize() {
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        panelVychodu.setItems(seznamVychodu);
        panelVeciVProstoru.setItems(seznamVeciVProstoru);
        panelPostavyVProstoru.setItems(seznamPostavVProstoru);
        panelVeciInventar.setItems(seznamVeciInventar);
        hra.getHerniPlan().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
            aktualizujSeznamVychodu();
            aktualizujPolohuHrace();
            aktualizujSeznamVeciVProstoru();
            aktualizujSeznamPostavVProstoru();
        });
        hra.registruj(ZmenaHry.KONEC_HRY, () -> aktualizujKonecHry());
        hra.getHerniPlan().registruj(ZmenaHry.ZMENA_INVENTARE, this::aktualizujSeznamVeciInventar);
        aktualizujSeznamVychodu();
        aktualizujSeznamVeciVProstoru();
        aktualizujSeznamVeciInventar();
        aktualizujSeznamPostavVProstoru();
        vlozSouradnice();
        panelVychodu.setCellFactory(param -> new ListCellProstor());
        panelVeciVProstoru.setCellFactory(param -> new ListCellVec());
        panelPostavyVProstoru.setCellFactory(param -> new ListCellPostava());
        panelVeciInventar.setCellFactory(param -> new ListCellInventar());
    }

    @FXML
    private void aktualizujSeznamVeciInventar() {
        seznamVeciInventar.clear();
        Inventar inventar = hra.getHerniPlan().getInventar();
        seznamVeciInventar.addAll(inventar.getVeci());
    }

    @FXML
    private void aktualizujSeznamPostavVProstoru() {
        seznamPostavVProstoru.clear();
        seznamPostavVProstoru.addAll(hra.getHerniPlan().getAktualniProstor().getPostavy());
    }

    @FXML
    private void aktualizujSeznamVeciVProstoru() {
        seznamVeciVProstoru.clear();
        Prostor aktualniProstor = hra.getHerniPlan().getAktualniProstor();
        seznamVeciVProstoru.addAll(aktualniProstor.veci);
    }

    private void vlozSouradnice() {
        souradniceProstoru.put("aula", new Point2D(84, 22));
        souradniceProstoru.put("chodba", new Point2D(84, 120));
        souradniceProstoru.put("ucebna", new Point2D(241, 120));
        souradniceProstoru.put("hlavak", new Point2D(84, 218));
        souradniceProstoru.put("chodov", new Point2D(241, 286));
    }

    @FXML
    private void aktualizujSeznamVychodu() {
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
    }

    private void aktualizujPolohuHrace() {
        String prostor = hra.getHerniPlan().getAktualniProstor().getNazev();
        hrac.setLayoutX(souradniceProstoru.get(prostor).getX());
        hrac.setLayoutY(souradniceProstoru.get(prostor).getY());
    }

    @FXML
    private void odesliVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
    }

    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> " + prikaz + "\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek + "\n\n");
    }

    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Skutečně si přejete ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }

    private void aktualizujKonecHry() {
        if (hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
        }
        vstup.setDisable(hra.konecHry());
        tlacitkoOdesli.setDisable(hra.konecHry());
        panelVychodu.setDisable(hra.konecHry());
        panelPostavyVProstoru.setDisable(hra.konecHry());
        panelVeciVProstoru.setDisable(hra.konecHry());
        panelVeciInventar.setDisable(hra.konecHry());
    }

    @FXML
    private void klikPanelVychodu(MouseEvent mouseEvent) {
        Prostor cil = panelVychodu.getSelectionModel().getSelectedItem();
        if (cil == null) return;
        String prikaz = PrikazJdi.NAZEV + " " + cil.getNazev();
        zpracujPrikaz(prikaz);
    }

    public void napovedaKlik(ActionEvent actionEvent) {
        Stage napovedaStage = new Stage();
        WebView wv = new WebView();
        Scene napovedaScena = new Scene(wv);
        napovedaStage.setScene(napovedaScena);
        napovedaStage.show();
        wv.getEngine().load(getClass().getResource("napoveda.html").toExternalForm());
    }

    @FXML
    private void klikPanelVeciVProstoru(MouseEvent mouseEvent) {
        Vec vecKSebrani = panelVeciVProstoru.getSelectionModel().getSelectedItem();
        if (vecKSebrani == null) return;
        String prikaz = PrikazSeber.NAZEV + " " + vecKSebrani.getNazev();
        zpracujPrikaz(prikaz);
        aktualizujSeznamVeciVProstoru();
        aktualizujSeznamVeciInventar();
    }

    @FXML
    private void klikPanelPostavyVProstoru(MouseEvent mouseEvent) {
        Postava postavaVMistnosti = panelPostavyVProstoru.getSelectionModel().getSelectedItem();
        if (postavaVMistnosti == null) return;
        String prikaz = PrikazMluvit.NAZEV + " " + postavaVMistnosti.getNazev();
        zpracujPrikaz(prikaz);
        aktualizujSeznamPostavVProstoru();
    }

    public void klikPanelVeciInventar(MouseEvent mouseEvent) {
        Vec vecKPredani = panelVeciInventar.getSelectionModel().getSelectedItem();
        if (vecKPredani == null) return;
        String prikaz = PrikazDat.NAZEV + " " + vecKPredani.getNazev() + " spoluzak";
        zpracujPrikaz(prikaz);
        aktualizujSeznamVeciInventar();
    }

    public void novaHraKlik(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Opravdu chcete začít novou hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            hra = new Hra();
            vystup.clear();
            vystup.appendText(hra.vratUvitani() + "\n");
            seznamVychodu.clear();
            seznamVeciInventar.clear();
            seznamVeciVProstoru.clear();
            seznamPostavVProstoru.clear();
            hra.getHerniPlan().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
                aktualizujSeznamVychodu();
                aktualizujPolohuHrace();
                aktualizujSeznamVeciVProstoru();
                aktualizujSeznamPostavVProstoru();
                aktualizujSeznamVeciInventar();
            });
            hra.registruj(ZmenaHry.KONEC_HRY, this::aktualizujKonecHry);
            hra.getHerniPlan().registruj(ZmenaHry.ZMENA_INVENTARE, this::aktualizujSeznamVeciInventar);

            aktualizujSeznamVychodu();
            aktualizujPolohuHrace();
            aktualizujSeznamVeciVProstoru();
            aktualizujSeznamPostavVProstoru();
            aktualizujSeznamVeciInventar();

            vstup.setDisable(false);
            tlacitkoOdesli.setDisable(false);
            panelVychodu.setDisable(false);
        }
    }
}