package geometrija;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;



public abstract class Oblik implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Color bojaIvice;
	public boolean selektovan;

	public Oblik(){

	}
	
	
  
	public Oblik(Color bojaIvice){

		bojaIvice = this.bojaIvice;

	}
	
	public Object clone()throws CloneNotSupportedException{  
		
		return super.clone();  
	} 

	public abstract void crtajSe(Graphics g);
	public abstract void selektovan(Graphics g);
	public abstract boolean sadrzi(int x, int y);

	public Color getBojaIvice() {
		return bojaIvice;
	}
	public void setBojaIvice(Color bojaIvice) {
		this.bojaIvice = bojaIvice;
	}
	public boolean isSelektovan() {
		return selektovan;
	}
	
	public void setSelektovan(boolean selektovan) {
		
		this.selektovan = selektovan;

	}
	
	public String getHex(Color c) {
		
		String hex = String.format("#%02x%02x%02x", c.getRed(),c.getGreen(),c.getBlue());
		return hex;
	}
	
	

}
