package strategy;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import crtanje.NaslovnaPokretanje;

public class OpenOperation implements Strategy {
	
	

	@Override
	public int doOperation(Object o, File f) {
		
		NaslovnaPokretanje np = (NaslovnaPokretanje) o;
		
		try{
			
			
			np.getTextArea().setText("");
			np.getController().getModel().getListaObjekata().removeAll(np.getController().getModel().getListaObjekata());
			
			FileInputStream ft = new FileInputStream(f);
	         DataInputStream in = new DataInputStream(ft);
	         BufferedReader br = new BufferedReader(new InputStreamReader(in));
	         String strline;
	        
	         while((strline = br.readLine()) != null){
	         	
	             
	             np.getTextArea().append(strline + "\n");
	         }
	         in.close();
	         
	        np.getController().getCmdUndoRedo1().getUndo().removeAllElements();
	        np.getController().getCmdUndoRedo1().getRedo().removeAllElements();
	        
        
           
            
        }catch(Exception ek){
            System.err.println("Error: " + ek.getMessage());
        }
		
         return 0;
	}

}
