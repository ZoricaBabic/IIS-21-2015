package strategy;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStreamReader;

import mvc.Frame;

public class LoadLogOperation implements Strategy {
	
	

	@Override
	public int doOperation(Object o, File f) {
		
		Frame frame = (Frame) o;
		
		try{
			
			
			
			FileInputStream ft = new FileInputStream(f);
	        DataInputStream in = new DataInputStream(ft);
	        BufferedReader br = new BufferedReader(new InputStreamReader(in));
	         String strline;
	         
	         	frame.getTextArea().setText("");
				frame.getController().getModel().getListOfShapes().removeAll(frame.getController().getModel().getListOfShapes());
				frame.getController().getCmdUndoRedo().getUndo().removeAllElements();
				frame.getController().getCmdUndoRedo().getRedo().removeAllElements();
				frame.getBtnUndo().setEnabled(false);
				frame.getBtnRedo().setEnabled(false);
				frame.getBtnCmdbycmd().setEnabled(false);
		
				frame.setDone(false);
		
				frame.setTxtOpened(true);
	        
	         while((strline = br.readLine()) != null){
	         	
	             
	        	 frame.getTextArea().append(strline + "\n");
	         }
	         in.close();
	         
	        
	        
        
           
            
        }catch(Exception ek){
            System.err.println("Error: " + ek.getMessage());
        }
		
		
	
		
         return 0;
	}

}
