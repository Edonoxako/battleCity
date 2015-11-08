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

	//������� ���� � ���� �������� ����� � �������
	public void showObjects() {
		System.out.println("Existing objects: ");
		Iterator<GameObject> iterator = objects.iterator();
		while (iterator.hasNext()) {
			iterator.next().who();
		}
	}
}
