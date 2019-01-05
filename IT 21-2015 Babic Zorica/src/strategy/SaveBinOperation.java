package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import mvc.Frame;

public class SaveBinOperation implements Strategy {

	@Override
	public int doOperation(Object o, File f) {

		Frame frame = (Frame) o;
		
		ObjectOutputStream outputStream = null;
		try {
			
			outputStream = new ObjectOutputStream(new FileOutputStream(f));
			outputStream.writeObject(frame.getController().getModel().getListOfShapes());
			outputStream.close();
			
			frame.setDone(false);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.getBtnCmdbycmd().setEnabled(false);
		return 0;
	}

}
