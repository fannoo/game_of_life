package eletjatek;

public class Iteracio {

    /**
     * A szimuláció elet metódusa hívja, feladata kiszámolni, hogy a mátrix mely sejtjei fognak élni, illetve halni a következő iterációban a megadott szabályok mellett
     * létrejön egy új mátrix, ezt fogjuk feltölteni a sejtek új állapotával
     * a mátrix minden elemére kiszámolom, hogy hány szomszédja van, majd ha az adott sejt élő
     * és ha a tulélésnél beállított számok valamelyikével megegyezik a szomszédai száma, akkor a következő iterációban is életben marad
     * ha nem egyezik meg egyik beállított szám sem a szomszédok számával (tehát egyszer sem lépett be az if ágba az előző for cikluson belül)
     * akkor halott lesz a sejt a következő iterációban
     * ellenben, ha a sejt halott volt, de a születésnél beállított számok valamelyikével megegyezik a szomszédok száma
     * akkor következő iterációban élővé válik a sejt
     * ha nem egyezik meg egyik beállított szám sem a szomszédok számával (tehát egyszer sem lépett be az if ágba az előző for cikluson belül)
     * akkor halott lesz a sejt a következő iterációban
     *
     * @param matrix   jelenlegi mátrix
     * @param szuletes az elo szomszedok szamaval indexelve megkapja hogy szuletik-e uj cella
     * @param tuleles  az elo szomszedok szamaval indexelve megkapja hogy tulel-e az adott cella
     * @return újonnan elkészített mátrix
     */
    public static Sejt[][] ujIteracio(Sejt[][] matrix, boolean szuletes[], boolean tuleles[]) {
        int eloSzomszedDb;
        int db = 0;
        Sejt[][] ujMatrix = new Sejt[400][200];

        // vegigiteral minden elemen
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                eloSzomszedDb = eloSzomszedokSzama(i, j, matrix);
                if (matrix[i][j] == Sejt.ELO) {
                    // Ha megfelelo dbszamu elo szomszed van, akkor tulel
                    if (eloSzomszedDb != 0 && tuleles[eloSzomszedDb - 1])
                        ujMatrix[i][j] = Sejt.ELO;
                    else ujMatrix[i][j] = Sejt.HALOTT;
                } else {
                    // Ha megfelelo dbszamu elo szomszed van, akkor uja szuletik
                    if (eloSzomszedDb != 0 && szuletes[eloSzomszedDb - 1])
                        ujMatrix[i][j] = Sejt.ELO;
                    else ujMatrix[i][j] = Sejt.HALOTT;
                }
            }
        }
        return ujMatrix;
    }

    /**
     * a paraméterként kapott sor előtti sortól az utána lévő sorig (ez 3 sor)
     * és a paraméterként kapott oszlop előtti oszloptól az utána következő oszlopig (ez 3 oszlop)
     * megnézzük, hogy az éppen aktuális sejt, ahol a ciklus van elő-e
     * illetve ha igen, akkor a szomszédokat számláló int-et egyel növelem
     * előfordulhat, hogy olyan sejt szomszédait szeretnénk megkapni, ami a mátrix szélén van
     * ebben az esetben ArrayIndexOutOfBoundsException-t kapunk, de ignorálom és inkább folytatom a következő iterációval
     * a ciklus végén még meg kell nézni, hogy a paraméterként kapott helyen lévő sejt élő volt, mert az előzőekben azt is beleszámoltuk, akkor a szomszédok közé
     * és ki kell vonni a számból
     * egyébként így már visszatérhetünk a számmal
     *
     * @param x      megkapja, hogy a mátrix mely sorában,
     * @param y      illetve mely oszlopában lévő sejt szomszédait kell megszámolni
     * @param matrix ezen felül természetesen a mátrixra is szükség van ehhez
     * @return visszaadja, hogy hány szomszéddal rendelkezik az adott sejt
     */
    public static int eloSzomszedokSzama(int x, int y, Sejt[][] matrix) {
        int szam = 0;
        for (int szelesseg = (x - 1); szelesseg <= (x + 1); szelesseg++) {
            for (int magassag = (y - 1); magassag <= (y + 1); magassag++) {
                // sajat magat ne szamolja bele
                if (szelesseg == x && magassag == y)
                    continue;
                // ha a szelen van, akkor ne vizsgalja az eloseget
                if (szelesseg < 0 || szelesseg >= matrix.length || magassag < 0 || magassag >= matrix[0].length)
                    continue;
                if (matrix[szelesseg][magassag] == Sejt.ELO) szam++;
            }
        }
        return szam;
    }
}
