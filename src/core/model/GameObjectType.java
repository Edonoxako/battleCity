package core.model;

/**
 * Created by Евгений on 01.12.2015.
 */
public enum GameObjectType {

    BG(0), WALL(1), ROAD(2), TREE(3), HOUSE(4);

    private int type;

    GameObjectType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static GameObjectType toType(int value) {
        switch (value) {
            case 0: return BG;
            case 1: return WALL;
            case 2: return ROAD;
            case 3: return TREE;
            case 4: return HOUSE;
        }
        return null;
    }
}
