package crtanje;

import java.awt.Graphics;
import java.util.Iterator;

import javax.jws.WebParam.Mode;
import javax.swing.JComponent;
import javax.swing.JPanel;

import geometrija.HexagonAdapter;
import geometrija.Krug;
import geometrija.Kvadrat;
import geometrija.Oblik;
import geometrija.PovrsinskiOblik;
import geometrija.Pravougaonik;
import hexagon.Hexagon;



public class ProstorZaCrtanje extends JPanel  {




	//dodato
	private Model model;



	public void setModel(Model model) {

		this.model = model;
	}




	@Override
	protected void paintComponent(Graphics g){

		super.paintComponent(g);

		if (model != null) {

			Iterator<Oblik> it = model.getListaObjekata().iterator();
				
			while(it.hasNext()) {
				
			it.next().crtajSe(g);
			}
			repaint();
		}

		for(Oblik o: model.getListaObjekata()){

			o.crtajSe(g);


			if(o instanceof Pravougaonik){


				((Pravougaonik) o).popuni(g);
			} 
			else if (o instanceof Kvadrat){


				((Kvadrat) o).popuni(g);
			} 
			else if(o instanceof Krug){


				((Krug) o).popuni(g);
				
				
			}  

		}
		
		for(Oblik o: model.getListaObjekata()){
			
			if(o instanceof HexagonAdapter) {
				
				//System.out.println("selektovan " + o.isSelektovan());
				
			} else {
				
				if(o.isSelektovan() == true) {
					
					o.selektovan(g);
					
				} else {
					
					o.setSelektovan(false);
				}
			}
			
		
			
		}
		
		
		

		
		
		

		

	}

}

