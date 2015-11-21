package core.managers;

import core.graphics.Scene;
import core.model.GameObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		objects.add(obj);
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

	//��������� �����-���������� ��� ���������� ������ ��������
	public class GameObjectComparator implements Comparator<GameObject> {

		@Override
		public int compare(GameObject o1, GameObject o2) {
			//����������� � �������, � ������� ���������� �������� � GameObjectType
			return o1.getType().compareTo(o2.getType());
		}
	}
}
