package test;

import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.GameObjectType;
import core.utils.IdService;

/**
 * Created by Евгений on 01.12.2015.
 */
public class GameObjectFactory {

    public static GameObject createObject(GameObjectCategory category, GameObjectType type, int subtype) {

        if (category == GameObjectCategory.Environment) {

            switch (type) {
                case WALL:
                    return new WallObject(IdService.generateId(), subtype);
            }

        }

        return null;
    }

}
