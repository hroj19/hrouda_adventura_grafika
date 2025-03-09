package cz.vse.hrouda_adventura_grafika.logika;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Inventar {
    private Set<Vec> veci = new HashSet<>();

    public String zobrazitObsah() {

        String vracenyText = "Obsah inventáře: \n";
        if (veci.size() > 0) {
            for (Vec vec : veci) {
                vracenyText += vec.getNazev() + ", ";
            }
            vracenyText = vracenyText.substring(0, vracenyText.length() - 2);
        } else {
            vracenyText += "nic";
        }
        return vracenyText;
    }
    public Boolean obsahujeVec(String nazev) {
        for (Vec vec : veci) {
            if (vec.getNazev().equals(nazev)) {
                return true;
            }
        }
        return false;
    }

    /**
     * odebere vec z inventare
     * @param nazev nazev veci, kterou chceme odebrat
     * @return vrati vec, kterou jsme odebrali
     */
    public void odeberVec(String nazev) {
        veci.remove(nazev);
    }

    /**
     * zjisti, jestli je inventar plny
     * @return vraci true, pokud plny je a false pokud neni
     */
    public Boolean jePlny() {
        if(veci.size()>=4) {
            return true;
        }
        return false;
    }

    /**
     * zkontroluje, jestli inventar neni plny a vlozi vec do inventare
     * @param vec vec kterou vkladame do inventare
     * @return vraci vec, ktera byla vlozena, null pokud vlozena nebyla
     */
    public boolean vlozVec(Vec vec) {
        if(!jePlny()) {
            veci.add(vec);
            return true;
        }
        return false;
    }
}