package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Р•РІРіРµРЅРёР№ on 21.11.2015.
 */
public class TileMap {

    //Р’С‹СЃРѕС‚Р° Рё С€РёСЂРёРЅР° РєР°СЂС‚С‹
    private int height;
    private int width;

    //РљРѕР»РёС‡РµСЃС‚РІРѕ СЃС‚СЂРѕРє РІ РєР°СЂС‚Рµ. Р§РёСЃС‚Рѕ РґР»СЏ СЃР»СѓР¶РµР±РЅС‹С… С†РµР»РµР№
    private int linesCount = 0;

    //Р—РґРµСЃСЊ Р±СѓРґСѓС‚ Р»РµР¶Р°С‚СЊ РІСЃРµ СЃС‚Р°С‚РёС‡РЅС‹Рµ РѕР±СЉРµРєС‚С‹ РєР°СЂС‚С‹ (СЃС‚РµРЅРєРё, СЏС‰РёРєРё, РїСЊСЏРЅС‹Р№ Р”РёРјР° Рё С‚.Рґ.)
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

    //РњРµС‚РѕРґ РїРѕСЃС‚СЂРѕС‡РЅРѕ РїРѕР»СѓС‡Р°РµС‚ СЃС‚СЂРѕРєРё РєР°СЂС‚С‹ РёР· Р·Р°РіСЂСѓР·С‡РёРєР° СЂРµСЃСѓСЂСЃРѕРІ Рё С‚СѓС‚ Р¶Рµ РёС‰РµС‚ РІ РЅРёС… СЃС‚Р°С‚РёС‡РЅС‹Рµ РѕР±СЉРµРєС‚С‹
    public void addLine(String line) {
        if (linesCount < height) {

            //РџСЂРµРІСЂР°С‰Р°РµРј С‚РµРєСЃС‚РѕРІСѓСЋ СЃС‚СЂРѕРєСѓ РІ С†РµР»РѕС‡РёСЃР»РµРЅРЅС‹Р№ РјР°СЃСЃРёРІ. РЎР»Р°РІР° stream api!
            String[] mapLine = line.split(" ");

            //Р�С‰РµРј СЃС‚Р°С‚РёС‡РЅС‹Рµ РѕР±СЉРµРєС‚С‹ РІ РјР°СЃСЃРёРІРµ
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

    //РљР»Р°СЃСЃ, СЃРѕРґРµСЂР¶Р°С‰РёР№ РІ СЃРµР±Рµ РјРµС‚Р°-РґР°РЅРЅС‹Рµ Рѕ СЃС‚Р°С‚РёС‡РЅРѕРј РѕР±СЉРµРєС‚Рµ РєР°СЂС‚С‹ (РєРѕРѕСЂРґРёРЅР°С‚С‹ Рё С‚РёРї)
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
