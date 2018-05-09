package model;

import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import java.util.Formatter;
import java.util.Locale;
import java.util.Random;

public class DicePairImpl implements DicePair {

    private int dice1;
    private int dice2;
    private int numFaces;

    public DicePairImpl(int numFaces) {
        this.numFaces = numFaces;
        Random r = new Random();
        dice1 = r.nextInt(numFaces) + 1;
        dice2 = r.nextInt(numFaces) + 1;
    }

    public DicePairImpl() {
        this(GameEngine.NUM_FACES);
    }

    public int getDice1() {
        return this.dice1;
    }

    public int getDice2() {
        return this.dice2;
    }

    public int getNumFaces() {
        return this.numFaces;
    }

    //a human readable String that lists the values of this DicePair instance (see OutputTrace.txt)
    public String toString() {
        Formatter formatter = new Formatter(new StringBuilder(), Locale.US);
        String display;
        formatter.format("dice1=%s, dice2=%s, numFaces=%d", dice1, dice2, numFaces);
        display = formatter.toString();
        formatter.close();
        return display;
    }
}

