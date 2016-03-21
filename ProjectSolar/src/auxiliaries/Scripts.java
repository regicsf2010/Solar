package auxiliaries;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Scripts {
	
	public static void writeMatlabScript(String fileName, double values[]){
		File file = new File("output/" + fileName + ".m");
		
		try {
			if (!file.exists())
				file.createNewFile();	
		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter p = new BufferedWriter(fw);
			p.write("y = [ ");
			for (double d : values) 
				p.write(d + " ");
			p.write("];");
			p.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
