package core.managers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.GameObjectType;
import test.GameObjectFactory;
import test.TileMap;

public class ObjectManager {

	private ArrayList<GameObject> objects;
	
	public ObjectManager() {
		objects = new ArrayList<GameObject>(200);
	}


	public ArrayList<GameObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<GameObject> objects) {
		this.objects = objects;
	}
	
	public GameObject getObject(int id){
		return objects.get(id);
	}

    //Проверяет, существует ли объект по данному id
    public boolean isObjectExists(int id) {
        for (GameObject object : objects) {
            if (object.hasId(id)) return true;
        }
        return false;
    }

	//Добавляет объект на сцену
	public void addObject(GameObject obj) {
		objects.add(obj);
	}

	//Удаляет объекты из сцены
	public void removeObject(int id) {
		//Ищем объект с помощью итератора
		Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			//Если нашли, то удаляем
			if (iterator.next().hasId(id)) {
				iterator.remove();
				return;
			}
		}
	}
	
	public void removeAllObject(){
		objects.removeAll(objects);
	}

	//Выводит инфу о всех объектах сцены в консоль
	public void showObjects() {
		System.out.println("Existing objects: ");
		Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			iterator.next().who();
		}
	}
	//Обновляет объекты
    public void updateObject() {
    	Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			iterator.next().update();
		}
    }
    
    //Отрисовывает объекты
    public void drawObject() {
    	Scene.clear();
    	Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			iterator.next().draw(Scene.getGraphics());
		}
		Scene.swapBuffers();
    }

    //Сортирует объекты по их типу
    public void sortObjects() {
        Collections.sort(objects, new GameObjectComparator());
    }

	public void createMap(TileMap map) {
        map.getMapObjects().stream()
                .forEach(obj -> {
                    GameObjectCategory category = GameObjectCategory.toCategory(obj.getCategory());
                    GameObjectType type = GameObjectType.toType(obj.getType());
                    GameObject object = GameObjectFactory.createObject(category, type, obj.getSubtype());

                    object.setX(obj.getCoordX());
                    object.setY(obj.getCoordY());

                    objects.add(object);
                });
    }

	//Внутрений класс-компаратор для сортировки списка объектов
	public class GameObjectComparator implements Comparator<GameObject> {

		@Override
		public int compare(GameObject o1, GameObject o2) {
			//Сортируется в порядке, в котором определены значения в GameObjectCategory
			return o1.getCategory().compareTo(o2.getCategory());
		}
	}
}
