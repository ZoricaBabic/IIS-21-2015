package cmd;

import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.Oblik;

public class CmdDeselectShape implements Command{
	
	private Model model = new Model();
	private Oblik o;
	
	public CmdDeselectShape(Model model,Oblik o) {
		
		this.model = model;
		this.o = o;
	}

	@Override
	public void execute() {
		o.setSelektovan(false);
		NaslovnaPokretanje.getTextArea().append("Deselected: " + o.toString() +"\n");
		
	}

	@Override
	public void unexecute() {
		o.setSelektovan(true);
		NaslovnaPokretanje.getTextArea().append("Selected: " + o.toString() +"\n");
		
		
	}

}
