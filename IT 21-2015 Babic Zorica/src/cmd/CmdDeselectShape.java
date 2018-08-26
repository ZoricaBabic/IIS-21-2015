package cmd;

import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.Oblik;

public class CmdDeselectShape implements Command{
	
	private Model model = new Model();
	private Oblik o;
	public static boolean print = true;
	
	public CmdDeselectShape(Model model,Oblik o) {
		
		this.model = model;
		this.o = o;
	}

	@Override
	public void execute() {
		
		o.setSelektovan(false);
		
		if(print == true) {
			
			NaslovnaPokretanje.getTextArea().append("Deselected: " + o.toString() +"\n");
		}
		
		print = true;
	}

	@Override
	public void unexecute() {
		o.setSelektovan(true);
		
		if(print == true) {
			
			NaslovnaPokretanje.getTextArea().append("UNDO >>> Selected: " + o.toString() +"\n");
		}

		
		print = true;
	}

}
