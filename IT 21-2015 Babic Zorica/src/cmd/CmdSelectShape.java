package cmd;

import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.Oblik;

public class CmdSelectShape implements Command {
	
	private Model model = new Model();
	private Oblik o;


	public  CmdSelectShape(Model model, Oblik o) {
		
		this.model = model;
		this.o =o;
	}

	@Override
	public void unexecute() {
		
		o.setSelektovan(false);
		NaslovnaPokretanje.getTextArea().append("Unselected: " + o.toString() +"\n");
		
	}

	@Override
	public void execute() {
		
		o.setSelektovan(true);
		NaslovnaPokretanje.getTextArea().append("Selected: " + o.toString() +"\n");
		
	}

}
