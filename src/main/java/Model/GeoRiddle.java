package Model;

import Exceptions.AlreadySolvedException;

import java.util.Hashtable;

public class GeoRiddle extends Riddle<String, HumanPlayer> {
    String solution;

    Hashtable<String,String> answers = new Hashtable<>();

    public GeoRiddle( int level, String  name ) {
        super(level,name);
    }

    @Override
    public void setAnswers(Integer pos, String answer) {

    }

    @Override
    public void setSolution(String msg) {
        this.solution = msg;
    }

    @Override
    public String getSolution() {
        return this.solution;
    }

    @Override
    public String printRiddle() {
        return null;
    }

    @Override
    public boolean solveRiddle(String userGuess) throws AlreadySolvedException {

        return false;
    }
}
