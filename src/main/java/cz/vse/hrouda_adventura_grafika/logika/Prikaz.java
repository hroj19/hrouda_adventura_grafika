package cz.vse.hrouda_adventura_grafika.logika;

public class Prikaz implements IPrikaz {

    private String NAME;
    private HerniPlan plan;

    public Prikaz(String name, HerniPlan plan) {
        this.NAME = name;
        this.plan = plan;
    }

    /**
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return null;
    }

    /**
     * vrati nazev prikazu
     * @return text nazvu prikazu
     */
    @Override
    public String getNazev() {
        return this.NAME;
    }

    /**
     * vrati herni plan
     * @return herni plan;
     */
    public HerniPlan getHerniPlan() {
        return this.plan;
    }
}
