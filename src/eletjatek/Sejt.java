package eletjatek;

import java.awt.Color;
import java.awt.Graphics;

public enum Sejt {        //a sejt enumnak két konstans értéke van

    ELO(new Color(242, 201, 255)),        //élő (amihez egy lila szín tartozik)
    HALOTT(new Color(121, 115, 122));    //vagy halott (ami pedig szürke)

    private final Color szin;

    /**
     * a sejt konstruktora nincs meghívva, azért van rá szükség, hogy a színeket a konstansokhoz rendelhessem
     *
     * @param sz
     */
    private Sejt(Color sz) {
        this.szin = sz;
    }

    /**
     * a paraméterként kapott adatokkal megrajzol egy sejtet
     *
     * @param g egy Graphics dolog
     * @param x a mátrixban a sor száma
     * @param y a mátrixban az oszlop száma
     */
    public void rajzol(Graphics g, int x, int y) {
        g.setColor(szin);
        g.fillRect(x * 4, y * 4, 4, 4);
    }
}
