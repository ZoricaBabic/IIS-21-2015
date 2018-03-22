package cmd;

import java.util.ArrayList;

import geometrija.Oblik;

public class CmdUndo implements Command {
	
	ArrayList<Oblik> commands = new ArrayList<Oblik>();
	private CmdUpdateShape cmdUpdate;
	
	public CmdUndo(CmdUpdateShape cmdUpdate) {
		
		this.cmdUpdate = cmdUpdate;
	}
	
	
	@Override
	public void execute() {
		
		if(commands.isEmpty() == false) {
			
			cmdUpdate.unexecute();
			
		
		}
		
	}

	@Override
	public void unexecute() {
		
		
		//stisnuto je redo
		
	}
	
	
	public void add(Oblik s) {

		commands.add(s);
	}
	public Oblik get(int i) {

		return commands.get(i);
	}

	public boolean remove(Oblik s) {

		return commands.remove(s);
	}

	public boolean removeAllFromList() {

		return commands.removeAll(commands);
	}

}
