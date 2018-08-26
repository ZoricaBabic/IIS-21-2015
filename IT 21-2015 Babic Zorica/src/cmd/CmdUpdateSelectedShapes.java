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
	
	//kad se odsleketuje vise 
	private Model model = new Model();
	private ArrayList<Oblik> selectedShapes = new ArrayList<Oblik>();
	public static boolean print = true;
	
	
	public CmdUpdateSelectedShapes(Model model, ArrayList<Oblik> selectedShapes) {
		
		this.model = model;
		this.selectedShapes = selectedShapes;
		
	}
	
	

	@Override
	public void execute() {
		
		if(!selectedShapes.isEmpty()) {
			if(print == true) {
				
				NaslovnaPokretanje.getTextArea().append("Multiple shapes deselected: ");
			}
			
			for(int i=0; i<selectedShapes.size(); i++) {
				
				selectedShapes.get(i).setSelektovan(false);
				
				if(print == true) {
					
					NaslovnaPokretanje.getTextArea().append("        Deselected: " + selectedShapes.get(i).toString());
				}
				
				
			}
			
			if(print == true) {

				NaslovnaPokretanje.getTextArea().append("\n");
			}
		}
		
		print = true;
		
		
	}

	@Override
	public void unexecute() {
		
		if(!selectedShapes.isEmpty()) {
			
			if(print == true) {
				
				NaslovnaPokretanje.getTextArea().append("UNDO >>> Multiple shapes selected: ");
			}
			
			
			for(int i=0; i<selectedShapes.size(); i++) {
				
				selectedShapes.get(i).setSelektovan(true);
				
				if(print == true) {
					
					NaslovnaPokretanje.getTextArea().append("        Selected: " + selectedShapes.get(i).toString());
				}
				
				
			}
			
			if(print == true) {

				NaslovnaPokretanje.getTextArea().append("\n");
			}
			
		}
		
		print = true;
		
	}

}
