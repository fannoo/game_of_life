package eletjatek;

import java.awt.Graphics;
import java.io.*;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Szimulacio extends JPanel {

    private Sejt[][] matrix;                            //az élő illetve halott sejtekből alkotott 2 dimenziós tömb
    private final Lep lepes = new Lep();                        //a Lep class megvalósítja az ActionListener interfészt
    private final Timer idozito = new Timer(50, lepes);        //a timer a lepest kapja action listenernek
    private boolean fut;                                //azt tárolja, hogy éppen fut-e a szimuláció, azaz el van-e indítva a timer
    private final boolean[] szuletes = new boolean[8];        //azt tárolja, hogy hány szomszéd esetén születik egy sejt, ahol az (index+1) a szomszédok száma
    private final boolean[] tuleles = new boolean[8];            //azt tárolja, hogy hány szomszéd esetén él túl egy sejt, ahol az (index+1) a szomszédok száma

    /**
     * a konstruktor feltölti a mátrixot random élő vagy halott sejtekkel
     * a lepes attribútumnak meg kell kapnia a szimulációt annak létrejöttekor
     * 400x200 sejt jelenik meg a táblán
     * ha a 0 és 1 között random sorsolt szám 0.5 feletti,
     * akkor az adott sejt élő lesz, egyébként halott
     */
    public Szimulacio() {
        lepes.setSzimulacio(this);
        matrix = new Sejt[400][200];
        // inicializalja random igaz hamis ertekekkel a kezdo tablat
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (Math.random() > 0.5)
                    matrix[i][j] = Sejt.ELO;
                else
                    matrix[i][j] = Sejt.HALOTT;
            }
        }
    }

    /**
     * elindítja a timert
     * a fut boolean true lesz, hiszen már elindult a szimuláció
     */
    public void start() {
        idozito.start();
        fut = true;
    }

    /**
     * megállítja a timert
     * a fut boolean false lesz, hiszen megállt a szimuláció
     */
    public void stop() {
        fut = false;
        idozito.stop();
    }

    /**
     * az iteráció metódusa kiszámolja, hogy a következő iterációban hogyan kell kinéznie a mátrixnak
     * szóval a visszatérési értéke lesz ezúttal az új mátrix
     */
    public void elet() {
        matrix = Iteracio.ujIteracio(matrix, szuletes, tuleles);
    }

    /**
     * @return visszaadja, hogy fut-e a szimuláció (tehát el van-e indítva a timer)
     */
    public boolean fut_e() {
        return fut;
    }

    /**
     * beállítja a szuletes tömböt
     * végignézem, hogy mely checkboxok voltak pipálva
     * ahol igen, ott true
     * egyébként false érték lesz a szuletes tömbben
     *
     * @param e - checkboxok tömbjét kapja paraméterként
     */
    public void setSzuletes(JCheckBox[] e) {
        for (int i = 0; i < 8; i++) {
            this.szuletes[i] = e[i].isSelected();
        }
    }

    /**
     * beállítja a tuleles tömböt
     * végignézem, hogy mely checkboxok voltak pipálva
     * ahol igen, ott true
     * egyébként false érték lesz a tuleles tömbben
     *
     * @param e - checkboxok tömbjét kapja paraméterként
     */
    public void setTuleles(JCheckBox[] e) {
        for (int i = 0; i < 8; i++) {
            this.tuleles[i] = e[i].isSelected();
        }
    }

    /**
     * ennyi időnként fogja a timer meghívni a lep action listenert
     *
     * @param i beállítandó idő
     */
    public void setIdo(int i) {
        idozito.setDelay(i);
    }

    /**
     * A parameterkent kapott matrixot allitja be az osztaly matrix attributumakent
     *
     * @param m egy sejtekbol allo ketto dimenzios tomb
     */
    public void setMatrix(Sejt[][] m) {
        this.matrix = m;
    }

    /**
     * ezt a metódust nem hívom meg kézzel a programban egyszer sem,
     * hiszen ez meghívódik, amikor a mátrix egy része megváltozik
     * nem tudom, hogy miért van így, de az internet azt mondta, hogy fogadjam el, hogy ez így működik, és ne foglalkozzak vele
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != null)
                    this.matrix[i][j].rajzol(g, i, j);
            }
        }
        this.repaint();
    }

    /**
     * getterek a teszteléshez
     */

    public Sejt[][] getMatrix() {
        return matrix;
    }

    public boolean[] getSzuletes() {
        return szuletes;
    }

    public boolean[] getTuleles() {
        return tuleles;
    }
}
