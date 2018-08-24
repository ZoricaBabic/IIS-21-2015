package strategy;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;

public class LogOperation implements Strategy{

	@Override
	public int doOperation(Object o, File f) {
		
		try {
			
			FileWriter fw = new FileWriter(f.getPath());
			fw.write((String) o);
			fw.flush();
			fw.close();
			
			
		}catch(Exception ee) {
			
			JOptionPane.showMessageDialog(null, ee.getMessage());
		}
		return 0;
	}

}
