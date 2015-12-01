package core.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import test.TileMap;

public class ResourceLoader {
	public static final String PATH = "res/";
	public static final String DEFAULT_TEXTURE_PATH = "src/res/sprite/48/";
	private static String DEFAULT_CONFIG_PATH = "src/res/config/config.properties";
	
	 public static String getMimeType(String fileUrl)
			  throws java.io.IOException, MalformedURLException
			 {
				 String type = null;
				 URL u = new URL(fileUrl);
				 URLConnection uc = null;
				 uc = u.openConnection();
				 type = uc.getContentType();
				 return type;
			 }
	
    public static TileMap loadMap(String mapFile) {

        URL url = TileMap.class.getClassLoader().getResource(mapFile);
        Path path = null;
        try {
            path = Paths.get(url.toURI());

            TileMap tileMap = null;
            Charset charset = Charset.forName("UTF-8");
            try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
                String line = null;

                int mapHeight = Integer.parseInt(reader.readLine());
                int mapWidth = Integer.parseInt(reader.readLine());
                tileMap = new TileMap(mapHeight, mapWidth);

                while ((line = reader.readLine()) != null) {
                    tileMap.addLine(line);
                }

                return tileMap;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
	public static BufferedImage loadImage(String fileName){
		
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(new File(DEFAULT_TEXTURE_PATH + fileName));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	//«‡„ÛÊ‡ÂÚ Ù‡ÈÎ Ò Ì‡ÒÚÓÈÍ‡ÏË.
	public static FileInputStream loadConfigDefault(){
	    try {
	    	return  new FileInputStream(DEFAULT_CONFIG_PATH);
	    } catch (IOException e) {
	        System.err.println("‘‡ÈÎ Ò‚ÓÈÒÚ‚ ÓÚÒÛÒÚ‚ÛÂÚ!");
	    }
		return null;
		
	}
//	private String loadConfig(String fileName) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            InputStream is = (InputStream) getClass().getResourceAsStream(PATH + fileName);
//            BufferedReader br = new BufferedReader(new InputStreamReader(is, "Cp1251"));
//            while (true) {
//                String line = br.readLine();
//                if (line == null)
//                    break;
//                sb.append(line).append("\n");
//            }
//        } catch (IOException ex) {
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            ex.printStackTrace(pw);
//            pw.flush();
//            pw.close();
//            sb.append("Error: ").append("\n\n");
//            sb.append(sw.getBuffer().toString());
//        }
//        return sb.toString();
//    }
}
/*–∑–∞–≥—Ä—É–∑–∏—Ç—å —Ñ–∞–π–ª –∏ –ø–æ–ª—É—á–∏—Ç—å –æ–±—ä–µ–∫—Ç —Ñ–∞–π–ª–∞, –Ω–∞–ø–∏—Å–∞—Ç—å —ç–∫–∑–µ–º–ø–ª—è—Ä –∫–ª–∞—Å—Å–∞ LoadFile() –∏ —Ä–∞–±–æ—Ç–∞—Ç—å —Å –Ω–∏–º*/
/*public class ResurseLoader {
 public static final String PATH = "res/";
 public static BufferedImage loadImage(String fileName){..}
 public static setPath(String path){...};
 public static BufferedRead loadFile(String fileName){...}
}*/