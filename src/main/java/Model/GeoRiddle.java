package Model;

import Exceptions.AlreadySolvedException;

import java.util.Hashtable;

public class GeoRiddle extends Riddle {
    int solution;

    Hashtable<String,String> answers = new Hashtable<>();

    public GeoRiddle( int level, String  name ) {
        super(level,name);
    }

    @Override
    public void setAnswers(Integer pos, String answer) {

    }

    @Override
    public void setSolution(int msg) {
        this.solution = msg;
    }

    @Override
    public int getSolution() {
        return this.solution;
    }

    @Override
    public String printRiddle() {
        return null;
    }

    @Override
    public boolean solveRiddle(int userGuess) throws AlreadySolvedException {

        return false;
    }
}
