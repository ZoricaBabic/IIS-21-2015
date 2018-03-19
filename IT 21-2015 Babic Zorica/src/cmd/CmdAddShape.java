package cmd;

import crtanje.Model;
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
		
		
	
		
		
	}

	@Override
	public void unexecute() {
		

		
		
		
	
		model.remove(o);
	
		
	
	}

}
