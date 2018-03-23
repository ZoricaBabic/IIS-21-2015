package cmd;

import java.util.ArrayList;

import crtanje.Controller;
import crtanje.Model;
import geometrija.Oblik;

public class CmdUndoRedo implements Command {
	
	private ArrayList<Oblik> commands = new ArrayList<Oblik> ();
	private int currentPostition = -1;
	private Model model = new Model();
	private CmdAddShape cmdAddShape;
	
	public CmdUndoRedo() {
		
	}
	
	public CmdUndoRedo(Model model) {
		
		this.model = model;
	}
	
	public void add(Oblik s) {
		
		commands.add(s);
		currentPostition++;
		
		boolean exists = false;
		
		if(model.getListaObjekata().isEmpty() == false) {
			
			for(int i = 0; i<model.getListaObjekata().size(); i++) {
				
				if(model.getListaObjekata().get(i).equals(s)) {
					
					exists = true;
					
					
				}
				
				
			}
		} 
		
		if(exists == false) {
			
			cmdAddShape = new CmdAddShape(model,s);
			cmdAddShape.execute();
			//currentPostition = commands.size()-1;
		}
		
		
		
		
		
	}
	
	public void remove(Oblik s) {
		
		commands.remove(s);
	}
	

	@Override
	public void execute() {
		
		
		
		/*cmdAddShape.execute();
		currentPostition++;
		cmdAddShape.setShape(commands.get(currentPostition+1));*/
		
		
		
		
	}

	@Override
	public void unexecute() {
		
		currentPostition--;
		
		if(model.getListaObjekata().size() > 1) {
			
			if(commands.get(currentPostition).equals(commands.get(currentPostition+1)) == false) {
				
				cmdAddShape.unexecute();
				cmdAddShape.setShape(commands.get(currentPostition));
			}
			
		} else if(model.getListaObjekata().size() == 1) {
			
			cmdAddShape.unexecute();
		}
		
		/*currentPostition--;
		
		if(model.getListaObjekata().size() > 1) {
			
			if(commands.get(currentPostition).equals(commands.get(currentPostition+1)) == false){
				
				cmdAddShape.unexecute();
				cmdAddShape.setShape(commands.get(currentPostition));

			}
			
			
		} else if(model.getListaObjekata().size() > 0) {
			
			cmdAddShape.unexecute();
			cmdAddShape.setShape(commands.get(currentPostition));
			
		}*/
		
		
		
		
		
		
		
		
		
		
		
		//cmdAddShape.unexecute();
		
		
		
		
		
		
	}

}
