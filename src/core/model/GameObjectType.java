package core.model;

/**
 * Created by Евгений on 01.12.2015.
 */
public enum GameObjectType {

    WALL(1);

    private int type;

    GameObjectType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static GameObjectType toType(int value) {
        switch (value) {
            case 1: return WALL;
        }
        return null;
    }
}
