package crtanje;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import cmd.CmdAddShape;
import cmd.CmdRemoveShape;
import cmd.CmdUndo;
import cmd.CmdUpdateShape;
import dlg.DlgOsobineKruga;
import dlg.DlgOsobineKvadrata;
import dlg.DlgOsobineLinije;
import dlg.DlgOsobinePravougaonika;
import dlg.DlgOsobineTacke;
import geometrija.HexagonAdapter;
import geometrija.Krug;
import geometrija.Kvadrat;
import geometrija.Linija;
import geometrija.Oblik;
import geometrija.PovrsinskiOblik;
import geometrija.Pravougaonik;
import geometrija.Tacka;
import observer.Button;

public class Controller {

	private static final String Oblik = null;
	private Model model;
	private NaslovnaPokretanje frame;
	private CmdUpdateShape cmdUpdate;
	private CmdAddShape cmdAddShape;
	private CmdRemoveShape cmdRemove;
	private boolean s;
	private int count;
	private Oblik o;
	private int position=-1;
	private String lastAction;
	private Oblik previouslySelectedShape;
	private boolean n = false;
	
	//private CmdUndo undoRedo;

	private int firstClick = 0;

	Button button;


	public Controller(Model model, NaslovnaPokretanje frame) {

		this.model = model;
		this.frame = frame;

		button = new Button(frame);


	} 

	public Controller() {



	}

	public void setBojaIvice(Color c) {

		model.setBojaIvice(c);
	}

	public void setBojaUnutrasnjosti(Color c) {

		model.setBojaIvice(c);
	}

	public void actionPerfomedDelete(ActionEvent e) { //treba odraditi za hexagon

		if(checkIfSelectedShapeExists() == 0){
			JOptionPane.showMessageDialog(null, "Niste ništa selektovali");

		} else  {

			int odgovor = JOptionPane.YES_NO_OPTION;

			JOptionPane.showConfirmDialog (null, "Da li želite obrisati oblik koji ste selektovali","Poruka", odgovor);

			if(odgovor == JOptionPane.YES_OPTION){

				ArrayList<Oblik> oblici = new ArrayList<Oblik>();

				for(int i=0; i<model.getListaObjekata().size(); i++) {


					if(model.getListaObjekata().get(i).isSelektovan() == true) {

						oblici.add(model.getListaObjekata().get(i));

					}


				}	

				for(int i=0; i<oblici.size(); i++) {



					cmdRemove = new CmdRemoveShape(model,oblici.get(i));
					model.addToStackUndo(oblici.get(i));
					cmdRemove.execute();

					frame.getTextArea().append("Deleted: " + oblici.get(i) + "\n");

				}

			}

		}
	}




