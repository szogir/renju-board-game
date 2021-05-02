package Core;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Save {

	//kimentés  (a Game objektumot szerializálom és írom ki)
	public static void SaveGame(Game g, String s) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(s));
			os.writeObject(g);
			os.close();	//be is zárom
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
