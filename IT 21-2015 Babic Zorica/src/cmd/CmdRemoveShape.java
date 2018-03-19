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

		if(o instanceof HexagonAdapter) {


			for(int i =0; i<model.getListaObjekata().size(); i++) {

				if(((HexagonAdapter) o).getHexagon().equals(((HexagonAdapter) model.getListaObjekata().get(i)).getHexagon())) {

					model.getListaObjekata().remove(i);


				}

			}

		}else if(o instanceof Pravougaonik) {
			
			
			int k = model.getListaObjekata().indexOf(o);
			
			System.out.println(k);
			for(int i=-1; i<model.getListaObjekata().size(); i++) {
				
				
			}
			

		} else {

			model.remove(o);


		}









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
