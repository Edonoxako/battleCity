package core.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class Resour�eLoader {
	public static final String PATH = "res/";
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
	 //если расширение будет image/jpeg то выполнется loadImage(или спрайты, не важно)

	public static BufferedImage loadImage(String fileName){
		
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(new File(PATH + fileName));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	//��������� ���� � �����������.
	public static FileInputStream loadConfigDefault(){
	    try {
	    	return  new FileInputStream(DEFAULT_CONFIG_PATH);
	    } catch (IOException e) {
	        System.err.println("���� ������� ����������!");
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
/*загрузить файл и получить объект файла, написать экземпляр класса LoadFile() и работать с ним*/
/*public class ResurseLoader {
 public static final String PATH = "res/";
 public static BufferedImage loadImage(String fileName){..}
 public static setPath(String path){...};
 public static BufferedRead loadFile(String fileName){...}
}*/