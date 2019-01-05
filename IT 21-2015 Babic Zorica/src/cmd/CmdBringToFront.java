package cmd;

import java.util.Collections;

import geometry.Shape;
import mvc.Frame;
import mvc.Model;

public class CmdBringToFront implements Command {
	
	private Model model = new Model();
	private Shape s;
	private int i=0;
	
	public CmdBringToFront (Model model, Shape s) {
		
		this.model=model;
		this.s=s;
	}
	
	

	@Override
	public void execute() {
		

		
		i = model.getListOfShapes().indexOf(s);
		
		Collections.swap(model.getListOfShapes(), i, model.getListOfShapes().size()-1); 
		
		/*if(print==true) {
			
			Frame.textArea.append("Bring to front: " + s +"\n");
		}
		
		print =true;*/
		
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		
		Collections.swap(model.getListOfShapes(), model.getListOfShapes().size()-1, i); 
		
		/*if(print == true) {
			
			Frame.textArea.append("UNDO >>> Bring to back: " + s +"\n");
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
