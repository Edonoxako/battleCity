package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Евгений on 21.11.2015.
 */
public class TileMap {

    //Высота и ширина карты
    private int height;
    private int width;

    //Количество строк в карте. Чисто для служебных целей
    private int linesCount = 0;

    //Здесь будут лежать все статичные объекты карты (стенки, ящики, пьяный Дима и т.д.)
    private List<MapObject> mapObjects = new ArrayList<>();


    public TileMap(int height, int width) {
        this.height = height;
        this.width = width;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<MapObject> getMapObjects() {
        return mapObjects;
    }

    //Метод построчно получает строки карты из загрузчика ресурсов и тут же ищет в них статичные объекты
    public void addLine(String line) {
        if (linesCount < height) {

            //Превращаем текстовую строку в целочисленный массив. Слава stream api!
            String[] mapLine = line.split(" ");

            //Ищем статичные объекты в массиве
            for (int i = 0; i < mapLine.length; i++) {
                if (!mapLine[i].equals("0")) {
                    int[] params = Arrays.stream(mapLine[i].split(":")).mapToInt(Integer::parseInt).toArray();
                    mapObjects.add(new MapObject(params[0], params[1], params[2], i, linesCount));
                }
            }

            linesCount++;
        }
    }

    public void showDump() {
        mapObjects.stream().forEach(System.out::println);
    }

    //Класс, содержащий в себе мета-данные о статичном объекте карты (координаты и тип)
    public class MapObject {

        private int category;
        private int type;
        private int subtype;
        private int coordX;
        private int coordY;

        public MapObject(int category, int type, int subtype, int x, int y) {
            this.category = category;
            this.type = type;
            this.subtype = subtype;
            this.coordX = x;
            this.coordY = y;
        }

        public int getCoordX() {
            return coordX;
        }

        public int getCoordY() {
            return coordY;
        }

        public int getType() {
            return type;
        }

        public int getCategory() {
            return category;
        }

        public int getSubtype() {
            return subtype;
        }

        @Override
        public String toString() {
            return "type: " + type + " coordX: " + coordX + " coordY: " + coordY;
        }
    }
}
