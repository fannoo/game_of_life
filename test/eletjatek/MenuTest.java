package eletjatek;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

import java.io.*;

public class MenuTest {

    private Menu menu;

    @Before
    public void setUp() {
        menu = new Menu();
        menu.setSzimulacio(new Szimulacio());
    }

    /**
     * ellenőrzi, hogy a checkboxok kattinthatóak-e, amikor el van indítva a szimulácó
     */
    @Test
    public void startStopTest() {
        menu.indit();
        for (JCheckBox temp : menu.getSzuletes())
            assertFalse(temp.isEnabled());
        for (JCheckBox temp : menu.getTuleles())
            assertFalse(temp.isEnabled());

        menu.indit();
        for (JCheckBox temp : menu.getSzuletes())
            assertTrue(temp.isEnabled());
        for (JCheckBox temp : menu.getTuleles())
            assertTrue(temp.isEnabled());
    }

    @Test
    public void mentesTest() throws IOException {
        menu.mentes();

        File f = new File(System.getProperty("user.dir") + "allapot.dat");
        assertTrue((f.exists() && !f.isDirectory()));
    }


    @Test
    public void visszatoltTest() throws IOException, ClassNotFoundException {
        Szimulacio temp = menu.getSzimulacio();
        menu.mentes();

        menu.setSzimulacio(new Szimulacio());
        menu.visszatoltes();

        assertArrayEquals(menu.getSzimulacio().getMatrix(), temp.getMatrix());
    }
}