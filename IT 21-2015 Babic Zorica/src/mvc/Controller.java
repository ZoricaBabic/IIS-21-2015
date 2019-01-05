package mvc;






import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

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

import cmd.CmdUndoRedo;
import cmd.CmdUpdateSelectedShapes;
import cmd.CmdUpdateShape;
import dlg.DlgCircleProperties;
import dlg.DlgSquareProperties;
import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Square;
import geometry.SurfaceShape;
import dlg.DlgLineProperties;
import dlg.DlgRectangleProperties;
import dlg.DlgPointProperties;
import hexagon.Hexagon;
import observer.Subject;
import strategy.Context;
import strategy.LoadBinOperation;
import strategy.SaveLogOperation;
import strategy.LoadLogOperation;
import strategy.SaveBinOperation;
import strategy.SavePngOperation;



public class Controller {


	private Model model;
	private Frame frame;
	private Context context;
	private Subject subject;
	private boolean justRead = false;
	private CmdUndoRedo cmdUndoRedo;
	private boolean print = true;


	public Controller(Model model, Frame frame) {

		this.model = model;
		this.frame = frame;
		//CmdUpdateShape.setModel(model);

		//button = new Button(frame);
		//cmdUndoRedo = new CmdUndoRedo(model);
		cmdUndoRedo = new CmdUndoRedo();
		subject = new Subject();



		subject.attach(frame.getBtnDelete());
		subject.attach(frame.getBtnModify());

		checkIfSelectedShapeExists();

	} 

	public Controller() {



	}

	public void setBorderColor(Color c) {

		model.setBorderColor(c);
	}

	public void setFillColor(Color c) {

		model.setBorderColor(c);
	}



	public void saveTxt(File f) {

		context = new Context(new SaveLogOperation());
		context.executeStrategy(frame, f); 


	}

	public void openTxt(File f) {

		context = new Context(new LoadLogOperation());
		context.executeStrategy(frame, f);
	}

	public void loadPainting(File f) {

		context = new Context(new LoadBinOperation());
		context.executeStrategy(frame, f);
	}

	public void saveBin(File f) {

		context = new Context(new SaveBinOperation());
		context.executeStrategy(frame, f);
	}

