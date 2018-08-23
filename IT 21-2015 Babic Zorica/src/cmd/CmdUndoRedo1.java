package cmd;

import java.util.ArrayList;
import java.util.Stack;

public class CmdUndoRedo1 implements Command{
	
	private ArrayList<Command> commandList = new ArrayList<Command>();
	
	private Stack<Command> undo = new Stack();
	private Stack<Command> redo = new Stack();
	private int currentPosition = -1;
	

	public CmdUndoRedo1() {
		
		
	}
	
	public void addToCommandList(Command command) {
		
		redo.removeAllElements();
		
		System.out.println("Duzina redo: " + redo.size());
		
		
		undo.push(command);
		
		System.out.println("Duzzina undo: " + undo.size());
	

		
		/*if(currentPosition != commandList.size()-1) {
			
			while(currentPosition+1 < commandList.size()) {
				
				commandList.remove(currentPosition);
				currentPosition++;
				System.out.println("Brise se!");
			}
			
		}
		
		commandList.add(command);
		currentPosition = commandList.size()-1;*/
		
		
		
	
		

		
	
		
	}
	


	@Override
	public void execute() {
		
	
		
		undo.peek().unexecute();
		redo.push(undo.peek());
		undo.pop();
		
		//undo
		/*if(currentPosition != -1) {
			
			commandList.get(currentPosition).unexecute();
			currentPosition--;
		}*/
		
		

	
		
	}

	@Override
	public void unexecute() {
		
		
		//redo
		redo.peek().execute();
		undo.push(redo.peek());
		redo.pop();
		
		/*if(currentPosition != commandList.size()-1) {
			
			currentPosition++;
			commandList.get(currentPosition).execute();
		} else {
			
			commandList.get(currentPosition).execute();
		}*/
		
		
	
	
		
		
	}
	
	public ArrayList<Command> getCommandList() {
		return commandList;
	}

	public void setCommandList(ArrayList<Command> commandList) {
		this.commandList = commandList;
	}




	public int getCurrentPosition() {
		return currentPosition;
	}




	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Stack<Command> getUndo() {
		return undo;
	}

	public void setUndo(Stack<Command> undo) {
		this.undo = undo;
	}

	public Stack<Command> getRedo() {
		return redo;
	}

	public void setRedo(Stack<Command> redo) {
		this.redo = redo;
	}

	
}
