package eletjatek;

import java.io.*;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

public class Menu extends JPanel {

    //h�rom gombot, egy textfieldet, �s 16 checkboxot hoz l�tre a konstruktor
    private Szimulacio sz;
    private final JButton indit;
    private final JButton ment;
    private final JButton betolt;
    private final JCheckBox[] szuletes = new JCheckBox[8];
    private final JCheckBox[] tuleles = new JCheckBox[8];
    private final JFormattedTextField ido;

    /**
     * a gombok, textfield, �s checkboxokat jelen�ti meg, illetve a gombnyom�sra t�rt�n� dolgok f�ggv�nyeit h�vja
     * a start/stop gomb megnyom�s�ra, ha a szimul�ci� fut m�r, akkor meg�ll�tja azt
     * ha a start/stop gomb megnyom�sakor a szimul�ci� nem fut m�g, akkor elind�tja a men� konstruktora azt
     * a mentes gomb a szimul�ci� ment�s met�dus�t h�vja
     * a visszaoltes gomb a szimul�ci� visszt�lt�s met�dus�t h�vja
     */
    public Menu() {

        indit = new JButton("start");
        ment = new JButton("ment");
        betolt = new JButton("visszatölt");
        JLabel l1 = new JLabel("születés: ");
        JLabel l2 = new JLabel("túlélés: ");
        JLabel l3 = new JLabel("idő: ");

        //ez a r�sz biztos�tja, hogy az iter�ci�k k�z�tt eltelt id�t �ll�t� text field-be csak sz�mot lehessen �rni
        NumberFormat longFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(longFormat);
        numberFormatter.setValueClass(Long.class);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(0L);
        ido = new JFormattedTextField(numberFormatter);
        Long l = 50L;
        ido.setValue(l);    // a text field tartalma a program ind�t�sakor 50
        ido.setColumns(4);

        indit.addActionListener(e -> indit());

        ment.addActionListener(e -> {        //a mentes gomb a szimul�ci� ment�s met�dus�t h�vja
            try {
                mentes();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //a visszaoltes gomb a szimul�ci� visszt�lt�s met�dus�t h�vja
        betolt.addActionListener(e -> {
            try {
                visszatoltes();
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }
        });

        for (int i = 1; i <= 8; i++) {        //l�trehozza a tuleles es szuletes JCheckbox t�pus� t�mb�ket
            szuletes[i - 1] = new JCheckBox(Integer.toString(i));    //illetve be�ll�tja, a sz�mokat 1-8-ig sz�vegk�nt
            tuleles[i - 1] = new JCheckBox(Integer.toString(i));
        }

        szuletes[2].setSelected(true);        //a Conway f�le �letj�t�k eredeti szab�lyai vannak be�ll�tva
        tuleles[1].setSelected(true);        //a program ind�t�sakor
        tuleles[2].setSelected(true);

        this.add(indit);        //hozz�adja a panelhez a l�trehozott gombokat, checkboxokat, stb.
        this.add(ment);
        this.add(betolt);

        this.add(l3);
        this.add(ido);

        this.add(l1);
        for (int i = 0; i < 8; i++) this.add(szuletes[i]);

        this.add(l2);
        for (int i = 0; i < 8; i++) this.add(tuleles[i]);

    }

    /**
     * ha a szimul�ci� fut m�r, akkor meg�ll�tja azt
     * ha a start/stop gomb megnyom�sakor a szimul�ci� nem fut m�g
     * a szimul�ci� megkapja az aktu�lisan a felhaszn�l� be�ll�tott szab�lyokat
     * illetve, hogy mennyi id�t �ll�tott be a felhaszn�l� a k�t iter�ci� k�z�tt
     * akkor elind�tja a men� konstruktora azt
     * am�g fut a szimul�ci� addig nem lehet a szab�lyokon v�ltoztatni
     * illetve az iter�ci�k k�z�tt eltelt id�n sem
     */
    protected void indit() {
        if (sz.fut_e()) {
            sz.stop();
            indit.setText("start");
            for (int i = 0; i < 8; i++) {
                szuletes[i].setEnabled(true);
                tuleles[i].setEnabled(true);
            }
            ido.setEnabled(true);
        } else {
            sz.setSzuletes(szuletes);
            sz.setTuleles(tuleles);
            sz.setIdo(((Long) ido.getValue()).intValue());
            sz.start();
            indit.setText("stop");
            for (int i = 0; i < 8; i++) {
                szuletes[i].setEnabled(false);
                tuleles[i].setEnabled(false);
            }
            ido.setEnabled(false);
        }
    }

    /**
     * @param sz be�ll�tja a param�terk�nt kapott szimul�ci�t
     */
    public void setSzimulacio(Szimulacio sz) {
        this.sz = sz;
    }

    public Szimulacio getSzimulacio() {
        return sz;
    }

    public JCheckBox[] getSzuletes() {
        return szuletes;
    }

    public JCheckBox[] getTuleles() {
        return tuleles;
    }

    /**
     * szerializálja a mátrixot
     * a felhasználói könyvtárban az allapot.dat fájlba szeretném tárolni
     * ha ez a fájl nem létezik,akkor először létrehozom
     * majd az előadáson tanult módon szeriazálom
     *
     */
    protected void mentes() throws IOException {
        File f = new File(System.getProperty("user.dir") + "allapot.dat");
        if (!f.exists()) {
            f.createNewFile();
        }
        FileOutputStream fileOut = new FileOutputStream(f);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(sz.getMatrix());
        objectOut.close();
    }

    /**
     * vissztölti a korábban szerializált mátrixot
     * a felhasználói könyvtárban az allapot.dat fájlból töltjük vissza
     * ha létezik a fájl
     * akkor az előadáson tanult módon megoldom a szerializált mátrix visszatöltését
     * előfordulhat, hogy nem létezik a fájl, de csak akkor, ha korábban nem volt mentve semmi, és ebben az esetben nincs is mit visszatölteni, szóval nem foglalkozom ezzel a helyzettel
     *
     */
    protected void visszatoltes() throws IOException, ClassNotFoundException {
        File f = new File(System.getProperty("user.dir") + "allapot.dat");
        if (f.exists()) {
            FileInputStream fileIn = new FileInputStream(f);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Sejt[][] readObject = (Sejt[][]) objectIn.readObject();
            sz.setMatrix(readObject);
            objectIn.close();
        }
    }
}


