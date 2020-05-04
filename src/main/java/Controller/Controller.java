package Controller;

import Model.Actor;
import Model.ActorList;
import Model.HumanPlayer;
import Util.UI;
import Util.UIFactory;
import View.UserView;

public abstract class Controller {
    public ActorList actorList;
    public HumanPlayer humanPlayer;
    public UserView userView;
    public UIFactory uiFactory;
    public UI ui;

    public Controller() {
        ui = uiFactory.createUI("c");
        userView = new UserView(ui);
        uiFactory = new UIFactory();
    }

    public void runGame() {

        humanPlayer = userView.loginPlayer(actorList);
    }


}
