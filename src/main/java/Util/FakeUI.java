package Util;

public class FakeUI extends UI {
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