	public void actionPerfomedModify(ActionEvent e) { //za hexagon odradraditi

		int count = 0;
		for(Oblik o: model.getListaObjekata()) {

			if(o.isSelektovan() == true) {

				count++;

			}
		}

		if(count == 0) {

			JOptionPane.showMessageDialog(null, "Niste ništa selektovali!");

		} else if(count > 1) {

			JOptionPane.showMessageDialog(null, "Selektovano je više oblika. Selektujte samo oblik koji želite da modifikujete!");

		} else {

			for(Oblik o: model.getListaObjekata()) {

				if(o.isSelektovan() == true) {


					if(o instanceof Pravougaonik){

						DlgOsobinePravougaonika osobine = new DlgOsobinePravougaonika();


						osobine = new DlgOsobinePravougaonika();

						//podesavanje vrednosti
						osobine.setX(((Pravougaonik) o).gettGoreLevo().getX());
						osobine.setY(((Pravougaonik) o).gettGoreLevo().getY());
						osobine.setDuzina(((Pravougaonik) o).getDuzinaStranice());
						osobine.setSirina(((Pravougaonik) o).getSirina());
						osobine.setBojaIvice(o.getBojaIvice());
						osobine.setBojaUnutrasnjosti(((PovrsinskiOblik) o).getBojaUnutrasnjosti());

						//prikazivanje vrednosti
						osobine.getTxtXKoordinata().setText(Integer.toString(osobine.getX()));
						osobine.getTxtYKoordinata().setText(Integer.toString(osobine.getY()));
						osobine.getTxtDuzina().setText(Integer.toString(osobine.getDuzina()));
						osobine.getTxtSirina().setText(Integer.toString(osobine.getSirina()));
						osobine.getEdpBojaIvice().setBackground(osobine.getBojaIvice());
						osobine.getEdpBojaUnutrasnjosti().setBackground(osobine.getBojaUnutrasnjosti());

						osobine.setVisible(true);

						if(osobine.isVisible() == false) {

							Pravougaonik novi = new Pravougaonik(new Tacka(osobine.getX(),osobine.getY()),osobine.getDuzina(),osobine.getSirina(),osobine.getBojaIvice(),osobine.getBojaUnutrasnjosti());
							novi.setSelektovan(true);

							Oblik s = CopyShape(novi);

							model.addToStackUndo(s);

							cmdUpdate = new CmdUpdateShape(o,novi);

							cmdUpdate.execute();

							frame.getTextArea().append(novi + "\n");



							//model.setOblik(l);

						}


					} else if(o instanceof Tacka){

						DlgOsobineTacke osobine = new DlgOsobineTacke();

						osobine.setX(((Tacka) o).getX());
						osobine.setY(((Tacka) o).getY());
						osobine.setBojaIvice(o.getBojaIvice());


						osobine.getTxtXKoordinata().setText(Integer.toString(osobine.getX()));
						osobine.getTxtYKoordinata().setText(Integer.toString(osobine.getY()));
						osobine.getEdpBoja().setBackground(osobine.getBojaIvice());


						osobine.setVisible(true);
						Tacka t = osobine.getT();
						//view.repaint();

						t.setSelektovan(true);

						Oblik s = CopyShape(t);

						model.addToStackUndo(s);

						cmdUpdate = new CmdUpdateShape(o,t);

						cmdUpdate.execute();

						frame.getTextArea().append(t + "\n");

						/*model.remove(model.stackPeek());

						CmdAddShape cmd = new CmdAddShape(model,t);
						cmd.execute();

						model.removeAll();*/




					} else if(o instanceof Linija){



						DlgOsobineLinije osobine = new DlgOsobineLinije();


						osobine.setxPocetna(((Linija) o).gettPocetna().getX());
						osobine.setyPocetna(((Linija) o).gettPocetna().getY());
						osobine.setxKrajnja(((Linija) o).gettKrajnja().getX());
						osobine.setyKrajnja(((Linija) o).gettKrajnja().getY());
						osobine.setBojaIvice(o.getBojaIvice());



						osobine.getTxtXKoordinataPocetneTacke().setText(Integer.toString(osobine.getxPocetna()));
						osobine.getTxtYKoordinataPocetneTacke().setText(Integer.toString(osobine.getyPocetna()));
						osobine.getTxtXKoordinataKrajnjeTacke().setText(Integer.toString(osobine.getxKrajnja()));
						osobine.getTxtYKoordinataKrajnjeTacke().setText(Integer.toString(osobine.getyKrajnja()));

						osobine.getEdpBoja().setBackground(osobine.getBojaIvice());


						osobine.setVisible(true);



						if(osobine.isVisible() == false) {

							Linija novi = new Linija(new Tacka(osobine.getxPocetna(),osobine.getyPocetna()), new Tacka (osobine.getxKrajnja(),osobine.getyKrajnja()),osobine.getBojaIvice());
							novi.setSelektovan(true);

							Oblik s = CopyShape(novi);

							model.addToStackUndo(s);

							cmdUpdate = new CmdUpdateShape(o,novi);

							cmdUpdate.execute();

							frame.getTextArea().append(novi + "\n");


							//model.setOblik(l);

						}

					}else if(o instanceof HexagonAdapter) { 



						DlgOsobineKruga osobine = new DlgOsobineKruga();
						osobine.setX(((HexagonAdapter) o).getCentar().getX());
						osobine.setY(((HexagonAdapter) o).getCentar().getY());
						osobine.setPoluprecnik(((HexagonAdapter) o).getHexagon().getR());
						osobine.setBojaIvice(((HexagonAdapter) o).getHexagon().getBorderColor());
						osobine.setBojaUnutrasnjosti(((HexagonAdapter) o).getHexagon().getAreaColor());

						osobine.getTxtXKoordinata().setText(Integer.toString(osobine.getX()));
						osobine.getTxtYKoordinata().setText(Integer.toString(osobine.getY()));
						osobine.getTxtPoluprecnik().setText(Integer.toString(osobine.getPoluprecnik()));
						osobine.getEdpBojaIvice().setBackground(osobine.getBojaIvice());
						osobine.getEdpBojaUnutrasnjosti().setBackground(osobine.getBojaUnutrasnjosti());
						osobine.setVisible(true);

						if(osobine.isVisible() == false) {

							HexagonAdapter novi = new HexagonAdapter(new Tacka(osobine.getX(),osobine.getY()),osobine.getPoluprecnik(),osobine.getBojaIvice(),osobine.getBojaUnutrasnjosti());
							//novi.setSelektovan(true);
							novi.setSelektovan(true);




							Oblik s = CopyShape(novi);

							model.addToStackUndo(s);


							cmdUpdate = new CmdUpdateShape(o,novi);
							cmdUpdate.execute();

							frame.getTextArea().append(novi + "\n");



							//lastAction = "Modify";



							//model.setOblik(l);

						}

						/*model.remove(model.stackPeek());

						CmdAddShape cmd = new CmdAddShape(model,kr);
						cmd.execute();

						model.removeAll();*/
						//view.repaint();






					} else  if(o instanceof Kvadrat){

						DlgOsobineKvadrata osobine = new DlgOsobineKvadrata();
						osobine.setX(((Kvadrat) o).gettGoreLevo().getX());
						osobine.setY(((Kvadrat) o).gettGoreLevo().getY());
						osobine.setDuzinaStranice(((Kvadrat) o).getDuzinaStranice());
						osobine.setBojaIvice(o.getBojaIvice());
						osobine.setBojaUnutrasnjosti(((PovrsinskiOblik) o).getBojaUnutrasnjosti());

						osobine.getTxtXKoordinata().setText(Integer.toString(osobine.getX()));
						osobine.getTxtYKoordinata().setText(Integer.toString(osobine.getY()));
						osobine.getTxtDuzinaStranice().setText(Integer.toString(osobine.getDuzinaStranice()));
						osobine.getEdpBojaIvice().setBackground(osobine.getBojaIvice());
						osobine.getEdpBojaUnutrasnjosti().setBackground(osobine.getBojaUnutrasnjosti());


						osobine.setVisible(true);

						if(osobine.isVisible() == false) {

							Kvadrat novi = new Kvadrat(new Tacka(osobine.getX(),osobine.getY()),osobine.getDuzinaStranice(),osobine.getBojaIvice(),osobine.getBojaUnutrasnjosti());
							novi.setSelektovan(true);

							Oblik s = CopyShape(novi);

							model.addToStackUndo(s);

							cmdUpdate = new CmdUpdateShape(o,novi);

							cmdUpdate.execute();

							frame.getTextArea().append(novi + "\n");


							//model.setOblik(l);

						}




					} else if (o instanceof Krug) {

						DlgOsobineKruga osobine = new DlgOsobineKruga();
						osobine.setX(((Krug) o).getCentar().getX());
						osobine.setY(((Krug) o).getCentar().getY());
						osobine.setPoluprecnik(((Krug) o).getR());
						osobine.setBojaIvice(o.getBojaIvice());
						osobine.setBojaUnutrasnjosti(((PovrsinskiOblik) o).getBojaUnutrasnjosti());


						osobine.getTxtXKoordinata().setText(Integer.toString(osobine.getX()));
						osobine.getTxtYKoordinata().setText(Integer.toString(osobine.getY()));
						osobine.getTxtPoluprecnik().setText(Integer.toString(osobine.getPoluprecnik()));
						osobine.getEdpBojaIvice().setBackground(osobine.getBojaIvice());
						osobine.getEdpBojaUnutrasnjosti().setBackground(osobine.getBojaUnutrasnjosti());
						osobine.setVisible(true);



						if(osobine.isVisible() == false) {

							Krug novi = new Krug(new Tacka(osobine.getX(),osobine.getY()),osobine.getPoluprecnik(),osobine.getBojaIvice(),osobine.getBojaUnutrasnjosti());
							//novi.setSelektovan(true);
							novi.setSelektovan(true);

							Oblik s = CopyShape(novi);

							model.addToStackUndo(s);

							//undoRedo.add(s);



							cmdUpdate = new CmdUpdateShape(o,novi);

							cmdUpdate.execute();

							frame.getTextArea().append(novi + "\n");


							//lastAction = "Modify";



							//model.setOblik(l);

						}

						/*model.remove(model.stackPeek());

						CmdAddShape cmd = new CmdAddShape(model,kr);
						cmd.execute();

						model.removeAll();*/
						//view.repaint();

					}



				}


			}







		}





	}

