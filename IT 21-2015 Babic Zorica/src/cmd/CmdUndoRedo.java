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
		
		for(int i=0; i<commands.size(); i++) {

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



		/*if(cp == 0) {

			cmdAddShape.unexecute();
			cp = -1;

		} else if (cp != -1) {

			System.out.println("Proveravam da li je isti kao prethodni");

			if(commands.get(cp).equals(commands.get(cp-1))) {

				cmdUpdateShape.unexecute();
				cp--;
				model.getListaObjekata().set(model.getListaObjekata().indexOf(commands.get(cp)),commands.get(cp));
				//System.out.println(commands.get(cp));
				if(cp > 0) {

					cmdUpdateShape.setOldState(commands.get(cp-1));
				}

				/*System.out.println(commands.get(cp-1));
				System.out.println(cmdUpdateShape.getOldState());
				System.out.println(cmdUpdateShape.getOriginal());
				System.out.println(cmdUpdateShape.getNewState());


		}*/

		/*if(cp != -1) {

			System.out.println("Trenutna pozicija je: " + cp);

			if(model.getUnselectedShapes().isEmpty()==false) {

				System.out.println("Broj selektovanih oblika je: " + model.getUnselectedShapes().size());
				for(int i = 0; i<model.getUnselectedShapes().size(); i++) {

					model.getUnselectedShapes().get(i).setSelektovan(true);
				//kako ubaciti!!!!

				}


			} else

			if(cp == 0) { // ako je prvi oblik u listi onda ga obrisi

				cmdAddShape.unexecute();
				cp = -1; 

			} else if (commands.get(cp).isSelektovan() == true &&  commands.get(cp).equals(commands.get(cp-1)) == false) {

				model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(cp))).setSelektovan(false);
				cp--;

				if(commands.get(cp).isSelektovan() == true &&  commands.get(cp).equals(commands.get(cp-1)) == true) {

					cmdUpdateShape.setOldState(commands.get(cp-1));
					cmdUpdateShape.setOriginal(model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(cp))));
					cmdUpdateShape.setNewState(commands.get(cp+1));
				}


			} else if(commands.get(cp).isSelektovan() == false && commands.get(cp).equals(commands.get(cp-1)) == false) {

				System.out.println("Treba da se obrise!");
				cmdAddShape.unexecute();
				cp--;
				cmdAddShape.setShape(commands.get(cp));


			} else if(commands.get(cp).isSelektovan() == true && commands.get(cp-1).isSelektovan() == false){

				System.out.println("Tretnuni oblik je selektovan!");
				cmdUpdateShape.unexecute();
				cp--;

				if(cp > 0)

				cmdUpdateShape.setOldState(commands.get(cp-1));
				cmdUpdateShape.setOriginal(model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(cp))));
				cmdUpdateShape.setNewState(commands.get(cp+1));
			}











		}*/





		/*System.out.println("Broj odselektovanih oblika: " + model.getUnselectedShapes().size());


		System.out.println("Current: " + cp);
		System.out.println("Komandi ima: " + commands.size());


		if(model.getUnselectedShapes().isEmpty() == true) {

			if(cp > 0 ) {

				if(commands.get(cp).equals(commands.get(cp-1)) == true) {

					cmdUpdateShape.unexecute();
					cp--;

					if(cp > 0) {


						Oblik s =  model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(cp-1)));
						if(commands.get(cp).equals(commands.get(cp-1)) == true) {
						 System.out.println("Ulazi ovde");

							System.out.println(s); //true
							System.out.println(commands.get(cp)); //true
							System.out.println(commands.get(cp-1)); // false				
							cmdUpdateShape.setOldState(commands.get(cp-1));
							cmdUpdateShape.setOriginal(s);
							cmdUpdateShape.setNewState(commands.get(cp));


						}  else {


							System.out.println("Ipak ovde");
						}


					}




				} else {

					if(commands.get(cp).isSelektovan() == true) {

						if(cp != -1) {

							model.getListaObjekata().get(model.getListaObjekata().indexOf(commands.get(cp))).setSelektovan(false);
							cp--;


						}



					} else {


						cmdAddShape.unexecute();
						cp--;
						cmdAddShape.setShape(commands.get(cp));
					}


				}
			} else {

				System.out.println("Ima jedan oblik");
				cmdAddShape.unexecute();
				cp = -1;

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

		/*if(cp > 0) {

			System.out.println("Ima prethodnika");

			System.out.println(commands.get(cp));
			System.out.println(commands.get(cp-1));
			if(commands.get(cp).equals(commands.get(cp-1)) || commands.get(cp).isSelektovan() == true) {

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

				int index = model.getListaObjekata().indexOf(commands.get(cp));
				model.getListaObjekata().set(index, original);
				cp--;

				Oblik oldState = null;
				try {


					oldState = (Oblik) commands.get(cp-1).clone();


				} catch (CloneNotSupportedException e) {

					System.out.println("Error!");
				}

				Oblik newState = null;
				try {


					newState = (Oblik) commands.get(cp).clone();


				} catch (CloneNotSupportedException e) {

					System.out.println("Error!");
				}


				cmdUpdateShape = new CmdUpdateShape(oldState,newState);
				cmdUpdateShape.execute();



			} else {



					cmdAddShape.unexecute();
					cp--;
					cmdAddShape.setShape(commands.get(cp));


				}





		} else {

			System.out.println("Nema prethodnika");

			System.out.println("Ima samo jedan oblik i treba da se obrise");
			cmdAddShape.unexecute();
			cp = -1;
		}*/






		/*if(cp > 0) { //ima prethodnika

			System.out.println(commands.get(cp));
			System.out.println(commands.get(cp-1));
			if(commands.get(cp).equals(commands.get(cp-1))) {

				System.out.println("Isti su, treba da se modifikuje");
				cmdUpdateShape.unexecute();
				int indeks = model.getListaObjekata().indexOf(commands.get(cp)); //pronadjem u listi onaj koji treba da izmenim
				Oblik help = null;
				try {


					help = (Oblik) cmdUpdateShape.getOriginal().clone();


				} catch (CloneNotSupportedException e) {

					System.out.println("Error!");
				}

				model.getListaObjekata().set(indeks, help);

				cp--;

				if(cp > 0) {

					cmdUpdateShape = new CmdUpdateShape(commands.get(cp-1),commands.get(cp));
					cmdUpdateShape.execute();
				}




			} else {

				System.out.println("Nisu isti, treba da se obriše");
				cmdAddShape.unexecute();
				cp--;
			}


		} else {

			System.out.println("Postoji samo jedan oblik!");
			cmdAddShape.unexecute();
			cp--;
		}*/




		/*if(cp >  0) {


			if(commands.get(cp).equals(commands.get(cp-1)) == false && commands.get(cp-1).isSelektovan() == false) {

				System.out.println("Nisu isti i nisu selektovani!");
				cmdAddShape.unexecute();
				cp--;
				cmdAddShape.setShape(commands.get(cp));


			} else if(commands.get(cp).equals(commands.get(cp-1)) == false && commands.get(cp-1).isSelektovan() == true) {

				System.out.println("Odeselektuj");
				cp--;


			}  else if (commands.get(cp).equals(commands.get(cp-1)) == true && commands.get(cp-1).isSelektovan() == true) {


				System.out.println("Ovde je pocela");

				cmdUpdateShape.unexecute();

				Oblik l = null;
				try {


					l = (Oblik) commands.get(cp).clone();


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

				cp--;

				if(cp > 0) {//ima prethodnika

					cmdUpdateShape = new CmdUpdateShape(commands.get(cp-1),commands.get(cp));
					cmdUpdateShape.execute();


				}


				System.out.println("Ovde se zavrsila");

				cmdAddShape.setShape(commands.get(cp));

			} else {

				cmdAddShape.unexecute();
				cp--;
				cmdAddShape.setShape(commands.get(cp));
			}



		} else {


			if(model.getListaObjekata().size() == 1) {

				cmdAddShape.setShape(model.getListaObjekata().get(0));
			}
			cmdAddShape.unexecute();

			cp = -1;

		}*/






	}

}
