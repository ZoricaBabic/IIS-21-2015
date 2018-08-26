package cmd;

import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.Oblik;


public class CmdAddShape implements Command {
	
	private Model model = new Model();
	private Oblik o;
	public static boolean print = true;
	public CmdAddShape(Model model, Oblik o) {
		this.model = model;
		this.o = o;
	}

	@Override
	public void execute() {
		

		model.add(o);
		System.out.println("Dodaje oblik");
		
		if(print == true) {
			
			NaslovnaPokretanje.getTextArea().append("Added: " + o.toString() +"\n");
			
		} 
		
		NaslovnaPokretanje.btnSelektuj.setEnabled(true);
		print=true;

	}

	@Override
	public void unexecute() {
		

		model.remove(o);
		System.out.println("Brise se oblik!");
		
		if(print == true) {
			
			NaslovnaPokretanje.getTextArea().append("UNDO >>> Removed: " + o.toString() +"\n");
		}
		
		
		
		if(model.getListaObjekata().isEmpty()) {
			
			NaslovnaPokretanje.btnSelektuj.setEnabled(false);
		}
		
		print=true;
		
	
	
	}

	//ovo ne treba
	public void setShape(Oblik o) {
		
		this.o = o;
		
		
	}


	public Oblik getShape() {
		
		return o;
		
	}

	public boolean isPrint() {
		return print;
	}

	public static void setPrint(boolean prints) {
		
		print = prints;
	}

}
