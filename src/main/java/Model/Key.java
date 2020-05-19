package Model;

import Exceptions.AlreadySolvedException;

import java.util.Set;
/*
    int level;
    int id;
    T solution;
    String name;
    Category category;
    boolean isSolved;
    String message;
    List<P> solvers = new ArrayList<>();
    Hashtable<String,T> answers = new Hashtable<String, T>();
 */

public class Key extends Riddle<Integer, HumanPlayer> {
    int solution;

    public Key(int level, String name){
        super(level,name);
        this.solution = 0;
    }

    @Override
    public void setAnswers(Integer pos, String answer) {
        answers.put(pos,answer);
    }

    @Override
    public void setSolution(Integer pos) {

        this.solution = pos;
    }

    @Override
    public Integer getSolution() {
        return this.solution;
    }

    @Override
    public String printRiddle() {
        String msg = this.message;
        Set<Integer> keys = answers.keySet();
        for(Integer k : keys) {
            msg += k + ": " + answers.get(k);
        }
        return msg;
    }

    @Override
    public boolean solveRiddle(Integer userGuess) throws AlreadySolvedException {
        return false;
    }

}
