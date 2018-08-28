package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;



public abstract class Shape implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Color borderColor;
	public boolean selected;

	public Shape(){

	}
	
	
  
	public Shape(Color borderColor){

		borderColor = this.borderColor;

	}
	
	public Object clone()throws CloneNotSupportedException{  
		
		return super.clone();  
	} 

	public abstract void draw(Graphics g);
	public abstract void selected(Graphics g);
	public abstract boolean contains(int x, int y);

	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		
		this.selected = selected;

	}
	
	public String getHex(Color c) {
		
		String hex = String.format("#%02x%02x%02x", c.getRed(),c.getGreen(),c.getBlue());
		return hex;
	}
	
	

}
