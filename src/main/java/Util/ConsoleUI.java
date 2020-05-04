package Util;

import java.util.Scanner;

public class ConsoleUI extends UI {
    Scanner ms;

    public ConsoleUI() {
        ms = new Scanner(System.in);
    }

    @Override
    public int getInteger(String msg) {
        return 0;
    }

    @Override
    public String getString(String msg) {
        return null;
    }

    @Override
    public void printMsg(String msg) {

    }
}
