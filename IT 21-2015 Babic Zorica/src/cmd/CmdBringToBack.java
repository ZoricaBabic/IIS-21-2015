package cmd;

import java.util.Collections;

import geometry.Shape;
import mvc.Frame;
import mvc.Model;

public class CmdBringToBack implements Command{
	
	private Model model = new Model();
	private Shape s; 
	private int i=0;

	
	
	public CmdBringToBack() {
		
	
	}
	
	public CmdBringToBack( Model model,Shape s) {
		
		this.model=model;
		this.s =s;
		
		
		
	}

	@Override
	public void execute() {
		
		
		i= model.getListOfShapes().indexOf(s);
		Collections.swap(model.getListOfShapes(), i, 0); 
		
		/*if(print == true) {
			
			Frame.textArea.append("Bring to back: " + s +"\n");
			//i=0;
		} 
		
		print = true;*/
		
			
		
		
		//frame.getTextArea().append("Bring to back: " + o +"\n");
		
	}

	@Override
	public void unexecute() {
		// TODO Auto-generated method stub
		Collections.swap(model.getListOfShapes(), 0, i); 
		
		/*if(print == true) {
			
			Frame.textArea.append("UNDO >>> Bring to front: " + s +"\n");
		}
		
		print=true;*/
		
		
	}

	public Shape getS() {
		return s;
	}

	public void setS(Shape s) {
		this.s = s;
	}

	

}
