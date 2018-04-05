package cmd;

import java.util.ArrayList;

import crtanje.Controller;
import crtanje.Model;
import geometrija.Oblik;

public class CmdUndoRedo implements Command {

	private ArrayList<Oblik> commands = new ArrayList<Oblik> ();
	private int currentPosition = -1;
	private Model model = new Model();
	private CmdAddShape cmdAddShape;
	private CmdUpdateShape cmdUpdateShape;
	private int redo = 0;


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

		if(currentPosition < commands.size()-1) {


			for(int i = currentPosition+1; i<commands.size(); i++) {

				commands.remove(i);

			}

		}

		commands.add(o);

		currentPosition = commands.size()-1;

		System.out.println("Current je: " + currentPosition);


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

		if(currentPosition < commands.size()-1) {


			for(int i = currentPosition+1; i<commands.size(); i++) {

				commands.remove(i);

			}

		}

		commands.add(o);

		currentPosition = commands.size()-1;



		cmdUpdateShape = new CmdUpdateShape(original,newState);
		cmdUpdateShape.execute();




	}

	public void remove(Oblik s) {

		commands.remove(s);
	}


	@Override
	public void execute() {

		int size = commands.size()-1;
		if(redo > 0) {

			for(int i=size; i>=redo; i--) {

				model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(i))).setSelektovan(false);
				model.getUnselectedShapes().add(model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(i))));

			}



		}

		redo = 0;








	}

	@Override
	public void unexecute() {

		System.out.println("Duzina liste undo je: " + commands.size());
		System.out.println("Duzina liste nacrtaniih oblika je : " +  model.getListaObjekata().size());

		if(currentPosition != -1) {

			System.out.println("Trenutna pozicija je: " + currentPosition);
			
			if(model.getUnselectedShapes().isEmpty()==false) {
				
				System.out.println("Broj selektovanih oblika je: " + model.getUnselectedShapes().size());
				for(int i = 0; i<model.getUnselectedShapes().size(); i++) {

					model.getUnselectedShapes().get(i).setSelektovan(true);
				//kako ubaciti!!!!
					
				}
			
				
			} else

			if(currentPosition == 0) { // ako je prvi oblik u listi onda ga obrisi

				cmdAddShape.unexecute();
				currentPosition = -1; 

			} else if (commands.get(currentPosition).isSelektovan() == true &&  commands.get(currentPosition).equals(commands.get(currentPosition-1)) == false) {
				
				model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(currentPosition))).setSelektovan(false);
				currentPosition--;
				
				if(commands.get(currentPosition).isSelektovan() == true &&  commands.get(currentPosition).equals(commands.get(currentPosition-1)) == true) {
					
					cmdUpdateShape.setOldState(commands.get(currentPosition-1));
					cmdUpdateShape.setOriginal(model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(currentPosition))));
					cmdUpdateShape.setNewState(commands.get(currentPosition+1));
				}
				

			} else if(commands.get(currentPosition).isSelektovan() == false && commands.get(currentPosition).equals(commands.get(currentPosition-1)) == false) {

				System.out.println("Treba da se obrise!");
				cmdAddShape.unexecute();
				currentPosition--;
				cmdAddShape.setShape(commands.get(currentPosition));


			} else if(commands.get(currentPosition).isSelektovan() == true && commands.get(currentPosition-1).isSelektovan() == false){
				
				System.out.println("Tretnuni oblik je selektovan!");
				cmdUpdateShape.unexecute();
				currentPosition--;

				if(currentPosition > 0)

				cmdUpdateShape.setOldState(commands.get(currentPosition-1));
				cmdUpdateShape.setOriginal(model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(currentPosition))));
				cmdUpdateShape.setNewState(commands.get(currentPosition+1));
			}






			




		}





		/*System.out.println("Broj odselektovanih oblika: " + model.getUnselectedShapes().size());


		System.out.println("Current: " + currentPosition);
		System.out.println("Komandi ima: " + commands.size());


		if(model.getUnselectedShapes().isEmpty() == true) {

			if(currentPosition > 0 ) {

				if(commands.get(currentPosition).equals(commands.get(currentPosition-1)) == true) {

					cmdUpdateShape.unexecute();
					currentPosition--;

					if(currentPosition > 0) {


						Oblik s =  model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(currentPosition-1)));
						if(commands.get(currentPosition).equals(commands.get(currentPosition-1)) == true) {
						 System.out.println("Ulazi ovde");

							System.out.println(s); //true
							System.out.println(commands.get(currentPosition)); //true
							System.out.println(commands.get(currentPosition-1)); // false				
							cmdUpdateShape.setOldState(commands.get(currentPosition-1));
							cmdUpdateShape.setOriginal(s);
							cmdUpdateShape.setNewState(commands.get(currentPosition));


						}  else {


							System.out.println("Ipak ovde");
						}


					}




				} else {

					if(commands.get(currentPosition).isSelektovan() == true) {

						if(currentPosition != -1) {

							model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(currentPosition))).setSelektovan(false);
							currentPosition--;


						}



					} else {


						cmdAddShape.unexecute();
						currentPosition--;
						cmdAddShape.setShape(commands.get(currentPosition));
					}


				}
			} else {

				System.out.println("Ima jedan oblik");
				cmdAddShape.unexecute();
				currentPosition = -1;

			}


		} else {


			System.out.println("Ovde ne sme da ulazi!");

			for(int i = 0; i<model.getUnselectedShapes().size(); i++) {

				model.getUnselectedShapes().get(i).setSelektovan(true);
				//commands.add(model.getUnselectedShapes().get(i));
				redo++;

			}

			model.getUnselectedShapes().removeAll(model.getUnselectedShapes());



		}*/




































		/*	for(int i =0 ; i< commands.size(); i++) {

			System.out.println(commands.get(i));
		}*/

		/*if(currentPosition > 0) {

			System.out.println("Ima prethodnika");

			System.out.println(commands.get(currentPosition));
			System.out.println(commands.get(currentPosition-1));
			if(commands.get(currentPosition).equals(commands.get(currentPosition-1)) || commands.get(currentPosition).isSelektovan() == true) {

				System.out.println("Isti je sa prethodnikom, treba da se izvrsi modifikacija");

				cmdUpdateShape.unexecute();

				for(int i = 0; i<model.getListaObjekata().size(); i++) {

					System.out.println(model.getListaObjekata().get(i));
				}

				Oblik original = null;
				try {


					original = (Oblik) cmdUpdateShape.getOriginal().clone();


				} catch (CloneNotSupportedException e) {

					System.out.println("Error!");
				}

				int index = model.getListaObjekata().indexOf(commands.get(currentPosition));
				model.getListaObjekata().set(index, original);
				currentPosition--;

				Oblik oldState = null;
				try {


					oldState = (Oblik) commands.get(currentPosition-1).clone();


				} catch (CloneNotSupportedException e) {

					System.out.println("Error!");
				}

				Oblik newState = null;
				try {


					newState = (Oblik) commands.get(currentPosition).clone();


				} catch (CloneNotSupportedException e) {

					System.out.println("Error!");
				}


				cmdUpdateShape = new CmdUpdateShape(oldState,newState);
				cmdUpdateShape.execute();



			} else {



					cmdAddShape.unexecute();
					currentPosition--;
					cmdAddShape.setShape(commands.get(currentPosition));


				}





		} else {

			System.out.println("Nema prethodnika");

			System.out.println("Ima samo jedan oblik i treba da se obrise");
			cmdAddShape.unexecute();
			currentPosition = -1;
		}*/






		/*if(currentPosition > 0) { //ima prethodnika

			System.out.println(commands.get(currentPosition));
			System.out.println(commands.get(currentPosition-1));
			if(commands.get(currentPosition).equals(commands.get(currentPosition-1))) {

				System.out.println("Isti su, treba da se modifikuje");
				cmdUpdateShape.unexecute();
				int indeks = model.getListaObjekata().indexOf(commands.get(currentPosition)); //pronadjem u listi onaj koji treba da izmenim
				Oblik help = null;
				try {


					help = (Oblik) cmdUpdateShape.getOriginal().clone();


				} catch (CloneNotSupportedException e) {

					System.out.println("Error!");
				}

				model.getListaObjekata().set(indeks, help);

				currentPosition--;

				if(currentPosition > 0) {

					cmdUpdateShape = new CmdUpdateShape(commands.get(currentPosition-1),commands.get(currentPosition));
					cmdUpdateShape.execute();
				}




			} else {

				System.out.println("Nisu isti, treba da se obriše");
				cmdAddShape.unexecute();
				currentPosition--;
			}


		} else {

			System.out.println("Postoji samo jedan oblik!");
			cmdAddShape.unexecute();
			currentPosition--;
		}*/




		/*if(currentPosition >  0) {


			if(commands.get(currentPosition).equals(commands.get(currentPosition-1)) == false && commands.get(currentPosition-1).isSelektovan() == false) {

				System.out.println("Nisu isti i nisu selektovani!");
				cmdAddShape.unexecute();
				currentPosition--;
				cmdAddShape.setShape(commands.get(currentPosition));


			} else if(commands.get(currentPosition).equals(commands.get(currentPosition-1)) == false && commands.get(currentPosition-1).isSelektovan() == true) {

				System.out.println("Odeselektuj");
				currentPosition--;


			}  else if (commands.get(currentPosition).equals(commands.get(currentPosition-1)) == true && commands.get(currentPosition-1).isSelektovan() == true) {


				System.out.println("Ovde je pocela");

				cmdUpdateShape.unexecute();

				Oblik l = null;
				try {


					l = (Oblik) commands.get(currentPosition).clone();


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

				currentPosition--;

				if(currentPosition > 0) {//ima prethodnika

					cmdUpdateShape = new CmdUpdateShape(commands.get(currentPosition-1),commands.get(currentPosition));
					cmdUpdateShape.execute();


				}


				System.out.println("Ovde se zavrsila");

				cmdAddShape.setShape(commands.get(currentPosition));

			} else {

				cmdAddShape.unexecute();
				currentPosition--;
				cmdAddShape.setShape(commands.get(currentPosition));
			}



		} else {


			if(model.getListaObjekata().size() == 1) {

				cmdAddShape.setShape(model.getListaObjekata().get(0));
			}
			cmdAddShape.unexecute();

			currentPosition = -1;

		}*/






	}

}
