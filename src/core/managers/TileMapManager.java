package core.managers;

import test.TileMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Евгений on 21.11.2015.
 */
public class TileMapManager {

    private TileMap tileMap;

    public void loadMap(String mapFile) {

        URL url = getClass().getClassLoader().getResource(mapFile);
        Path path = null;
        try {
            path = Paths.get(url.toURI());

            tileMap = null;
            Charset charset = Charset.forName("UTF-8");
            try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
                String line = null;

                int mapHeight = Integer.parseInt(reader.readLine());
                int mapWidth = Integer.parseInt(reader.readLine());
                tileMap = new TileMap(mapHeight, mapWidth);

                while ((line = reader.readLine()) != null) {
                    tileMap.addLine(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public TileMap getTileMap() {
        return tileMap;
    }
}
