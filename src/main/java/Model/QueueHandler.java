package Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueHandler {
    ArrayBlockingQueue<Message> messages;

    public QueueHandler(int capacity) {
        messages = new ArrayBlockingQueue<>(capacity);
    }

    public void addItemToQueu(Message msg){
        messages.add(msg);
    }
    public Message getItemFromQueue(){
        Message message = null;
        try {
            message = messages.take();
        } catch (InterruptedException e) {
            System.out.println("error  " + e.getStackTrace());
        }
        return message;
    }


}
