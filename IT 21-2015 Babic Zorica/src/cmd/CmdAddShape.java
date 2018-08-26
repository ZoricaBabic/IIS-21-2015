package cmd;

import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.Oblik;


public class CmdAddShape implements Command {
	
	private Model model = new Model();
	private Oblik o;
	private boolean print = true;
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
		
		NaslovnaPokretanje.getTextArea().append("Removed: " + o.toString() +"\n");
		
		if(model.getListaObjekata().isEmpty()) {
			
			NaslovnaPokretanje.btnSelektuj.setEnabled(false);
		}
		
	
		
	
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

	public void setPrint(boolean print) {
		this.print = print;
	}

}
