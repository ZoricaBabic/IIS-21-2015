package cmd;

import java.util.ArrayList;

import crtanje.Model;
import geometrija.HexagonAdapter;
import geometrija.Kvadrat;
import geometrija.Oblik;
import geometrija.Pravougaonik;

public class CmdRemoveShape implements Command {

	private Model model = new Model();
	private Oblik o;
	private int count = 0;
	private ArrayList<Oblik> removedShapes = new ArrayList<Oblik>();

	public CmdRemoveShape(Model model, Oblik o) {

		this.model = model;
		this.o = o;
	}

	public CmdRemoveShape(Model model) {

		this.model = model;

	}


	public CmdRemoveShape(Model model, ArrayList<Oblik> removedShapes) {

		this.model = model;
		this.removedShapes = removedShapes;
	}

	@Override
	public void execute() {

		if(!removedShapes.isEmpty()  && removedShapes.size() > 1) {

			for(int i =0; i<removedShapes.size(); i++) {

				model.remove(removedShapes.get(i));
			}

		} else {

			model.remove(o);
		}






	}

	@Override
	public void unexecute() {


		if(!removedShapes.isEmpty() && removedShapes.size() > 1) {

			for(int i =0; i<removedShapes.size(); i++) {

				model.add(removedShapes.get(i));
			}
		} else {

			model.add(o);
		}









	}


	public Oblik getO() {
		return o;
	}


	public void setO(Oblik o) {
		this.o = o;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
