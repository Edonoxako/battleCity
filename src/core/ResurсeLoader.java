package core;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.omg.CORBA.portable.InputStream;

public class ResurсeLoader {
	public static final String PATH = "res/";
	
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
	 //если расширение будет image/jpeg то выполнетс€ loadImage(или спрайты, не важно)

	public static BufferedImage loadImage(String fileName){
		
		BufferedImage image = null;
		
		try {
			
			image = ImageIO.read(new File(PATH + fileName));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	//если расширение будет text/plain то выполнетс€ loadImage
	private String loadConfig(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = (InputStream) getClass().getResourceAsStream(PATH + fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "Cp1251"));
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                sb.append(line).append("\n");
            }
        } catch (IOException ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            pw.flush();
            pw.close();
            sb.append("Error: ").append("\n\n");
            sb.append(sw.getBuffer().toString());
        }
        return sb.toString();
    }
}
/*загрузить файл и получить объект файла, написать экземпл€р класса LoadFile() и работать с ним*/
/*public class ResurseLoader {
 public static final String PATH = "res/";
 public static BufferedImage loadImage(String fileName){..}
 public static setPath(String path){...};
 public static BufferedRead loadFile(String fileName){...}
}*/