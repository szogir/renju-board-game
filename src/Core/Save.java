package Core;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Save {

	//kiment�s  (a Game objektumot szerializ�lom �s �rom ki)
	public static void SaveGame(Game g, String s) {
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(s));
			os.writeObject(g);
			os.close();	//be is z�rom
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
