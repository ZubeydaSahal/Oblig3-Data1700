package com.example.oblig3data1700;

import java.util.Comparator;

public class Sorter implements Comparator<Billett> {

    /*Her så lager jeg en metode som sammenligner to billetter
    * hvor jeg sammenliger etternavn til bilettet. Denne bruker compare
    * metoden som ligger i Comparator grensesnittet*/
    public int compare(Billett s1, Billett s2){
        return s1.getEtternavn().toLowerCase().compareTo((s2.getEtternavn().toLowerCase()));
    }
}