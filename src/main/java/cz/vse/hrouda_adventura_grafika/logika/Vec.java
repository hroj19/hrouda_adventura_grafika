package cz.vse.hrouda_adventura_grafika.logika;

public class Vec {
    private String nazev;
    private String popis;
    private boolean lzeVzit;

    public Vec(String nazev, String popis, boolean lzeVzit) {
        this.nazev = nazev;
        this.popis = popis;
        this.lzeVzit = lzeVzit;
    }

    public String getNazev() {
        return nazev;
    }

    public String getPopis() {
        return popis;
    }

    public boolean getLzeVzit() {
        return lzeVzit;
    }
}
