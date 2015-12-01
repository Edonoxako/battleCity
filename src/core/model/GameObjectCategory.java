package core.model;

/**
 * Created by Евгений on 10.11.2015.
 *
 * Типы игровых объектов (игрок, враг и т.д.)
 */

public enum GameObjectCategory {
		Background(0), 		//Фон;
		Entity(1),		//Игрок, противник;
		Environment(2),		//Окружение;
		UI(3);	//Интерфейс;
		
		private int value;
		
		GameObjectCategory(int value){
			this.value = value;
		}
		
		//Возвращает int значение типа объекта.
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
