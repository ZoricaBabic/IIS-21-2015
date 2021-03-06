package cmd;

import java.util.Collections;

import geometry.Shape;
import mvc.Frame;
import mvc.Model;

public class CmdToFront implements Command {
	
	private Model model = new Model();
	private Shape s;
	private int i=0;

	
	public CmdToFront(Model model, Shape o) {
		
		this.model = model;
		this.s=o;
	}

	@Override
	public void execute() {
		
		
		
		
		i=model.getListOfShapes().indexOf(s);

		Collections.swap(model.getListOfShapes(), i, i+1);  
		
		/*if(print == true) {
			
			Frame.textArea.append("Move to front: " + s +"\n");
		}
		
		print = true;*/
		
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		Collections.swap(model.getListOfShapes(), i+1, i); 
		
		/*if(print == true) {
			
			Frame.textArea.append("UNDO >>> Move to back: " + s +"\n");
		}
		
		print = true;*/
		
	}

	public Shape getS() {
		return s;
	}

	public void setS(Shape s) {
		this.s = s;
	}

}