	/*public void makeChangePravougaonik() {

		/*p = new Pravougaonik(new Tacka(x,y),duzina,sirina,bojaIvice,bojaUnutrasnjosti);
		setP(p);

		Pravougaonik novi = new Pravougaonik(new Tacka(osobine.getX(),osobine.getY()),osobine.getDuzina(),osobine.getSirina(),osobine.getBojaIvice(),osobine.getBojaUnutrasnjosti());
		Pravougaonik stari = new Pravougaonik(osobine.getP().gettGoreLevo(),osobine.getP().getDuzinaStranice(),osobine.getP().getSirina(),osobine.getP().getBojaIvice(),osobine.getP().getBojaUnutrasnjosti());

		for(Oblik o: model.getListaObjekata()) {

			if(o.equals(stari)) {

				cmdUpdate = new CmdUpdateShape(o,novi);
				cmdUpdate.execute();


			}
		}





		/*for(Oblik l: model.getListaObjekata()) {

			if(l.equals(osobine.getP())) {

				/*System.out.println(((Pravougaonik) l).getSirina() + " " + osobine.getP().getSirina());

				cmdUpdate = new CmdUpdateShape(l,new Pravougaonik(new Tacka(model.getX(),model.getY()),model.getDuzina(),model.getSirina(),model.getBojaIvice(),model.getBojaUnutrasnjosti()));
				cmdUpdate.execute();

				//model.setOblik(l);
			}
		}*/



	/*DlgOsobinePravougaonika osobine = new DlgOsobinePravougaonika();
		osobine.getP();
		System.out.println("Stara duzina je: " +osobine.getP().getDuzinaStranice());
		System.out.println("Nova duzina je: " +model.getDuzina());*/






	/*for(Oblik l: model.getListaObjekata()) {

			if(l.equals(o)) {

				System.out.println("Isti su");						
				cmdUpdate = new CmdUpdateShape(l,p);
				cmdUpdate.execute();

				model.setOblik(l);



			}

		}


	}*/



	public void mouseClickedSelection(MouseEvent e) {

		if(model.getListaObjekata().size() == 0){

			JOptionPane.showMessageDialog(null, "Niste ništa nacrtali!");

		} else {

			model.setOdabranOblik("");


		}


	}

