package cz.vse.hrouda_adventura_grafika.logika;

public class PrikazSeber extends Prikaz {
    public PrikazSeber(HerniPlan plan) {
        super("seber", plan);
    }

    /**
     * sebere predmet a vlozi ho do inventare
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mam jako sebrat? Musis mi to rict!";
        } else if (parametry.length == 1 && this.getHerniPlan().getAktualniProstor().obsahujeVec(parametry[0])) {
            // pokud je druhe slovo takové, které lze vložit do kabelky
            Vec vec = this.getHerniPlan().getAktualniProstor().vratVec(parametry[0]);
            if (vec.getLzeVzit()) {
                this.getHerniPlan().getInventar().vlozVec(vec);
                getHerniPlan().getAktualniProstor().odeberVec(parametry[0]);
                if (parametry[0].equals("tycinka")) {
                    return "Vložil jsi do inventáře tyčinku a otevřel jí, pod obalem je kód: 3124.";
                }

                return "Vložil jsi do inventáře: " + vec.getNazev() + ".";
            } else {
                return "Tohle nelze vzít, můžeš to ale zkusit koupit nebo ukrást";
            }

        }

        return "Tohle tu neni.";
    }
}
