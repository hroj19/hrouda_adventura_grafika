package cz.vse.hrouda_adventura_grafika.logika;

public class PrikazMluvit extends Prikaz{
    /**
     * Class PrikazMluvit - dedi ze tridy Prikaz
     * @author Dominik Brych
     *    @version pro školní rok 2018/2019
     * @param plan
     */
    public PrikazMluvit(HerniPlan plan) {
        super("mluvit", plan );
    }

    /**
     * metoda promluvi na danou postavu
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Na koho mám mluvit?";
        }
        else if(parametry.length == 1) {
            // pokud je druhe slovo takové, které lze vložit do
            for (Postava p : this.getHerniPlan().getAktualniProstor().getPostavy()) {
                if (p.getNazev().equals(parametry[0])) {
                    return p.mluvit();
                }
            }

        }

        return "Tahle osoba tu neni!";
    }
}
