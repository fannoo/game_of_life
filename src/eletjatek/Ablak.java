package eletjatek;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Ablak {

    private JFrame ablak;
    private Szimulacio sz;
    private Menu menu;

    /**
     * létrehoz egy ablakot, egy szimuláció illetve menü panelt
     * az új ablak neve Game of Life lesz, ami a Bezárás gomb megnyomására bezáródik, illetve nem lehet átméretezni
     * beállítom a tábla méretét, amit a sejtek kirajzolnak
     * a sejtek az ablak alsó részén helyezkednek el
     * a menü az ablak felső részében kap helyet
     */
    public Ablak() {
        ablak = new JFrame("Game of Life");
        ablak.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ablak.setResizable(false);

        sz = new Szimulacio();
        sz.setPreferredSize(new Dimension(800, 400));
        ablak.add(sz, BorderLayout.SOUTH);

        menu = new Menu();
        menu.setSzimulacio(sz);
        ablak.add(menu, BorderLayout.NORTH);

        ablak.pack();
        ablak.setVisible(true);
    }

}
