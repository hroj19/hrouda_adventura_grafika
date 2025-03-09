package test;

import cz.vse.hrouda_adventura_grafika.logika.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class VelikostInventareTest {
    @Test
    public void testVelikosti() {
        Map<String, Prostor> prostory = new HashMap<>();
        Prostor aula = new Prostor("aula", "Vencovského aula");
        prostory.put(aula.getNazev(), aula);
        Vec tycinka = new Vec("tycinka", "Čokoládová tyčinka s kódem", true);
        Vec tycinka1 = new Vec("tycinka1", "Čokoládová tyčinka s kódem", true);
        Vec tycinka2 = new Vec("tycinka2", "Čokoládová tyčinka s kódem", true);
        Vec tycinka3 = new Vec("tycinka3", "Čokoládová tyčinka s kódem", true);
        Vec tycinka4 = new Vec("tycinka4", "Čokoládová tyčinka s kódem", true);
        aula.vlozVec(tycinka);
        Inventar inventar = new Inventar();
        inventar.vlozVec(tycinka);
        inventar.vlozVec(tycinka1);
        inventar.vlozVec(tycinka2);
        inventar.vlozVec(tycinka3);
        inventar.vlozVec(tycinka4);
        assert (inventar.obsahujeVec("tycinka"));
        assert (inventar.obsahujeVec("tycinka1"));
        assert (inventar.obsahujeVec("tycinka2"));
        assert (inventar.obsahujeVec("tycinka3"));
        assert (!inventar.obsahujeVec("tycinka4"));
    }
}
