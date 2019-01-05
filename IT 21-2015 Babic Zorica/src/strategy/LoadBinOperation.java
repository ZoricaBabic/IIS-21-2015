package strategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import mvc.Frame;

public class LoadBinOperation implements Strategy {

	@Override
	public int doOperation(Object o, File f) {
		
		Frame frame = (Frame) o;
		
		
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
			
			
			try {
				
				
				frame.getController().getModel().getListOfShapes().removeAll(frame.getController().getModel().getListOfShapes());
				frame.getController().getModel().getListOfShapes().addAll((ArrayList<geometry.Shape>)inputStream.readObject());
				frame.getController().getCmdUndoRedo().getUndo().removeAllElements();
				frame.getController().getCmdUndoRedo().getRedo().removeAllElements();
				frame.getBtnUndo().setEnabled(false);
				frame.getBtnRedo().setEnabled(false);
				frame.getTextArea().setText("");
			
				
				frame.setDone(false);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			frame.getPnlForDrawing().repaint();
			
			File file= new File(f.getAbsolutePath().replaceAll("bin", "txt"));
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {

				frame.getTextArea().append(line + "\n");
			}
			br.close();
			inputStream.close();
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		frame.getBtnCmdbycmd().setEnabled(false);		
		
		
		return 0;
	}

}
