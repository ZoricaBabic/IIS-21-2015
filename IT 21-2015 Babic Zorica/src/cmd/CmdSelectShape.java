package cmd;

import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.Oblik;

public class CmdSelectShape implements Command {
	
	private Model model = new Model();
	private Oblik o;
	public static boolean print = true;


	public  CmdSelectShape(Model model, Oblik o) {
		
		this.model = model;
		this.o =o;
	}

	@Override
	public void unexecute() {
		
		o.setSelektovan(false);
		if(print == true) {
			
			NaslovnaPokretanje.getTextArea().append("UNDO >>> Unselected: " + o.toString() +"\n");
		}
	
		print = true;
	}

	@Override
	public void execute() {
		
		o.setSelektovan(true);
		if(print == true) {
			
			NaslovnaPokretanje.getTextArea().append("Selected: " + o.toString() +"\n");
		}
		print = true;
		
		
	}

	public static boolean isPrint() {
		return print;
	}

	public static void setPrint(boolean print) {
		CmdSelectShape.print = print;
	}

}
