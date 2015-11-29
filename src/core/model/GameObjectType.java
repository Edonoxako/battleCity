package core.model;

/**
 * Created by ������� on 10.11.2015.
 *
 * ���� ������� �������� (�����, ���� � �.�.)
 */

public enum GameObjectType {
		Background(0), 		//���;
		Entity(1),		//�����, ���������;
		Environment(2),		//���������;
		UI(3);	//���������;
		
		private int value;
		
		GameObjectType(int value){
			this.value = value;
		}
		//���������� int �������� ���� �������.
		public int getState(){
			return value;
		}
		
}
