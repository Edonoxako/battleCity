package core.managers;

import core.model.GameObject;
import test.Tr;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	public Object getObject(int id){
		return 0;
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

	//Выводит инфу о всех объектах сцены в консоль
	public void showObjects() {
		System.out.println("Existing objects: ");
		Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			iterator.next().who();
		}
	}
}
