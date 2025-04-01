package cz.vse.hrouda_adventura_grafika.logika;


import cz.vse.hrouda_adventura_grafika.main.Pozorovatel;
import cz.vse.hrouda_adventura_grafika.main.PredmetPozorovani;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 *
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan implements PredmetPozorovani {

    private Prostor aktualniProstor;
    private Inventar inventar = new Inventar();
    private Map<String, Prostor> prostory = new HashMap<>();
    private Set<Pozorovatel> seznamPozorovatelu = new HashSet<>();

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor aula = new Prostor("aula","Vencovského aula");
        Prostor chodba = new Prostor("chodba", "Chodba v Nové budově");
        Prostor ucebna = new Prostor("ucebna","Učebna Rajské budovy");
        Prostor hlavak = new Prostor("hlavak","Atrium Hlavního nádraží");
        Prostor chodov = new Prostor("chodov","Nástupiště metra Chodov");

        prostory.put(aula.getNazev(), aula);
        prostory.put(chodba.getNazev(), chodba);
        prostory.put(ucebna.getNazev(), ucebna);
        prostory.put(hlavak.getNazev(), hlavak);
        prostory.put(chodov.getNazev(), chodov);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        aula.setVychod(chodba);
        chodba.setVychod(aula);
        chodba.setVychod(ucebna);
        chodba.setVychod(hlavak);
        hlavak.setVychod(chodba);
        hlavak.setVychod(chodov);
        chodov.setVychod(hlavak);
        ucebna.setVychod(chodba);

        aktualniProstor = aula;  // hra začíná v aule

        Ucitel ucitel = new Ucitel("ucitel", "Usadťe se, to je určitě zas planý poplach.", null, null);
        Spoluzak spoluzak = new Spoluzak("spoluzak", "Jsem omdlenej", new Vec("lahev", "Láhev plná vody", true), new Vec("klic", "Klíč k učebně v RB", true));

        aula.vlozPostavu(ucitel);
        aula.vlozPostavu(spoluzak);

        Vec kamen = new Vec("kamen", "kamen zde", true);
        Vec automat = new Vec("automat_lokni", "Automat Lokni", false);
        Vec bomba = new Vec("bomba", "Odjištěná bomba, co za chvíli vybuchne", false);
        Vec lahev = new Vec("lahev", "Prázdná láhev na vodu", true);
        Vec tycinka = new Vec("tycinka", "Čokoládová tyčinka s kódem", true);
        chodba.vlozVec(automat);
        ucebna.vlozVec(bomba);
        chodov.vlozVec(lahev);
        hlavak.vlozVec(tycinka);
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    public Map<String, Prostor> getProstory() {
        return prostory;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        upozorniPozorovatele();
    }

    private void upozorniPozorovatele() {
        for(Pozorovatel pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
    }

    public Inventar getInventar() {
        return this.inventar;
    }

    @Override
    public void registruj(Pozorovatel pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }
}