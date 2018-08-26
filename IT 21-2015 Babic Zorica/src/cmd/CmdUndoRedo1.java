package cmd;

import java.util.ArrayList;
import java.util.Stack;

import crtanje.NaslovnaPokretanje;

public class CmdUndoRedo1 implements Command{
	
	private ArrayList<Command> commandList = new ArrayList<Command>();

	
	private Stack<Command> undo = new Stack<Command>();
	private Stack<Command> redo = new Stack<Command>();
	private int currentPosition = -1;
	

	public CmdUndoRedo1() {
		
		
	}
	
	public void addToCommandList(Command command) {
		
		redo.removeAllElements();
		undo.push(command);
		
		System.out.println("Dodale se komanda!");

		
	}
	


	@Override
	public void execute() {
		
	
		//undo
		
		undo.peek().unexecute();
		
		
		redo.push(undo.peek());
		undo.pop();
		
		System.out.println("Brise se komanda");

		

	
		
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
