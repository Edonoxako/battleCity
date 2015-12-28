package test;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.GameObjectType;
import core.utils.IdService;
import game.object.WallObject;

/**
 * Created by Евгений on 01.12.2015.
 */
public class GameObjectFactory {

    public static GameObject createObject(GameObjectCategory category, GameObjectType type, int subtype) {

        if (category == GameObjectCategory.Environment) {

            switch (type) {
                case WALL:
                    return new WallObject(IdService.generateId(), subtype);
    			case TREE:
    				return new TreeObject(IdService.generateId(), subtype);
    			case HOUSE:
    				return new HouseObject(IdService.generateId(), subtype);
			default:
				break;
                }

        }
        if (category == GameObjectCategory.Background) {
        	switch (type) {
             case BG:
            	return new BgObject(IdService.generateId(), subtype);
			case ROAD:
				return new RoadObject(IdService.generateId(), subtype);
			default:
				break;
        	}
        }

        return null;
    }

}
