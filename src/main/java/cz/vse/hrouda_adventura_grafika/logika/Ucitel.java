package cz.vse.hrouda_adventura_grafika.logika;

public class Ucitel extends Postava{
private Hra hra;

    public Ucitel(String nazev, String coRika, Vec coChce, Vec coMa) {
        super(nazev, coRika, coChce, coMa);
        this.hra = hra;
    }
}
