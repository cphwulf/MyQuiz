package View;

import Model.ActorList;
import Model.HumanPlayer;
import Util.UI;

public class UserView {
    UI ui;

    public UserView(UI ui)  {
        this.ui = ui;
    }

    public HumanPlayer loginPlayer(ActorList actorList) {
        HumanPlayer humanPlayer = null;
        while(humanPlayer== null) {
            String userName = ui.getString("Username:");
            String password = ui.getString("Password:");
        }

        return humanPlayer;
    }
}
