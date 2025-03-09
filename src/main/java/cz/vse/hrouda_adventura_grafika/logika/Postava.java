package cz.vse.hrouda_adventura_grafika.logika;

import java.util.HashMap;
import java.util.Map;

public class Postava{

    private String nazev;
    private String coRika;
    private Vec coChce;
    private Vec coMa;
    private boolean jizMluvil = false;
    private boolean probehlaVymena = false;

    public Postava(String nazev, String coRika, Vec coChce, Vec coMa) {
        this.nazev = nazev;
        this.coRika = coRika;
        this.coChce = coChce;
        this.coMa = coMa;
    }

    public String getJmeno() {
        return nazev;
    }

    public String getNazev() {
        return nazev;
    }

    public String getCoRika() {
        return coRika;
    }

    public Vec getCoChce() {
        return coChce;
    }

    public Vec getCoMa() {
        return coMa;
    }

    public void setCoChce(Vec coChce) {
        this.coChce = coChce;
    }

    public void setCoMa(Vec coMa) {
        this.coMa = coMa;
    }

public String mluvit() {
    return coRika;
}

    public Map<String, Vec> vymenitVec(Vec vec) {
        probehlaVymena = true;
        Map<String, Vec> veci = new HashMap<>();
        veci.put("odeber", vec);
        veci.put("pridej", coMa);
        coChce = null;
        Vec puvVec = coMa;
        coMa = vec;
        return veci;
    }

    /**
     * zjisti, jestli postava jiz mluvila
     * @return true pokud jiz mluvit, false pokud jeste nemluvil
     */
    public Boolean getJizMluvil() {
        return jizMluvil;
    }
}
