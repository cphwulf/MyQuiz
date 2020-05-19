package Exceptions;

public class ItemAlreadyPickedException extends Exception {
    public ItemAlreadyPickedException(String msg) {
        System.out.println("Item-error: " + msg);
    }
}
