package strategy;

import java.awt.Shape;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import crtanje.NaslovnaPokretanje;
import geometrija.Oblik;

public class LoadPainting implements Strategy {

	@Override
	public int doOperation(Object o, File f) {
		
		NaslovnaPokretanje np = (NaslovnaPokretanje) o;
		np.getTextArea().setText("");
		np.getController().getModel().getListaObjekata().removeAll(np.getController().getModel().getListaObjekata());
		
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(f));
			
			try {
				np.getController().getModel().getListaObjekata().addAll((ArrayList<Oblik>)inputStream.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			np.getPnlZaCrtanje().repaint();
			
			File file= new File(f.getAbsolutePath().replaceAll("bin", "txt"));
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {

				np.getTextArea().append(line + "\n");
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		np.btnCmdbycmd.setEnabled(false);
		
		
		
		return 0;
	}

}
