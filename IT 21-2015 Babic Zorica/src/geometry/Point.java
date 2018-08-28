package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;


	public Point(){

	}

	public Point(int x, int y){
		
		this.x = x;
		this.y = y; 
	}

	public Point (int x, int y, Color border){

		this.x = x;
		this.y = y; 
		setBorderColor(border);
	}

	/*public void pomeriNa(int x, int y){
		setX(this.x);
		setY(this.y);
	}

	public void pomeriZa(int x, int y){
		setX(x+this.x);
		setY(y+this.y);
	}*/

	public double distance(Point t){

		double dx = x-t.getX();
		double dy = y-t.getY();
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance; 

	}

	public String toString(){
		
		
		return "Point: (" + x +"," +y+")"+", "+"outline: "+getHex(getBorderColor())+", "+"Selected? " + isSelected();
		
		
				
	}

	public boolean equals(Object o){

		if(o instanceof Point){
			Point helper = (Point) o;
			if(x == helper.x && y == helper.y){
				return true;
			}
			return false;
		}
		return false; 
	}

	public int compareTo(Object o){

		if(o instanceof Point){
			Point helper = (Point) o;
			return (int) this.distance(new Point(0, 0)) - (int)helper.distance(new Point(0, 0));
		}
		return 0;
	}


	public boolean contains(int x, int y){
		Point clickPlace = new Point(x, y);
		if(clickPlace.distance(this)<=2)
			return true;
		else
			return false;
	}

	public void selected(Graphics g){
		
		
		g.setColor(Color.BLUE);
		g.drawRect(x-3, y-3,6, 6);
		setSelected(true);

	}



	public void draw(Graphics g){

		g.setColor(getBorderColor());
		g.drawLine(x+2, y, x-2, y);
		g.drawLine(x,  y-2, x, y+2);
		/*if(isSelektovan()){
			selektovan(g);
		}*/

	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}



}
