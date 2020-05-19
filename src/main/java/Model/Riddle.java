package Model;

import Exceptions.AlreadySolvedException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class Riddle<T, P> {
    int level;
    int id;
    T solution;
    String name;
    Category category;
    boolean isSolved;
    String message;
    List<P> solvers = new ArrayList<>();
    Hashtable<Integer,String> answers = new Hashtable<Integer, String>();

    public Riddle(int level, String name)  {
        this.level=level;
        this.name=name;
        this.isSolved=false;
    }

    public boolean addSolver(T solution, P player) {
        if (solvers.contains(player)) {
            return false;
        } else {
            solvers.add(player) ;
            return true;
        }
    }

    public Hashtable<Integer, String> getAnswers() {
        return answers;
    }

    public Category getCategory() {
        return category;
    }


    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }
    public abstract void setSolution(T msg);
    public abstract  T getSolution();
    public abstract String printRiddle();
    public abstract boolean solveRiddle(T userGuess) throws AlreadySolvedException;
    public abstract void setAnswers(Integer pos, String answer);
}

