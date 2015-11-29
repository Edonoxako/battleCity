package core.model;

/**
 * Created by Евгений on 10.11.2015.
 *
 * Типы игровых объектов (игрок, враг и т.д.)
 */

public enum GameObjectType {
		Background(0), 		//Фон;
		Entity(1),		//Игрок, противник;
		Environment(2),		//Окружение;
		UI(3);	//Интерфейс;
		
		private int value;
		
		GameObjectType(int value){
			this.value = value;
		}
		//Возвращает int значение типа объекта.
		public int getState(){
			return value;
		}
		
}
