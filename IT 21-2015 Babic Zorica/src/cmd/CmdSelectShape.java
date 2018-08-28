package cmd;



import geometry.Shape;
import mvc.Frame;

public class CmdSelectShape implements Command {
	

	private Shape o;
	public static boolean print = true;


	public  CmdSelectShape(Shape o) {
		
		
		this.o =o;
	}

	@Override
	public void unexecute() {
		
		o.setSelected(false);
		if(print == true) {
			
			Frame.textArea.append("UNDO >>> Deselected: " + o.toString() +"\n");
		}
	
		print = true;
	}

	@Override
	public void execute() {
		
	
		
		o.setSelected(true);
		if(print == true) {
			
			Frame.textArea.append("Selected: " + o.toString() +"\n");
		}
		print = true;
		
		
	}

	

}
