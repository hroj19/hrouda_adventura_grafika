package cz.vse.hrouda_adventura_grafika.logika;

public class PrikazKod implements IPrikaz {
    private static final String NAZEV = "kod";
    private HerniPlan plan;

    private Hra hra;

    public PrikazKod(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }


    public String provedPrikaz(String... parametry){
        if (parametry.length == 0) {
            return "Nezadal jsi žádný kód!";
        }
        if (parametry[0].equals("3124")) {
            hra.setKonecHry(true);
            return "Gratuluji, zadal jsi správný kód a zneškodnil jsi bombu!";
        } else {
            hra.setKonecHry(true);
            return "Zadal jsi špatný kód! BOOOM BOOOM BOOM";
        }
    }

    public String getNazev() {
        return NAZEV;
    }
}