	public void mouseClickedPnl(int x, int y) {
		
		for(int i =0; i<model.getListaObjekata().size(); i++) {
				
				if(model.getListaObjekata().get(i).isSelektovan() == true) {

					model.getUnselectedShapes().add(model.getListaObjekata().get(i));
					countForRedo++;
					
							
						
				}
			}
			
		
		if(model.getOdabranOblik() == ""){
			
			
			
			int m=0;

			for(int i=0; i<model.getListaObjekata().size(); i++) {

				//selektovanje oblika na koji je kliknut
				if(model.getListaObjekata().get(i).sadrzi(x, y)){

					Oblik q = CopyShape(model.getListaObjekata().get(i));

					m++;

					model.getListaObjekata().get(i).setSelektovan(true);

					Oblik s = CopyShape(model.getListaObjekata().get(i));


					model.addToStackUndo(s);

					//undoRedo.add(s);

					//undoRedo = new CmdUndo(cmdUpdate);
					cmdUpdate = new CmdUpdateShape(q,model.getListaObjekata().get(i));
					cmdUpdate.execute(); //selektovan oblik

					button.setStatus(true);
					frame.getBtnRedo().setEnabled(false);

					frame.getTextArea().append(s.toString() +"\n");





					if(m>1) { // sleketovanje poslednje nacrtanog


						for(int k=0; k<model.getListaObjekata().size(); k++) {

							if(!model.getListaObjekata().get(k).equals(model.getLastShapeOnStackUndo())) {

								model.getListaObjekata().get(k).setSelektovan(false);
								frame.getTextArea().append(model.getListaObjekata().get(k) +"\n"); //KAD SE ODSELEKTUJEE!

							}
						}
					} 

				} 


			}

			




			if(m==0) {
				
				System.out.println("Ne sadrzi nijedan oblik!");
				n=true;

				for(Oblik o: model.getListaObjekata()) {

					if(o instanceof HexagonAdapter) {

						((HexagonAdapter) o).unselect();
					} //premestiti drugačije!

					o.setSelektovan(false);

					frame.getTextArea().append(o.toString() +"\n");
					button.setStatus(false);

				}


			}
			
			


			lastAction = "Selekcija";
			frame.getBtnUndo().setEnabled(true);
			checkIfSelectedShapeExists();



		}





		if(model.getOdabranOblik() == "Tacka" || model.getOdabranOblik() == null)
		{

			model.setX(x);
			model.setY(y);
			//view.repaint();

			Tacka t = new Tacka(model.getX(),model.getY());
			t.setBojaIvice(model.getBojaIvice());
			cmdAddShape = new CmdAddShape(model,t);
			cmdAddShape.execute();


			Oblik l = CopyShape(t);


			model.addToStackUndo(l);

			frame.getBtnRedo().setEnabled(false);
			frame.getBtnUndo().setEnabled(true);
			frame.getBtnSelektuj().setEnabled(true);

			frame.getTextArea().append(t.toString() +"\n");
			//view.repaint();


		}

		if(model.getOdabranOblik() == "Hexagon") {

			s=true;

			String s=JOptionPane.showInputDialog("Unesi duzinu poluprecnika hexagona");

			try{

				int r = Integer.parseInt(s);
				if(r > 0){

					model.setR(r);
					model.setX(x);
					model.setY(y);
					//view.repaint();
					HexagonAdapter h = new HexagonAdapter(new Tacka(model.getX(),model.getY()),model.getR(),model.getBojaIvice(),model.getBojaUnutrasnjosti());

					cmdAddShape = new CmdAddShape(model,h);
					cmdAddShape.execute();

					Oblik l = CopyShape(h);
					model.addToStackUndo(l);


					frame.getBtnRedo().setEnabled(false);
					frame.getBtnUndo().setEnabled(true);
					frame.getBtnSelektuj().setEnabled(true);

					frame.getTextArea().append(h.toString() +"\n");




				} else {

					JOptionPane.showMessageDialog(null, "Niste dobro uneli polupre�?nik kruga!");
				}

			} catch (NumberFormatException k){

				JOptionPane.showMessageDialog(null, "Niste dobro uneli polupre�?nik kruga!");


			} catch(NullPointerException k){

				JOptionPane.showConfirmDialog(null, "Niste uneli polupre�?nik kruga!");
			}

		}

		if(model.getOdabranOblik() == "Linija"){


			if(model.isDvaKlika() == false){

				model.setX(x);
				model.setY(y);
				model.setDvaKlika(true);
			} else {

				model.setNovoX(x);
				model.setNovoY(y);
				//view.repaint();
				Linija l = new Linija(new Tacka(model.getX(),model.getY()), new Tacka(model.getNovoX(),model.getNovoY()));
				l.setBojaIvice(model.getBojaIvice());
				cmdAddShape = new CmdAddShape(model,l);
				cmdAddShape.execute();

				Oblik k = CopyShape(l);


				model.addToStackUndo(k);



				frame.getBtnRedo().setEnabled(false);
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnSelektuj().setEnabled(true);

				frame.getTextArea().append(l.toString() +"\n");

				model.setDvaKlika(false);
			}

		}

		if(model.getOdabranOblik() == "Pravougaonik"){

			model.setX(x);
			model.setY(y);


			String d=JOptionPane.showInputDialog("Unesi duzinu pravougaonika");

			try{

				int duzina = Integer.parseInt(d);
				if(duzina>0){

					model.setDuzina(duzina);
					String s = JOptionPane.showInputDialog("Unesi sirinu pravougoonika");


					try{

						int sirina = Integer.parseInt(s);
						if(sirina>0){

							model.setSirina(sirina);

							model.setDuzina(duzina);
							//view.repaint();
							Pravougaonik p = new Pravougaonik(new Tacka(model.getX(),model.getY()), model.getDuzina(),model.getSirina());

							p.setBojaIvice(model.getBojaIvice());
							p.setBojaUnutrasnjosti(model.getBojaUnutrasnjosti());

							cmdAddShape = new CmdAddShape(model,p);
							cmdAddShape.execute();


							Oblik l = CopyShape(p);


							model.addToStackUndo(l);


							frame.getBtnRedo().setEnabled(false);
							frame.getBtnUndo().setEnabled(true);
							frame.getBtnSelektuj().setEnabled(true);

							frame.getTextArea().append(p.toString() +"\n");




						}
						else{
							JOptionPane.showMessageDialog(null, "Niste dobro uneli sirinu pravougaonika!");
						}
					} catch(NumberFormatException a){

						JOptionPane.showMessageDialog(null, "Niste dobro uneli sirinu pravougaonika!");

					} catch(NullPointerException k){

						JOptionPane.showMessageDialog(null, "Niste uneli sirinu pravougaonika");
					}

				} else {

					JOptionPane.showMessageDialog(null, "Niste dobro uneli dužinu pravougaonika!");
				}
			} catch(NumberFormatException a){

				JOptionPane.showMessageDialog(null, "Niste dobro uneli dužinu pravougaonika!");

			} catch(NullPointerException k){

				JOptionPane.showMessageDialog(null, "Niste uneli dužinu pravougaonika!");
			}



		}

		if(model.getOdabranOblik() == "Kvadrat"){



			String s=JOptionPane.showInputDialog("Unesi duzinu stranice kvadrata");

			try{

				int duzinaStranice = Integer.parseInt(s);
				if(duzinaStranice>0){

					model.setDuzinaStranice(duzinaStranice);
					model.setX(x);
					model.setY(y);
					//view.repaint();
					Kvadrat kv = new Kvadrat(new Tacka(model.getX(),model.getY()), model.getDuzinaStranice());
					kv.setBojaIvice(model.getBojaIvice());
					kv.setBojaUnutrasnjosti(model.getBojaUnutrasnjosti());
					cmdAddShape = new CmdAddShape(model,kv);
					cmdAddShape.execute();

					Oblik l = CopyShape(kv);
					model.addToStackUndo(l);


					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(false);
					frame.getBtnSelektuj().setEnabled(true);

					frame.getTextArea().append(kv.toString() +"\n");

				} else {
					JOptionPane.showMessageDialog(null, "Niste  dobro uneli dužinu stranice kvadrata!");
				}

			} catch(NumberFormatException a){

				JOptionPane.showMessageDialog(null, "Niste dobro uneli dužinu stranice kvadrata!");

			} catch(NullPointerException k){

				JOptionPane.showMessageDialog(null, "Niste uneli dužinu stanice kvadrata!");
			}




		}

		if(model.getOdabranOblik() == "Krug"){


			s=true;

			String s=JOptionPane.showInputDialog("Unesi duzinu poluprecnika kruga");

			try{

				int r = Integer.parseInt(s);
				if(r > 0){

					model.setR(r);
					model.setX(x);
					model.setY(y);
					//view.repaint();

					Krug kr = new Krug(new Tacka(model.getX(),model.getY()),model.getR());
					kr.setBojaIvice(model.getBojaIvice());
					kr.setBojaUnutrasnjosti(model.getBojaUnutrasnjosti());

					cmdAddShape = new CmdAddShape(model,kr); 
					cmdAddShape.execute(); //dodajem ga u listu


					Oblik l = CopyShape(kr);


					model.addToStackUndo(l);

					//	undoRedo.add(l);




					/*cmdUpdate = new CmdUpdateShape(kr,kr); //postavljam vrednosti u cmdUpdate
					cmdUpdate.execute();*/


					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(false);
					frame.getBtnSelektuj().setEnabled(true);

					frame.getTextArea().append(kr.toString() +"\n");







				} else {

					JOptionPane.showMessageDialog(null, "Niste dobro uneli polupre�?nik kruga!");
				}

			} catch (NumberFormatException k){

				JOptionPane.showMessageDialog(null, "Niste dobro uneli polupre�?nik kruga!");


			} catch(NullPointerException k){

				JOptionPane.showConfirmDialog(null, "Niste uneli polupre�?nik kruga!");
			}



		}
	}



