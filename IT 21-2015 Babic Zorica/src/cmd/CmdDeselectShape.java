package cmd;



import geometry.Shape;
import mvc.Frame;

public class CmdDeselectShape implements Command{
	
	private Shape s;

	
	public CmdDeselectShape(Shape s) {
		

		this.s = s;
	}

	@Override
	public void execute() {
		
		s.setSelected(false);
		
		/*if(print == true) {
			
			Frame.textArea.append("Deselected: " + s.toString() +"\n");
		}
		
		print = true;*/
	}

	@Override
	public void unexecute() {
		s.setSelected(true);
		
		/*if(print == true) {
			
			Frame.textArea.append("UNDO >>> Selected: " + s.toString() +"\n");
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
