package cmd;

import java.util.ArrayList;
import java.util.Stack;

import mvc.Frame;

public class CmdUndoRedo implements Command{
	
	private ArrayList<Command> commandList = new ArrayList<Command>();
	private Stack<Command> undo = new Stack<Command>();
	private Stack<Command> redo = new Stack<Command>();
	private int currentPosition = -1;
	

	public CmdUndoRedo() {
		
		
	}
	
	public void addToCommandList(Command command) {
		
		//Frame.done=true;
		redo.removeAllElements();
		undo.push(command);
		


		
	}
	


	@Override
	public void execute() {
		
	
		//undo
		//Frame.done=true;
		undo.peek().unexecute();
		redo.push(undo.peek());
		undo.pop();
		
		
	}

	@Override
	public void unexecute() {
		
		
		//redo
		redo.peek().execute();
		
		undo.push(redo.peek());
		redo.pop();

		
	
	
		
		
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
