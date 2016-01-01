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

		int backgroundObjectsCount = 0;
		int entityObjectsCount = 0;
		int environmentObjectsCount = 0;

		switch (obj.getCategory()) {

			case Background:
				backgroundObjectsCount = (int) objects.stream()
						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Background)
						.count();
				objects.add(backgroundObjectsCount, obj);
				break;
			case Entity:
				backgroundObjectsCount = (int) objects.stream()
						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Background)
						.count();

				entityObjectsCount = (int) objects.stream()
						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Entity)
						.count();

				objects.add(backgroundObjectsCount + entityObjectsCount, obj);
				break;
			case Environment:
				backgroundObjectsCount = (int) objects.stream()
						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Background)
						.count();

				entityObjectsCount = (int) objects.stream()
						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Entity)
						.count();

				environmentObjectsCount = (int) objects.stream()
						.filter(gameObject -> gameObject.getCategory() == GameObjectCategory.Environment)
						.count();

				objects.add(backgroundObjectsCount + entityObjectsCount + environmentObjectsCount, obj);
				break;
			case UI:
				objects.add(obj);
				break;

		}
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
	public boolean checkObject(int x, int y){
		if(dumpMap[y][x]!=null){
			return true;
		}
		else return false;
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
