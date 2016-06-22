package auxiliaries;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Scripts {
	
	public static void writeMatlabScript(String fileName, double values[]){
		File file = new File("output/data/" + fileName + ".m");
		
		try {
			if (!file.exists())
				file.createNewFile();	
		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter p = new BufferedWriter(fw);
			//p.write("y = [ ");
			for (double d : values) 
				p.write(d + " ");
			//p.write("];");
			p.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void writeMatlabScript(String fileName, double val1[], double val2[]){
		File file = new File("output/data/" + fileName + ".m");
		
		try {
			if (!file.exists())
				file.createNewFile();	
		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter p = new BufferedWriter(fw);
			
			//p.write("x1 = [ ");
			for (double d : val1) 
				p.write(d + " ");
			//p.write("];");
			p.newLine();
			
			//p.write("x2 = [ ");
			for (double d : val2) 
				p.write(d + " ");
			//p.write("];");
			
			p.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void writeMatlabScript(String fileName, double val1[], double val2[], double val3[]){
		File file = new File("output/data/" + fileName + ".m");
		
		try {
			if (!file.exists())
				file.createNewFile();	
		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter p = new BufferedWriter(fw);
			
			//p.write("x1 = [ ");
			for (double d : val1) 
				p.write(d + " ");
			//p.write("];");
			p.newLine();
			
			//p.write("x2 = [ ");
			for (double d : val2) 
				p.write(d + " ");
			//p.write("];");
			p.newLine();
			
			//p.write("x3 = [ ");
			for (double d : val3) 
				p.write(d + " ");
			//p.write("];");
			
			p.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
