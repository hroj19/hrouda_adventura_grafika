package cz.vse.hrouda_adventura_grafika.logika;

public class PrikazInventar extends Prikaz{
    public PrikazInventar(HerniPlan plan) {
        super("inventar", plan);
    }

    /**
     * zobrazi predmety v inventari
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return this.getHerniPlan().getInventar().zobrazitObsah();
    }
}
