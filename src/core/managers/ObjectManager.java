package core.managers;

import java.util.*;

import core.graphics.Scene;
import core.model.GameObject;
import core.model.GameObjectCategory;
import core.model.GameObjectType;
import test.GameObjectFactory;
import test.TileMap;

public class ObjectManager {

	private LinkedList<GameObject> objects;

//	private int backgroundObjectsCount = 0;
//	private int entityObjectsCount = 0;
//	private int environmentObjectsCount = 0;
//	private int uiObjectsCount = 0;

	public ObjectManager() {
		objects = new LinkedList<>();
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

    //���������, ���������� �� ������ �� ������� id
    public boolean isObjectExists(int id) {
        for (GameObject object : objects) {
            if (object.hasId(id)) return true;
        }
        return false;
    }

	//��������� ������ �� �����
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

	//������� ������� �� �����
	public void removeObject(int id) {
		//���� ������ � ������� ���������
		Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			//���� �����, �� �������
			if (iterator.next().hasId(id)) {
				iterator.remove();
				return;
			}
		}
	}
	
	public void removeAllObject(){
		objects.removeAll(objects);
	}

	//������� ���� � ���� �������� ����� � �������
	public void showObjects() {
		System.out.println("Existing objects: ");
		Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			iterator.next().who();
		}
	}
	//��������� �������
    public void updateObject() {
    	Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			iterator.next().update();
		}
    }
    
    //������������ �������
    public void drawObject() {
    	Scene.clear();
    	Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			iterator.next().draw(Scene.getGraphics());
		}
		Scene.swapBuffers();
    }

    //��������� ������� �� �� ����
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

	//��������� �����-���������� ��� ���������� ������ ��������
	public class GameObjectComparator implements Comparator<GameObject> {

		@Override
		public int compare(GameObject o1, GameObject o2) {
			//����������� � �������, � ������� ���������� �������� � GameObjectCategory
			return o1.getCategory().compareTo(o2.getCategory());
		}
	}
}
