package cz.vse.hrouda_adventura_grafika.logika;

import java.util.Map;

public class PrikazDat extends Prikaz {
    public PrikazDat(HerniPlan plan) {
        super("dat", plan);
    }
    public static final String NAZEV = "dat";

    /**
     * provede vymenu predmetu
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return text vysledku prikazy
     */
    public String provedPrikaz(String... parametry) {

        Inventar inventar = this.getHerniPlan().getInventar();
        Prostor aktualniProstor = this.getHerniPlan().getAktualniProstor();
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mám dát? Specifikuj předmět.";
        } else if (parametry.length == 1) {
            return "Komu to mám dát?";
        } else if (parametry.length == 2 && inventar.obsahujeVec(parametry[0])) {
            if (aktualniProstor.obsahujePostavu(parametry[1])) {
                Postava postava = aktualniProstor.vratPostavu(parametry[1]);
                Vec vecChce = postava.getCoChce();
                Vec vecMa = postava.getCoMa();
                if (vecChce != null) {
                    if (vecChce.getNazev().equals(parametry[0])) {
                        if (inventar.obsahujeVec(vecChce.getNazev())) {
                            inventar.odeberVec(parametry[0]);
                            inventar.vlozVec(vecMa);
                            return "Dáváš " + vecChce.getNazev() + " postavě " + postava.getJmeno() + " a dostáváš " + vecMa.getNazev() + ".";
                        } else {
                            return "Tohle u sebe nemám.";
                        }
                    } else {
                        return "Tohle u sebe nemám.";
                    }
                }
            }
        }
        return null;
    }
}