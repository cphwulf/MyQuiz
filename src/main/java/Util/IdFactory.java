package Util;

public class IdFactory {
    private static IdFactory idFactory;
    private static int playerId;
    private static int itemID;

    private IdFactory() {
    }

    public static IdFactory getInstance() {
        if (idFactory == null) {
            idFactory = new IdFactory();
        }
        return idFactory;
    }

    public int getItemID() {
        itemID++;
        return itemID;
    }

    public int getActorId() {
        playerId++;
        return playerId;
    }

}
