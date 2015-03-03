package Atomic.util;

import java.io.InputStream;

public class ResourceLoader {
	public static InputStream load(String fileName){
		InputStream input = ResourceLoader.class.getResourceAsStream(fileName);
		if(input == null){
			input = ResourceLoader.class.getResourceAsStream("/"+fileName);
		}
		return input;
	}
}
