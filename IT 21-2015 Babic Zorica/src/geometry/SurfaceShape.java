package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color fillColor;
	
	public abstract void fillUp(Graphics g);

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

}
