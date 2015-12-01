package core.model;

/**
 * Created by ������� on 10.11.2015.
 *
 * ���� ������� �������� (�����, ���� � �.�.)
 */

public enum GameObjectCategory {
		Background(0), 		//���;
		Entity(1),		//�����, ���������;
		Environment(2),		//���������;
		UI(3);	//���������;
		
		private int value;
		
		GameObjectCategory(int value){
			this.value = value;
		}
		
		//���������� int �������� ���� �������.
		public int getState(){
			return value;
		}

		public static GameObjectCategory toCategory(int value) {
			switch (value) {
				case 0: return Background;
				case 1: return Entity;
				case 2: return Environment;
				case 3: return UI;
			}
			return null;
		}
		
}
