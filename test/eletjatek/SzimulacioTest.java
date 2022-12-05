package eletjatek;

import javax.swing.JCheckBox;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SzimulacioTest {

    Szimulacio sz;
    JCheckBox[] c = new JCheckBox[8];

    @Before
    public void setUp() {
        sz = new Szimulacio();
        for (int i = 0; i < 8; i++) {
            c[i] = new JCheckBox();
        }
    }

    @Test
    public void szimulacioTeszt() {
        assertNotNull(sz.getMatrix());
    }

    @Test
    public void setSzuletesTeszt() {
        sz.setSzuletes(c);
        boolean tomb[] = {false, false, false, false, false, false, false, false};
        assertArrayEquals(tomb, sz.getSzuletes());
    }

    @Test
    public void setTulelesTeszt() {
        sz.setTuleles(c);
        boolean tomb[] = {false, false, false, false, false, false, false, false};
        assertArrayEquals(tomb, sz.getTuleles());
    }

}