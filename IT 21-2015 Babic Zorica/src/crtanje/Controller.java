package crtanje;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Collections;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import cmd.CmdAddShape;
import cmd.CmdRemoveShape;
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
import geometrija.Pravougaonik;
import geometrija.Tacka;
import observer.Button;
import observer.ButtonObserver;
import geometrija.PovrsinskiOblik;

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

	/*public void actionPerfomedDelete(ActionEvent e) {

		if(model.stackIsEmpty() == true){

			JOptionPane.showMessageDialog(null, "Niste ništa selektovali");

		} else  {

			int odgovor = JOptionPane.YES_NO_OPTION;
			JOptionPane.showConfirmDialog (null, "Da li želite obrisati oblik koji ste selektovali","Poruka", odgovor);

			if(odgovor == JOptionPane.YES_OPTION){

				if(model.stackPeek() instanceof Pravougaonik){


					model.remove(model.stackPeek());
					//model.removeFromStack();
					model.removeAll();
					//view.repaint();


				} else if(model.stackPeek() instanceof Tacka){

					model.getListaObjekata().remove(model.stackPeek());
					//model.removeFromStack();
					model.removeAll();
					//view.repaint();

				} else if(model.stackPeek() instanceof Linija){

					model.remove(model.stackPeek());
					//model.removeFromStack();
					model.removeAll();
					//view.repaint();


				} else if(model.stackPeek() instanceof Kvadrat){

					model.remove(model.stackPeek());
					//model.removeFromStack();
					model.removeAll();
					//view.repaint();


				} else if (model.stackPeek() instanceof Krug) {

					model.remove(model.stackPeek());
					//model.removeFromStack();
					model.removeAll();
					//view.repaint();


				} 
			}
		}

	}*/


	public void actionPerfomedModify(ActionEvent e) {

		int count = 0;
		for(Oblik o: model.getListaObjekata()) {

			if(o.isSelektovan() == true) {

				count++;
				System.out.println("Selektovano je: " +count);
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


							cmdUpdate = new CmdUpdateShape(o,novi);
							cmdUpdate.execute();

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

						for(Oblik l: model.listaObjekata) {

							if(l.equals(o)) {

								System.out.println("Isti su");						
								cmdUpdate = new CmdUpdateShape(l,t);
								cmdUpdate.execute();
								model.setOblik(l);

							}

						}

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

						System.out.println(osobine.getBojaIvice());

						osobine.getTxtXKoordinataPocetneTacke().setText(Integer.toString(osobine.getxPocetna()));
						osobine.getTxtYKoordinataPocetneTacke().setText(Integer.toString(osobine.getyPocetna()));
						osobine.getTxtXKoordinataKrajnjeTacke().setText(Integer.toString(osobine.getxKrajnja()));
						osobine.getTxtYKoordinataKrajnjeTacke().setText(Integer.toString(osobine.getyKrajnja()));
						System.out.println(osobine.getBojaIvice());
						osobine.getEdpBoja().setBackground(osobine.getBojaIvice());


						osobine.setVisible(true);



						if(osobine.isVisible() == false) {

							Linija novi = new Linija(new Tacka(osobine.getxPocetna(),osobine.getyPocetna()), new Tacka (osobine.getxKrajnja(),osobine.getyKrajnja()),osobine.getBojaIvice());

							cmdUpdate = new CmdUpdateShape(o,novi);
							cmdUpdate.execute();

							//model.setOblik(l);

						}





					} else if(o instanceof Kvadrat){

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

							cmdUpdate = new CmdUpdateShape(o,novi);
							cmdUpdate.execute();

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
						
							cmdUpdate = new CmdUpdateShape(o,novi);
							cmdUpdate.execute();
							
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
		
		
		checkIfSelectedShapeExists();
		
	
		if(model.getOdabranOblik() == ""){

			int m=0;

			for(int i=0; i<model.getListaObjekata().size(); i++) {

				//selektovanje oblika na koji je kliknut
				if(model.getListaObjekata().get(i).sadrzi(x, y)){
					
					Oblik q = null;
					try {


						q = (Oblik) model.getListaObjekata().get(i).clone();
						
					} catch (CloneNotSupportedException e) {

						System.out.println("greška");
					}
					
					

					m++;
					model.getListaObjekata().get(i).setSelektovan(true);
					model.addToStackUndo(model.getListaObjekata().get(i));
					System.out.println(q.toString());
					System.out.println(model.getListaObjekata().get(i).toString());
					cmdUpdate = new CmdUpdateShape(q,model.getListaObjekata().get(i));
					cmdUpdate.execute();
					
					
					button.setStatus(true);
					frame.getBtnRedo().setEnabled(false);
					
					if(m>1) {
						
						for(int k=0; k<model.getListaObjekata().size(); k++) {
							
							if(!model.getListaObjekata().get(k).equals(model.getLastShapeOnStackUndo())) {
								
								model.getListaObjekata().get(k).setSelektovan(false);
								
							}
						}
					} 
					
				}


			}

			if(m==0) {

				for(Oblik o: model.getListaObjekata()) {

					o.setSelektovan(false);
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
					HexagonAdapter h = new HexagonAdapter(new Tacka(model.getX(),model.getY()),model.getR());
					h.getHexagon().setAreaColor(model.getBojaUnutrasnjosti());
					h.getHexagon().setBorderColor(model.getBojaIvice());
					cmdAddShape = new CmdAddShape(model,h);
					cmdAddShape.execute();
					
					System.out.println(h.toString());
					
					frame.getBtnRedo().setEnabled(false);
					frame.getBtnUndo().setEnabled(true);
					frame.getBtnSelektuj().setEnabled(true);
					



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
				
				System.out.println(l.toString());
				
				frame.getBtnRedo().setEnabled(false);
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnSelektuj().setEnabled(true);

				model.setDvaKlika(false);
			}

		}

		if(model.getOdabranOblik() == "Pravougaonik"){


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
							model.setX(x);
							model.setY(y);
							//view.repaint();
							Pravougaonik p = new Pravougaonik(new Tacka(model.getX(),model.getY()), model.getDuzina(),model.getSirina());

							System.out.println("Duzina pravougaonika je: " + p.getDuzinaStranice() + " Sirina pravougaonika je: " + p.getSirina());
							p.setBojaIvice(model.getBojaIvice());
							p.setBojaUnutrasnjosti(model.getBojaUnutrasnjosti());
							cmdAddShape = new CmdAddShape(model,p);
							cmdAddShape.execute();
							
							System.out.println(p.toString());
							
							frame.getBtnRedo().setEnabled(false);
							frame.getBtnUndo().setEnabled(true);
							frame.getBtnSelektuj().setEnabled(true);

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
					frame.getBtnUndo().setEnabled(true);
					System.out.println(kv.toString());
					
					frame.getBtnRedo().setEnabled(false);
					frame.getBtnSelektuj().setEnabled(true);

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
					cmdAddShape.execute();
					
					model.addToStackUndo(kr);
					
					
					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(false);
					frame.getBtnSelektuj().setEnabled(true);



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

		System.out.println("Tacka");

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

	public void undo() {
		
		
		if(checkIfSelectedShapeExists() == 0) { //ako nema selektovanih oblika
			
			if(model.getStackUndo().isEmpty() == false) {
				
				for(int i=0; i<model.getListaObjekata().size(); i++) {
					
					if(model.getListaObjekata().get(i).equals(model.getLastShapeOnStackUndo())) {
						
						cmdRemove = new CmdRemoveShape(model,model.getListaObjekata().get(i));
						model.addToStackRedo(model.getListaObjekata().get(i));
						model.removeFromStackUndo();
						cmdRemove.execute(); //obrišem ga iz liste
						
					}
				}
			}
			
			
		} else if (checkIfSelectedShapeExists() == 1) {
			
			model.addToStackRedo(cmdUpdate.getNewState());
			System.out.println(model.getLastShapeOnStackRedo().toString());
			cmdUpdate.unexecute();
			System.out.println(model.getLastShapeOnStackRedo().toString());
			model.removeFromStackUndo(); //brisem sa stacka selektovani
			System.out.println(model.getLastShapeOnStackRedo().toString());
			
			for(int i=0; i<model.getListaObjekata().size(); i++) {
				
				if(model.getListaObjekata().get(i).equals(cmdUpdate.getNewState())) {
					
					System.out.println(model.getLastShapeOnStackRedo().toString());
					model.getListaObjekata().get(i).setSelektovan(false);
					cmdUpdate = new CmdUpdateShape(cmdUpdate.getNewState(),model.getListaObjekata().get(i));
					cmdUpdate.execute();//obrišem ga iz liste
					System.out.println(model.getLastShapeOnStackRedo().toString());
					
				}
			}
			
			
			
		
			
			
			
		}
		
		
		
	
		//if(lastAction == "Selekcija") {


			/*if(model.getStackUndo().isEmpty() == false) {


				Oblik s;
				s = (Oblik) model.getLastShapeOnStackUndo();
				s.setSelektovan(false);

				/*cmdUpdate = new CmdUpdateShape(model.getLastShapeOnStackUndo(),s);
				cmdUpdate.execute();

				cmdUpdate.unexecute();

				for(int i=0; i<model.getListaObjekata().size(); i++) {


					if(cmdUpdate.getOldState().equals(model.getListaObjekata().get(i))) {


						try {


							Oblik q = (Oblik) cmdUpdate.getOldState().clone();
							model.getListaObjekata().set(i, q);
							model.addToStackRedo(model.getLastShapeOnStackUndo());
							model.removeFromStackUndo();



						} catch (CloneNotSupportedException e) {

							System.out.println("greška");
						}


					}

				}


			}*/


		/*} else if(lastAction == "Modify") {
			
			
			cmdUpdate.unexecute();
			
		}*/
		
		checkIfSelectedShapeExists();






	}


	public void redo() {
		
	
		if(model.getStackRedo().isEmpty() == false) {
			
			
			
			if(model.getLastShapeOnStackRedo().isSelektovan() == false) {
				
				cmdRemove.unexecute();
				model.removeFromStackRedo();
				model.addToStackUndo(cmdRemove.getO());
				
				
				
			} else {
				
				System.out.println("na redo je selektovan oblik!");
			}
			
			
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


		}*/



	}

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
			
			System.out.println("Ovede je");
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








}
