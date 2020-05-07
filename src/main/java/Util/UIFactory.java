package Util;

public class UIFactory {

    public static UI createUI(String type) {
        UI ui = null;
        switch (type) {
            case "c":
                ui = new ConsoleUI();
                break;
            case "f":
                ui = new FakeUI();
                break;
        }
        return ui;
    }


}
