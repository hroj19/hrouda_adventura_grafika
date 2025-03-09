package cz.vse.hrouda_adventura_grafika.logika;

public class Spoluzak extends Postava{
    private HerniPlan plan;

    public Spoluzak(String nazev, String coRika, Vec coChce, Vec coMa) {
        super(nazev, coRika, coChce, coMa);
        this.plan = plan;
    }
}
