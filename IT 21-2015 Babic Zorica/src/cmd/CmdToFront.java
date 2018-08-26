package cmd;

import java.util.Collections;

import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.Oblik;

public class CmdToFront implements Command {
	
	private Model model = new Model();
	private Oblik o;
	private int i=0;
	public static boolean print = true;
	
	public CmdToFront(Model model, Oblik o) {
		
		this.model = model;
		this.o=o;
	}

	@Override
	public void execute() {
		
		
		i=model.getListaObjekata().indexOf(o);

		Collections.swap(model.getListaObjekata(), i, i+1);  
		
		if(print == true) {
			
			NaslovnaPokretanje.getTextArea().append("Move to front: " + o +"\n");
		}
		
		print = true;
		
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		Collections.swap(model.getListaObjekata(), i+1, i); 
		
		if(print == true) {
			
			NaslovnaPokretanje.getTextArea().append("UNDO >>> Move to back: " + o +"\n");
		}
		
		print = true;
		
	}

}
