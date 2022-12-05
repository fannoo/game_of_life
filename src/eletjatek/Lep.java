package eletjatek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ezt az action listenert hívja a szimuláció timer-e,
 * ezért feladata, hogy az elet metódust hívja, ami pedig a mátrixot változtatja
 * az iteracio segítségével
 */
public class Lep implements ActionListener {
    private Szimulacio sz;

    /**
     * a szimuláció élet metódusát hívja
     *
     * @param e egy ActionEvent-et kell kapnia az actionPerformed metódusnak
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        sz.elet();
    }

    /**
     * a menu hívja, amikor létrehoz egy Lep-et
     * beállítja a szimuláció attribútumot
     *
     * @param sz az ablak konstruktorában létrehozott szimulációt kell kapja paraméterként
     */
    public void setSzimulacio(Szimulacio sz) {
        this.sz = sz;
    }

}