	public void runCommandByCommand(String line,String lineBefore) {

		print = false;
		/*CmdAddShape.print = false;
		CmdBringToBack.print = false;
		CmdBringToFront.print = false;
		CmdDeselectShape.print = false;
		CmdRemoveShape.print = false;
		CmdSelectShape.print = false;
		CmdToBack.print = false;
		CmdToFront.print = false;
		CmdUpdateSelectedShapes.print = false;
		CmdUpdateShape.print = false;*/


		if (line.contains("UNDO >>> Selected: ")) {

		
			undo();

		}else if(line.contains("UNDO >>> Removed:")) {


			//CmdAddShape.setPrint(false);
			undo();


		} else if(line.contains("UNDO >>> Deselected: ")) {

			
			undo();


		}  else if(line.contains("UNDO >>> Bring to front: ")) {

			
			
			undo();

		} else if(line.contains("UNDO >>> Bring to back: ")) {

			
			undo();
		}  else if(line.contains("UNDO >>> Move to front: ")) {

			
			undo();
		}  else if (line.contains("UNDO >>> Move to back: ")) {

			
			undo();

		}  else if(line.contains("UNDO >>> Multiple shapes added: ")) {



		
			undo();


		} else if (line.contains("UNDO >>> Added:")){


			
			undo();

		} else if (line.contains("UNDO >>> Multiple shapes selected: ")) {

			
			undo();


		} else if (line.contains("UNDO >>> Modified: ")) {

			
			undo();
		} else if(line.contains("Added:")) {

			if(line.contains("Circle")) {

				setChosenShape("Circle");
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Circle: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];
				String radius = between(line, "radius: ",", outline");


				model.setFillColor(stringToColor(fill));
				model.setBorderColor(stringToColor(outline));
				model.setR(Integer.parseInt(radius));
				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));

				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));


			} else if (line.contains("Square")) {



				setChosenShape("Square");
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Square: (",")");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();
				String width = between(line, "width: ",", outline");


				model.setFillColor(stringToColor(fill));
				model.setBorderColor(stringToColor(outline));
				model.setSideLength(Integer.parseInt(width));
				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));
				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));


			} else if(line.contains("Rectangle")){


				setChosenShape("Rectangle");
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Rectangle: (",")");
				String[] myString = s.split(",");
				String x = myString[0].trim();
				String y = myString[1].trim();
				String width = between(line, "width: ",", height");
				String height = between(line,"height: ",", outline");


				model.setFillColor(stringToColor(fill));
				model.setBorderColor(stringToColor(outline));
				model.setLength(Integer.parseInt(width));
				model.setWidth(Integer.parseInt(height));
				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));
				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));


			} else if(line.contains("Line")) {

				//Added: Line: startPoint (272,100), endPoint (208,91), outline: #000000, Selected? false
				setChosenShape("Line");
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

				model.setBorderColor(stringToColor(outline));

				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));
				model.setNewX(Integer.parseInt(newX));
				model.setNewY(Integer.parseInt(newY));

				;

				model.setTwoClicks(true);
				justRead=true;
				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));


			} else if (line.contains("Hexagon")) {

				//Added: Hexagon: (130,144), radius: 50, outline: #000000, fill: #ffffff, Selected? false  

				setChosenShape("Hexagon");
				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Hexagon: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];
				String radius = between(line, "radius: ",", outline");


				model.setFillColor(stringToColor(fill));
				model.setBorderColor(stringToColor(outline));
				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));
				model.setR(Integer.parseInt(radius));

				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));

			} else if (line.contains("Point")) {

				//Added: Point: (681,232), outline: #000000, Selected? false

				setChosenShape("Point");
				String outline = between(line, "outline: ", ", Selected?");

				String s = between(line, "Point: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];




				model.setBorderColor(stringToColor(outline));
				model.setX(Integer.parseInt(x));
				model.setY(Integer.parseInt(y));


				justRead=true;
				mouseClickedPnl(Integer.parseInt(x),Integer.parseInt(y));
			}


		}  else if(line.contains("Selected:")) {

		
			//Selected: Circle: (398,139), radius: 50, outline: #000000, fill: #ffffff, Selected? true

			if(line.contains("Circle")) {

				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Circle: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];
				String radius = between(line, "radius: ",", outline");

				Circle k = new Circle(new Point(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(radius),stringToColor(outline),stringToColor(fill));
				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(k)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model.getListOfShapes().get(i));
						
						if(print == true) {
							
							frame.getTextArea().append("Selected: " + model.getListOfShapes().get(i).toString() +"\n");
						}
						print = true;
						
					

						cmdSelectShape.execute();
						cmdUndoRedo.addToCommandList(cmdSelectShape);
						frame.setDone(true);

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

				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(ha)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model.getListOfShapes().get(i));
						
	if(print == true) {
							
							frame.getTextArea().append("Selected: " + model.getListOfShapes().get(i).toString() +"\n");
						}
						print = true;

						cmdSelectShape.execute();
						cmdUndoRedo.addToCommandList(cmdSelectShape);
						frame.setDone(true);

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

				Line l = new Line(new Point(Integer.parseInt(x),Integer.parseInt(y)),new Point(Integer.parseInt(newX),Integer.parseInt(newY)), stringToColor(outline));
				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(l)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model.getListOfShapes().get(i));
						
	if(print == true) {
							
							frame.getTextArea().append("Selected: " + model.getListOfShapes().get(i).toString() +"\n");
						}
						print = true;

						cmdSelectShape.execute();
						cmdUndoRedo.addToCommandList(cmdSelectShape);
						frame.setDone(true);

					}
				}
			} else if(line.contains("Point")) {

				//Selected: Line: startPoint (310,66), endPoint (555,67), outline: #000000, Selected? true



				String outline = between(line, "outline: ", ", Selected?");

				String s = between(line, "Point: (",")");
				String[] myString = s.split(",");

				String x = myString[0];
				String y = myString[1];






				Point t = new Point(Integer.parseInt(x),Integer.parseInt(y),stringToColor(outline));
				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(t)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model.getListOfShapes().get(i));
						
	if(print == true) {
							
							frame.getTextArea().append("Selected: " + model.getListOfShapes().get(i).toString() +"\n");
						}
						print = true;

						cmdSelectShape.execute();
						cmdUndoRedo.addToCommandList(cmdSelectShape);
						frame.setDone(true);

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

				Rectangle p = new Rectangle(new Point(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),Integer.parseInt(height),stringToColor(outline),stringToColor(fill));


				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(p)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model.getListOfShapes().get(i));
						
	if(print == true) {
							
							frame.getTextArea().append("Selected: " + model.getListOfShapes().get(i).toString() +"\n");
						}
						print = true;

						cmdSelectShape.execute();
						cmdUndoRedo.addToCommandList(cmdSelectShape);
						frame.setDone(true);


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

				Square k = new Square(new Point(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),stringToColor(outline),stringToColor(fill));

				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(k)) {

						CmdSelectShape cmdSelectShape = new CmdSelectShape(model.getListOfShapes().get(i));
						
	if(print == true) {
							
							frame.getTextArea().append("Selected: " + model.getListOfShapes().get(i).toString() +"\n");
						}
						print = true;

						cmdSelectShape.execute();
						cmdUndoRedo.addToCommandList(cmdSelectShape);
						frame.setDone(true);

					}
				}

			}



		}  else if(line.contains("Bring to back: ")) { //mozda će biti problem zbog containt UNDOO >> BRING TO BACK


		
			bringToBack();


		} else if(line.contains("Bring to front: ")) {

		
			bringToFront();
		}  else if(line.contains("Move to back: ")) {

		
			moveToBack();

		} else if(line.contains("Move to front: ")) {

		
			moveToFront();
		}  else if(line.contains("Deselected: ") && !line.contains("Multiple")) {

			
			//Selected: Circle: (398,139), radius: 50, outline: #000000, fill: #ffffff, Selected? true

			if(line.contains("Circle")) {

				String outline = between(line, "outline: ", ", fill:");
				String fill = between(line, "fill: ",", Selected");
				String s = between(line, "Circle: (",")");
				String[] myString = s.split(",");
				String x = myString[0];
				String y = myString[1];
				String radius = between(line, "radius: ",", outline");

				Circle k = new Circle(new Point(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(radius),stringToColor(outline),stringToColor(fill));
				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(k)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model.getListOfShapes().get(i));
						
						if(print == true) {
						
						frame.getTextArea().append("Deselected: " + model.getListOfShapes().get(i).toString() +"\n");
						}
					
						print = true;
						cmdDeselectShape.execute();
						cmdUndoRedo.addToCommandList(cmdDeselectShape);
						frame.setDone(true);
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

				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(ha)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model.getListOfShapes().get(i));
						
						if(print == true) {
							
							frame.getTextArea().append("Deselected: " + model.getListOfShapes().get(i).toString() +"\n");
							}
						
							print = true;
						cmdDeselectShape.execute();
						cmdUndoRedo.addToCommandList(cmdDeselectShape);
						frame.setDone(true);

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

				Line l = new Line(new Point(Integer.parseInt(x),Integer.parseInt(y)),new Point(Integer.parseInt(newX),Integer.parseInt(newY)), stringToColor(outline));
				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(l)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model.getListOfShapes().get(i));
						
						if(print == true) {
							
							frame.getTextArea().append("Deselected: " + model.getListOfShapes().get(i).toString() +"\n");
							}
						
							print = true;
						cmdDeselectShape.execute();
						cmdUndoRedo.addToCommandList(cmdDeselectShape);
						frame.setDone(true);

					}
				}
			} else if(line.contains("Point")) {

				//Selected: Line: startPoint (310,66), endPoint (555,67), outline: #000000, Selected? true



				String outline = between(line, "outline: ", ", Selected?");

				String s = between(line, "Point: (",")");
				String[] myString = s.split(",");

				String x = myString[0];
				String y = myString[1];






				Point t = new Point(Integer.parseInt(x),Integer.parseInt(y),stringToColor(outline));
				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(t)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model.getListOfShapes().get(i));
						if(print == true) {
							
							frame.getTextArea().append("Deselected: " + model.getListOfShapes().get(i).toString() +"\n");
							}
						
							print = true;
						cmdDeselectShape.execute();
						cmdUndoRedo.addToCommandList(cmdDeselectShape);
						frame.setDone(true);

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

				Rectangle p = new Rectangle(new Point(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),Integer.parseInt(height),stringToColor(outline),stringToColor(fill));


				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(p)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model.getListOfShapes().get(i));
						if(print == true) {
							
							frame.getTextArea().append("Deselected: " + model.getListOfShapes().get(i).toString() +"\n");
							}
						
							print = true;
						cmdDeselectShape.execute();
						cmdUndoRedo.addToCommandList(cmdDeselectShape);
						frame.setDone(true);

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

				Square k = new Square(new Point(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),stringToColor(outline),stringToColor(fill));

				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).equals(k)) {

						CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(model.getListOfShapes().get(i));
						if(print == true) {
							
							frame.getTextArea().append("Deselected: " + model.getListOfShapes().get(i).toString() +"\n");
							}
						
							print = true;
						cmdDeselectShape.execute();
						cmdUndoRedo.addToCommandList(cmdDeselectShape);
						frame.setDone(true);

					}
				}

			}

			//uzmem oblik
		} else if (line.contains("Removed: ") && !line.contains("Multiple")) {


		
			delete();

		}  else if(line.contains("Multiple shapes removed:")){

			delete();

		}  else if (line.contains("Multiple shapes deselected: ")) {

	

			ArrayList<Shape> ss = new ArrayList<Shape>();
			int k=0;


			for(Shape o: model.getListOfShapes()) {

				if(o.isSelected() == true) {

					ss.add(o);
					k++;
				}

			}

			if(k>1) {


				CmdUpdateSelectedShapes cmdUpdate = new CmdUpdateSelectedShapes(ss);
				
				if(!ss.isEmpty()) {
					
					if(print == true) {
						
						frame.getTextArea().append("Multiple shapes deselected: ");
					}
					
					for(int i=0; i<ss.size(); i++) {
						
					
						
						if(print == true) {
							
							frame.getTextArea().append("        Deselected: " + ss.get(i).toString());
						}
						
						
					}
					
					if(print == true) {

						frame.getTextArea().append("\n");
					}
				}
				
				print = true;
			
				cmdUpdate.execute();
				cmdUndoRedo.addToCommandList(cmdUpdate);
				frame.setDone(true);

			} else if (k==1) {


				CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(ss.get(0));
				if(print == true) {
					
					frame.getTextArea().append("Deselected: " + ss.get(0).toString() +"\n");
					}
				
					print = true;
				cmdDeselectShape.execute();
				cmdUndoRedo.addToCommandList(cmdDeselectShape);
				frame.setDone(true);

			}


		} else if (line.contains("Modified: ")){

			if(lineBefore != null) {

			
				//Selected: Circle: (398,139), radius: 50, outline: #000000, fill: #ffffff, Selected? true

				if(line.contains("Circle") && lineBefore.contains("Circle")) {


					String outline1 = between(lineBefore, "outline: ", ", fill:");
					String fill1 = between(lineBefore, "fill: ",", Selected");
					String s1 = between(lineBefore, "Circle: (",")");
					String[] myString1 = s1.split(",");
					String x1 = myString1[0];
					String y1 = myString1[1];
					String radius1 = between(lineBefore, "radius: ",", outline");

					Circle k1 = new Circle(new Point(Integer.parseInt(x1),Integer.parseInt(y1)),Integer.parseInt(radius1),stringToColor(outline1),stringToColor(fill1));
					k1.setSelected(true);

					String outline = between(line, "outline: ", ", fill:");
					String fill = between(line, "fill: ",", Selected");
					String s = between(line, "Circle: (",")");
					String[] myString = s.split(",");
					String x = myString[0];
					String y = myString[1];
					String radius = between(line, "radius: ",", outline");

					Circle k2 = new Circle(new Point(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(radius),stringToColor(outline),stringToColor(fill));
					k2.setSelected(true);
					for(int i=0; i<model.getListOfShapes().size(); i++) {

						if(model.getListOfShapes().get(i).equals(k1)) {

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListOfShapes().get(i),k2,model);
							if(print == true) {

								frame.getTextArea().append("Modified: " + k2 + "\n");
							}

							cmdUpdate.execute();
							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);
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
					ha1.setSelected(true);

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
					ha.setSelected(true);

					for(int i=0; i<model.getListOfShapes().size(); i++) {

						if(model.getListOfShapes().get(i).equals(ha1)) {

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListOfShapes().get(i),ha,model);
							if(print == true) {

								frame.getTextArea().append("Modified: " + ha + "\n");
							}
							cmdUpdate.execute();
							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);

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


					Line l1 = new Line(new Point(Integer.parseInt(x1),Integer.parseInt(y1)),new Point(Integer.parseInt(newX1),Integer.parseInt(newY1)), stringToColor(outline1));
					l1.setSelected(true);



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

					Line l = new Line(new Point(Integer.parseInt(x),Integer.parseInt(y)),new Point(Integer.parseInt(newX),Integer.parseInt(newY)), stringToColor(outline));
					l.setSelected(true);

					for(int i=0; i<model.getListOfShapes().size(); i++) {

						if(model.getListOfShapes().get(i).equals(l1)) {

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListOfShapes().get(i),l,model);
							if(print == true) {

								frame.getTextArea().append("Modified: " + l + "\n");
							}
							cmdUpdate.execute();
							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);

						}
					}
				} else if(line.contains("Point") && lineBefore.contains("Point")) {

					//Selected: Line: startPoint (310,66), endPoint (555,67), outline: #000000, Selected? true


					String outline1 = between(lineBefore, "outline: ", ", Selected?");

					String s1 = between(lineBefore, "Point: (",")");
					String[] myString1 = s1.split(",");

					String x1 = myString1[0];
					String y1 = myString1[1];

					Point t1 = new Point(Integer.parseInt(x1),Integer.parseInt(y1),stringToColor(outline1));
					t1.setSelected(true);


					String outline = between(line, "outline: ", ", Selected?");
					String s = between(line, "Point: (",")");
					String[] myString = s.split(",");

					String x = myString[0];
					String y = myString[1];






					Point t = new Point(Integer.parseInt(x),Integer.parseInt(y),stringToColor(outline));
					t.setSelected(true);


					for(int i=0; i<model.getListOfShapes().size(); i++) {

						if(model.getListOfShapes().get(i).equals(t1)) {

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListOfShapes().get(i),t,model);
							if(print == true) {

								frame.getTextArea().append("Modified: " + t + "\n");
							}
							cmdUpdate.execute();
							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);

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

					Rectangle p1 = new Rectangle(new Point(Integer.parseInt(x1),Integer.parseInt(y1)),Integer.parseInt(width1),Integer.parseInt(height1),stringToColor(outline1),stringToColor(fill1));
					p1.setSelected(true);



					String outline = between(line, "outline: ", ", fill:");
					String fill = between(line, "fill: ",", Selected");
					String s = between(line, "Rectangle: (",")");
					String[] myString = s.split(",");
					String x = myString[0].trim();
					String y = myString[1].trim();
					String width = between(line, "width: ",", height");
					String height = between(line,"height: ",", outline");

					Rectangle p = new Rectangle(new Point(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),Integer.parseInt(height),stringToColor(outline),stringToColor(fill));   
					p.setSelected(true);

					for(int i=0; i<model.getListOfShapes().size(); i++) {

						if(model.getListOfShapes().get(i).equals(p1)) {

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListOfShapes().get(i),p,model);
							if(print == true) {

								frame.getTextArea().append("Modified: " + p + "\n");
							}
							cmdUpdate.execute();
							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);

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

					Square k1 = new Square(new Point(Integer.parseInt(x1),Integer.parseInt(y1)),Integer.parseInt(width1),stringToColor(outline1),stringToColor(fill1));
					k1.setSelected(true);



					String outline = between(line, "outline: ", ", fill:");
					String fill = between(line, "fill: ",", Selected");
					String s = between(line, "Square: (",")");
					String[] myString = s.split(",");
					String x = myString[0].trim();
					String y = myString[1].trim();
					String width = between(line, "width: ",", outline");

					Square k = new Square(new Point(Integer.parseInt(x),Integer.parseInt(y)),Integer.parseInt(width),stringToColor(outline),stringToColor(fill));
					k.setSelected(true);
					for(int i=0; i<model.getListOfShapes().size(); i++) {

						if(model.getListOfShapes().get(i).equals(k1)) {


							CmdUpdateShape cmdUpdate = new CmdUpdateShape(model.getListOfShapes().get(i),k,model);
							
							if(print == true) {

								frame.getTextArea().append("Modified: " + k + "\n");
							}
							cmdUpdate.execute();
							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);
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

		context = new Context(new SavePngOperation());
		context.executeStrategy(frame, f);
	}

	//BRISANJE
	public void actionPerfomedDelete(ActionEvent e) { 

		if(checkIfSelectedShapeExists() == 0){

			JOptionPane.showMessageDialog(null, "You have not selected any shape!");

		} else  {

			int odgovor = JOptionPane.YES_NO_OPTION;

			JOptionPane.showConfirmDialog (null, "\n" + 
					"Do you want to delete the shape you selected?","Message", odgovor);

			if(odgovor == JOptionPane.YES_OPTION){


				delete();

			}

		}


		checkIfSelectedShapeExists();
	}

	public void delete() {


		ArrayList<Shape> shapes = new ArrayList<Shape>();

		for(int i=0; i<model.getListOfShapes().size(); i++) {

			if(model.getListOfShapes().get(i).isSelected() == true) {


				shapes.add(model.getListOfShapes().get(i));

			}

		}	



		if(shapes.size() == 1) {


			//obirsan je jedan oblik
			CmdRemoveShape cmdRemove = new CmdRemoveShape(model,shapes.get(0));
			
			if(print == true) {

				frame.getTextArea().append("Removed: " + shapes.get(0) +"\n");
			}
			print = true;
			cmdRemove.execute();
			cmdUndoRedo.addToCommandList(cmdRemove);
			frame.setDone(true);



		} else {


			//obrisano je više oblika
			CmdRemoveShape cmdRemove = new CmdRemoveShape(model,shapes);
			
			if(print ==true) {

				frame.getTextArea().append("Multiple shapes removed: ");
			}
			
			
			for(int i =0; i<shapes.size(); i++) {

				model.remove(shapes.get(i));

				if(print == true) {

					frame.getTextArea().append("        Removed: " + shapes.get(i).toString());
				}

			}

			if(print == true) {

				frame.getTextArea().append("\n");
			}
			
			print = true;
			cmdRemove.execute();
			cmdUndoRedo.addToCommandList(cmdRemove);
			frame.setDone(true);


		}
	}


	//MODIFIKACIJA
	public void actionPerfomedModify(ActionEvent e) { //za hexagon odradraditi

		int count = 0;
		for(Shape shape: model.getListOfShapes()) {

			if(shape.isSelected() == true) {

				count++;


			}


		}

		if(count == 0) {

			JOptionPane.showMessageDialog(null, "You have not selected any shape!");

		} else if(count > 1) {

			JOptionPane.showMessageDialog(null, "\n" + 
					"Several shapes have been selected. Select only the shape you want to modify!");

		} else {

			for(Shape s: model.getListOfShapes()) {

				if(s.isSelected() == true) {

					if(s instanceof Rectangle){



						DlgRectangleProperties properties = new DlgRectangleProperties();


						properties = new DlgRectangleProperties();
						properties.setDone(false);

						properties.setxD(((Rectangle) s).getpUpLeft().getX());
						properties.setyD(((Rectangle) s).getpUpLeft().getY());
						properties.setLength(((Rectangle) s).getSideLength());
						properties.setWidthD(((Rectangle) s).getWidth());
						properties.setBorderColor(s.getBorderColor());
						properties.setFillColor(((SurfaceShape) s).getFillColor());

						//prikazivanje vrednosti
						properties.getTxtX().setText(Integer.toString(properties.getxD()));
						properties.getTxtY().setText(Integer.toString(properties.getyD()));
						properties.getTxtLength().setText(Integer.toString(properties.getLength()));
						properties.getTxtWidth().setText(Integer.toString(properties.getWidthD()));
						properties.getEdpBorderColor().setBackground(properties.getBorderColor());
						properties.getEdpFillColor().setBackground(properties.getFillColor());

						properties.setVisible(true);

						if(properties.isVisible() == false && properties.isDone() == true) {

							Rectangle newShape = new Rectangle(new Point(properties.getxD(),properties.getyD()),properties.getLength(),properties.getWidthD(),properties.getBorderColor(),properties.getFillColor());
							newShape.setSelected(true);


							CmdUpdateShape cmdUpdate = new CmdUpdateShape(s,newShape,model);
							
							if(print == true) {

								frame.getTextArea().append("Modified: " + newShape + "\n");
							}
							cmdUpdate.execute();
							cmdUndoRedo.addToCommandList(cmdUpdate);
							properties.setDone(false);
							frame.setDone(true);


						}


					} else if(s instanceof Square){



						DlgSquareProperties properties = new DlgSquareProperties();
						properties.setDone(false);
						properties.setX(((Square) s).getpUpLeft().getX());
						properties.setY(((Square) s).getpUpLeft().getY());
						properties.setLength(((Square) s).getSideLength());
						properties.setBorderColor(s.getBorderColor());
						properties.setFillColor(((SurfaceShape) s).getFillColor());

						properties.getTxtX().setText(Integer.toString(properties.getX()));
						properties.getTxtY().setText(Integer.toString(properties.getY()));
						properties.getTxtLength().setText(Integer.toString(properties.getLength()));
						properties.getEdpBorderColor().setBackground(properties.getBorderColor());
						properties.getEdpFillColor().setBackground(properties.getFillColor());
						properties.setVisible(true);

						if(properties.isVisible() == false && properties.isDone() == true) {



							Square newShape = new Square(new Point(properties.getX(),properties.getY()),properties.getLength(),properties.getBorderColor(),properties.getFillColor());
							newShape.setSelected(true);


							CmdUpdateShape cmdUpdate = new CmdUpdateShape(s,newShape,model);
							if(print == true) {

								frame.getTextArea().append("Modified: " + newShape + "\n");
							}

							cmdUpdate.execute();

							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);

							properties.setDone(false);


						}



					} else if (s instanceof Circle) {



						DlgCircleProperties properties = new DlgCircleProperties();
						properties.setTitle("Krug");
						properties.setX(((Circle) s).getCenter().getX());
						properties.setY(((Circle) s).getCenter().getY());
						properties.setR(((Circle) s).getR());
						properties.setBorderColor(s.getBorderColor());
						properties.setFillColor(((SurfaceShape) s).getFillColor());


						properties.getTxtX().setText(Integer.toString(properties.getX()));
						properties.getTxtY().setText(Integer.toString(properties.getY()));
						properties.getTxtR().setText(Integer.toString(properties.getR()));
						properties.getEdpBorderColor().setBackground(properties.getBorderColor());
						properties.getEdpFillColor().setBackground(properties.getFillColor());
						properties.setVisible(true);



						if(properties.isVisible() == false && properties.isDone()==true) {

							Circle newShape = new Circle(new Point(properties.getX(),properties.getY()),properties.getR(),properties.getBorderColor(),properties.getFillColor());
							//novi.setSelektovan(true);
							newShape.setSelected(true);


							CmdUpdateShape cmdUpdate = new CmdUpdateShape(s,newShape,model);
							if(print == true) {

								frame.getTextArea().append("Modified: " + newShape + "\n");
							}
							cmdUpdate.execute();

							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);
							//model.getCommands().add(q);


							properties.setDone(false);


						}
					} 


					else if(s instanceof Point){



						DlgPointProperties properties = new DlgPointProperties();
						properties.setDone(false);
						properties.setX(((Point) s).getX());
						properties.setY(((Point) s).getY());
						properties.setBorderColor(s.getBorderColor());


						properties.getTxtXKoordinata().setText(Integer.toString(properties.getX()));
						properties.getTxtY().setText(Integer.toString(properties.getY()));
						properties.getEdpColor().setBackground(properties.getBorderColor());


						properties.setVisible(true);

						if(properties.isVisible() == false && properties.isDone() == true) {

							Point t = properties.getPoint();
							//view.repaint();

							t.setSelected(true);





							CmdUpdateShape cmdUpdate = new CmdUpdateShape(s,t,model);
							
							if(print == true) {

								frame.getTextArea().append("Modified: " + t + "\n");
							}

							cmdUpdate.execute();

							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);

							properties.setDone(false);
						}







					} else if(s instanceof Line){



						DlgLineProperties properties = new DlgLineProperties();
						properties.setDone(false);
						properties.setxStart(((Line) s).getStart().getX());
						properties.setyStart(((Line) s).getStart().getY());
						properties.setxEnd(((Line) s).getEnd().getX());
						properties.setyEnd(((Line) s).getEnd().getY());
						properties.setBorderColor(s.getBorderColor());



						properties.getTxtXStart().setText(Integer.toString(properties.getxStart()));
						properties.getTxtYStart().setText(Integer.toString(properties.getyStart()));
						properties.getTxtXEnd().setText(Integer.toString(properties.getxEnd()));
						properties.getTxtYEnd().setText(Integer.toString(properties.getyEnd()));

						properties.getEdpColor().setBackground(properties.getBorderColor());


						properties.setVisible(true);



						if(properties.isVisible() == false && properties.isDone() == true) {

							Line newShape = new Line(new Point(properties.getxStart(),properties.getyStart()), new Point (properties.getxEnd(),properties.getyEnd()),properties.getBorderColor());
							newShape.setSelected(true);



							//model.addToStackUndo(s);

							CmdUpdateShape cmdUpdate = new CmdUpdateShape(s,newShape,model);
							if(print == true) {

								frame.getTextArea().append("Modified: " + newShape + "\n");
							}
							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);

							cmdUpdate.execute();

							properties.setDone(false);

							//frame.getTextArea().append(novi + "\n");


							//model.setOblik(l);

						}


					}else if(s instanceof HexagonAdapter) { 



						DlgCircleProperties propertiesh = new DlgCircleProperties();
						propertiesh.setTitle("Hexagon");
						propertiesh.setDone(false);
						propertiesh.setX(((HexagonAdapter) s).getHexagon().getX());
						propertiesh.setY(((HexagonAdapter) s).getHexagon().getY());
						propertiesh.setR(((HexagonAdapter) s).getHexagon().getR());
						propertiesh.setBorderColor(((HexagonAdapter) s).getHexagon().getBorderColor());
						propertiesh.setFillColor(((HexagonAdapter) s).getHexagon().getAreaColor());

						propertiesh.getTxtX().setText(Integer.toString(propertiesh.getX()));
						propertiesh.getTxtY().setText(Integer.toString(propertiesh.getY()));
						propertiesh.getTxtR().setText(Integer.toString(propertiesh.getR()));
						propertiesh.getEdpBorderColor().setBackground(propertiesh.getBorderColor());
						propertiesh.getEdpFillColor().setBackground(propertiesh.getFillColor());
						propertiesh.setVisible(true);

						if(propertiesh.isVisible() == false && propertiesh.isDone() == true) {

							Hexagon hexagon = new Hexagon(propertiesh.getX(),propertiesh.getY(),propertiesh.getR());
							hexagon.setAreaColor(propertiesh.getFillColor());
							hexagon.setBorderColor(propertiesh.getBorderColor());
							hexagon.setSelected(true);

							HexagonAdapter hexagonAdapter = new HexagonAdapter(hexagon);
							CmdUpdateShape cmdUpdate = new CmdUpdateShape(s,hexagonAdapter,model);
							if(print == true) {

								frame.getTextArea().append("Modified: " + hexagonAdapter + "\n");
							}
							cmdUpdate.execute();

							cmdUndoRedo.addToCommandList(cmdUpdate);
							frame.setDone(true);

							propertiesh.setDone(false);





						} 



					}


				}
			}










		}

		checkIfSelectedShapeExists();

	}


	//KORISNIK ZELI DA SELEKTUJE
	public void mouseClickedSelection(MouseEvent e) {

		if(model.getListOfShapes().size() == 0){

			JOptionPane.showMessageDialog(null, "\n" + 
					"You did not draw anything!");

		} else {

			model.setChosenShape("");


		}


	}

	//SELEKCIJA
	public void mouseClickedPnl(int x, int y) {


		if(model.getChosenShape() == ""){

			int m =0;


			for(int i=0; i<model.getListOfShapes().size(); i++) {

				if(model.getListOfShapes().get(i).contains(x, y)) {

					m++;
				}
			}

			if(m>1) {

				//selektuje poslednji
				Stack<Shape> stackShape = new Stack<Shape>();

				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).contains(x, y)) {

						stackShape.push(model.getListOfShapes().get(i));
					}
				}


				if(stackShape.peek().isSelected() == false) {



					CmdSelectShape cmdSelectShape = new CmdSelectShape(stackShape.peek());
					
					
	if(print == true) {
							
							frame.getTextArea().append("Selected: " + stackShape.peek().toString() +"\n");
						}
						print = true;
					cmdSelectShape.execute();
					cmdUndoRedo.addToCommandList(cmdSelectShape);
					frame.setDone(true);




				}


				stackShape.removeAllElements();




			} else if (m==1 || m==0) {

				for(int i=0; i<model.getListOfShapes().size(); i++) {

					if(model.getListOfShapes().get(i).contains(x, y)) {

						if(model.getListOfShapes().get(i).isSelected() == false) {


							CmdSelectShape cmdSelectShape = new CmdSelectShape(model.getListOfShapes().get(i));
							
							if(print == true) {
								
								frame.getTextArea().append("Selected: " + model.getListOfShapes().get(i).toString() +"\n");
							}
							print = true;
							cmdSelectShape.execute();
							cmdUndoRedo.addToCommandList(cmdSelectShape);
							frame.setDone(true);




						}
					}
				}




			}








			//klikom na panel deselktuju se svi oblici

			if(m==0) {

				ArrayList<Shape> ss = new ArrayList<Shape>();
				int k=0;



				for(Shape s: model.getListOfShapes()) {

					if(s.isSelected() == true) {

						ss.add(s);
						k++;

					}



				}

				if(k>1) {


					CmdUpdateSelectedShapes cmdUpdate = new CmdUpdateSelectedShapes(ss);
					cmdUpdate.execute();
					cmdUndoRedo.addToCommandList(cmdUpdate);
					frame.setDone(true);

				} else if (k==1) {


					CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(ss.get(0));
					if(print == true) {
						
						frame.getTextArea().append("Deselected: " + ss.get(0).toString() +"\n");
						}
					
						print = true;
					cmdDeselectShape.execute();
					cmdUndoRedo.addToCommandList(cmdDeselectShape);
					frame.setDone(true);

				}



			}





		} else {


			//kad se crta novi, sve ostale odselektovati
			ArrayList<Shape> ss = new ArrayList<Shape>();
			int k=0;



			for(Shape s: model.getListOfShapes()) {

				if(s.isSelected() == true) {

					ss.add(s);
					k++;


				}



			}

			if(k>1) {


				CmdUpdateSelectedShapes cmdUpdate = new CmdUpdateSelectedShapes(ss);
				cmdUpdate.execute();
				cmdUndoRedo.addToCommandList(cmdUpdate);
				frame.setDone(true);




			} else if (k==1) {


				CmdDeselectShape cmdDeselectShape = new CmdDeselectShape(ss.get(0));
				
if(print == true) {
					
					frame.getTextArea().append("Deselected: " + ss.get(0).toString() +"\n");
					}
				
					print = true;
				cmdDeselectShape.execute();

				cmdUndoRedo.addToCommandList(cmdDeselectShape);
				frame.setDone(true);


			}


		}




		//CRTANJE
		if(model.getChosenShape() == "Point" || model.getChosenShape() == null)
		{

			if(justRead==false) {

				model.setX(x);
				model.setY(y);
				//view.repaint();

				Point t = new Point(model.getX(),model.getY());
				t.setBorderColor(model.getBorderColor());


				CmdAddShape cmdAddShape = new CmdAddShape(model,t);

				cmdAddShape.execute();

				if(print == true) {


					frame.getTextArea().append("Added: " + t.toString() +"\n");

				}



				frame.getBtnSave().setEnabled(true);

				cmdUndoRedo.addToCommandList(cmdAddShape);
				frame.setDone(true);

				frame.getBtnRedo().setEnabled(false);
				frame.getBtnUndo().setEnabled(true);
				frame.getBtnSelect().setEnabled(true);


			} else {


				Point t = new Point(model.getX(),model.getY());
				t.setBorderColor(model.getBorderColor());


				CmdAddShape cmdAddShape = new CmdAddShape(model,t);


				cmdAddShape.execute();

				if(print == true) {



					frame.getTextArea().append("Added: " + t.toString() +"\n");


				}

				frame.getBtnSave().setEnabled(true);




				cmdUndoRedo.addToCommandList(cmdAddShape);
				frame.setDone(true);
				
				
				frame.getBtnSelect().setEnabled(true);




				justRead=false;
			}




		}

		if(model.getChosenShape() == "Hexagon") {



			String s=null;

			if(model.getR() == -1) {


				try{

					s=JOptionPane.showInputDialog("Enter the radius of the hexagon");

					int r = Integer.parseInt(s);

					if(r > 0){

						model.setR(r);
						model.setX(x);
						model.setY(y);
						//view.repaint();

						Hexagon hexagon = new Hexagon(model.getX(),model.getY(),model.getR());
						hexagon.setAreaColor(model.getFillColor());
						hexagon.setBorderColor(model.getBorderColor());

						HexagonAdapter h = new HexagonAdapter(hexagon);
						h.setSelected(false);



						//HexagonAdapter h = new HexagonAdapter(new Tacka(model.getX(),model.getY()),model.getR(),model.getBojaIvice(),model.getBojaUnutrasnjosti());

						CmdAddShape cmdAddShape = new CmdAddShape(model,h);
						cmdAddShape.execute();

						if(print == true) {



							frame.getTextArea().append("Added: " + h.toString() +"\n");


						}

						frame.getBtnSave().setEnabled(true);

						cmdUndoRedo.addToCommandList(cmdAddShape);
						frame.setDone(true);

						/*Oblik l = CopyShape(h);
						model.addToStackUndo(l);*/


						frame.getBtnRedo().setEnabled(false);
						frame.getBtnUndo().setEnabled(true);
						//frame.getBtnSelektuj().setEnabled(true);
						
						frame.getBtnSelect().setEnabled(true);

						//frame.getTextArea().append("Drawing: " + h.toString() +"\n");

					} else {

						JOptionPane.showMessageDialog(null, "Niste dobro uneli poluprecnik hexagona!");
					}

				} catch (Exception e){

					if(s!=null) {

						JOptionPane.showMessageDialog(null, "\n" + 
								"You did not enter the radius of hexagon well! ");
					}



				}


			} else {


				int r = model.getR();
				if(r > 0){

					Hexagon hexagon = new Hexagon(model.getX(),model.getY(),model.getR());
					hexagon.setAreaColor(model.getFillColor());
					hexagon.setBorderColor(model.getBorderColor());
					HexagonAdapter h = new HexagonAdapter(hexagon);
					h.setSelected(false);



					//HexagonAdapter h = new HexagonAdapter(new Tacka(model.getX(),model.getY()),model.getR(),model.getBojaIvice(),model.getBojaUnutrasnjosti());

					CmdAddShape cmdAddShape = new CmdAddShape(model,h);

					cmdAddShape.execute();

					if(print == true) {



						frame.getTextArea().append("Added: " + h.toString() +"\n");


					}

					frame.getBtnSave().setEnabled(true);
					cmdUndoRedo.addToCommandList(cmdAddShape);
					frame.setDone(true);
					frame.getBtnSelect().setEnabled(true);







				} else {

					JOptionPane.showMessageDialog(null, "You did not enter the radius of hexagon well!");
				}


			}


			model.setR(-1);

		}

		if(model.getChosenShape() == "Line"){


			if(model.isTwoClicks() == false){

				model.setX(x);
				model.setY(y);
				model.setTwoClicks(true);


			} else {

				if(justRead == false) {

					model.setNewX(x);
					model.setNewY(y);
					frame.getBtnRedo().setEnabled(false);
					frame.getBtnUndo().setEnabled(true);

				} 


				//view.repaint();
				Line l = new Line(new Point(model.getX(),model.getY()), new Point(model.getNewX(),model.getNewY()));
				l.setBorderColor(model.getBorderColor());

				CmdAddShape cmdAddShape = new CmdAddShape(model,l);



				cmdAddShape.execute();
				if(print == true) {



					frame.getTextArea().append("Added: " + l.toString() +"\n");


				}

				frame.getBtnSave().setEnabled(true);



				cmdUndoRedo.addToCommandList(cmdAddShape);
				frame.setDone(true);
				frame.getBtnSelect().setEnabled(true);

				/*Oblik k = CopyShape(l);
				model.addToStackUndo(k);*/




				//frame.getBtnSelektuj().setEnabled(true);

				//frame.getTextArea().append("Drawing: " + l.toString() +"\n");

				model.setTwoClicks(false);
				justRead=false;
			}

		}

		if(model.getChosenShape() == "Rectangle"){

			String d=null;
			String s=null;
			if(model.getLength() == -1) {



				try{


					d=JOptionPane.showInputDialog("Enter the length of the rectangle");
					model.setX(x);
					model.setY(y);
					int length = Integer.parseInt(d);



					if(length>0){

						model.setLength(length);



						try{


							s = JOptionPane.showInputDialog("Enter the width of the rectangle");
							int width = Integer.parseInt(s);
							if(width>0){

								model.setWidth(width);

								model.setLength(length);
								//view.repaint();
								Rectangle p = new Rectangle(new Point(model.getX(),model.getY()), model.getLength(),model.getWidth());

								p.setBorderColor(model.getBorderColor());
								p.setFillColor(model.getFillColor());

								CmdAddShape cmdAddShape = new CmdAddShape(model,p);
								cmdAddShape.execute();

								if(print == true) {



									frame.getTextArea().append("Added: " + p.toString() +"\n");


								}
								frame.getBtnSelect().setEnabled(true);

								frame.getBtnSave().setEnabled(true);

								cmdUndoRedo.addToCommandList(cmdAddShape);
								frame.setDone(true);

								frame.getBtnRedo().setEnabled(false);
								frame.getBtnUndo().setEnabled(true);





							}
							else{
								JOptionPane.showMessageDialog(null, "\n" + 
										"You did not enter the correct width of the rectangle!");
							}
						} catch(NumberFormatException a){

							if(s!=null) {

								JOptionPane.showMessageDialog(null, "\n" + 
										"You did not enter the correct width of the rectangle!");
							}


						} 

					} else {

						JOptionPane.showMessageDialog(null, "\n" + 
								"You did not enter the correct length of the rectangle!");
					}
				} catch(NumberFormatException a){

					if(d!=null) {

						JOptionPane.showMessageDialog(null, "\n" + 
								"You did not enter the correct length of the rectangle!");
					}




				} 
			} else {




				model.setX(x);
				model.setY(y);
				int length = model.getLength();
				if(length>0){

					model.setLength(length);




					int width = model.getWidth();

					if(width>0){

						model.setWidth(width);

						model.setLength(length);
						//view.repaint();
						Rectangle p = new Rectangle(new Point(model.getX(),model.getY()), model.getLength(),model.getWidth());

						p.setBorderColor(model.getBorderColor());
						p.setFillColor(model.getFillColor());

						CmdAddShape cmdAddShape = new CmdAddShape(model,p);

						cmdAddShape.execute();

						if(print == true) {



							frame.getTextArea().append("Added: " + p.toString() +"\n");


						}

						frame.getBtnSave().setEnabled(true);

						cmdUndoRedo.addToCommandList(cmdAddShape);
						frame.setDone(true);
						
						frame.getBtnSelect().setEnabled(true);







					} 
				}
			}




			model.setLength(-1);
			model.setWidth(-1);



		}

		if(model.getChosenShape() == "Square"){


			String s = null;

			if(model.getSideLength() == -1) {



				try{


					s=JOptionPane.showInputDialog("\n" + 
							"Enter the length");
					int sideLength = Integer.parseInt(s);
					if(sideLength>0){

						model.setSideLength(sideLength);
						model.setX(x);
						model.setY(y);
						//view.repaint();
						Square square = new Square(new Point(model.getX(),model.getY()), model.getSideLength());
						square.setBorderColor(model.getBorderColor());
						square.setFillColor(model.getFillColor());
						CmdAddShape cmdAddShape = new CmdAddShape(model,square);
						cmdAddShape.execute();

						if(print == true) {



							frame.getTextArea().append("Added: " + square.toString() +"\n");


						}

						frame.getBtnSave().setEnabled(true);

						cmdUndoRedo.addToCommandList(cmdAddShape);
						frame.setDone(true);
						
						frame.getBtnSelect().setEnabled(true);



						/*Oblik l = CopyShape(kv);
						model.addToStackUndo(l);*/


						frame.getBtnUndo().setEnabled(true);
						frame.getBtnRedo().setEnabled(false);
						//frame.getBtnSelektuj().setEnabled(true);

						//frame.getTextArea().append("Drawing: " + kv.toString() +"\n");

					} else {

						JOptionPane.showMessageDialog(null, "You did not enter the length correctly!");
					}

				} catch(NumberFormatException a){

					if(s!=null) {

						JOptionPane.showMessageDialog(null, "You did not enter the length correctly!");
					}



				} 

			} else {





				int sideLength = model.getSideLength();

				if(sideLength>0){

					model.setSideLength(sideLength);
					model.setX(x);
					model.setY(y);
					//view.repaint();
					Square square = new Square(new Point(model.getX(),model.getY()), model.getSideLength());
					square.setBorderColor(model.getBorderColor());
					square.setFillColor(model.getFillColor());
					CmdAddShape cmdAddShape = new CmdAddShape(model,square);

					cmdAddShape.execute();

					if(print == true) {



						frame.getTextArea().append("Added: " + square.toString() +"\n");


					}

					frame.getBtnSave().setEnabled(true);

					cmdUndoRedo.addToCommandList(cmdAddShape);
					frame.setDone(true);
					
					frame.getBtnSelect().setEnabled(true);



					/*Oblik l = CopyShape(kv);
						model.addToStackUndo(l);*/


					//frame.getBtnSelektuj().setEnabled(true);

					//frame.getTextArea().append("Drawing: " + kv.toString() +"\n");

				} 




			}



			model.setSideLength(-1);

		}

		if(model.getChosenShape() == "Circle"){



			String s = null;
			int r;

			if(model.getR() == -1) {





				try{

					s=JOptionPane.showInputDialog("Enter the radius of the circle");
					r = Integer.parseInt(s);



					if(r > 0){

						model.setR(r);
						model.setX(x);
						model.setY(y);
						//view.repaint();

						Circle circle = new Circle(new Point(model.getX(),model.getY()),model.getR());
						circle.setBorderColor(model.getBorderColor());
						circle.setFillColor(model.getFillColor());

						CmdAddShape cmdAddShape = new CmdAddShape(model,circle); 
						cmdAddShape.execute();

						if(print == true) {



							frame.getTextArea().append("Added: " + circle.toString() +"\n");


						}

						frame.getBtnSave().setEnabled(true);



						cmdUndoRedo.addToCommandList(cmdAddShape);
						frame.setDone(true);
						
						frame.getBtnSelect().setEnabled(true);



						/*Oblik l = CopyShape(kr);
						model.addToStackUndo(l);*/






						frame.getBtnUndo().setEnabled(true);
						frame.getBtnRedo().setEnabled(false);
						//frame.getBtnSelektuj().setEnabled(true);

						//frame.getTextArea().append("Drawing: " + kr.toString() +"\n");







					} else {

						JOptionPane.showMessageDialog(null, "You did not enter the radius of circle well");
					}

				} catch (NumberFormatException k){

					if(s!=null) {

						JOptionPane.showMessageDialog(null, "You did not enter the radius of circle well!");
					}




				} 

			} else {






				if(model.getR() > -1){



					Circle circle = new Circle(new Point(model.getX(),model.getY()),model.getR());
					circle.setBorderColor(model.getBorderColor());
					circle.setFillColor(model.getFillColor());

					CmdAddShape cmdAddShape = new CmdAddShape(model,circle);

					cmdAddShape.execute();

					if(print == true) {



						frame.getTextArea().append("Added: " + circle.toString() +"\n");


					}

					frame.getBtnSave().setEnabled(true);




					cmdUndoRedo.addToCommandList(cmdAddShape);
					frame.setDone(true);
					frame.getBtnSelect().setEnabled(true);












				} 

			}



			model.setR(-1);

		}

		checkIfSelectedShapeExists();
	}




	//SETOVANJE BOJE UNUTRASNJOSTI
	public Color mouseClickedFillColor() {


		Color currentFillColor = model.getFillColor();
		Color fillColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);

		if(fillColor  == null) {

			model.setFillColor(currentFillColor);

			return currentFillColor;
		} else {

			model.setFillColor(fillColor);
			return fillColor;


		}

	}


	//SETOVANJE BOJE IVICE
	public Color mouseClickedBorderColor() {


		Color currentBorederColor = model.getBorderColor();
		Color borderColor = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);

		if(borderColor  == null) {

			model.setBorderColor(currentBorederColor);

			return currentBorederColor;
		} else {

			model.setBorderColor(borderColor);
			return borderColor;


		}
	}

	//SETOVANJE ODABRANOG OBLIKA
	public void setChosenShape(String s) {

		model.setChosenShape(s);

	}




	//UNDO
	public void undo() {


		if(!cmdUndoRedo.getUndo().isEmpty()) {

			if(cmdUndoRedo.getUndo().peek() instanceof CmdAddShape && print == true) {

				frame.getTextArea().append("UNDO >>> Removed: " + ((CmdAddShape) cmdUndoRedo.getUndo().peek()).getShape().toString() +"\n");
				
			} else if (cmdUndoRedo.getUndo().peek() instanceof CmdBringToBack && print == true) {
				
			
				frame.getTextArea().append("UNDO >>> Bring to front: " + ((CmdBringToBack) cmdUndoRedo.getUndo().peek()).getS().toString() +"\n");
		
			} else if (cmdUndoRedo.getUndo().peek() instanceof CmdBringToFront && print == true) {
				
				frame.getTextArea().append("UNDO >>> Bring to back: " + ((CmdBringToBack) cmdUndoRedo.getUndo().peek()).getS().toString() +"\n");
				
				
			} else if (cmdUndoRedo.getUndo().peek() instanceof CmdDeselectShape && print == true) {
				
				frame.getTextArea().append("UNDO >>> Selected: " + ((CmdBringToBack) cmdUndoRedo.getUndo().peek()).getS().toString().toString() +"\n");
				
			} else if (cmdUndoRedo.getUndo().peek() instanceof CmdRemoveShape && print == true) {
				
				((CmdRemoveShape) cmdUndoRedo.getUndo().peek()).getRemovedShapes().isEmpty();
				if(!((CmdRemoveShape) cmdUndoRedo.getUndo().peek()).getRemovedShapes().isEmpty()  && ((CmdRemoveShape) cmdUndoRedo.getUndo().peek()).getRemovedShapes().size() > 1) {

					if(print == true) {

						frame.getTextArea().append("UNDO >>> Multiple shapes added: ");
					}


					for(int i =0; i<((CmdRemoveShape) cmdUndoRedo.getUndo().peek()).getRemovedShapes().size(); i++) {

						//model.add(removedShapes.get(i));
						if(print == true) {

							frame.getTextArea().append("        Added: " + ((CmdRemoveShape) cmdUndoRedo.getUndo().peek()).getRemovedShapes().get(i).toString());
						}

					}

					if(print == true) {

				
						frame.getTextArea().append("\n");
					}
				} else {

					//model.add(s);

					if(print == true) {
						
						frame.getTextArea().append("UNDO >>> Added: " + ((CmdBringToBack) cmdUndoRedo.getUndo().peek()).getS().toString() +"\n");

					}

				}
				
			} else if (cmdUndoRedo.getUndo().peek() instanceof CmdSelectShape && print == true) {
				
				if(print == true) {
					
					frame.getTextArea().append("UNDO >>> Deselected: " + ((CmdSelectShape) cmdUndoRedo.getUndo().peek()).getO().toString() +"\n");
				}
			
				print = true;
			} else if (cmdUndoRedo.getUndo().peek() instanceof CmdToBack && print == true) {
				
				if(print == true) {
					
					frame.getTextArea().append("UNDO >>> Move to front: " + ((CmdBringToBack) cmdUndoRedo.getUndo().peek()).getS().toString() +"\n");
				}
				
				print = true;
			} else if (cmdUndoRedo.getUndo().peek() instanceof CmdToFront && print == true) {
				
				if(print == true) {
					
					frame.getTextArea().append("Move to front: " + ((CmdBringToBack) cmdUndoRedo.getUndo().peek()).getS().toString() +"\n");
				}
				
				print = true;
			} else if (cmdUndoRedo.getUndo().peek() instanceof CmdUpdateSelectedShapes && print == true) {
				
			
				if(!((CmdUpdateSelectedShapes) cmdUndoRedo.getUndo().peek()).getSelectedShapes().isEmpty()) {
					
					if(print == true) {
						
						frame.getTextArea().append("UNDO >>> Multiple shapes selected: ");
					}
					
					
					for(int i=0; i<((CmdUpdateSelectedShapes) cmdUndoRedo.getUndo().peek()).getSelectedShapes().size(); i++) {
						
						((CmdUpdateSelectedShapes) cmdUndoRedo.getUndo().peek()).getSelectedShapes().get(i).setSelected(true);
						
						if(print == true) {
							
							frame.getTextArea().append("        Selected: " + ((CmdUpdateSelectedShapes) cmdUndoRedo.getUndo().peek()).getSelectedShapes().get(i).toString());
						}
						
						
					}
					
					if(print == true) {

						frame.getTextArea().append("\n");
					}
					
				}
				
				print = true;
				
			} else if (cmdUndoRedo.getUndo().peek() instanceof CmdUpdateShape && print == true) {
				
				if(print == true) {

					frame.getTextArea().append("UNDO >>> Modified: " + ((CmdUpdateShape) cmdUndoRedo.getUndo().peek()).getOriginal() + "\n");
				}
			}
			
			
			cmdUndoRedo.execute();



			if(cmdUndoRedo.getUndo().isEmpty()) {

				frame.getBtnUndo().setEnabled(false);
				frame.getBtnRedo().setEnabled(true);

			} else {

				frame.getBtnUndo().setEnabled(true);
			}
		} 


		if(model.getListOfShapes().isEmpty()) {

			frame.getBtnSelect().setEnabled(false);
		}

		checkIfSelectedShapeExists();
		print = true;




	}

	//REDO
	public void redo() {

		if(!cmdUndoRedo.getRedo().isEmpty()) {

			cmdUndoRedo.unexecute();
			if(cmdUndoRedo.getRedo().isEmpty()) {

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



			for(int i=0; i<model.getListOfShapes().size(); i++) {

				if(model.getListOfShapes().get(i).isSelected() == true) {



					if((i+1)< model.getListOfShapes().size()) {


						CmdToFront cmdToFront = new CmdToFront(model,model.getListOfShapes().get(i));
						
						if(print == true) {
							
							frame.getTextArea().append("Move to front: " + model.getListOfShapes().get(i) +"\n");
						}
						
						print = true;
						cmdToFront.execute();
						cmdUndoRedo.addToCommandList(cmdToFront);
						frame.setDone(true);
						//Collections.swap(model.getListaObjekata(), i, i+1); 
						i=model.getListOfShapes().size()-1;



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




			for(int i=0; i<model.getListOfShapes().size(); i++) {

				if(model.getListOfShapes().get(i).isSelected() == true) {

					if((i+1)<model.getListOfShapes().size()) {



						CmdBringToFront cmdBringToFront = new CmdBringToFront(model,model.getListOfShapes().get(i));
						
						
						if(print==true) {
							
							frame.getTextArea().append("Bring to front: " + model.getListOfShapes().get(i).toString() +"\n");
						}
						
						print =true;
						cmdBringToFront.execute();
						cmdUndoRedo.addToCommandList(cmdBringToFront);
						frame.setDone(true);
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



			for(int i=0; i<model.getListOfShapes().size(); i++) {

				if(model.getListOfShapes().get(i).isSelected() == true) {


					if(i>0) {


						CmdToBack cmdToBack = new CmdToBack(model,model.getListOfShapes().get(i));
						
						if(print == true) {
							
							frame.getTextArea().append("Move to back: " + model.getListOfShapes().get(i).toString() +"\n");
						}
						
						print = true;
						cmdToBack.execute();
						cmdUndoRedo.addToCommandList(cmdToBack);
						frame.setDone(true);
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


			for(int i=0; i<model.getListOfShapes().size(); i++) {

				if(model.getListOfShapes().get(i).isSelected() == true) {



					if(i>0) {


						CmdBringToBack cmdBringToBack = new CmdBringToBack(model,model.getListOfShapes().get(i));
						if(print == true) {

							frame.getTextArea().append("Bring to back: " + model.getListOfShapes().get(i).toString() +"\n");
							//i=0;
						}
						cmdBringToBack.execute();
						
						if(print == false) {
							
							print = true;
						}


						cmdUndoRedo.addToCommandList(cmdBringToBack);
						frame.setDone(true);
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

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	//RUKOVANJE DUGMADIMA
	public int checkIfSelectedShapeExists() {

		int n = 0;

		for(int i=0; i<model.getListOfShapes().size(); i++) {

			if(model.getListOfShapes().get(i).isSelected() == true) {

				n++;
			} 



		}

		if(n==1) {

			subject.setState(true);

		} else if (n==0) {

			subject.setState(false);
		} else if (n>1) {



			frame.getBtnModify().setEnabled(false);
		}

		return n;



	}

	//KOPIRANJE OBLIKA
	/*public static Shape CopyShape(Shape s) {


		Shape o = null;
		try {


			o = (Shape) s.clone();


		} catch (CloneNotSupportedException e) {

			System.out.println("Error!");
		}

		return o;







	}*/


	//KOPIRANJE OBLIKA
	/*public static HexagonAdapter copyHexagon(HexagonAdapter s) {


		HexagonAdapter o = null;
		try {


			o = (HexagonAdapter) s.clone();


		} catch (CloneNotSupportedException e) {

			System.out.println("Error!");
		}

		return o;







	}*/

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public CmdUndoRedo getCmdUndoRedo() {
		return cmdUndoRedo;
	}

	public void setCmdUndoRedo(CmdUndoRedo cmdUndoRedo) {
		this.cmdUndoRedo = cmdUndoRedo;
	}








}