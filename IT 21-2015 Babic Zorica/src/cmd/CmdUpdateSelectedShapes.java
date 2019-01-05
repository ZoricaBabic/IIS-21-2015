package cmd;

import java.util.ArrayList;

import geometry.Shape;
import mvc.Frame;


public class CmdUpdateSelectedShapes implements Command {
	
	//kad se odsleketuje vise 

	private ArrayList<Shape> selectedShapes = new ArrayList<Shape>();

	
	
	public CmdUpdateSelectedShapes(ArrayList<Shape> selectedShapes) {

	
		this.selectedShapes = selectedShapes;
		
	}
	
	

	@Override
	public void execute() {
		
		if(!selectedShapes.isEmpty()) {
			
			/*if(print == true) {
				
				Frame.textArea.append("Multiple shapes deselected: ");
			}*/
			
			for(int i=0; i<selectedShapes.size(); i++) {
				
				selectedShapes.get(i).setSelected(false);
				
				/*if(print == true) {
					
					Frame.textArea.append("        Deselected: " + selectedShapes.get(i).toString());
				}*/
				
				
			}
			
			/*if(print == true) {

				Frame.textArea.append("\n");
			}*/
		}
		
		//print = true;
		
		
	}

	@Override
	public void unexecute() {
		
		if(!selectedShapes.isEmpty()) {
			
			/*if(print == true) {
				
				Frame.textArea.append("UNDO >>> Multiple shapes selected: ");
			}*/
			
			
			for(int i=0; i<selectedShapes.size(); i++) {
				
				selectedShapes.get(i).setSelected(true);
				
				/*if(print == true) {
					
					Frame.textArea.append("        Selected: " + selectedShapes.get(i).toString());
				}*/
				
				
			}
			
			/*if(print == true) {

				Frame.textArea.append("\n");
			}*/
			
		}
		
		//print = true;
		
	}



	public ArrayList<Shape> getSelectedShapes() {
		return selectedShapes;
	}



	public void setSelectedShapes(ArrayList<Shape> selectedShapes) {
		this.selectedShapes = selectedShapes;
	}

}
