package cmd;

import java.util.Collections;

import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.Oblik;

public class CmdBringToBack implements Command{
	
	private Model model = new Model();
	private Oblik o; 
	private int i=0;
	
	
	public CmdBringToBack() {
		
		
	}
	
	public CmdBringToBack( Model model,Oblik o) {
		
		this.model=model;
		this.o =o;
		
		
		
	}

	@Override
	public void execute() {
		
		
		i= model.getListaObjekata().indexOf(o);
		Collections.swap(model.getListaObjekata(), i, 0); 
		NaslovnaPokretanje.getTextArea().append("Bring to back: " + o +"\n");
			//i=0;
			
		
		
		//frame.getTextArea().append("Bring to back: " + o +"\n");
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		Collections.swap(model.getListaObjekata(), 0, i); 
		NaslovnaPokretanje.getTextArea().append("Bring to front: " + o +"\n");
		
	}

	

}
