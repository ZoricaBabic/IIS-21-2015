package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import crtanje.NaslovnaPokretanje;

public class SaveBin implements Strategy {

	@Override
	public int doOperation(Object o, File f) {

		NaslovnaPokretanje np = (NaslovnaPokretanje) o;
		
		ObjectOutputStream outputStream = null;
		try {
			
			outputStream = new ObjectOutputStream(new FileOutputStream(f));
			outputStream.writeObject(np.getController().getModel().getListaObjekata());
			outputStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		np.btnCmdbycmd.setEnabled(false);
		return 0;
	}

}
