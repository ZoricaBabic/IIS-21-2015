package cmd;

import java.util.ArrayList;

import geometry.Shape;
import mvc.Frame;
import mvc.Model;


public class CmdRemoveShape implements Command {

	private Model model = new Model();
	private Shape s;
	private int count = 0;
	private ArrayList<Shape> removedShapes = new ArrayList<Shape>();


	public CmdRemoveShape(Model model, Shape o) {

		this.model = model;
		this.s = o;
	}

	public CmdRemoveShape(Model model) {

		this.model = model;

	}


	public CmdRemoveShape(Model model, ArrayList<Shape> removedShapes) {

		this.model = model;
		this.removedShapes = removedShapes;
	}

	@Override
	public void execute() {
		
	

		if(!removedShapes.isEmpty()  && removedShapes.size() > 1) {

			/*if(print ==true) {

				Frame.textArea.append("Multiple shapes removed: ");
			}*/


			for(int i =0; i<removedShapes.size(); i++) {

				model.remove(removedShapes.get(i));

				/*if(print == true) {

					Frame.textArea.append("        Removed: " + removedShapes.get(i).toString());
				}*/

			}

			/*if(print == true) {

				Frame.textArea.append("\n");
			}*/


		} else {

			model.remove(s);
			/*if(print == true) {

				Frame.textArea.append("Removed: " + s +"\n");
			}
			print = true;*/

		}



		//print = true;


	}

	@Override
	public void unexecute() {


		if(!removedShapes.isEmpty() && removedShapes.size() > 1) {

			/*if(print == true) {

				Frame.textArea.append("UNDO >>> Multiple shapes added: ");
			}*/


			for(int i =0; i<removedShapes.size(); i++) {

				model.add(removedShapes.get(i));
				/*if(print == true) {

					Frame.textArea.append("        Added: " + removedShapes.get(i).toString());
				}*/

			}

			/*if(print == true) {

				Frame.textArea.append("\n");
			}*/
		} else {

			model.add(s);

			/*if(print == true) {
				
				Frame.textArea.append("UNDO >>> Added: " + s +"\n");

			}*/

		}


		//print = true;




	}


	public Shape getS() {
		return s;
	}


	public void setS(Shape o) {
		this.s = o;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ArrayList<Shape> getRemovedShapes() {
		return removedShapes;
	}

	public void setRemovedShapes(ArrayList<Shape> removedShapes) {
		this.removedShapes = removedShapes;
	}

}
