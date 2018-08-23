package cmd;

import java.util.ArrayList;

import crtanje.Controller;
import crtanje.Model;
import geometrija.Oblik;

public class CmdUndoRedo implements Command {

	private ArrayList<Oblik> commands = new ArrayList<Oblik> ();
	private int cp = -1;
	private Model model = new Model();
	private CmdAddShape cmdAddShape;
	private CmdUpdateShape cmdUpdateShape;
	private CmdRemoveShape cmdRemoveShape;
	
	
	
	private int redo = 0;


	public CmdUndoRedo() {

	}

	public CmdUndoRedo(Model model) {

		this.model = model;
		cmdRemoveShape = new CmdRemoveShape(model);
	}

	public void add(Oblik s) {


		Oblik o = null;
		try {


			o = (Oblik) s.clone();


		} catch (CloneNotSupportedException e) {

			System.out.println("Error!");
		}

		if(cp < commands.size()-1) {


			for(int i = cp+1; i<commands.size(); i++) {

				commands.remove(i);

			}

		}

		commands.add(o);

		cp = commands.size()-1;

		System.out.println("Current je: " + cp);


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

		if(cp < commands.size()-1) {


			for(int i = cp+1; i<commands.size(); i++) {

				commands.remove(i);

			}

		}

		commands.add(o);

		cp = commands.size()-1;

		cmdUpdateShape = new CmdUpdateShape(original,newState);
		cmdUpdateShape.execute();




	}

	public void remove(Oblik s) {

		commands.remove(s);
	}


	@Override
	public void execute() {

		System.out.println("Trenutni INDEX JE: " + cp);

		if(cp == -1) {

			cmdAddShape.execute();
			cp = 0;
			if(commands.size() > 1) {

				cmdAddShape.setShape(commands.get(cp+1));
			}


		} else if (cp >=0 && commands.size()-1 != cp) { // ako nije poslednji

			if(commands.get(cp).equals(commands.get(cp+1))) {

				System.out.println("Isti su!");
				cmdUpdateShape.execute();

				cp++;

				Oblik s = Controller.CopyShape(commands.get(cp));
				model.getListaObjekata().set(model.getListaObjekata().indexOf(s),s);





			} else {

				System.out.println("Nisu isti");
				for(int i=0; i<commands.size(); i++) {

					System.out.println(commands.get(i));
				}

				System.out.println("Trenutni je:" + commands.get(cp));
				System.out.println("na update je: " + cmdUpdateShape.getOriginal());


				if(commands.get(cp+1).isSelektovan() == true && commands.get(cp).isSelektovan() == true) {

					System.out.println("Ulazi");
					Oblik s = Controller.CopyShape(commands.get(cp+1));
					model.getListaObjekata().set(model.getListaObjekata().indexOf(s),s);
					cp++;

					Oblik u = Controller.CopyShape(commands.get(cp));
					cmdUpdateShape.setNewState(u);
					Oblik old = Controller.CopyShape(commands.get(cp));
					old.setSelektovan(false);
					cmdUpdateShape.setOriginal(u);
					cmdUpdateShape.setOldState(old);



				} else if(commands.get(cp+1).isSelektovan() == true && commands.get(cp).isSelektovan() == false) {


					System.out.println("Da li ovde ulaziiiiiiiiiiiiiiiiiiiiiii");
					Oblik s = Controller.CopyShape(commands.get(cp+1));
					model.getListaObjekata().set(model.getListaObjekata().indexOf(s),s);
					cp++;

					Oblik u = Controller.CopyShape(commands.get(cp));
					cmdUpdateShape.setNewState(u);
					Oblik old = Controller.CopyShape(commands.get(cp));
					old.setSelektovan(false);
					cmdUpdateShape.setOriginal(u);
					cmdUpdateShape.setOldState(old);


				} else {

					Oblik u = Controller.CopyShape(commands.get(cp+1));
					cmdAddShape.setShape(u);
					cmdAddShape.execute();

					cp++;

					/*if(commands.size()-1 > cp){

						cmdAddShape.setShape(commands.get(cp+1));
					}*/


				}





			}
		}

	}

	@Override
	public void unexecute() {
		
		

		/*for(int i=0; i<commands.size(); i++) {

			System.out.println(commands.get(i));
		}

		System.out.println("Duzina liste undo je: " + commands.size());
		System.out.println("Duzina liste nacrtaniih oblika je : " +  model.getListaObjekata().size());



		if(cp == 0) {

			cmdAddShape.unexecute();
			cp = -1;
			cmdAddShape.setShape(commands.get(0));

		} else if (cp >0) {

			//proveravam da li mu je prethodnoik isti
			if(commands.get(cp).equals(commands.get(cp-1))) {

				cmdUpdateShape.unexecute();
				cp--;

				Oblik s = Controller.CopyShape(commands.get(cp));
				model.getListaObjekata().set(model.getListaObjekata().indexOf(s),s);






			} else {

				if(commands.get(cp-1).isSelektovan() == true && commands.get(cp).isSelektovan() == true) {


					cmdUpdateShape.unexecute();

					Oblik s = Controller.CopyShape(cmdUpdateShape.getOriginal());
					model.getListaObjekata().set(model.getListaObjekata().indexOf(s),s);
					cp--;

					Oblik u = Controller.CopyShape(commands.get(cp));
					cmdUpdateShape.setNewState(u);
					Oblik old = Controller.CopyShape(commands.get(cp));
					old.setSelektovan(false);
					cmdUpdateShape.setOriginal(u);
					cmdUpdateShape.setOldState(old);

				} else if(commands.get(cp-1).isSelektovan() == false && commands.get(cp).isSelektovan() == true) {


					cmdUpdateShape.unexecute();
					Oblik s = Controller.CopyShape(cmdUpdateShape.getOriginal());
					model.getListaObjekata().set(model.getListaObjekata().indexOf(s),s);


					cp--;

					Oblik u = Controller.CopyShape(commands.get(cp));
					cmdUpdateShape.setNewState(u);
					Oblik old = Controller.CopyShape(commands.get(cp));
					old.setSelektovan(false);
					cmdUpdateShape.setOriginal(u);
					cmdUpdateShape.setOldState(old);


				}else {

					System.out.println("koji je na redu?");
					cmdAddShape.unexecute();
					cp--;
					cmdAddShape.setShape(commands.get(cp));

				}



			}

		}






	}*/

}
}
