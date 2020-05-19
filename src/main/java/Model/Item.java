package Model;

import Util.IdFactory;

public abstract class Item {
    boolean canBeCarried;
    String description;
    String name;
    int itemID;
    int value;

    public Item(String name) {
        this.name = name;
        this.itemID = IdFactory.getInstance().getItemID();
        this.canBeCarried = true;
        this.value = 0;
    }
    public String getName() {
        return this.name;
    }

    public boolean isCanBeCarried() {
        return canBeCarried;
    }

    public void setCanBeCarried(boolean canBeCarried) {
        this.canBeCarried = canBeCarried;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getItemID() {
        return itemID;
    }

}
