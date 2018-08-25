package strategy;

import java.awt.TextArea;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import crtanje.NaslovnaPokretanje;

public class LogOperation implements Strategy{

	@Override
	public int doOperation(Object o, File f) {
		
		NaslovnaPokretanje np = (NaslovnaPokretanje) o;
		
		try {
		
			BufferedWriter bf = null;
 			try {
 				bf = new BufferedWriter((new FileWriter(f.getAbsolutePath())));
 				//((BufferedWriter) o).write(bf);
 				String ok = o.toString();
 				 np.getTextArea().write(bf);
 				bf.close();
 			} catch (IOException ee) {
 				// TODO Auto-generated catch block
 				ee.printStackTrace();
 			}
 		  
			
			
		}catch(Exception ee) {
			
			JOptionPane.showMessageDialog(null, ee.getMessage());
		}
		return 0;
	}

}
