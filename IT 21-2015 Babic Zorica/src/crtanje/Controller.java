package crtanje;


import java.awt.Button;


import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

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
import strategy.LoadPainting;
import strategy.LogOperation;
import strategy.OpenOperation;
import strategy.SaveBin;
import strategy.SavePaintingOperation;



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

	private boolean justRead = false;




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
	
	public void loadPainting(File f) {
		
		context = new Context(new LoadPainting());
		context.executeStrategy(frame, f);
	}
	
	public void saveBin(File f) {
		
		context = new Context(new SaveBin());
		context.executeStrategy(frame, f);
	}

	public void runCommandByCommand(String line,String lineBefore) {
		
		
		CmdAddShape.print = false;
		CmdBringToBack.print = false;
		CmdBringToFront.print = false;
		
		CmdDeselectShape.print = false;
		CmdRemoveShape.print = false;
		CmdSelectShape.print = false;
		CmdToBack.print = false;
		CmdToFront.print = false;
		CmdUpdateSelectedShapes.print = false;
		CmdUpdateShape.print = false;
		
		
		if (line.contains("UNDO >>> Selected: ")) {
			
			CmdDeselectShape.print=false;
			undo();
			
		}else if(line.contains("UNDO >>> Removed:")) {

			//Removed: Circle: (403,143), radius: 50, outline: #000000, fill: #ffffff, Selected? false
			CmdAddShape.setPrint(false);
			undo();


		} else if(line.contains("UNDO >>> Deselected: ")) {
			
			CmdSelectShape.print = false;
			undo();
			
			
		}  else if(line.contains("UNDO >>> Bring to front: ")) {
			
			CmdBringToBack.print = false;
			undo();
			
		} else if(line.contains("UNDO >>> Bring to back: ")) {
			
			CmdBringToFront.print = false;
			undo();
		}  else if(line.contains("UNDO >>> Move to front: ")) {
			
			CmdToBack.print=false;
			undo();
		}  else if (line.contains("UNDO >>> Move to back: ")) {
			
			CmdToFront.print=false;
			undo();
			
		}  else if(line.contains("UNDO >>> Multiple shapes added: ")) {
			
			
			
			CmdRemoveShape.print = false;
			undo();
			
			
		} else if (line.contains("UNDO >>> Added:")){
			
			
			CmdRemoveShape.print = false;
			undo();
			
		} else if (line.contains("UNDO >>> Multiple shapes selected: ")) {
			
			CmdUpdateSelectedShapes.print = false;
			undo();
			
			
		} else if (line.contains("UNDO >>> Modified: ")) {
			
			CmdUpdateShape.print = false;
			undo();
		} else if(line.contains("Added:")) {

			if(line.contains("Circle")) {

				setOdabranOblik("Krug");
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Circle: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];
				String radius = between(line, "radius: ",", outline");


				model.setBojaUnutrasnjosti(stringToColor(fill));
				model.setBojaIvice(stringToColor(outline));
				model.setR(Integer.parseInt(radius));
				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));

				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));


			} else if (line.contains("Square")) {



				setOdabranOblik("Kvadrat");
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Square: (",")");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();
				String width = between(line, "width: ",", outline");


				model.setBojaUnutrasnjosti(stringToColor(fill));
				model.setBojaIvice(stringToColor(outline));
				model.setDuzinaStranice(Integer.parseInt(width));
				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));
				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));


			} else if(line.contains("Rectangle")){


				setOdabranOblik("Pravougaonik");
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Rectangle: (",")");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();
				String width = between(line, "width: ",", height");
				String height = between(line,"height: ",", outline");


				model.setBojaUnutrasnjosti(stringToColor(fill));
				model.setBojaIvice(stringToColor(outline));
				model.setDuzina(Integer.parseInt(width));
				model.setSirina(Integer.parseInt(height));
				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));
				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));


			} else if(line.contains("Line")) {

				//Added: Line: startPoint (272,100), endPoint (208,91), outline: #000000, Selected? false
				setOdabranOblik("Linija");
				String outline = between(line, "outline: ", ", Selected");
				//startpoint
				String s = between(line, "startPoint (", "), endPoint");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();

				//endPoint

				String sa = between(line, "endPoint (", "), outline:");
				String[] myStrings = sa.split(",");
				String newX = myStrings[0].trim();
				String newY = myStrings[1].trim();

				model.setBojaIvice(stringToColor(outline));

				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));
				model.setNovoX(Integer.parseInt(newX));
				model.setNovoY(Integer.parseInt(newY));

		;

				model.setDvaKlika(true);
				justRead=true;
				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));


			} else if (line.contains("Hexagon")) {

				//Added: Hexagon: (130,144), radius: 50, outline: #000000, fill: #ffffff, Selected? false  

				setOdabranOblik("Hexagon");
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Hexagon: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];
				String radius = between(line, "radius: ",", outline");


				model.setBojaUnutrasnjosti(stringToColor(fill));
				model.setBojaIvice(stringToColor(outline));
				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));
				model.setR(Integer.parseInt(radius));

				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));

			} else if (line.contains("Point")) {

				//Added: Point: (681,232), outline: #000000, Selected? false

				setOdabranOblik("Tacka");
				String outline = between(line, "outline: ", ", Selected?");

				String s = between(line, "Point: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];




				model.setBojaIvice(stringToColor(outline));
				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));


				justRead=true;
				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));
			}
			
			
		}  else if(line.contains("Selected:")) {
			
			CmdSelectShape.print = false;
			//Selected: Circle: (398,139), radius: 50, outline: #000000, fill: #ffffff, Selected? true

			if(line.contains("Circle")) {

				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Circle: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];
				String radius = between(line, "radius: ",", outline");

				Krug k = new Krug(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(radius),stringToColor(outline),stringToColor(fill));
				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(k)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model,model.getListaObjekata().get(i));
						cmdSelectShape.print=false;
						cmdSelectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdSelectShape);
					}
				}
			} else if (line.contains("Hexagon")) {

				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Hexagon: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];
				String radius = between(line, "radius: ",", outline");

				Hexagon h = new Hexagon(Integer.parseInt(x),Integer.parseInt(y),Integer.parseInt(radius));
				h.setAreaColor(stringToColor(fill));
				h.setBorderColor(stringToColor(outline));
				HexagonAdapter ha = new HexagonAdapter(h);

				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(ha)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model,model.getListaObjekata().get(i));
						cmdSelectShape.print=false;
						cmdSelectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdSelectShape);

					}
				}


			} else if(line.contains("Line")) {
				
				String outline = between(line, "outline: ", ", Selected");
				//startpoint
				String s = between(line, "startPoint (", "), endPoint");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();

				//endPoint

				String sa = between(line, "endPoint (", "), outline:");
				String[] myStrings = sa.split(",");
				String newX = myStrings[0].trim();
				String newY = myStrings[1].trim();
				
				Linija l = new Linija(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),new Tacka(Integer.parseInt(newX),Integer.parseInt(newY)), stringToColor(outline));
				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(l)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model,model.getListaObjekata().get(i));
						cmdSelectShape.print=false;
						cmdSelectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdSelectShape);

					}
				}
			} else if(line.contains("Point")) {
				
				//Selected: Line: startPoint (310,66), endPoint (555,67), outline: #000000, Selected? true


				setOdabranOblik("Tacka");
				String outline = between(line, "outline: ", ", Selected?");

				String s = between(line, "Point: (",")");
				String[] myString = s.split(",");
			
				String x = myString[0];
				String y = myString[1];
				
			

				
				

				Tacka t = new Tacka(Integer.parseInt(x),Integer.parseInt(y),stringToColor(outline));
				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(t)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model,model.getListaObjekata().get(i));
						cmdSelectShape.print=false;
						cmdSelectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdSelectShape);

					}
				}


			} else if (line.contains("Rectangle")) {
				
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Rectangle: (",")");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();
				String width = between(line, "width: ",", height");
				String height = between(line,"height: ",", outline");
				
				Pravougaonik p = new Pravougaonik(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),Integer.parseInt(height),stringToColor(outline),stringToColor(fill));
				
				
				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(p)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model,model.getListaObjekata().get(i));
						cmdSelectShape.print=false;
						cmdSelectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdSelectShape);

					}
				}
			} else if (line.contains("Square")) {
				
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Square: (",")");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();
				String width = between(line, "width: ",", outline");
				
				Kvadrat k = new Kvadrat(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),stringToColor(outline),stringToColor(fill));
				
				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(k)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model,model.getListaObjekata().get(i));
						cmdSelectShape.print=false;
						cmdSelectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdSelectShape);

					}
				}

			}



		}  else if(line.contains("Bring to back: ")) { //mozda će biti problem zbog containt UNDOO >> BRING TO BACK
			
			
			CmdBringToBack.print = false;
			bringToBack();
			
			
		} else if(line.contains("Bring to front: ")) {
			
			CmdBringToFront.print = false;
			bringToFront();
		}  else if(line.contains("Move to back: ")) {
			
			CmdToBack.print = false;
			moveToBack();
			
		} else if(line.contains("Move to front: ")) {
			
			CmdToFront.print=false;
			moveToFront();
		}  else if(line.contains("Deselected: ") && !line.contains("Multiple")) {
			
			CmdDeselectShape.print = false;
			//Selected: Circle: (398,139), radius: 50, outline: #000000, fill: #ffffff, Selected? true

			if(line.contains("Circle")) {

				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Circle: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];
				String radius = between(line, "radius: ",", outline");

				Krug k = new Krug(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(radius),stringToColor(outline),stringToColor(fill));
				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(k)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model,model.getListaObjekata().get(i));
						cmdDeselectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdDeselectShape);
					}
				}
			} else if (line.contains("Hexagon")) {

				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Hexagon: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];
				String radius = between(line, "radius: ",", outline");

				Hexagon h = new Hexagon(Integer.parseInt(x),Integer.parseInt(y),Integer.parseInt(radius));
				h.setAreaColor(stringToColor(fill));
				h.setBorderColor(stringToColor(outline));
				HexagonAdapter ha = new HexagonAdapter(h);

				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(ha)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model,model.getListaObjekata().get(i));
						cmdDeselectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdDeselectShape);

					}
				}


			} else if(line.contains("Line")) {
				
				String outline = between(line, "outline: ", ", Selected");
				//startpoint
				String s = between(line, "startPoint (", "), endPoint");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();

				//endPoint

				String sa = between(line, "endPoint (", "), outline:");
				String[] myStrings = sa.split(",");
				String newX = myStrings[0].trim();
				String newY = myStrings[1].trim();
				
				Linija l = new Linija(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),new Tacka(Integer.parseInt(newX),Integer.parseInt(newY)), stringToColor(outline));
				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(l)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model,model.getListaObjekata().get(i));
						cmdDeselectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdDeselectShape);

					}
				}
			} else if(line.contains("Point")) {
				
				//Selected: Line: startPoint (310,66), endPoint (555,67), outline: #000000, Selected? true


				setOdabranOblik("Tacka");
				String outline = between(line, "outline: ", ", Selected?");

				String s = between(line, "Point: (",")");
				String[] myString = s.split(",");
			
				String x = myString[0];
				String y = myString[1];
				
			

				
				

				Tacka t = new Tacka(Integer.parseInt(x),Integer.parseInt(y),stringToColor(outline));
				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(t)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model,model.getListaObjekata().get(i));
						cmdDeselectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdDeselectShape);

					}
				}


			} else if (line.contains("Rectangle")) {
				
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Rectangle: (",")");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();
				String width = between(line, "width: ",", height");
				String height = between(line,"height: ",", outline");
				
				Pravougaonik p = new Pravougaonik(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),Integer.parseInt(height),stringToColor(outline),stringToColor(fill));
				
				
				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(p)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model,model.getListaObjekata().get(i));
						cmdDeselectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdDeselectShape);

					}
				}
			} else if (line.contains("Square")) {
				
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Square: (",")");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();
				String width = between(line, "width: ",", outline");
				
				Kvadrat k = new Kvadrat(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),stringToColor(outline),stringToColor(fill));
				
				for(int i=0; i<model.getListaObjekata().size(); i++) {

					if(model.getListaObjekata().get(i).equals(k)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model,model.getListaObjekata().get(i));
						cmdDeselectShape.execute();
						cmdUndoRedo1.addToCommandList(cmdDeselectShape);

					}
				}

			}
			
			//uzmem oblik
		} else if (line.contains("Removed: ") && !line.contains("Multiple")) {
			
			
			CmdRemoveShape.print = false;
			delete();
			
		}  else if(line.contains("Multiple shapes removed:")){
			
			CmdRemoveShape.print = false;
			delete();
			
		}  else if (line.contains("Multiple shapes deselected: ")) {
			
			CmdUpdateSelectedShapes.print = false;
			
			ArrayList<Oblik> ss = new ArrayList<Oblik>();
			int k=0;
			n=true;

			for(Oblik o: model.getListaObjekata()) {

				if(o.isSelektovan() == true) {

					ss.add(o);
					k++;
				}

			}

			if(k>1) {


				CmdUpdateSelectedShapes cmdUpdate = new CmdUpdateSelectedShapes(model,ss);
				cmdUpdate.execute();
				cmdUndoRedo1.addToCommandList(cmdUpdate);

			} else if (k==1) {


				CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model,ss.get(0));
				cmdDeselectShape.execute();
				cmdUndoRedo1.addToCommandList(cmdDeselectShape);

			}
			
			
		} else if (line.contains("Modified: ")){
			
			if(lineBefore != null) {
				
				CmdUpdateShape.print = false;
				//Selected: Circle: (398,139), radius: 50, outline: #000000, fill: #ffffff, Selected? true

				if(line.contains("Circle") && lineBefore.contains("Circle")) {
					
					
					String outline1 = between(lineBefore, "outline: ", ", fill:");
					String fill1 = between(lineBefore, "fill: ",", Selected");
					String s1 = between(lineBefore, "Circle: (",")");
					String[] myString1 = s1.split(",");
					String x1 = myString1[0];
					String y1 = myString1[1];
					String radius1 = between(lineBefore, "radius: ",", outline");
					
					Krug k1 = new Krug(new Tacka(Integer.parseInt(x1),Integer.parseInt(y1)),Integer.parseInt(radius1),stringToColor(outline1),stringToColor(fill1));
					k1.setSelektovan(true);
			
					String outline = between(line, "outline: ", ", fill:");
					String fill = between(line, "fill: ",", Selected");
					String s = between(line, "Circle: (",")");
					String[] myString = s.split(",");
					String x = myString[0];
					String y = myString[1];
					String radius = between(line, "radius: ",", outline");

					Krug k2 = new Krug(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(radius),stringToColor(outline),stringToColor(fill));
					k2.setSelektovan(true);
					for(int i=0; i<model.getListaObjekata().size(); i++) {

						if(model.getListaObjekata().get(i).equals(k1)) {

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListaObjekata().get(i),k2);
							cmdUpdate.execute();
							cmdUndoRedo1.addToCommandList(cmdUpdate);
						}
					}
				} else if (line.contains("Hexagon") && lineBefore.contains("Hexagon")) {

					String outline1 = between(lineBefore, "outline: ", ", fill:");
					String fill1 = between(lineBefore, "fill: ",", Selected");
					String s1 = between(lineBefore, "Hexagon: (",")");
					String[] myString1 = s1.split(",");
					String x1 = myString1[0];
					String y1 = myString1[1];
					String radius1 = between(lineBefore, "radius: ",", outline");
					
					Hexagon h1 = new Hexagon(Integer.parseInt(x1),Integer.parseInt(y1),Integer.parseInt(radius1));
					h1.setAreaColor(stringToColor(fill1));
					h1.setBorderColor(stringToColor(outline1));
					HexagonAdapter ha1 = new HexagonAdapter(h1);
					ha1.setSelektovan(true);
					
					String outline = between(line, "outline: ", ", fill:");
					String fill = between(line, "fill: ",", Selected");
					String s = between(line, "Hexagon: (",")");
					String[] myString = s.split(",");
					String x = myString[0];
					String y = myString[1];
					String radius = between(line, "radius: ",", outline");

					Hexagon h = new Hexagon(Integer.parseInt(x),Integer.parseInt(y),Integer.parseInt(radius));
					h.setAreaColor(stringToColor(fill));
					h.setBorderColor(stringToColor(outline));
					HexagonAdapter ha = new HexagonAdapter(h);
					ha.setSelektovan(true);

					for(int i=0; i<model.getListaObjekata().size(); i++) {

						if(model.getListaObjekata().get(i).equals(ha1)) {

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListaObjekata().get(i),ha);
							cmdUpdate.execute();
							cmdUndoRedo1.addToCommandList(cmdUpdate);

						}
					}


				} else if(line.contains("Line") && lineBefore.contains("Line")) {
					
					String outline1 = between(lineBefore, "outline: ", ", Selected");
					//startpoint
					String s1 = between(lineBefore, "startPoint (", "), endPoint");
					String[] myString1 = s1.split(",");
					String x1 = myString1[0].trim();
					String y1 = myString1[1].trim();
					
					//endPoint

					String sa1 = between(lineBefore, "endPoint (", "), outline:");
					String[] myStrings1 = sa1.split(",");
					String newX1 = myStrings1[0].trim();
					String newY1 = myStrings1[1].trim();
					
					
					Linija l1 = new Linija(new Tacka(Integer.parseInt(x1),Integer.parseInt(y1)),new Tacka(Integer.parseInt(newX1),Integer.parseInt(newY1)), stringToColor(outline1));
					l1.setSelektovan(true);
					
					

					String outline = between(line, "outline: ", ", Selected");
					//startpoint
					String s = between(line, "startPoint (", "), endPoint");
					String[] myString = s.split(",");
					String x = myString[0].trim();
					String y = myString[1].trim();
					
					//endPoint

					String sa = between(line, "endPoint (", "), outline:");
					String[] myStrings = sa.split(",");
					String newX = myStrings[0].trim();
					String newY = myStrings[1].trim();
					
					Linija l = new Linija(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),new Tacka(Integer.parseInt(newX),Integer.parseInt(newY)), stringToColor(outline));
					l.setSelektovan(true);
					
					for(int i=0; i<model.getListaObjekata().size(); i++) {

						if(model.getListaObjekata().get(i).equals(l1)) {

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListaObjekata().get(i),l);
							cmdUpdate.execute();
							cmdUndoRedo1.addToCommandList(cmdUpdate);

						}
					}
				} else if(line.contains("Point") && lineBefore.contains("Point")) {
					
					//Selected: Line: startPoint (310,66), endPoint (555,67), outline: #000000, Selected? true


					String outline1 = between(lineBefore, "outline: ", ", Selected?");

					String s1 = between(lineBefore, "Point: (",")");
					String[] myString1 = s1.split(",");
				
					String x1 = myString1[0];
					String y1 = myString1[1];
					
					Tacka t1 = new Tacka(Integer.parseInt(x1),Integer.parseInt(y1),stringToColor(outline1));
					t1.setSelektovan(true);

					
					String outline = between(line, "outline: ", ", Selected?");
					String s = between(line, "Point: (",")");
					String[] myString = s.split(",");
				
					String x = myString[0];
					String y = myString[1];
					
				

					
					

					Tacka t = new Tacka(Integer.parseInt(x),Integer.parseInt(y),stringToColor(outline));
					t.setSelektovan(true);
					
					
					for(int i=0; i<model.getListaObjekata().size(); i++) {

						if(model.getListaObjekata().get(i).equals(t1)) {

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListaObjekata().get(i),t);
							cmdUpdate.execute();
							cmdUndoRedo1.addToCommandList(cmdUpdate);

						}
					}


				} else if (line.contains("Rectangle") && lineBefore.contains("Rectangle")) {
					
					String outline1 = between(lineBefore, "outline: ", ", fill:");
					String fill1 = between(lineBefore, "fill: ",", Selected");
					String s1 = between(lineBefore, "Rectangle: (",")");
					String[] myString1 = s1.split(",");
					String x1 = myString1[0].trim();
					String y1 = myString1[1].trim();
					String width1 = between(lineBefore, "width: ",", height");
					String height1 = between(lineBefore,"height: ",", outline");
					
					Pravougaonik p1 = new Pravougaonik(new Tacka(Integer.parseInt(x1),Integer.parseInt(y1)),Integer.parseInt(width1),Integer.parseInt(height1),stringToColor(outline1),stringToColor(fill1));
					p1.setSelektovan(true);
					
					
					
					String outline = between(line, "outline: ", ", fill:");
					String fill = between(line, "fill: ",", Selected");
					String s = between(line, "Rectangle: (",")");
					String[] myString = s.split(",");
					String x = myString[0].trim();
					String y = myString[1].trim();
					String width = between(line, "width: ",", height");
					String height = between(line,"height: ",", outline");
					
					Pravougaonik p = new Pravougaonik(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),Integer.parseInt(height),stringToColor(outline),stringToColor(fill));   
					p.setSelektovan(true);
					
					for(int i=0; i<model.getListaObjekata().size(); i++) {

						if(model.getListaObjekata().get(i).equals(p1)) {

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListaObjekata().get(i),p);
							cmdUpdate.execute();
							cmdUndoRedo1.addToCommandList(cmdUpdate);

						}
					}
				} else if (line.contains("Square") && lineBefore.contains("Square")) {
					
					String outline1 = between(lineBefore, "outline: ", ", fill:");
					String fill1 = between(lineBefore, "fill: ",", Selected");
					String s1 = between(lineBefore, "Square: (",")");
					String[] myString1 = s1.split(",");
					String x1 = myString1[0].trim();
					String y1 = myString1[1].trim();
					String width1 = between(lineBefore, "width: ",", outline");
					
					Kvadrat k1 = new Kvadrat(new Tacka(Integer.parseInt(x1),Integer.parseInt(y1)),Integer.parseInt(width1),stringToColor(outline1),stringToColor(fill1));
					k1.setSelektovan(true);
					
					
					
					String outline = between(line, "outline: ", ", fill:");
					String fill = between(line, "fill: ",", Selected");
					String s = between(line, "Square: (",")");
					String[] myString = s.split(",");
					String x = myString[0].trim();
					String y = myString[1].trim();
					String width = between(line, "width: ",", outline");
					
					Kvadrat k = new Kvadrat(new Tacka(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),stringToColor(outline),stringToColor(fill));
					k.setSelektovan(true);
					for(int i=0; i<model.getListaObjekata().size(); i++) {

						if(model.getListaObjekata().get(i).equals(k1)) {

							
							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListaObjekata().get(i),k);
							cmdUpdate.execute();
							cmdUndoRedo1.addToCommandList(cmdUpdate);
						}
					}

				}
			}
			
			
		}




	}

	static String between(String value, String a, String b) {
		// Return a substring between the two strings.
		int posA = value.indexOf(a);
		if (posA == -1) {
			return "";
		}
		int posB = value.lastIndexOf(b);
		if (posB == -1) {
			return "";
		}
		int adjustedPosA = posA + a.length();
		if (adjustedPosA >= posB) {
			return "";
		}
		return value.substring(adjustedPosA, posB);
	}


	public static Color stringToColor(final String value) {
		if (value == null) {
			return Color.black;
		}
		try {
			// get color by hex or octal value
			return Color.decode(value);
		} catch (NumberFormatException nfe) {
			// if we can't decode lets try to get it by name
			try {
				// try to get a color by name using reflection
				final Field f = Color.class.getField(value);

				return (Color) f.get(null);

			} catch (Exception ce) {
				// if we can't get any color return black
				return Color.black;
			}
		}
	}

	public void savePainting(File f) {

		context = new Context(new SavePaintingOperation());
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
				
				
				delete();

			}

		}


		checkIfSelectedShapeExists();
	}

	public void delete() {
		

		ArrayList<Oblik> oblici = new ArrayList<Oblik>();

		for(int i=0; i<model.getListaObjekata().size(); i++) {

				if(model.getListaObjekata().get(i).isSelektovan() == true) {

				
					oblici.add(model.getListaObjekata().get(i));

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

					

						DlgOsobinePravougaonika osobine = new DlgOsobinePravougaonika();


						osobine = new DlgOsobinePravougaonika();
						osobine.setDone(false);
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

						if(osobine.isVisible() == false && osobine.isDone() == true) {

							Pravougaonik novi = new Pravougaonik(new Tacka(osobine.getX(),osobine.getY()),osobine.getDuzina(),osobine.getSirina(),osobine.getBojaIvice(),osobine.getBojaUnutrasnjosti());
							novi.setSelektovan(true);

							Oblik s = CopyShape(novi);

							//model.addToStackUndo(s);

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(o,novi);

							cmdUpdate.execute();
							cmdUndoRedo1.addToCommandList(cmdUpdate);
							osobine.setDone(false);

							//frame.getTextArea().append(novi + "\n");



							//model.setOblik(l);

						}


					} else if(o instanceof Kvadrat){

					

						DlgOsobineKvadrata osobine = new DlgOsobineKvadrata();
						osobine.setDone(false);
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

						if(osobine.isVisible() == false && osobine.isDone() == true) {

							Kvadrat novi = new Kvadrat(new Tacka(osobine.getX(),osobine.getY()),osobine.getDuzinaStranice(),osobine.getBojaIvice(),osobine.getBojaUnutrasnjosti());
							novi.setSelektovan(true);

							Oblik s = CopyShape(novi);

							//model.addToStackUndo(s);

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(o,novi);

							cmdUpdate.execute();

							cmdUndoRedo1.addToCommandList(cmdUpdate);
							
							osobine.setDone(false);

							//frame.getTextArea().append(novi + "\n");


							//model.setOblik(l);

						}



					} else if (o instanceof Krug) {



						DlgOsobineKruga osobine = new DlgOsobineKruga();
						osobine.setTitle("Krug");
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



						if(osobine.isVisible() == false && osobine.isDone()==true) {

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
							osobine.setDone(false);

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

						

						DlgOsobineTacke osobine = new DlgOsobineTacke();
						osobine.setDone(false);
						osobine.setX(((Tacka) o).getX());
						osobine.setY(((Tacka) o).getY());
						osobine.setBojaIvice(o.getBojaIvice());


						osobine.getTxtXKoordinata().setText(Integer.toString(osobine.getX()));
						osobine.getTxtYKoordinata().setText(Integer.toString(osobine.getY()));
						osobine.getEdpBoja().setBackground(osobine.getBojaIvice());


						osobine.setVisible(true);
						
						if(osobine.isVisible() == false && osobine.isDone() == true) {
							
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
							osobine.setDone(false);
						}
						
						
						




					} else if(o instanceof Linija){

					

						DlgOsobineLinije osobine = new DlgOsobineLinije();
						osobine.setDone(false);
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



						if(osobine.isVisible() == false && osobine.isDone() == true) {

							Linija novi = new Linija(new Tacka(osobine.getxPocetna(),osobine.getyPocetna()), new Tacka (osobine.getxKrajnja(),osobine.getyKrajnja()),osobine.getBojaIvice());
							novi.setSelektovan(true);

							Oblik s = CopyShape(novi);

							//model.addToStackUndo(s);

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(o,novi);
							cmdUndoRedo1.addToCommandList(cmdUpdate);

							cmdUpdate.execute();
							
							osobine.setDone(false);

							//frame.getTextArea().append(novi + "\n");


							//model.setOblik(l);

						}


					}else if(o instanceof HexagonAdapter) { 


					
						DlgOsobineKruga osobineh = new DlgOsobineKruga();
						osobineh.setTitle("Hexagon");
						osobineh.setDone(false);
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

						if(osobineh.isVisible() == false && osobineh.isDone() == true) {

							Hexagon hexagon = new Hexagon(osobineh.getX(),osobineh.getY(),osobineh.getPoluprecnik());
							hexagon.setAreaColor(osobineh.getBojaUnutrasnjosti());
							hexagon.setBorderColor(osobineh.getBojaIvice());
							hexagon.setSelected(true);

							HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon);
							CmdUpdateShape cmdUpdate = new CmdUpdateShape(o,hexagonAdapter);
							cmdUpdate.execute();

							cmdUndoRedo1.addToCommandList(cmdUpdate);
							
							osobineh.setDone(false);





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

			if(justRead==false) {

				model.setX(x);
				model.setY(y);
				//view.repaint();

				Tacka t = new Tacka(model.getX(),model.getY());
				t.setBojaIvice(model.getBojaIvice());


				CmdAddShape cmdAddShape = new CmdAddShape(model,t);

				cmdAddShape.execute();


				/*Oblik l = CopyShape(t);
				model.addToStackUndo(l);*/

				cmdUndoRedo1.addToCommandList(cmdAddShape);

				frame.getBtnRedo().setEnabled(false);
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnSelektuj().setEnabled(true);

				//frame.getTextArea().append("Drawing: " + t.toString() +"\n");
				//view.repaint();
			} else {


				Tacka t = new Tacka(model.getX(),model.getY());
				t.setBojaIvice(model.getBojaIvice());


				CmdAddShape cmdAddShape = new CmdAddShape(model,t);
				cmdAddShape.setPrint(false);

				cmdAddShape.execute();


				/*Oblik l = CopyShape(t);
				model.addToStackUndo(l);*/

				cmdUndoRedo1.addToCommandList(cmdAddShape);

				frame.getBtnRedo().setEnabled(false);
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnSelektuj().setEnabled(true);

				//frame.getTextArea().append("Drawing: " + t.toString() +"\n");
				//view.repaint();

				justRead=false;
			}




		}

		if(model.getOdabranOblik() == "Hexagon") {

			s=true;

			String s=null;

			if(model.getR() == -1) {

				

				try{
					
					s=JOptionPane.showInputDialog("Unesi duzinu poluprecnika hexagona");
			
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
						
						JOptionPane.showMessageDialog(null, "Niste dobro uneli poluprecnik hexagona!");
					}

				} catch (Exception e){
					
						if(s!=null) {
							
							JOptionPane.showMessageDialog(null, "Niste dobro uneli poluprecnik hexagona!");
						}
						
						
					
				}


			} else {
				
				
					int r = model.getR();
					if(r > 0){

						Hexagon hexagon = new Hexagon(model.getX(),model.getY(),model.getR());
						hexagon.setAreaColor(model.getBojaUnutrasnjosti());
						hexagon.setBorderColor(model.getBojaIvice());
						HexagonAdapter h = new HexagonAdapter(hexagon);
						h.setSelektovan(false);



						//HexagonAdapter h = new HexagonAdapter(new Tacka(model.getX(),model.getY()),model.getR(),model.getBojaIvice(),model.getBojaUnutrasnjosti());

						CmdAddShape cmdAddShape = new CmdAddShape(model,h);
						cmdAddShape.setPrint(false);
						cmdAddShape.execute();
						cmdUndoRedo1.addToCommandList(cmdAddShape);



						frame.getBtnRedo().setEnabled(false);
						frame.getBtnUndo().setEnabled(true);




					} else {

						JOptionPane.showMessageDialog(null, "Niste dobro uneli polupre�?nik kruga!");
					}

			
			}


			model.setR(-1);

		}

		if(model.getOdabranOblik() == "Linija"){


			if(model.isDvaKlika() == false){

				model.setX(x);
				model.setY(y);
				model.setDvaKlika(true);


			} else {

				if(justRead == false) {

					model.setNovoX(x);
					model.setNovoY(y);

				} 


				//view.repaint();
				Linija l = new Linija(new Tacka(model.getX(),model.getY()), new Tacka(model.getNovoX(),model.getNovoY()));
				l.setBojaIvice(model.getBojaIvice());

				CmdAddShape cmdAddShape = new CmdAddShape(model,l);

				if(justRead==true) {

					cmdAddShape.setPrint(false);
				}

				cmdAddShape.execute();



				cmdUndoRedo1.addToCommandList(cmdAddShape);

				/*Oblik k = CopyShape(l);
				model.addToStackUndo(k);*/



				frame.getBtnRedo().setEnabled(false);
				frame.getBtnUndo().setEnabled(true);
				//frame.getBtnSelektuj().setEnabled(true);

				//frame.getTextArea().append("Drawing: " + l.toString() +"\n");

				model.setDvaKlika(false);
				justRead=false;
			}

		}

		if(model.getOdabranOblik() == "Pravougaonik"){

			String d=null;
			String s=null;
			if(model.getDuzina() == -1) {

				
				
				try{
					
					
					d=JOptionPane.showInputDialog("Unesi duzinu pravougaonika");
					model.setX(x);
					model.setY(y);
					int duzina = Integer.parseInt(d);
					
					
					
					if(duzina>0){

						model.setDuzina(duzina);
						


						try{
							
							
							s = JOptionPane.showInputDialog("Unesi sirinu pravougoonika");
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

								frame.getBtnRedo().setEnabled(false);
								frame.getBtnUndo().setEnabled(true);
					




							}
							else{
								JOptionPane.showMessageDialog(null, "Niste dobro uneli sirinu pravougaonika!");
							}
						} catch(NumberFormatException a){

							if(s!=null) {
								
								JOptionPane.showMessageDialog(null, "Niste dobro uneli sirinu pravougaonika!");
							}
							

						} 

					} else {

						JOptionPane.showMessageDialog(null, "Niste dobro uneli dužinu pravougaonika!");
					}
				} catch(NumberFormatException a){
					
					if(d!=null) {
						
						JOptionPane.showMessageDialog(null, "Niste dobro uneli dužinu pravougaonika!");
					}

					
					

				} 
			} else {


				try{

					model.setX(x);
					model.setY(y);
					int duzina = model.getDuzina();
					if(duzina>0){

						model.setDuzina(duzina);



						try{

							int sirina = model.getSirina();

							if(sirina>0){

								model.setSirina(sirina);

								model.setDuzina(duzina);
								//view.repaint();
								Pravougaonik p = new Pravougaonik(new Tacka(model.getX(),model.getY()), model.getDuzina(),model.getSirina());

								p.setBojaIvice(model.getBojaIvice());
								p.setBojaUnutrasnjosti(model.getBojaUnutrasnjosti());

								CmdAddShape cmdAddShape = new CmdAddShape(model,p);
								cmdAddShape.setPrint(false);
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




			model.setDuzina(-1);
			model.setSirina(-1);

		}

		if(model.getOdabranOblik() == "Kvadrat"){


			String s = null;

			if(model.getDuzinaStranice() == -1) {

			
				
				try{
					
					
					s=JOptionPane.showInputDialog("Unesi duzinu stranice kvadrata");
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
						cmdAddShape.setPrint(true);
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
					
					if(s!=null) {
						
						JOptionPane.showMessageDialog(null, "Niste dobro uneli dužinu stranice kvadrata!");
					}

					

				} 

			} else {

				

				try{

					int duzinaStranice = model.getDuzinaStranice();

					if(duzinaStranice>0){

						model.setDuzinaStranice(duzinaStranice);
						model.setX(x);
						model.setY(y);
						//view.repaint();
						Kvadrat kv = new Kvadrat(new Tacka(model.getX(),model.getY()), model.getDuzinaStranice());
						kv.setBojaIvice(model.getBojaIvice());
						kv.setBojaUnutrasnjosti(model.getBojaUnutrasnjosti());
						CmdAddShape cmdAddShape = new CmdAddShape(model,kv);
						cmdAddShape.setPrint(false);
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



			model.setDuzinaStranice(-1);

		}

		if(model.getOdabranOblik() == "Krug"){


			s=true; //proveriti šta ovo znači
			String s = null;
			int r;

			if(model.getR() == -1) {

				

				

				try{
					
					s=JOptionPane.showInputDialog("Unesi duzinu poluprecnika kruga");
					r = Integer.parseInt(s);



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
					
					if(s!=null) {
						
						JOptionPane.showMessageDialog(null, "Niste dobro uneli polupre�?nik kruga!");
					}

					


				} 

			} else {


				
				try{


					if(model.getR() > -1){



						Krug kr = new Krug(new Tacka(model.getX(),model.getY()),model.getR());
						kr.setBojaIvice(model.getBojaIvice());
						kr.setBojaUnutrasnjosti(model.getBojaUnutrasnjosti());

						CmdAddShape cmdAddShape = new CmdAddShape(model,kr);
						cmdAddShape.setPrint(false);
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



			model.setR(-1);

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
		
		if(checkIfSelectedShapeExists() == 1) {
			
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

	
		






	}


	public void bringToFront() {
		
		
		if(checkIfSelectedShapeExists() == 1) {
			
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

		}

		
		
		/*if(o!=null) {

			frame.getTextArea().append("Bring to front: " + o +"\n");
		}*/



	}

	public void moveToBack() {

		if(checkIfSelectedShapeExists() == 1) {
			
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
	






	}

	public void bringToBack() {

		//checkIfSelectedShapeExists();
		
		if(checkIfSelectedShapeExists() == 1) {
			
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

	public CmdUndoRedo1 getCmdUndoRedo1() {
		return cmdUndoRedo1;
	}

	public void setCmdUndoRedo1(CmdUndoRedo1 cmdUndoRedo1) {
		this.cmdUndoRedo1 = cmdUndoRedo1;
	}








}