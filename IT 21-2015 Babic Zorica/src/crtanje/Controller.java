package crtanje;


import java.awt.Button;


import java.awt.Color;


import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import cmd.CmdAddShape;
import cmd.CmdBringToBack;
import cmd.CmdBringToFront;
import cmd.CmdDeselectShape;
import cmd.CmdRemoveShape;
import cmd.CmdSelectShape;
import cmd.CmdToBack;
import cmd.CmdToFront;
//import cmd.CmdUndoRedo;
import cmd.CmdUndoRedo1;
import cmd.CmdUpdateSelectedShapes;
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
import observer.Subject;
import strategy.Context;
import strategy.LogOperation;
import strategy.OpenOperation;



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
	private ArrayList<Oblik> selectedShapes = new ArrayList<Oblik>();
	private Context context;
	

	private Subject subject;
	

	
	
	//private CmdUndo undoRedo;

	private int firstClick = 0;

	private CmdUndoRedo1 cmdUndoRedo1;

	





	public Controller(Model model, NaslovnaPokretanje frame) {

		this.model = model;
		this.frame = frame;

		//button = new Button(frame);
		//cmdUndoRedo = new CmdUndoRedo(model);
		cmdUndoRedo1 = new CmdUndoRedo1();
		
		subject = new Subject();
		subject.attach(NaslovnaPokretanje.btnObrisi);
		subject.attach(NaslovnaPokretanje.btnModifikuj);
		checkIfSelectedShapeExists();


	} 

	public Controller() {



	}

	public void setBojaIvice(Color c) {

		model.setBojaIvice(c);
	}

	public void setBojaUnutrasnjosti(Color c) {

		model.setBojaIvice(c);
	}
	
	//cuvanje txt fajlsa
	
	public void saveTxt(File f) {
		
		context = new Context(new LogOperation());
		context.executeStrategy(frame, f); 
	
		
	}
	
	public void openTxt(File f) {
		
		context = new Context(new OpenOperation());
		context.executeStrategy(frame, f);
			
			
		
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
					
					//obirsan je jedan oblik
					CmdRemoveShape cmdRemove = new CmdRemoveShape(model,oblici.get(0));
					cmdRemove.execute();
					cmdUndoRedo1.addToCommandList(cmdRemove);
					

					
				} else {
					//obrisano je više oblika
					CmdRemoveShape cmdRemove = new CmdRemoveShape(model,oblici);
					cmdRemove.execute();
					cmdUndoRedo1.addToCommandList(cmdRemove);

					
				}





			}

		}
		
		
		checkIfSelectedShapeExists();
	}



	//MODIFIKACIJA
	public void actionPerfomedModify(ActionEvent e) { //za hexagon odradraditi

		int count = 0;
		for(Oblik o: model.getListaObjekata()) {

			//if(o instanceof HexagonAdapter) {

				/*HexagonAdapter h = new HexagonAdapter( ((HexagonAdapter) o).getHexagon());
				if(h.getHexagon().isSelected() == true) {
					count++;
				}*/

			//} else {

				if(o.isSelektovan() == true) {

					count++;
			

				}
			//}

		}

		if(count == 0) {

			JOptionPane.showMessageDialog(null, "Niste ništa selektovali!");

		} else if(count > 1) {

			JOptionPane.showMessageDialog(null, "Selektovano je više oblika. Selektujte samo oblik koji želite da modifikujete!");

		} else {

			for(Oblik o: model.getListaObjekata()) {

				if(o.isSelektovan() == true) {
					
						if(o instanceof Pravougaonik){
						
						System.out.println("Pravougaonik!!!!!");

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

							//frame.getTextArea().append(novi + "\n");



							//model.setOblik(l);

						}


					} else if(o instanceof Kvadrat){
						
						System.out.println("Kvadrat!!!!!");

						
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

							//frame.getTextArea().append(novi + "\n");


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

							//frame.getTextArea().append(novi + "\n");


							//lastAction = "Modify";



							//model.setOblik(l);

						}

						/*model.remove(model.stackPeek());
						CmdAddShape cmd = new CmdAddShape(model,kr);
						cmd.execute();
						model.removeAll();*/
						//view.repaint();

					} 


				 else if(o instanceof Tacka){
						
						System.out.println("Taacka!!!!!");

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

						//frame.getTextArea().append(t + "\n");

						/*model.remove(model.stackPeek());
						CmdAddShape cmd = new CmdAddShape(model,t);
						cmd.execute();
						model.removeAll();*/




					} else if(o instanceof Linija){

						System.out.println("Linija!!!!!");

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

							//frame.getTextArea().append(novi + "\n");


							//model.setOblik(l);

						}


					}else if(o instanceof HexagonAdapter) { 


						System.out.println("HexagonAdapter!!!!!");
						DlgOsobineKruga osobineh = new DlgOsobineKruga();
						osobineh.setX(((HexagonAdapter) o).getHexagon().getX());
						osobineh.setY(((HexagonAdapter) o).getHexagon().getY());
						osobineh.setPoluprecnik(((HexagonAdapter) o).getHexagon().getR());
						osobineh.setBojaIvice(((HexagonAdapter) o).getHexagon().getBorderColor());
						osobineh.setBojaUnutrasnjosti(((HexagonAdapter) o).getHexagon().getAreaColor());

						osobineh.getTxtXKoordinata().setText(Integer.toString(osobineh.getX()));
						osobineh.getTxtYKoordinata().setText(Integer.toString(osobineh.getY()));
						osobineh.getTxtPoluprecnik().setText(Integer.toString(osobineh.getPoluprecnik()));
						osobineh.getEdpBojaIvice().setBackground(osobineh.getBojaIvice());
						osobineh.getEdpBojaUnutrasnjosti().setBackground(osobineh.getBojaUnutrasnjosti());
						osobineh.setVisible(true);

						if(osobineh.isVisible() == false) {
							
							Hexagon hexagon = new Hexagon(osobineh.getX(),osobineh.getY(),osobineh.getPoluprecnik());
							hexagon.setAreaColor(osobineh.getBojaUnutrasnjosti());
							hexagon.setBorderColor(osobineh.getBojaIvice());
							hexagon.setSelected(true);
							
							HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon);
							CmdUpdateShape cmdUpdate = new CmdUpdateShape(o,hexagonAdapter);
							cmdUpdate.execute();

							cmdUndoRedo1.addToCommandList(cmdUpdate);
							
					



					} 



				}


			}
				}

				
				







		}
		
		checkIfSelectedShapeExists();

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

		/*model.getUnselectedShapes().removeAll(model.getUnselectedShapes());
		for(int i =0; i<model.getListaObjekata().size(); i++) {
			if(model.getListaObjekata().get(i).isSelektovan() == true) {
				model.getUnselectedShapes().add(model.getListaObjekata().get(i));
				countForRedo++;
			}
		}*/

		//SELEKCIJA I CRTANJE
		if(model.getOdabranOblik() == ""){

			int m =0;


			for(int i=0; i<model.getListaObjekata().size(); i++) {

				if(model.getListaObjekata().get(i).sadrzi(x, y)) {

					m++;
				}
			}

			if(m>1) {
				//selektuje poslednji
				Stack<Oblik> stackOblik = new Stack<Oblik>();

				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).sadrzi(x, y)) {

						stackOblik.push(model.getListaObjekata().get(i));
					}
				}


				if(stackOblik.peek().isSelektovan() == false) {

					//model.getUnselectedShapes().removeAll(model.getUnselectedShapes());

					//if(stackOblik.peek() instanceof HexagonAdapter) {

						/*System.out.println("HEXAGON ADAPTER!!!!!!!!!!!!");
						Hexagon hexagon = new Hexagon(((HexagonAdapter) stackOblik.peek()).getHexagon().getX(),((HexagonAdapter) stackOblik.peek()).getHexagon().getY(),((HexagonAdapter) stackOblik.peek()).getHexagon().getR());
						hexagon.setAreaColor(((HexagonAdapter) stackOblik.peek()).getHexagon().getAreaColor());
						hexagon.setBorderColor(((HexagonAdapter) stackOblik.peek()).getHexagon().getBorderColor());
						HexagonAdapter q = new HexagonAdapter(hexagon);
						q.setSelektovan(stackOblik.peek().isSelektovan());
						Hexagon hexagon2 = new Hexagon(((HexagonAdapter) stackOblik.peek()).getHexagon().getX(),((HexagonAdapter) stackOblik.peek()).getHexagon().getY(),((HexagonAdapter) stackOblik.peek()).getHexagon().getR());
						hexagon2.setAreaColor(((HexagonAdapter) stackOblik.peek()).getHexagon().getAreaColor());
						hexagon2.setBorderColor(((HexagonAdapter) stackOblik.peek()).getHexagon().getBorderColor());
						HexagonAdapter s = new HexagonAdapter(hexagon2);
						s.setSelektovan(true);
						CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
						cmdUpdate.execute(); 
						cmdUndoRedo1.addToCommandList(cmdUpdate);*/
						
						CmdSelectShape cmdSelectShape = new CmdSelectShape(model,stackOblik.peek());
						cmdSelectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdSelectShape);
						
						
						//frame.getTextArea().append("Selected: " + s.toString() +"\n");

					//} else {


						/*Oblik q = CopyShape(stackOblik.peek());
						Oblik s = CopyShape(stackOblik.peek());
						s.setSelektovan(true);
						CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
						cmdUpdate.execute(); 
						cmdUndoRedo1.addToCommandList(cmdUpdate);
						//model.getCommands().add(s);
						frame.getTextArea().append("Selected: " + s.toString() +"\n");
						selectedShapes.add(s);*/





					//}



					position++;
					/*button.setStatus(true);
					frame.getBtnRedo().setEnabled(false);*/
					

				}


				stackOblik.removeAllElements();




			} else if (m==1 || m==0) {

				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).sadrzi(x, y)) {

						if(model.getListaObjekata().get(i).isSelektovan() == false) {

							//model.getUnselectedShapes().removeAll(model.getUnselectedShapes());

							//if(model.getListaObjekata().get(i) instanceof HexagonAdapter) {

								/*System.out.println("HEXAGON ADAPTER!!!!!!!!!!!!");
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
								//model.addToStackUndo(s);
								CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
								cmdUpdate.execute(); 
								cmdUndoRedo1.addToCommandList(cmdUpdate);
								//model.getCommands().add(s);
								frame.getTextArea().append("Selected: " + s.toString() +"\n");*/
								
								CmdSelectShape cmdSelectShape = new CmdSelectShape(model,model.getListaObjekata().get(i));
								cmdSelectShape.execute();
								cmdUndoRedo1.addToCommandList(cmdSelectShape);

							//} else {



								/*Oblik q = CopyShape(model.getListaObjekata().get(i));
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
								frame.getTextArea().append("Selected: " + s.toString() +"\n");
								selectedShapes.add(s);*/



							//}



							position++;
							/*button.setStatus(true);
							frame.getBtnRedo().setEnabled(false);*/


						}
					}
				}




			}




			/*for(int i=0; i<model.getListaObjekata().size(); i++) {
				//selektovanje oblika na koji je kliknut
				if(model.getListaObjekata().get(i).sadrzi(x, y)){
					if(model.getListaObjekata().get(i).isSelektovan() == false) {
						//model.getUnselectedShapes().removeAll(model.getUnselectedShapes());
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
							frame.getTextArea().append("Selected: " + s.toString() +"\n");
						} else {
							if(m==1) {
								System.out.println("Već neki sadrži");
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
								frame.getTextArea().append("Selected: " + s.toString() +"\n");
								selectedShapes.add(s);
							}
						}
						position++;
						button.setStatus(true);
						frame.getBtnRedo().setEnabled(false);
						/*if(m>1) { // sleketovanje poslednje nacrtanog
							System.out.println("Selektovano je vise oblika!");
							for(int k=0; k<model.getListaObjekata().size(); k++) {
								if(!model.getListaObjekata().get(k).equals(cmdUndoRedo1.getUndo().peek())) {
									model.getListaObjekata().get(k).setSelektovan(false);
									frame.getTextArea().append(model.getListaObjekata().get(k) +"\n"); //KAD SE ODSELEKTUJEE!
								}
							}
						} 
					}
				} 
			}*/





			//klikom na panel deselktuju se svi oblici
			
			if(m==0) {
				
				ArrayList<Oblik> ss = new ArrayList<Oblik>();
				int k=0;
				System.out.println("Ne sadrzi nijedan oblik!");
				n=true;

				for(Oblik o: model.getListaObjekata()) {

					if(o.isSelektovan() == true) {

						ss.add(o);
						k++;

						/*Oblik q = CopyShape(o);
						m++;
						Oblik s = CopyShape(o);
						s.setSelektovan(false);
						CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
						cmdUpdate.execute(); 
						cmdUndoRedo1.addToCommandList(cmdUpdate);
						frame.getTextArea().append("Deselected " + s.toString() +"\n");
						button.setStatus(false);*/
					}



				}

				if(k>1) {


					CmdUpdateSelectedShapes cmdUpdate = new CmdUpdateSelectedShapes(model,ss);
					cmdUpdate.execute();
					cmdUndoRedo1.addToCommandList(cmdUpdate);

				} else if (k==1) {

					/*Oblik q = CopyShape(ss.get(0));
					Oblik s = CopyShape(ss.get(0));
					s.setSelektovan(false);
					CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
					cmdUpdate.execute(); 
					cmdUndoRedo1.addToCommandList(cmdUpdate);
					frame.getTextArea().append("Deselected: " + s.toString() +"\n");*/
					
					CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model,ss.get(0));
					cmdDeselectShape.execute();
					cmdUndoRedo1.addToCommandList(cmdDeselectShape);
					//button.setStatus(false);
				}



			}




			lastAction = "Selekcija";
			/*frame.getBtnUndo().setEnabled(true);
			checkIfSelectedShapeExists();*/



		} else {

			//for(int i =0; i<model.getListaObjekata().size(); i++) {
				
				ArrayList<Oblik> ss = new ArrayList<Oblik>();
				int k=0;
				//System.out.println("Ne sadrzi nijedan oblik!");
				n=true;

				for(Oblik o: model.getListaObjekata()) {

					if(o.isSelektovan() == true) {

						ss.add(o);
						k++;

						/*Oblik q = CopyShape(o);
						m++;
						Oblik s = CopyShape(o);
						s.setSelektovan(false);
						CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
						cmdUpdate.execute(); 
						cmdUndoRedo1.addToCommandList(cmdUpdate);
						frame.getTextArea().append("Deselected " + s.toString() +"\n");
						button.setStatus(false);*/
					}



				}

				if(k>1) {


					CmdUpdateSelectedShapes cmdUpdate = new CmdUpdateSelectedShapes(model,ss);
					cmdUpdate.execute();
					cmdUndoRedo1.addToCommandList(cmdUpdate);

					/*for(int i=0; i<ss.size(); i++) {
						frame.getTextArea().append("Deselected multiple shapes: " + ss.get(i).toString() +"\n");
					}*/

					

				} else if (k==1) {

					/*Oblik q = CopyShape(ss.get(0));
					Oblik s = CopyShape(ss.get(0));
					s.setSelektovan(false);
					CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
					cmdUpdate.execute(); 
					cmdUndoRedo1.addToCommandList(cmdUpdate);
					frame.getTextArea().append("Deselected: " + s.toString() +"\n");*/
					
					CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model,ss.get(0));
					cmdDeselectShape.execute();
					cmdUndoRedo1.addToCommandList(cmdDeselectShape);
				
					//button.setStatus(false);
				}
				
				
				

				/*if(model.getListaObjekata().get(i) instanceof HexagonAdapter) {
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
					frame.getTextArea().append("Deselected " + s.toString() +"\n");
				} else {
					if(model.getListaObjekata().get(i).isSelektovan() == true) {
						Oblik q = CopyShape(model.getListaObjekata().get(i));
						Oblik s = CopyShape(model.getListaObjekata().get(i));
						s.setSelektovan(false);
						CmdUpdateShape cmdUpdate = new CmdUpdateShape(q,s);
						cmdUpdate.execute(); 
						cmdUndoRedo1.addToCommandList(cmdUpdate);
						//model.getCommands().add(s);
						frame.getTextArea().append("Deselected " + s.toString() +"\n");
					}
				}*/




			//}



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

			//frame.getTextArea().append("Drawing: " + t.toString() +"\n");
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
					//frame.getBtnSelektuj().setEnabled(true);

					//frame.getTextArea().append("Drawing: " + h.toString() +"\n");




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
				//frame.getBtnSelektuj().setEnabled(true);

				//frame.getTextArea().append("Drawing: " + l.toString() +"\n");

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
							//frame.getBtnSelektuj().setEnabled(true);

							//frame.getTextArea().append("Drawing: " + p.toString() +"\n");




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
					//frame.getBtnSelektuj().setEnabled(true);

					//frame.getTextArea().append("Drawing: " + kv.toString() +"\n");

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
					//frame.getBtnSelektuj().setEnabled(true);

					//frame.getTextArea().append("Drawing: " + kr.toString() +"\n");







				} else {

					JOptionPane.showMessageDialog(null, "Niste dobro uneli polupre�?nik kruga!");
				}

			} catch (NumberFormatException k){

				JOptionPane.showMessageDialog(null, "Niste dobro uneli polupre�?nik kruga!");


			} catch(NullPointerException k){

				JOptionPane.showConfirmDialog(null, "Niste uneli polupre�?nik kruga!");
			}



		}
		
		checkIfSelectedShapeExists();
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
		
		checkIfSelectedShapeExists();




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
		
		checkIfSelectedShapeExists();






		//cmdUndoRedo.execute();


	}



	public void moveToFront() {

		checkIfSelectedShapeExists();
		Oblik o=null;

		for(int i=0; i<model.getListaObjekata().size(); i++) {

			if(model.getListaObjekata().get(i).isSelektovan() == true) {
				
				

				if((i+1)<model.getListaObjekata().size()) {
					 o = model.getListaObjekata().get(i);
					 
					 CmdToFront cmdToFront = new CmdToFront(model,model.getListaObjekata().get(i));
					 cmdToFront.execute();
					 cmdUndoRedo1.addToCommandList(cmdToFront);
					 //Collections.swap(model.getListaObjekata(), i, i+1); 
					 i=model.getListaObjekata().size()-1;
					


				}


			}

		}
		
		checkIfSelectedShapeExists();
		
		/*if(o!=null) {
			
			frame.getTextArea().append("Move to front: " + o +"\n");
		}*/
		
		




	}


	public void bringToFront() {

		checkIfSelectedShapeExists();
		Oblik o=null;


		for(int i=0; i<model.getListaObjekata().size(); i++) {

			if(model.getListaObjekata().get(i).isSelektovan() == true) {

				if((i+1)<model.getListaObjekata().size()) {
					
					o=model.getListaObjekata().get(i);
							
					CmdBringToFront cmdBringToFront = new CmdBringToFront(model,model.getListaObjekata().get(i));
					cmdBringToFront.execute();
					cmdUndoRedo1.addToCommandList(cmdBringToFront);
					//Collections.swap(model.getListaObjekata(), i, model.getListaObjekata().size()-1); 
					//i=model.getListaObjekata().size()-1;
					


				}


			}

		}
		
		checkIfSelectedShapeExists();
		
		/*if(o!=null) {
			
			frame.getTextArea().append("Bring to front: " + o +"\n");
		}*/



	}

	public void moveToBack() {

		checkIfSelectedShapeExists();
		Oblik o=null;

		for(int i=0; i<model.getListaObjekata().size(); i++) {

			if(model.getListaObjekata().get(i).isSelektovan() == true) {

				
				if(i>0) {
					
					o=model.getListaObjekata().get(i);
					CmdToBack cmdToBack = new CmdToBack(model,model.getListaObjekata().get(i));
					cmdToBack.execute();
					cmdUndoRedo1.addToCommandList(cmdToBack);
					//Collections.swap(model.getListaObjekata(), i, i-1); 
					

				}


			}

		}
		
		checkIfSelectedShapeExists();
		/*if(o!=null) {
			
			frame.getTextArea().append("Move to back: " + o +"\n");
		}*/
		
		




	}

	public void bringToBack() {

		checkIfSelectedShapeExists();

		Oblik o = null;
		for(int i=0; i<model.getListaObjekata().size(); i++) {

			if(model.getListaObjekata().get(i).isSelektovan() == true) {
				
				
			
				if(i>0) {
					
					o = model.getListaObjekata().get(i);
					CmdBringToBack cmdBringToBack = new CmdBringToBack(model,model.getListaObjekata().get(i));
					cmdBringToBack.execute();

					cmdUndoRedo1.addToCommandList(cmdBringToBack);
					/*o = model.getListaObjekata().get(i);
					Collections.swap(model.getListaObjekata(), i, 0); 
					i=0;*/
					


				}


			}

		}
		
		checkIfSelectedShapeExists();
		
		/*if(o!=null) {
			
			frame.getTextArea().append("Bring to back: " + o +"\n");
		}*/

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

			if(model.getListaObjekata().get(i).isSelektovan() == true) {

				n++;
			} 



		}
		
		if(n==1) {
			
			subject.setState(true);
		} else if (n==0) {
			
			subject.setState(false);
		} else if (n>1) {
			
			NaslovnaPokretanje.btnModifikuj.setEnabled(false);
		}
		
		return n;

		/*if(n==1) {
			button.setStatus(true);
			return n;
		} else if (n>1) {
			//button.setStatus(false);
			return n;
		} else {
			//button.setStatus(false);
			return n;
		}*/





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

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}








}