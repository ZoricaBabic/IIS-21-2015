package cmd;

import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.Oblik;


public class CmdAddShape implements Command {
	
	private Model model = new Model();
	private Oblik o;
	
	public CmdAddShape(Model model, Oblik o) {
		this.model = model;
		this.o = o;
	}


	@Override
	public void execute() {
		

		model.add(o);
		NaslovnaPokretanje.getTextArea().append("Added: " + o.toString() +"\n");

	}

	@Override
	public void unexecute() {
		

		model.remove(o);
		NaslovnaPokretanje.getTextArea().append("Removed: " + o.toString() +"\n");
		
	
		
	
	}

	//ovo ne treba
	public void setShape(Oblik o) {
		
		this.o = o;
		
		
	}


	public Oblik getShape() {
		
		return o;
		
	}

}
