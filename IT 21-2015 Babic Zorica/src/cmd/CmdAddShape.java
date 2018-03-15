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
		
		
		
		/*if(model.getStackRedo().isEmpty() == false) {
			
			model.add(model.getLastShapeOnStackRedo());
			model.addToStackUndo(model.getLastShapeOnStackRedo());
			model.removeFromStackRedo();
			
		} else {
			
			model.add(o);
			if(model.getStackRedo().isEmpty() == false) {
				model.removeFromStackRedo();
				
			}
			 //brisanje kad se nesto novo desi
			model.addToStackUndo(o);
			
		
			
		}*/
		
		//model.addToStackUndo(o);
		model.add(o);
		
		
	
		
		
		
	}

	@Override
	public void unexecute() {
		
		/*for(int i=0; i<model.getListaObjekata().size(); i++) {
			
			if(model.getListaObjekata().get(i).equals(model.getLastShapeOnStackUndo())) {
				
				model.addToStackRedo(model.getListaObjekata().get(i));
				model.remove(model.getListaObjekata().get(i));
				model.removeFromStackUndo();
				
				
			}
		}
		
		System.out.println("Unexecute " + model.getStackUndo().size());*/
		
		
		
	
		model.remove(o);
	
		
	
	}

}
