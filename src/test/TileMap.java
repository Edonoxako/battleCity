package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Евгений on 21.11.2015.
 */
public class TileMap {

    private int height;
    private int width;
    private int[][] map;

    private int linesCount = 0;


    public TileMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.map = new int[width][height];
    }

    public TileMap(int height, int width, int[][] map) {
        this.height = height;
        this.width = width;
        this.map = map;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[][] getMap() {
        return map;
    }


    public void addLine(String line) {
        if (linesCount < height) {
            map[linesCount] = Arrays.stream(line.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            linesCount++;
        }
    }

    public void showDump() {
        for (int[] line : map) {
            Arrays.stream(line)
                    .mapToObj(String::valueOf)
                    .forEach(s -> System.out.print(s + " "));
            System.out.println("\n");
        }
    }
}
