package Atomic.util;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ResourceLoader {
	private static InputStream load(String fileName){
		InputStream input = ResourceLoader.class.getResourceAsStream(fileName);
		if(input == null){
			input = ResourceLoader.class.getResourceAsStream("/"+fileName);
		}
		return input;
	}
	
	public static Image loadTexture(String fileName){
		try {
			return ImageIO.read(load("texture/"+fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
