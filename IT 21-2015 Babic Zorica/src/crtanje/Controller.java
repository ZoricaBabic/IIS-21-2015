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
//import cmd.CmdUndoRedo;
import cmd.CmdUndoRedo1;
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
import hexagon.Hexagon;
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
	//private CmdUndoRedo cmdUndoRedo;

	//private CmdUndo undoRedo;

	private int firstClick = 0;
	
	private CmdUndoRedo1 cmdUndoRedo1;

	Button button;
	
	



	public Controller(Model model, NaslovnaPokretanje frame) {

		this.model = model;
		this.frame = frame;

		button = new Button(frame);
		//cmdUndoRedo = new CmdUndoRedo(model);
		cmdUndoRedo1 = new CmdUndoRedo1();



	} 

	public Controller() {



	}

	public void setBojaIvice(Color c) {

		model.setBojaIvice(c);
	}

	public void setBojaUnutrasnjosti(Color c) {

		model.setBojaIvice(c);
	}

	//BRISANJE
	public void actionPerfomedDelete(ActionEvent e) { //treba odraditi za hexagon

		if(checkIfSelectedShapeExists() == 0){
			
			JOptionPane.showMessageDialog(null, "Niste ništa selektovali");

		} else  {

			int odgovor = JOptionPane.YES_NO_OPTION;

			JOptionPane.showConfirmDialog (null, "Da li želite obrisati oblik koji ste selektovali","Poruka", odgovor);

			if(odgovor == JOptionPane.YES_OPTION){

				ArrayList<Oblik> oblici = new ArrayList<Oblik>();

				for(int i=0; i<model.getListaObjekata().size(); i++) {
					
					if(model.getListaObjekata().get(i) instanceof HexagonAdapter) {
						
						HexagonAdapter h = new HexagonAdapter(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon());
						
						System.out.println(h);
						
						if(h.getHexagon().isSelected() == true) {
							
							oblici.add(model.getListaObjekata().get(i));
						}
					} else {
						

						if(model.getListaObjekata().get(i).isSelektovan() == true) {

							oblici.add(model.getListaObjekata().get(i));

						}
					}
					
					
					
					




				}	
				
				int countDeleted = oblici.size();
				
				if(oblici.size() == 1) {
					
					CmdRemoveShape cmdRemove = new CmdRemoveShape(model,oblici.get(0));
					cmdRemove.execute();
					cmdUndoRedo1.addToCommandList(cmdRemove);
				} else {
					
					CmdRemoveShape cmdRemove = new CmdRemoveShape(model,oblici);
					cmdRemove.execute();
					cmdUndoRedo1.addToCommandList(cmdRemove);
					
					
					/*for(int i=0; i<oblici.size(); i++) {

						//CmdRemoveShape cmdRemove = new CmdRemoveShape(model,oblici.get(i));
						//model.addToStackUndo(oblici.get(i));
						cmdRemove.execute();
						cmdUndoRedo1.addToCommandList(cmdRemove);
						
						frame.getTextArea().append("Deleted: " + oblici.get(i) + "\n");
						countDeleted++;

					}*/
				}
				
				

				

			}

		}
	}



	//MODIFIKACIJA
	public void actionPerfomedModify(ActionEvent e) { //za hexagon odradraditi

		int count = 0;
		for(Oblik o: model.getListaObjekata()) {

			if(o instanceof HexagonAdapter) {
				
				HexagonAdapter h = new HexagonAdapter( ((HexagonAdapter) o).getHexagon());
				
				if(h.getHexagon().isSelected() == true) {
					
					count++;
				}
			
			} else {
				
				if(o.isSelektovan() == true) {

					count++;

				}
			}
			
		}

		if(count == 0) {

			JOptionPane.showMessageDialog(null, "Niste ništa selektovali!");

		} else if(count > 1) {

			JOptionPane.showMessageDialog(null, "Selektovano je više oblika. Selektujte samo oblik koji želite da modifikujete!");

		} else {

			for(Oblik o: model.getListaObjekata()) {
				
				if(o instanceof HexagonAdapter) {
					
					
					HexagonAdapter h = new HexagonAdapter( ((HexagonAdapter) o).getHexagon());
					
					if(h.getHexagon().isSelected() == true) {
						

						DlgOsobineKruga osobine = new DlgOsobineKruga();
						osobine.setX(((HexagonAdapter) o).getHexagon().getX());
						osobine.setY(((HexagonAdapter) o).getHexagon().getY());
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
							
							
							Hexagon hexagon = new Hexagon(h.getHexagon().getX(),h.getHexagon().getY(),h.getHexagon().getR());
							hexagon.setAreaColor(h.getHexagon().getAreaColor());
							hexagon.setBorderColor(h.getHexagon().getBorderColor());
							HexagonAdapter q = new HexagonAdapter(hexagon);
							q.setSelektovan(h.getHexagon().isSelected());
							
							Hexagon hexagonNew = new Hexagon(osobine.getX(),osobine.getY(),osobine.getPoluprecnik());
							hexagonNew.setAreaColor(osobine.getBojaIvice());
							hexagonNew.setBorderColor(osobine.getBojaUnutrasnjosti());
							HexagonAdapter s = new HexagonAdapter(hexagonNew);
							s.setSelektovan(true);
							
							
							/*Hexagon hexagon = new Hexagon ((HexagonAdapter) o).getHexagon().getX(), ((HexagonAdapter) o).getHexagon().getY(), ((HexagonAdapter) o).getHexagon().getR());
							hexagon.setAreaColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getAreaColor());
							hexagon.setBorderColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getBorderColor());
							HexagonAdapter q = new HexagonAdapter(hexagon);
							q.setSelektovan(model.getListaObjekata().get(i).isSelektovan());
							
							Hexagon hexagon2 = new Hexagon(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getX(),((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getY(),((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getR());
							hexagon2.setAreaColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getAreaColor());
							hexagon2.setBorderColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getBorderColor());
							HexagonAdapter s = new HexagonAdapter(hexagon2);
							s.setSelektovan(true);*/
							
						/*Hexagon hexagon = new Hexagon(osobine.getX(),osobine.getY(),osobine.getPoluprecnik());
						hexagon.setBorderColor(model.getBojaUnutrasnjosti());
						hexagon.setAreaColor(model.getBojaIvice());*/
						/*HexagonAdapter novi = new HexagonAdapter(hexagon);
					
						novi.setSelektovan(true);*/

							//Oblik s = CopyShape(novi);

							//model.addToStackUndo(s);


							CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
							cmdUpdate.execute();
							
							cmdUndoRedo1.addToCommandList(cmdUpdate);

							frame.getTextArea().append(s + "\n");



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

							//model.addToStackUndo(s);

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(o,novi);

							cmdUpdate.execute();
							cmdUndoRedo1.addToCommandList(cmdUpdate);

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

						//model.addToStackUndo(s);

						CmdUpdateShape cmdUpdate = new CmdUpdateShape(o,t);

						cmdUpdate.execute();
						
						cmdUndoRedo1.addToCommandList(cmdUpdate);

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

							//model.addToStackUndo(s);

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(o,novi);
							cmdUndoRedo1.addToCommandList(cmdUpdate);

							cmdUpdate.execute();

							frame.getTextArea().append(novi + "\n");


							//model.setOblik(l);

						}
						

					}else if(o instanceof HexagonAdapter) { 







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

							//model.addToStackUndo(s);

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(o,novi);

							cmdUpdate.execute();
							
							cmdUndoRedo1.addToCommandList(cmdUpdate);

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

							//model.addToStackUndo(s);

							//cmdUndoRedo.add(s);

							//cmdUndoRedo.update(o, novi);

							//undoRedo.add(s);

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(o,novi);
							cmdUpdate.execute();

							Oblik q = CopyShape(cmdUpdate.getOriginal());
							cmdUndoRedo1.addToCommandList(cmdUpdate);
							//model.getCommands().add(q);

							position++;

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


	//KORISNIK ZELI DA SELEKTUJE
	public void mouseClickedSelection(MouseEvent e) {

		if(model.getListaObjekata().size() == 0){

			JOptionPane.showMessageDialog(null, "Niste ništa nacrtali!");

		} else {

			model.setOdabranOblik("");


		}


	}
	
	//SELEKCIJA
	public void mouseClickedPnl(int x, int y) {

		model.getUnselectedShapes().removeAll(model.getUnselectedShapes());

		for(int i =0; i<model.getListaObjekata().size(); i++) {

			if(model.getListaObjekata().get(i).isSelektovan() == true) {


				model.getUnselectedShapes().add(model.getListaObjekata().get(i));
				countForRedo++;


			}
		}

		//SELEKCIJA I CRTANJE
		if(model.getOdabranOblik() == ""){


			int m=0;

			for(int i=0; i<model.getListaObjekata().size(); i++) {

				//selektovanje oblika na koji je kliknut
				if(model.getListaObjekata().get(i).sadrzi(x, y)){

					if(model.getListaObjekata().get(i).isSelektovan() == false) {

						model.getUnselectedShapes().removeAll(model.getUnselectedShapes());
						
						if(model.getListaObjekata().get(i) instanceof HexagonAdapter) {
							
							System.out.println("HEXAGON ADAPTER!!!!!!!!!!!!");
							
							Hexagon hexagon = new Hexagon(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getX(),((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getY(),((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getR());
							hexagon.setAreaColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getAreaColor());
							hexagon.setBorderColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getBorderColor());
							HexagonAdapter q = new HexagonAdapter(hexagon);
							q.setSelektovan(model.getListaObjekata().get(i).isSelektovan());
							
							Hexagon hexagon2 = new Hexagon(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getX(),((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getY(),((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getR());
							hexagon2.setAreaColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getAreaColor());
							hexagon2.setBorderColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getBorderColor());
							HexagonAdapter s = new HexagonAdapter(hexagon2);
							s.setSelektovan(true);
							
							System.out.println("NESELEKTOVAN>>>>>>>>>>> " + 	q.toString());
							System.out.println("SELEKTOVAN>>>>>>>>>>> " + 	s.toString());
							
							
							
			
							
							
														
							
							m++;
					
							
							
					
							
						
							//model.addToStackUndo(s);
							CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
							cmdUpdate.execute(); 
							cmdUndoRedo1.addToCommandList(cmdUpdate);
							//model.getCommands().add(s);
							frame.getTextArea().append(s.toString() +"\n");
							
						} else {
							

							Oblik q = CopyShape(model.getListaObjekata().get(i));
							m++;
							System.out.println(q);
							
							Oblik s = CopyShape(model.getListaObjekata().get(i));
							s.setSelektovan(true);
							
							System.out.println(q.toString());
							System.out.println(s.toString());
						
							//model.addToStackUndo(s);
							CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
							cmdUpdate.execute(); 
							cmdUndoRedo1.addToCommandList(cmdUpdate);
							//model.getCommands().add(s);
							frame.getTextArea().append(s.toString() +"\n");
						}
						

						
						position++;
						button.setStatus(true);
						frame.getBtnRedo().setEnabled(false);
						



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


			}






			if(m==0) {

				System.out.println("Ne sadrzi nijedan oblik!");
				n=true;

				for(Oblik o: model.getListaObjekata()) {

					/*if(o instanceof HexagonAdapter) {

						
						Oblik q = CopyShape(o);
						m++;
						Oblik s = CopyShape(o);
						s.setSelektovan(false);
						
						//model.addToStackUndo(s);
						CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
						cmdUpdate.execute(); 
						cmdUndoRedo1.addToCommandList(cmdUpdate);
						//((HexagonAdapter) o).unselect();
					} *///premestiti drugačije!

					//cmdUndoRedo.add(CopyShape(o));
					
					Oblik q = CopyShape(o);
					m++;
					Oblik s = CopyShape(o);
					s.setSelektovan(false);
					
					//model.addToStackUndo(s);
					CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
					cmdUpdate.execute(); 
					cmdUndoRedo1.addToCommandList(cmdUpdate);
					
					

					frame.getTextArea().append(o.toString() +"\n");
					button.setStatus(false);

				}


			}




			lastAction = "Selekcija";
			frame.getBtnUndo().setEnabled(true);
			checkIfSelectedShapeExists();



		} else {
			
			for(int i =0; i<model.getListaObjekata().size(); i++) {
				
				if(model.getListaObjekata().get(i) instanceof HexagonAdapter) {
					
					
					System.out.println("HEXAGON ADAPTER!!!!!!!!!!!!");
					
					Hexagon hexagon = new Hexagon(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getX(),((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getY(),((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getR());
					hexagon.setAreaColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getAreaColor());
					hexagon.setBorderColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getBorderColor());
					HexagonAdapter q = new HexagonAdapter(hexagon);
					q.setSelektovan(true);
					
				
					
					Hexagon hexagon2 = new Hexagon(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getX(),((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getY(),((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getR());
					hexagon2.setAreaColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getAreaColor());
					hexagon2.setBorderColor(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon().getBorderColor());
					HexagonAdapter s = new HexagonAdapter(hexagon2);
					s.setSelektovan(false);
					
					System.out.println("NESELEKTOVAN>>>>>>>>>>> " + 	q.toString());
					System.out.println("SELEKTOVAN>>>>>>>>>>> " + 	s.toString());
					

					CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
					cmdUpdate.execute(); 
					cmdUndoRedo1.addToCommandList(cmdUpdate);
					frame.getTextArea().append(s.toString() +"\n");
					
				} else {
					
					if(model.getListaObjekata().get(i).isSelektovan() == true) {
						
						Oblik q = CopyShape(model.getListaObjekata().get(i));
						
						Oblik s = CopyShape(model.getListaObjekata().get(i));
						s.setSelektovan(false);
						
						CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
						cmdUpdate.execute(); 
						cmdUndoRedo1.addToCommandList(cmdUpdate);
						//model.getCommands().add(s);
						frame.getTextArea().append(s.toString() +"\n");
					}
				}
				
				
				
		
			}
		
			
			/*for(Oblik o: model.getListaObjekata()) {

				if(o instanceof HexagonAdapter) {

					((HexagonAdapter) o).unselect();
				} //premestiti drugačije!

				//cmdUndoRedo.add(CopyShape(o));
				o.setSelektovan(false);
				

				frame.getTextArea().append(o.toString() +"\n");
				button.setStatus(false);

			}*/
		}




		//CRTANJE
		if(model.getOdabranOblik() == "Tacka" || model.getOdabranOblik() == null)
		{

			model.setX(x);
			model.setY(y);
			//view.repaint();

			Tacka t = new Tacka(model.getX(),model.getY());
			t.setBojaIvice(model.getBojaIvice());


			cmdAddShape = new CmdAddShape(model,t);
			cmdAddShape.execute();


			/*Oblik l = CopyShape(t);

			model.addToStackUndo(l);*/
			
			cmdUndoRedo1.addToCommandList(cmdAddShape);

			frame.getBtnRedo().setEnabled(false);
			frame.getBtnUndo().setEnabled(true);
			frame.getBtnSelektuj().setEnabled(true);

			frame.getTextArea().append("Drawing: " + t.toString() +"\n");
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
					
					Hexagon hexagon = new Hexagon(model.getX(),model.getY(),model.getR());
					hexagon.setAreaColor(model.getBojaUnutrasnjosti());
					hexagon.setBorderColor(model.getBojaIvice());
					
					HexagonAdapter h = new HexagonAdapter(hexagon);
					h.setSelektovan(false);
					
					
					
					//HexagonAdapter h = new HexagonAdapter(new Tacka(model.getX(),model.getY()),model.getR(),model.getBojaIvice(),model.getBojaUnutrasnjosti());

					CmdAddShape cmdAddShape = new CmdAddShape(model,h);
					cmdAddShape.execute();
					
					cmdUndoRedo1.addToCommandList(cmdAddShape);

					/*Oblik l = CopyShape(h);
					model.addToStackUndo(l);*/


					frame.getBtnRedo().setEnabled(false);
					frame.getBtnUndo().setEnabled(true);
					frame.getBtnSelektuj().setEnabled(true);

					frame.getTextArea().append("Drawing: " + h.toString() +"\n");




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
				CmdAddShape cmdAddShape = new CmdAddShape(model,l);
				cmdAddShape.execute();
				
				cmdUndoRedo1.addToCommandList(cmdAddShape);

				/*Oblik k = CopyShape(l);


				model.addToStackUndo(k);*/



				frame.getBtnRedo().setEnabled(false);
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnSelektuj().setEnabled(true);

				frame.getTextArea().append("Drawing: " + l.toString() +"\n");

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

							CmdAddShape cmdAddShape = new CmdAddShape(model,p);
							cmdAddShape.execute();
							
							cmdUndoRedo1.addToCommandList(cmdAddShape);


							/*Oblik l = CopyShape(p);


							model.addToStackUndo(l);*/


							frame.getBtnRedo().setEnabled(false);
							frame.getBtnUndo().setEnabled(true);
							frame.getBtnSelektuj().setEnabled(true);

							frame.getTextArea().append("Drawing: " + p.toString() +"\n");




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
					CmdAddShape cmdAddShape = new CmdAddShape(model,kv);
					cmdAddShape.execute();
					
					cmdUndoRedo1.addToCommandList(cmdAddShape);
					
					

					/*Oblik l = CopyShape(kv);
					model.addToStackUndo(l);*/


					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(false);
					frame.getBtnSelektuj().setEnabled(true);

					frame.getTextArea().append("Drawing: " + kv.toString() +"\n");

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


			s=true; //proveriti šta ovo znači

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

					CmdAddShape cmdAddShape = new CmdAddShape(model,kr); 
					cmdAddShape.execute();

					position++;
					
					cmdUndoRedo1.addToCommandList(cmdAddShape);
					


					/*Oblik l = CopyShape(kr);
					


					model.addToStackUndo(l);*/
					
					

				


					frame.getBtnUndo().setEnabled(true);
					frame.getBtnRedo().setEnabled(false);
					frame.getBtnSelektuj().setEnabled(true);

					frame.getTextArea().append("Drawing: " + kr.toString() +"\n");







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




	//SETOVANJE BOJE UNUTRASNJOSTI
	public Color mouseClickedBojaUnutrasnjosti() {
		
	
				Color currentBojaUnutrasnjosti = model.getBojaUnutrasnjosti();
				Color bojaUnutrasnjosti = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK);

				if(bojaUnutrasnjosti  == null) {

					model.setBojaUnutrasnjosti(currentBojaUnutrasnjosti);

					return currentBojaUnutrasnjosti;
				} else {

					model.setBojaUnutrasnjosti(bojaUnutrasnjosti);
					return bojaUnutrasnjosti;


				}

	}


	//SETOVANJE BOJE IVICE
	public Color mouseClickedBojaIvice() {


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
	
	//SETOVANJE ODABRANOG OBLIKA
	public void setOdabranOblik(String s) {
		
	

		model.setOdabranOblik(s);

	}


	private int countForRedo = 0;

	//UNDO
	public void undo() {
		
		if(!cmdUndoRedo1.getUndo().isEmpty()) {
			
			cmdUndoRedo1.execute();
			
			if(cmdUndoRedo1.getUndo().isEmpty()) {
				
				frame.getBtnUndo().setEnabled(false);
				frame.getBtnRedo().setEnabled(true);
			} else {
				
				frame.getBtnUndo().setEnabled(true);
			}
		} 
		
	


	}

	//REDO
	public void redo() {
		
		if(!cmdUndoRedo1.getRedo().isEmpty()) {
			
			cmdUndoRedo1.unexecute();
			if(cmdUndoRedo1.getRedo().isEmpty()) {
				
				frame.getBtnRedo().setEnabled(false);
				frame.getBtnUndo().setEnabled(true);
			} else {
				
				frame.getBtnRedo().setEnabled(true);
			}
		}
		
	
		
		/*if(cmdUndoRedo1.getCurrentPosition() == cmdUndoRedo1.getCommandList().size()-1) {
			
			frame.getBtnRedo().setEnabled(false);
		} else {
			
			frame.getBtnRedo().setEnabled(true);
			frame.getBtnUndo().setEnabled(true);
		}*/
		
		
		
		
		
		
		
		
	
		
		

		//cmdUndoRedo.execute();


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

	//RUKOVANJE DUGMADIMA
	public int checkIfSelectedShapeExists() {



		int n = 0;

		for(int i=0; i<model.getListaObjekata().size(); i++) {
			
			if(model.getListaObjekata().get(i) instanceof HexagonAdapter) {
				
				HexagonAdapter h = new HexagonAdapter(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon());
				
				System.out.println(h);
				
				if(h.getHexagon().isSelected() == true) {
					
					n++;
					System.out.println("Hexagon je selektovan!!!!!!!!");
				}
				
				
			} else {
				
				if(model.getListaObjekata().get(i).isSelektovan() == true) {
					
					System.out.println("Oblik je selektovan!!!!!!");

					n++;
				} 
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

	//KOPIRANJE OBLIKA
	public static Oblik CopyShape(Oblik s) {


		Oblik o = null;
		try {


			o = (Oblik) s.clone();


		} catch (CloneNotSupportedException e) {

			System.out.println("Error!");
		}

		return o;







	}
	
	
	//KOPIRANJE OBLIKA
	public static HexagonAdapter copyHexagon(HexagonAdapter s) {


		HexagonAdapter o = null;
		try {


			o = (HexagonAdapter) s.clone();


		} catch (CloneNotSupportedException e) {

			System.out.println("Error!");
		}

		return o;







	}








}
