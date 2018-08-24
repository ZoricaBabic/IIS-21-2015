package cmd;



import java.awt.Color;

import crtanje.Controller;
import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.HexagonAdapter;
import geometrija.Krug;
import geometrija.Kvadrat;
import geometrija.Linija;
import geometrija.Oblik;
import geometrija.PovrsinskiOblik;
import geometrija.Pravougaonik;
import geometrija.Tacka;
import hexagon.Hexagon;


public class CmdUpdateShape implements Command {

	private Oblik original;
	private Oblik newState;
	private Oblik oldState;
	public static Model model;

	public CmdUpdateShape(Oblik original,Oblik newState) {


		this.original = original;
		this.newState = newState;

	}

	public CmdUpdateShape() {


	}

	@Override
	public void execute() {

		if(original instanceof Tacka) {


			oldState = new Tacka();

			((Tacka) oldState).setX(((Tacka) original).getX());
			((Tacka) oldState).setY(((Tacka) original).getY());
			oldState.setBojaIvice(original.getBojaIvice());
			oldState.setSelektovan(original.isSelektovan());


			((Tacka) original).setX(((Tacka) newState).getX());
			((Tacka) original).setY(((Tacka) newState).getY());
			original.setBojaIvice(newState.getBojaIvice());
			original.setSelektovan(newState.isSelektovan());
			
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);
			
			


		} else if(original instanceof Linija) {

			oldState = new Linija();

			((Linija) oldState).settPocetna(((Linija) original).gettPocetna());
			((Linija) oldState).settKrajnja(((Linija) original).gettKrajnja());
			oldState.setBojaIvice(original.getBojaIvice());
			oldState.setSelektovan(original.isSelektovan());


			((Linija) original).settPocetna(((Linija) newState).gettPocetna());
			((Linija) original).settKrajnja(((Linija) newState).gettKrajnja());

			original.setBojaIvice(newState.getBojaIvice());
			original.setSelektovan(newState.isSelektovan());
			
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);


		} else if(original instanceof Krug) {

			oldState=new Krug();

			((Krug) oldState).setCentar(((Krug) original).getCentar());
			((Krug) oldState).setR(((Krug) original).getR());
			oldState.setBojaIvice(original.getBojaIvice());
			((Krug) oldState).setBojaUnutrasnjosti(((Krug) original).getBojaUnutrasnjosti());
			oldState.setSelektovan(original.isSelektovan());

			((Krug) original).setCentar(((Krug) newState).getCentar());
			((Krug) original).setR(((Krug) newState).getR());
			original.setBojaIvice(newState.getBojaIvice());
			((Krug) original).setBojaUnutrasnjosti(((Krug) newState).getBojaUnutrasnjosti());
			original.setSelektovan(newState.isSelektovan());




			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);



		} else if(original instanceof Pravougaonik) {



			oldState = new Pravougaonik();

			((Pravougaonik) oldState).settGoreLevo(((Pravougaonik) original).gettGoreLevo());
			((Pravougaonik) oldState).setSirina(((Pravougaonik) original).getSirina());
			((Pravougaonik) oldState).setDuzinaStranice(((Pravougaonik) original).getDuzinaStranice());
			((Pravougaonik) oldState).setBojaIvice(original.getBojaIvice());
			((Pravougaonik) oldState).setBojaUnutrasnjosti(((Pravougaonik) original).getBojaUnutrasnjosti());
			oldState.setSelektovan(original.isSelektovan());

			((Pravougaonik) original).settGoreLevo(((Pravougaonik) newState).gettGoreLevo());
			((Pravougaonik) original).setSirina(((Pravougaonik) newState).getSirina());
			((Pravougaonik) original).setDuzinaStranice(((Pravougaonik) newState).getDuzinaStranice());
			((Pravougaonik) original).setBojaIvice(newState.getBojaIvice());
			((Pravougaonik) original).setBojaUnutrasnjosti(((Pravougaonik) newState).getBojaUnutrasnjosti());
			original.setSelektovan(newState.isSelektovan());
			
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);


		}  else if(original instanceof Kvadrat) {

			oldState = new Kvadrat();



			((Kvadrat) oldState).settGoreLevo(((Kvadrat) original).gettGoreLevo());
			((Kvadrat) oldState).setDuzinaStranice(((Kvadrat) original).getDuzinaStranice());
			((Kvadrat) oldState).setBojaIvice(original.getBojaIvice());
			((Kvadrat) oldState).setBojaUnutrasnjosti(((Kvadrat) original).getBojaUnutrasnjosti());
			oldState.setSelektovan(original.isSelektovan());

			((Kvadrat) original).settGoreLevo(((Kvadrat) newState).gettGoreLevo());
			((Kvadrat) original).setDuzinaStranice(((Kvadrat) newState).getDuzinaStranice());
			((Kvadrat) original).setBojaIvice(newState.getBojaIvice());
			((Kvadrat) original).setBojaUnutrasnjosti(((Kvadrat) newState).getBojaUnutrasnjosti());
			original.setSelektovan(newState.isSelektovan());
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);
			
			
		} else if (original instanceof HexagonAdapter) {
			
		
			
			oldState = new HexagonAdapter();
			((HexagonAdapter) oldState).setHexagon(new Hexagon(((HexagonAdapter) original).getHexagon().getX(),((HexagonAdapter) original).getHexagon().getY(),((HexagonAdapter) original).getHexagon().getR()));
			((HexagonAdapter) oldState).getHexagon().setAreaColor(((HexagonAdapter) original).getHexagon().getAreaColor());
			((HexagonAdapter) oldState).getHexagon().setBorderColor(((HexagonAdapter) original).getHexagon().getBorderColor());
			oldState.setSelektovan(original.isSelektovan());
			
			System.out.println("OLD STATE: " + oldState);
			
			
			//menjam original
			
			((HexagonAdapter) original).setHexagon(((HexagonAdapter) newState).getHexagon());
			
			System.out.println("ORIGINAL JE SAD: " + original.toString());
			
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);
			

		}
		
		NaslovnaPokretanje.getTextArea().append("Modified: " + original + "\n");

	}

	@Override
	public void unexecute() {

		if(original instanceof Tacka) {


			((Tacka) original).setX(((Tacka) oldState).getX());
			((Tacka) original).setY(((Tacka) oldState).getY());
			original.setBojaIvice(oldState.getBojaIvice());
			original.setSelektovan(oldState.isSelektovan());
			
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);


		} else if(original instanceof Linija) {



			((Linija) original).settPocetna(((Linija) oldState).gettPocetna());
			((Linija) original).settKrajnja(((Linija) oldState).gettKrajnja());
			original.setBojaIvice(oldState.getBojaIvice());
			original.setSelektovan(oldState.isSelektovan());
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);

		} else if(original instanceof Krug) {



			((Krug) original).setCentar(((Krug) oldState).getCentar());
			((Krug) original).setR(((Krug) oldState).getR());
			((Krug) original).setBojaIvice(oldState.getBojaIvice());
			((Krug) original).setBojaUnutrasnjosti(((Krug) oldState).getBojaUnutrasnjosti());
			original.setSelektovan(oldState.isSelektovan());
			
			
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);





		} else if(original instanceof Pravougaonik) {



			((Pravougaonik) original).setSirina(((Pravougaonik) oldState).getSirina());
			((Pravougaonik) original).setDuzinaStranice(((Pravougaonik) oldState).getDuzinaStranice());
			((Pravougaonik) original).settGoreLevo(((Pravougaonik) oldState).gettGoreLevo());
			((Pravougaonik) original).setBojaIvice(((Pravougaonik) oldState).getBojaIvice());
			((Pravougaonik) original).setBojaUnutrasnjosti(((Pravougaonik) oldState).getBojaUnutrasnjosti());
			original.setSelektovan(oldState.isSelektovan());
			
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);



		} else if(original instanceof Kvadrat) {





			((Kvadrat) original).setDuzinaStranice(((Kvadrat) oldState).getDuzinaStranice());
			((Kvadrat) original).settGoreLevo(((Kvadrat) oldState).gettGoreLevo());
			((Kvadrat) original).setBojaIvice(((Kvadrat) oldState).getBojaIvice());
			((Kvadrat) original).setBojaUnutrasnjosti(((Kvadrat) oldState).getBojaUnutrasnjosti());
			original.setSelektovan(oldState.isSelektovan());
			
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);

		} else if(original instanceof HexagonAdapter) {
			
			/*((HexagonAdapter) original).getHexagon().setX(((HexagonAdapter) oldState).getHexagon().getX());
			((HexagonAdapter) original).getHexagon().setY(((HexagonAdapter) oldState).getHexagon().getY());
			((HexagonAdapter) original).getHexagon().setR(((HexagonAdapter) oldState).getHexagon().getR());
			((HexagonAdapter) original).getHexagon().setAreaColor(((HexagonAdapter) oldState).getHexagon().getAreaColor());
			((HexagonAdapter) original).getHexagon().setBorderColor(((HexagonAdapter) oldState).getHexagon().getBorderColor());
			((HexagonAdapter) original).getHexagon().setSelected(((HexagonAdapter) oldState).selektovan);*/
			
			((HexagonAdapter) original).setHexagon(((HexagonAdapter) oldState).getHexagon());
			
			model.getListaObjekata().set(model.getListaObjekata().indexOf(original), original);
			
			
		}
		
		NaslovnaPokretanje.getTextArea().append("Modified: " + original + "\n");
		

	}

	public Oblik getOriginal() {
		return original;
	}

	public void setOriginal(Oblik original) {
		this.original = original;
	}

	public Oblik getNewState() {
		return newState;
	}

	public void setNewState(Oblik newState) {
		this.newState = newState;
	}

	public Oblik getOldState() {
		return oldState;
	}

	public void setOldState(Oblik oldState) {
		this.oldState = oldState;
	}

	public Model getModel() {
		return model;
	}

	public static void setModel(Model model) {
		model = model;
	}




}
