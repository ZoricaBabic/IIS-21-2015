package cmd;

import geometry.Shape;
import mvc.Frame;
import mvc.Model;


public class CmdAddShape implements Command {
	
	private Model model = new Model();
	private Shape s;
	//private boolean print = true;

	
	public CmdAddShape(Model model, Shape os) {
		this.model = model;
		this.s = os;
		
	}

	@Override
	public void execute() {
		
	
		model.add(s);
		

		
		/*if(print == true) {
			
			Frame.textArea.append("Added: " + s.toString() +"\n");
			
		} */
		
		//Frame.btnSelect.setEnabled(true);
		//print=true;

	}

	@Override
	public void unexecute() {
		

		model.remove(s);
		
		
		/*if(print == true) {
			
			frame.getTextArea().append("UNDO >>> Removed: " + s.toString() +"\n");
		}*/
		
		
		
		/*if(model.getListOfShapes().isEmpty()) {
			
			Frame.btnSelect.setEnabled(false);
		}*/
		
		//print=true;
		
	
	
	}

	//ovo ne treba
	public void setShape(Shape o) {
		
		this.s = o;
		
		
	}


	public Shape getShape() {
		
		return s;
		
	}

	

}
