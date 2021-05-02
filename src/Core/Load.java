package Core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

public class Load {

	//bet�lt�s (Game objektumm� alak�tom a beolvasott tartalmat)
	public static Game LoadGame(String s) {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(s));
			Object g=is.readObject();
			is.close();	//be is z�rom
			if( g instanceof Game )
				return (Game)g;
			
			
		} catch (FileNotFoundException e) {
			System.err.println("File was not found!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
}
