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
	private CmdUpdateShape cmdUpdateShape;

	public CmdUndoRedo() {

	}

	public CmdUndoRedo(Model model) {

		this.model = model;
	}

	public void add(Oblik s) {
		
		
		Oblik o = null;
		try {


			o = (Oblik) s.clone();


		} catch (CloneNotSupportedException e) {

			System.out.println("Error!");
		}
		
		
		

		commands.add(o);
		currentPostition = commands.size()-1;

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

		}

	}
	
	public void update(Oblik original, Oblik newState) {
		
		
		Oblik o = null;
		try {


			o = (Oblik) newState.clone();


		} catch (CloneNotSupportedException e) {

			System.out.println("Error!");
		}
		
		commands.add(o);
		currentPostition = commands.size()-1;
		
		cmdUpdateShape = new CmdUpdateShape(original,newState);
		cmdUpdateShape.execute();
		
		
		
		
	}

	public void remove(Oblik s) {

		commands.remove(s);
	}


	@Override
	public void execute() {

		if(currentPostition < commands.size()-1 ) {
			
			//imam sledeci
			
			if(currentPostition != -1) {
				
				if(commands.get(currentPostition).equals(commands.get(currentPostition+1)) == false){
					
					cmdAddShape = new CmdAddShape(model,commands.get(currentPostition+1));
					cmdAddShape.execute();
					currentPostition++;
					
					
				} else {
					
					System.out.println("Modifikacija redo");
				}
				
				
			} else {
				
				cmdAddShape = new CmdAddShape(model,commands.get(currentPostition+1));
				cmdAddShape.execute();
				currentPostition++;
				
				
			}
			
			
		} else {
			
			
		}
	}

	@Override
	public void unexecute() {

		System.out.println("Current: " + currentPostition);
		System.out.println("Komandi ima: " + commands.size());
		
		if(currentPostition >  0) {
			
		
			if(commands.get(currentPostition).equals(commands.get(currentPostition-1)) == false && commands.get(currentPostition-1).isSelektovan() == false) {

				System.out.println("Nisu isti i nisu selektovani!");
				cmdAddShape.unexecute();
				currentPostition--;
				cmdAddShape.setShape(commands.get(currentPostition));
				
				
			} else if(commands.get(currentPostition).equals(commands.get(currentPostition-1)) == false && commands.get(currentPostition-1).isSelektovan() == true) {
				
				System.out.println("Odeselektuj");
				currentPostition--;
				
				
			}  else if (commands.get(currentPostition).equals(commands.get(currentPostition-1)) == true && commands.get(currentPostition-1).isSelektovan() == true) {
				
				
				System.out.println("Ovde je pocela");
				
				cmdUpdateShape.unexecute();
				
				Oblik l = null;
				try {


					l = (Oblik) commands.get(currentPostition).clone();


				} catch (CloneNotSupportedException e) {

					System.out.println("Error!");
				}
				
				int index = model.getListaObjekata().indexOf(l); //njega treba da menjam
				
				Oblik o = null;
				try {


					o = (Oblik) cmdUpdateShape.getOriginal().clone();


				} catch (CloneNotSupportedException e) {

					System.out.println("Error!");
				}
				
				model.getListaObjekata().set(index, o);
				
				currentPostition--;

				if(currentPostition > 0) {//ima prethodnika

					cmdUpdateShape = new CmdUpdateShape(commands.get(currentPostition-1),commands.get(currentPostition));
					cmdUpdateShape.execute();
					
					
				}
				
				
				System.out.println("Ovde se zavrsila");
				
				cmdAddShape.setShape(commands.get(currentPostition));
				
			} else {
				
				cmdAddShape.unexecute();
				currentPostition--;
				cmdAddShape.setShape(commands.get(currentPostition));
			}

			

		} else {
			
			
			if(model.getListaObjekata().size() == 1) {
				
				cmdAddShape.setShape(model.getListaObjekata().get(0));
			}
			cmdAddShape.unexecute();
			
			currentPostition = -1;
			
		}






	}

}
