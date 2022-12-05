package eletjatek;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class IteracioTest {

    Sejt[][] matrix;
    boolean szuletes[];
    boolean tuleles[];
    Sejt[][] ujMatrix;

    @Before
    public void setUp() {
        matrix = new Sejt[400][200];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Sejt.HALOTT;
            }
        }

        matrix[0][0] = Sejt.ELO;
        matrix[0][3] = Sejt.ELO;
        matrix[0][4] = Sejt.ELO;

        ujMatrix = new Sejt[400][200];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                ujMatrix[i][j] = Sejt.HALOTT;
            }
        }
    }

    @Test
    public void szomszedokSzamaNulla() {
        int result = Iteracio.eloSzomszedokSzama(0, 0, matrix);
        assertEquals(0, result);
    }

    @Test
    public void szomszedokSzamaKetto() {
        int result = Iteracio.eloSzomszedokSzama(1, 3, matrix);
        assertEquals(2,result);
    }

    @Test
    public void ujIteracioElso() {
        ujMatrix[0][3] = Sejt.ELO;
        ujMatrix[0][4] = Sejt.ELO;
        boolean t[] = {true, false, false, false, false, false, false, false};
        boolean sz[] = {false, false, false, false, false, false, false, false};
        Sejt[][] ujabbMatrix = Iteracio.ujIteracio(matrix, sz, t);
        assertArrayEquals(ujMatrix, ujabbMatrix);
    }

    @Test
    public void ujIteracioMasodik() {
        ujMatrix[1][3] = Sejt.ELO;
        ujMatrix[1][4] = Sejt.ELO;
        boolean t[] = {false, true, false, false, false, false, false, false};
        boolean sz[] = {false, true, false, false, false, false, false, false};
        Sejt[][] ujabbMatrix = Iteracio.ujIteracio(matrix, sz, t);
        assertArrayEquals(ujMatrix, ujabbMatrix);
    }

}
