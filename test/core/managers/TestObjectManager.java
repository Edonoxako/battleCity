package core.managers;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics2D;
import java.util.List;

import org.junit.Test;

import core.model.GameObject;
import core.model.GameObjectCategory;
import game.object.WallObject;

/**
 * Created by Евгений on 15.12.2015.
 */
public class TestObjectManager {

    public class TestGameObject extends GameObject {

        public TestGameObject(int id, GameObjectCategory category) {
            super(id, category);
        }

        @Override
        public void draw(Graphics2D g) {

        }

        @Override
        public void update() {

        }

		@Override
		public void collision(int x, int y, GameObject obj) {
			// TODO Auto-generated method stub
			
		}
    }


    @Test
    public void testIsObjectExists() {

        ObjectManager manager = new ObjectManager();
        manager.addObject(new WallObject(1, WallObject.DOWN_BRICK_WALL));

        assertEquals(true, manager.isObjectExists(1));
    }

    @Test
    public void testAddObject() {

        //Init
        ObjectManager manager = new ObjectManager();

        TestGameObject object1 = new TestGameObject(1, GameObjectCategory.Environment);
        TestGameObject object2 = new TestGameObject(2, GameObjectCategory.Entity);
        TestGameObject object3 = new TestGameObject(3, GameObjectCategory.Environment);
        TestGameObject object4 = new TestGameObject(4, GameObjectCategory.Entity);
        TestGameObject object5 = new TestGameObject(5, GameObjectCategory.Background);
        TestGameObject object6 = new TestGameObject(6, GameObjectCategory.Environment);
        TestGameObject object7 = new TestGameObject(7, GameObjectCategory.UI);

        //Making tests
        manager.addObject(object1);
        manager.addObject(object2);
        manager.addObject(object3);
        manager.addObject(object4);
        manager.addObject(object5);
        manager.addObject(object6);
        manager.addObject(object7);

        List<GameObject> objects = manager.getObjects();

        //Check test results
        assertEquals("Background", GameObjectCategory.Background, objects.get(0).getCategory());

        for (int i = 1; i < 3; i++) {
            assertEquals("Entity " + i, GameObjectCategory.Entity, objects.get(i).getCategory());
        }

        for (int i = 3; i < 6; i++) {
            assertEquals("Environment " + i, GameObjectCategory.Environment, objects.get(i).getCategory());
        }

        assertEquals("UI", GameObjectCategory.UI, objects.get(6).getCategory());

        //Test inserting in the middle of the list
        TestGameObject entityObject = new TestGameObject(8, GameObjectCategory.Entity);
        TestGameObject environmentObject = new TestGameObject(9, GameObjectCategory.Environment);

        manager.addObject(entityObject);
        manager.addObject(environmentObject);

        objects = manager.getObjects();
        assertEquals("Middle entity object", 8, objects.get(3).getId());
        assertEquals("Middle environment object", 9, objects.get(7).getId());

    }

}
