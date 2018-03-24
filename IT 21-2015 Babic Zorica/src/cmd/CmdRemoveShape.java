package cmd;

import crtanje.Model;
import geometrija.HexagonAdapter;
import geometrija.Kvadrat;
import geometrija.Oblik;
import geometrija.Pravougaonik;

public class CmdRemoveShape implements Command {

	private Model model = new Model();
	private Oblik o;

	public CmdRemoveShape(Model model, Oblik o) {

		this.model = model;
		this.o = o;
	}

	@Override
	public void execute() {
		
			model.remove(o);


	}

	@Override
	public void unexecute() {

		model.add(o);

	}


	public Oblik getO() {
		return o;
	}


	public void setO(Oblik o) {
		this.o = o;
	}

}
