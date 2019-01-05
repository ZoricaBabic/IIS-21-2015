package strategy;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import mvc.Frame;

public class SaveLogOperation implements Strategy{

	@Override
	public int doOperation(Object o, File f) {
		
		Frame frame = (Frame) o;
		try {
		
			BufferedWriter bf = null;
 			try {
 				
 				bf = new BufferedWriter((new FileWriter(f.getAbsolutePath())));
 				frame.getTextArea().write(bf);
 			
 				bf.close();
 				
 				frame.setDone(false);
 				
 				
 			} catch (IOException ee) {

 				ee.printStackTrace();
 			}
 		  
		}catch(Exception ee) {
			
			JOptionPane.showMessageDialog(null, ee.getMessage());
		}
		
		
		
		return 0;
	}

}