	/*public void mouseClickedBojaUnutrasnjosti() {
		//Color currentBojaUnutrasnjosti = pnlBojaUnutrasnjosti.getBackground();

				Color currentBojaUnutrasnjosti = model.getBojaUnutrasnjosti();

				Color bojaUnutrasnjosti = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK);
				if(bojaUnutrasnjosti  == null) {

					frame.setPnlBojaUnutrasnjosti(currentBojaUnutrasnjosti);
				} else {

					model.setBojaUnutrasnjosti(bojaUnutrasnjosti);
					frame.setPnlBojaUnutrasnjosti(currentBojaUnutrasnjosti);



				}
		}*/

	public void mouseClickedBojaUnutrasnjosti() {


		Color currentBojaUnutrasnjosti = model.getBojaUnutrasnjosti();

		Color bojaUnutrasnjosti = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK);
		if(bojaUnutrasnjosti  == null) {

			frame.setPnlBojaUnutrasnjosti(currentBojaUnutrasnjosti);
		} else {

			model.setBojaUnutrasnjosti(bojaUnutrasnjosti);
			frame.setPnlBojaUnutrasnjosti(currentBojaUnutrasnjosti);


		}

	}

	/*public void mouseClickedBojaIvice() {

		//Color currentBojaIvice = pnlBojaIvice.getBackground();
		Color currentBojaIvice = model.getBojaIvice();
		Color bojaIvice = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK);
		if(bojaIvice  == null) {

			model.setBojaIvice(currentBojaIvice);

			frame.setPnlBojaIvice(currentBojaIvice);
		} else {

			model.setBojaIvice(bojaIvice);
			frame.setPnlBojaIvice(bojaIvice);


		}
	}*/

	public Color mouseClickedBojaIvice() {

		//Color currentBojaIvice = pnlBojaIvice.getBackground();
		Color currentBojaIvice = model.getBojaIvice();
		Color bojaIvice = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK);

		if(bojaIvice  == null) {

			model.setBojaIvice(currentBojaIvice);

			return currentBojaIvice;
		} else {

			model.setBojaIvice(bojaIvice);
			return bojaIvice;


		}
	}

	public void setOdabranOblik(String s) {

		model.setOdabranOblik(s);

	}


	/*public void mouseClickedDrawing(MouseEvent e) {

		//model.removeAll();

		/*if(model.getOdabranOblik() == ""){

			System.out.println("Ušlo je ovde!");
			model.setxSelekcija(e.getX());
			model.setySelekcija(e.getY());
			System.out.println(e.getX() + " " + e.getY());
			//view.repaint();
		}

		if(model.getOdabranOblik() == "Tacka" || model.getOdabranOblik() == null)
		{

			model.setX(e.getX());
			model.setY(e.getY());
			//view.repaint();

			Tacka t = new Tacka(model.getX(),model.getY());
			t.setBojaIvice(model.getBojaIvice());
			CmdAddShape cmd = new CmdAddShape(model,t);
			cmd.execute();


		}

		if(model.getOdabranOblik() == "Linija"){

			if(model.isDvaKlika() == false){

				model.setX(e.getX());
				model.setY(e.getY());
				model.setDvaKlika(true);
			} else {

				model.setNovoX(e.getX());
				model.setNovoY(e.getY());
				//view.repaint();
				Linija l = new Linija(new Tacka(model.getX(),model.getY()), new Tacka(model.getNovoX(),model.getNovoY()));
				l.setBojaIvice(model.getBojaIvice());
				CmdAddShape cmd = new CmdAddShape(model,l);
				cmd.execute();

				model.setDvaKlika(false);
			}

		}

		if(model.getOdabranOblik() == "Pravougaonik"){

			model.setX(e.getX());
			model.setY(e.getY());
			//view.repaint();
			Pravougaonik p = new Pravougaonik(new Tacka(model.getX(),model.getY()), model.getDuzina(),model.getSirina());
			p.setBojaIvice(model.getBojaIvice());
			p.setBojaUnutrasnjosti(model.getBojaUnutrasnjosti());
			CmdAddShape cmd = new CmdAddShape(model,p);
			cmd.execute();

		}

		if(model.getOdabranOblik() == "Kvadrat"){


			model.setX(e.getX());
			model.setY(e.getY());
			//view.repaint();
			Kvadrat kv = new Kvadrat(new Tacka(model.getX(),model.getY()), model.getDuzinaStranice());
			kv.setBojaIvice(model.getBojaIvice());
			kv.setBojaUnutrasnjosti(model.getBojaUnutrasnjosti());
			CmdAddShape cmd = new CmdAddShape(model,kv);
			cmd.execute();

		}

		if(model.getOdabranOblik() == "Krug"){

			model.setX(e.getX());
			model.setY(e.getY());
			//view.repaint();
			Krug kr = new Krug(new Tacka(model.getX(),model.getY()),model.getR());
			kr.setBojaIvice(model.getBojaIvice());
			kr.setBojaUnutrasnjosti(model.getBojaUnutrasnjosti());
			CmdAddShape cmd = new CmdAddShape(model,kr);
			cmd.execute();

		}
	}*/
	
	private int countForRedo = 0;

	public void undo() {
		
		countForRedo = 0;

		checkIfSelectedShapeExists();
		
		if(countForRedo != 0 && n == true) {
			
			System.out.println("Vraćanje slektovanih oblika!");
			
			
			if(model.getUnselectedShapes().isEmpty() == false) {
				
				System.out.println("U listi je: " + model.getUnselectedShapes().size());
				
				
				for(int i = 0; i<model.getUnselectedShapes().size(); i++) {
					
					for(int k=0; k<model.getListaObjekata().size(); k++) {
						
						if(model.getUnselectedShapes().get(i).equals(model.getListaObjekata().get(k))) {
							
							System.out.println("Selektuj");
							model.addToStackRedo(model.getListaObjekata().get(k));
							countForRedo++;
							model.getListaObjekata().get(k).setSelektovan(true);
							
						}
					}
				}
				
				model.getUnselectedShapes().removeAll(model.getUnselectedShapes());
				
				
				
				n=false;
				
				
			} 
			
		} else {
			
			System.out.println("ovdeeeeee jeeeeeeeeeeee!");

			if(model.getStackUndo().isEmpty() == false) {

				System.out.println(model.getStackUndo().size());

				if(model.getStackUndo().size() > 1) {

					if(model.getLastShapeOnStackUndo().equals(model.getStackUndo().get(model.getStackUndo().size()-2)) == true) {

						System.out.println("undo modifikacijaaaaaa");
						//modifikacija
						cmdUpdate.unexecute();
						System.out.println(cmdUpdate.getOriginal().toString());
						Oblik original = CopyShape(cmdUpdate.getOriginal());
						int index = model.getListaObjekata().indexOf(original);
						model.getListaObjekata().set(index, original); //ovde samstala, ne kontam
						System.out.println(model.getListaObjekata().get(index).toString());
						model.addToStackRedo(model.getLastShapeOnStackUndo());
						model.removeFromStackUndo();
						
						if(model.getStackUndo().size() >=2) {
							
							if(model.getLastShapeOnStackUndo().equals(model.getStackUndo().get(model.getStackUndo().size()-2)) == false) {

								Oblik s = CopyShape(model.getLastShapeOnStackUndo());
								s.setSelektovan(false);
								cmdUpdate = new CmdUpdateShape(s,model.getLastShapeOnStackUndo());
								cmdUpdate.execute();

							} else {

								cmdUpdate = new CmdUpdateShape(model.getStackUndo().get(model.getStackUndo().size()-2),model.getLastShapeOnStackUndo());
								cmdUpdate.execute();


							}
							
							
						}
						
					
						
						
						

					} else if(model.getLastShapeOnStackUndo().equals(model.getStackUndo().get(model.getStackUndo().size()-2)) == false && model.getLastShapeOnStackUndo().isSelektovan() == true) {

						System.out.println("Odselektuj ga!");

						cmdUpdate.unexecute();

						Oblik original = CopyShape(cmdUpdate.getOriginal());
						int index = model.getListaObjekata().indexOf(original);
						model.getListaObjekata().set(index, original);
						model.addToStackRedo(model.getLastShapeOnStackUndo());
						model.removeFromStackUndo();

						if(model.getLastShapeOnStackUndo().equals(model.getStackUndo().get(model.getStackUndo().size()-2)) == false) {

							Oblik s = CopyShape(model.getLastShapeOnStackUndo());
							s.setSelektovan(false);
							cmdUpdate = new CmdUpdateShape(s,model.getLastShapeOnStackUndo());
							cmdUpdate.execute();

						} else {

							cmdUpdate = new CmdUpdateShape(model.getStackUndo().get(model.getStackUndo().size()-2),model.getLastShapeOnStackUndo());
							cmdUpdate.execute();


						}



					} else if(model.getLastShapeOnStackUndo().equals(model.getStackUndo().get(model.getStackUndo().size()-2)) == false && model.getLastShapeOnStackUndo().isSelektovan() == false) {


						cmdAddShape.unexecute();
						model.addToStackRedo(model.getLastShapeOnStackUndo());
						model.removeFromStackUndo();

						if(model.getStackUndo().isEmpty() == false) {

							cmdAddShape.setShape(model.getLastShapeOnStackUndo());


						}


					} 


				} else {

					System.out.println("Ima još jedan oblik i treba da se obriše!");

					cmdAddShape.unexecute();
					model.addToStackRedo(model.getLastShapeOnStackUndo());
					model.removeFromStackUndo();
				}


			}
			
			
		}
		
		

		checkIfSelectedShapeExists();

	}

	public void redo() {
		
		CmdUpdateShape cmdUpdateRedo = new CmdUpdateShape();
		
		if(countForRedo > 0) {
			
			
				
				for(int k = 0; k<model.getListaObjekata().size(); k++) {
					
					for(int i = 0; i<model.getStackRedo().size(); i++) {
						
						if(model.getListaObjekata().get(k).equals(model.getStackRedo().get(i))) {
							
							model.getListaObjekata().get(k).setSelektovan(false);
							model.getUnselectedShapes().add(model.getListaObjekata().get(k));
							
							n=true;
							
						}
					}
					
			}
			
			
		}  else {
			
			

			if(model.getStackRedo().isEmpty() == false) {

		
				if(model.getStackRedo().size() > 1 && model.getStackUndo().isEmpty() == false) {

					if(model.getLastShapeOnStackRedo().equals(model.getLastShapeOnStackUndo()) == true) { //ako je isti kao prethodni radi se o modifikaciji

						System.out.println("REDO RADI SE O MODIFIKACIJI");
						//modifikacija
						cmdUpdate.execute();
						Oblik original = CopyShape(cmdUpdate.getOriginal());
						int index = model.getListaObjekata().indexOf(original);
						model.getListaObjekata().set(index, model.getStackRedo().get(model.getStackRedo().size()-2));
						model.addToStackUndo(model.getLastShapeOnStackRedo());
						model.removeFromStackRedo();
						
						
						/*if(model.getLastShapeOnStackRedo().equals(model.getStackRedo().get(model.getStackRedo().size()-2)) == false) {

							Oblik s = CopyShape(model.getLastShapeOnStackRedo());
							s.setSelektovan(false);
							cmdUpdate = new CmdUpdateShape(s,model.getLastShapeOnStackUndo());
							cmdUpdate.execute();

						} else {

							cmdUpdate = new CmdUpdateShape(model.getStackUndo().get(model.getStackUndo().size()-2),model.getLastShapeOnStackUndo());
							cmdUpdate.execute();


						}*/
						
						
						

					} else if(model.getLastShapeOnStackRedo().equals(model.getStackRedo().get(model.getStackRedo().size()-2)) == false && model.getLastShapeOnStackRedo().isSelektovan() == true) {

						System.out.println("Odselektuj ga!");

						cmdUpdate.execute();

						Oblik original = CopyShape(cmdUpdate.getOriginal());
						int index = model.getListaObjekata().indexOf(original);
						model.getListaObjekata().set(index, original);
						model.addToStackUndo(model.getLastShapeOnStackRedo());
						model.removeFromStackRedo();

						/*if(model.getLastShapeOnStackRedo().equals(model.getStackRedo().get(model.getStackRedo().size()-2)) == false) {

							Oblik s = CopyShape(model.getLastShapeOnStackRedo());
							s.setSelektovan(false);
							cmdUpdate = new CmdUpdateShape(s,model.getLastShapeOnStackUndo());
							cmdUpdate.execute();

						} else {

							cmdUpdate = new CmdUpdateShape(model.getStackUndo().get(model.getStackUndo().size()-2),model.getLastShapeOnStackUndo());
							cmdUpdate.execute();


						}*/



					} else if(model.getLastShapeOnStackRedo().equals(model.getStackRedo().get(model.getStackRedo().size()-2)) == false && model.getLastShapeOnStackRedo().isSelektovan() == false) {


						cmdAddShape.execute();
						model.addToStackUndo(model.getLastShapeOnStackRedo());
						model.removeFromStackRedo();

						/*if(model.getStackRedo().isEmpty() == false) {

							cmdAddShape.setShape(model.getLastShapeOnStackRedo());


						}*/


					} 


				} else {

					System.out.println("Ima još jedan oblik i treba da se obriše!");

					cmdAddShape.execute();
					model.addToStackUndo(model.getLastShapeOnStackRedo());
					model.removeFromStackRedo();
				}


			}
			
			
		}




		/*if(model.getStackRedo().isEmpty() == false) {

			//proveravam da li je poslednji na steku selektovan
			//proveravam da li poslednji na steku nije selektovan

			if(model.getLastShapeOnStackRedo().isSelektovan() == true) {

				System.out.println("Oblik na redo je selektovan!");





			} else {


				System.out.println("Oblik na redo nije selektovan!");

				model.addToStackUndo(model.getLastShapeOnStackRedo());

				cmdAddShape.execute();
				model.removeFromStackRedo();

				if(model.getStackRedo().isEmpty() == false) {

					cmdAddShape.setShape(model.getLastShapeOnStackRedo());



				}


			}
		}*/





		/*if(model.getStackRedo().isEmpty() == false) {


			if(model.getLastShapeOnStackRedo().isSelektovan() == true) {

				System.out.println("Poslenji je selektovan!");

				cmdUpdate.execute();
				Oblik s = CopyShape(model.getLastShapeOnStackRedo());
				Oblik original = CopyShape(cmdUpdate.getOriginal());
				model.getListaObjekata().set(model.getListaObjekata().indexOf(s), original);
				model.addToStackUndo(model.getLastShapeOnStackRedo());
				model.removeFromStackRedo();

				if(model.getStackRedo().isEmpty() == false) {

					if(model.getLastShapeOnStackRedo().isSelektovan() == true) {

						Oblik k = CopyShape(model.getLastShapeOnStackRedo());
						Oblik p = CopyShape(model.getLastShapeOnStackRedo());
						cmdUpdate.setNewState(p);
						k.setSelektovan(false);
						cmdUpdate.setOldState(k);
						cmdUpdate.setOriginal(k);

					}


				}


			} else {


				System.out.println("Poslenji je nije selektovan!");


				model.addToStackUndo(model.getLastShapeOnStackRedo());
				cmdAddShape.execute();
				model.removeFromStackRedo();

				frame.getTextArea().append("Paint: " + model.getLastShapeOnStackUndo().toString() +"\n");

				if(model.getStackRedo().isEmpty() == false) {

					cmdAddShape.setShape(model.getLastShapeOnStackRedo());
				}


			}






		}*/

		/*if(model.getStackRedo().isEmpty() == false) {

			System.out.println(model.getStackRedo().size());


		}*/


		/*if(model.getStackRedo().isEmpty() == false) {

			if(model.getStackRedo().size() > 1) {


				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(model.getLastShapeOnStackRedo())) {

						Oblik fromList = CopyShape(model.getListaObjekata().get(i));
						Oblik s = CopyShape(model.getLastShapeOnStackRedo());
						Oblik k = CopyShape(model.getListaObjekata().get(i));
						model.getListaObjekata().set(i, s);
						model.addToStackUndo(model.getLastShapeOnStackRedo());
						model.removeFromStackRedo();

						cmdUpdate = new CmdUpdateShape(fromList,s);
						cmdUpdate.execute();



					}
				}


			} else if (model.getStackRedo().size() == 1) {


				cmdAddShape.execute();
				model.addToStackUndo(model.getLastShapeOnStackRedo());
				model.removeFromStackRedo();
			}




		}*/









		//model.addToStackUndo(model.getLastShapeOnStackRedo());


		/*if(model.getStackRedo().isEmpty() == false) {



			if(model.getLastShapeOnStackRedo().isSelektovan() == false) {


				cmdAddShape.execute();
				model.addToStackUndo(model.getLastShapeOnStackRedo());
				model.removeFromStackRedo();




			} else {


				cmdUpdate.unexecute();
				System.out.println(model.getLastShapeOnStackRedo().toString());
				model.addToStackUndo(model.getLastShapeOnStackRedo());
				model.removeFromStackRedo();

				for(int i=0; i<model.getListaObjekata().size(); i++) {


					if(model.getListaObjekata().get(i).equals(cmdUpdate.getOldState())) {

						Oblik s = CopyShape(model.getLastShapeOnStackUndo());
						//model.getListaObjekata().get(i).setSelektovan(true);
						model.getListaObjekata().set(i, s);
						/*cmdUpdate = new CmdUpdateShape(model.getListaObjekata().get(i),model.getLastShapeOnStackUndo());
						cmdUpdate.execute();*/
		//System.out.println(model.getLastShapeOnStackRedo().toString());

	}















	/*checkIfSelectedShapeExists();

		if(lastAction == "Selekcija") {


			if(model.getStackRedo().isEmpty() == false) {

				Oblik s;
				s = (Oblik) model.getLastShapeOnStackRedo();
				s.setSelektovan(true);

				cmdUpdate = new CmdUpdateShape(model.getLastShapeOnStackRedo(),s);
				cmdUpdate.execute();

				for(int i=0; i<model.getListaObjekata().size(); i++) {


					if(cmdUpdate.getOldState().equals(model.getListaObjekata().get(i))) {


						try {


							Oblik q = (Oblik) cmdUpdate.getOriginal().clone();
							model.getListaObjekata().set(i, q);
							model.addToStackUndo(model.getLastShapeOnStackRedo());
							model.removeFromStackRedo();




						} catch (CloneNotSupportedException e) {

							System.out.println("greška");
						}


					}

				}

				checkIfSelectedShapeExists();


			} 


		}


	}*/

	public void moveToFront() {

		checkIfSelectedShapeExists();


		for(int i=0; i<model.getListaObjekata().size(); i++) {

			if(model.getListaObjekata().get(i).isSelektovan() == true) {

				if((i+1)<model.getListaObjekata().size()) {

					Collections.swap(model.getListaObjekata(), i, i+1); 
					i=model.getListaObjekata().size();



				}


			}

		}




	}

	public void bringToFront() {

		checkIfSelectedShapeExists();


		for(int i=0; i<model.getListaObjekata().size(); i++) {

			if(model.getListaObjekata().get(i).isSelektovan() == true) {

				if((i+1)<model.getListaObjekata().size()) {

					Collections.swap(model.getListaObjekata(), i, model.getListaObjekata().size()-1); 
					i=model.getListaObjekata().size();


				}


			}

		}



	}

	public void moveToBack() {

		checkIfSelectedShapeExists();

		for(int i=0; i<model.getListaObjekata().size(); i++) {

			if(model.getListaObjekata().get(i).isSelektovan() == true) {

				if(i>0) {

					Collections.swap(model.getListaObjekata(), i, i-1); 

				}


			}

		}




	}

	public void bringToBack() {

		checkIfSelectedShapeExists();

		for(int i=0; i<model.getListaObjekata().size(); i++) {

			if(model.getListaObjekata().get(i).isSelektovan() == true) {

				if(i>0) {

					Collections.swap(model.getListaObjekata(), i, 0); 
					i=0;


				}


			}

		}

	}

	public NaslovnaPokretanje getFrame() {
		return frame;
	}

	public void setFrame(NaslovnaPokretanje frame) {
		this.frame = frame;
	}

	public int checkIfSelectedShapeExists() {



		int n = 0;

		for(int i=0; i<model.getListaObjekata().size(); i++) {

			if(model.getListaObjekata().get(i).isSelektovan() == true) {

				n++;
			} 


		}

		if(n==1) {



			button.setStatus(true);
			return n;


		} else if (n>1) {

			button.setStatus(false);
			return n;
		} else {

			button.setStatus(false);
			return n;


		}





	}

	public Oblik CopyShape(Oblik s) {


		Oblik o = null;
		try {


			o = (Oblik) s.clone();


		} catch (CloneNotSupportedException e) {

			System.out.println("Error!");
		}

		return o;







	}








}
