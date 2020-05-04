package Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HumanPlayerTest {
    HumanPlayer humanPlayer;
    Item item;

    @Before
    public void setUp() throws Exception {
        humanPlayer = new HumanPlayer("kurt");
        item = new Gun();
    }

    @Test
    public void pickupItem() {
        humanPlayer.pickupItem(item);
    }
}