package core.managers;

import java.util.*;

import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.GameObjectType;
import test.GameObjectFactory;
import test.TileMap;

public class ObjectManager {
	private int backgroundObjectsCount = 0;
	private int entityObjectsCount = 0;
	private int environmentObjectsCount = 0;
	private int uiObjectsCount = 0;
	private int mapWidth, mapHeight;
	private LinkedList<GameObject> objects;
	private GameObject[][] dumpMap;

	public ObjectManager() {
		objects = new LinkedList<>();
		mapWidth = 0;
		mapHeight = 0;
	}

	public LinkedList<GameObject> getObjects() {
		return objects;
	}

	public void setObjects(LinkedList<GameObject> objects) {
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

		switch (obj.getCategory()) {

			case Background:
//				backgroundObjectsCount = (int) objects.stream()
//						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Background)
//						.count();
				objects.add(backgroundObjectsCount, obj);
				backgroundObjectsCount++;
				break;
			case Entity:
//				backgroundObjectsCount = (int) objects.stream()
//						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Background)
//						.count();
//
//				entityObjectsCount = (int) objects.stream()
//						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Entity)
//						.count();
				objects.add(backgroundObjectsCount + entityObjectsCount, obj);
				entityObjectsCount++;
				break;
			case Environment:
//				backgroundObjectsCount = (int) objects.stream()
//						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Background)
//						.count();
//
//				entityObjectsCount = (int) objects.stream()
//						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Entity)
//						.count();
//
//				environmentObjectsCount = (int) objects.stream()
//						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Environment)
//						.count();

				objects.add(backgroundObjectsCount + entityObjectsCount + environmentObjectsCount, obj);
				environmentObjectsCount++;
				break;
			case UI:
				objects.add(obj);
				uiObjectsCount++;
				break;

		}
	}

	//Удаляет объекты из сцены
	public void removeObject(int id, GameObjectCategory goc) {
		int first = 0, last = 0;
		switch (goc) {
			case Background:
				first = 0;
				last = backgroundObjectsCount;
				break;
			case Entity:
				first = backgroundObjectsCount;
				last = first+entityObjectsCount;
				break;
			case Environment:
				first = backgroundObjectsCount+entityObjectsCount;
				last = first+environmentObjectsCount;
				break;
			case UI:
				first = backgroundObjectsCount+entityObjectsCount+environmentObjectsCount;
				last = first+uiObjectsCount;
				break;
	
			default:
				break;
		}
		//Получаем срез с листа
		Iterator<GameObject> iterator = objects.subList(first, last).iterator();
		GameObject temp;
		while (iterator.hasNext()) {
			//Уменьшаем счетчики объектов
			temp = iterator.next();
			if (temp.hasId(id)) {
				switch (temp.getCategory()) {
					case Background:
						backgroundObjectsCount--;
						break;
					case Entity:
						entityObjectsCount--;
						break;
					case Environment:
						environmentObjectsCount--;
						break;
					case UI:
						uiObjectsCount--;
						break;
				}
				//удаляем объект
				if(temp.getType()!=GameObjectType.EFFECTS)
				dumpMap[temp.getY()/48][temp.getX()/48] = null;
				iterator.remove();
				return;
			}
		}
	}
	
	public void removeAllObject(){
		backgroundObjectsCount =0;
		entityObjectsCount = 0;
		environmentObjectsCount = 0;
		uiObjectsCount = 0;
		if(dumpMap!=null)
		Arrays.fill(dumpMap, null);
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

	public boolean createMap(TileMap map) {
		if (map == null) return false;
		mapWidth = map.getWidth();
		mapHeight = map.getHeight();
		dumpMap = new GameObject[mapHeight+1][mapWidth+1];
        map.getMapObjects().stream().forEach(obj -> {
                    GameObjectCategory category = GameObjectCategory.toCategory(obj.getCategory());
                    GameObjectType type = GameObjectType.toType(obj.getType());
                    GameObject object = GameObjectFactory.createObject(category, type, obj.getSubtype());

                    object.setX(obj.getCoordX());
                    object.setY(obj.getCoordY());
                    
                    addObject(object);
                    
                    if (object.getCategory() == GameObjectCategory.Environment) 
                    	dumpMap[(int)(object.getY())][(int)(object.getX())] = object;
                });
        return true;
    }
	public boolean checkObject(int x, int y, int myId, int ignoreId){
		if(dumpMap[y][x]!=null){
			dumpMap[y][x].collision(x, y, getObjectForId(myId));
			return true;
		}
		Iterator<GameObject> iterator = objects.subList(backgroundObjectsCount, 
				backgroundObjectsCount+entityObjectsCount).iterator();
		GameObject temp;
		while (iterator.hasNext()) {
			temp = iterator.next();
			if (temp.getX()/48 == x && temp.getY()/48 == y && !temp.hasId(myId) && !temp.hasId(ignoreId)) {
				temp.collision(x, y, getObjectForId(myId));
				return true;
			}
		}
		return false;
	}
	
	public GameObject getObjectForId(int id){
		for (GameObject object : objects) {
            if (object.hasId(id)) return object;
        }
		return null;
	}
	
	//Задает сдвиг объектов по оси х
	public void moveObjectX(int dmx){
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).dmx = dmx;
		}
	}
	//Задает сдвиг объектов по оси у
	public void moveObjectY(int dmy){
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).dmy = dmy;
		}
	}

	public int getMapHeight(){
		return mapHeight*48;
	}
	public int getMapWidth(){
		return mapWidth*48;
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
