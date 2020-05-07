package Controller;
import Model.Event;

import java.io.PrintWriter;
import java.util.concurrent.LinkedBlockingQueue;

public class EventDispatcher  extends Thread {
    private LinkedBlockingQueue<Event> eventQueue;

    public EventDispatcher() {
        this.eventQueue = new LinkedBlockingQueue<Event>();
    }

    private synchronized void addEvent(Event event) {
        try {
            this.eventQueue.put(event);
        } catch (InterruptedException e) {
            return;
        }
    }

    public void run() {
        while (true) {
            try {
                Event e = this.eventQueue.take();
                PrintWriter[] w = e.getWriters();
                for (int i = 0; i < w.length; i++) {
                    w[i].println(e.toString());
                }

            } catch (InterruptedException e) {
                System.out.println("event queue got interrupt");
                return;
            }
        }
    }


}
