package cmd;

import java.util.ArrayList;

import crtanje.Model;
import crtanje.NaslovnaPokretanje;
import geometrija.HexagonAdapter;
import geometrija.Krug;
import geometrija.Kvadrat;
import geometrija.Linija;
import geometrija.Oblik;
import geometrija.Pravougaonik;
import geometrija.Tacka;
import hexagon.Hexagon;

public class CmdUpdateSelectedShapes implements Command {
	
	private Model model = new Model();
	private ArrayList<Oblik> selectedShapes = new ArrayList<Oblik>();
	
	
	public CmdUpdateSelectedShapes(Model model, ArrayList<Oblik> selectedShapes) {
		
		this.model = model;
		this.selectedShapes = selectedShapes;
		
	}
	
	

	@Override
	public void execute() {
		
		if(!selectedShapes.isEmpty()) {
			NaslovnaPokretanje.getTextArea().append("Multiple shapes deselected: \n");
			for(int i=0; i<selectedShapes.size(); i++) {
				
				selectedShapes.get(i).setSelektovan(false);
				NaslovnaPokretanje.getTextArea().append("             " + selectedShapes.get(i).toString() +"\n");
				
			}
		}
		
		
	}

	@Override
	public void unexecute() {
		
		if(!selectedShapes.isEmpty()) {
			
			NaslovnaPokretanje.getTextArea().append("Multiple shapes selected: \n");
			for(int i=0; i<selectedShapes.size(); i++) {
				
				selectedShapes.get(i).setSelektovan(true);
				NaslovnaPokretanje.getTextArea().append("             " + selectedShapes.get(i).toString() +"\n");
				
			}
		}
		
	}

}
