package Model;

import java.io.PrintWriter;

public abstract class Event {
    protected PrintWriter[] writers;
    protected String output;

    public Event(PrintWriter[] w, String s) {
        this.writers = w;
        this.output = s;
    }

    public String toString() {
        return this.output;
    }

    public PrintWriter[] getWriters() {
        return this.writers;
    }
}

