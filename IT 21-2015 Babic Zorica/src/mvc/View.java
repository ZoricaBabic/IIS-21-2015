package mvc;

import java.awt.Graphics;

import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Square;




public class View extends JPanel  {




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//dodato
	private Model model;



	public void setModel(Model model) {

		this.model = model;
	}




	@Override
	protected void paintComponent(Graphics g){

		super.paintComponent(g);

		if (model != null) {

			Iterator<Shape> it = model.getListOfShapes().iterator();
				
			while(it.hasNext()) {
				
			it.next().draw(g);
			}
			repaint();
		}

		for(Shape o: model.getListOfShapes()){

			o.draw(g);


			if(o instanceof Rectangle){


				((Rectangle) o).fillUp(g);
			} 
			else if (o instanceof Square){


				((Square) o).fillUp(g);
			} 
			else if(o instanceof Circle){


				((Circle) o).fillUp(g);
				
				
			}  

		}
		
		for(Shape o: model.getListOfShapes()){
			
			if(o instanceof HexagonAdapter) {
				
				
			} else {
				
				if(o.isSelected() == true) {
					
					o.selected(g);
					
				} else {
					
					o.setSelected(false);
				}
			}
			
		
			
		}
		
		

	}

}

