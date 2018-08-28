package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point center;
	private int r;
	
	public Circle(){
	}
	
	public Circle(Point center, int r){
		
	this.center = center;
		this.r = r;
	}
	public Circle(Point center, int r,Color borderColor){
		
		this(center, r);
		setBorderColor(borderColor);
	}
	public Circle(Point center, int r,Color borderColor, Color fillColor){
		this(center, r,borderColor);
		setFillColor(fillColor);
	}
	
	
	/*public void pomeriNa(int x, int y){
		center.pomeriNa(x, y);
	}
	public void pomeriZa(int x, int y){
		center.pomeriZa(x, y);
	}*/
	
	
	public double surface(){
		return r * r * Math.PI;
	}
	public double volume(){
		return 2 * r * Math.PI;
	}

	public String toString(){
		
		//return "Centar="+centar+", poluprecnik="+r+", " + "Selektovan? " + isSelektovan() + ".";
	
		//return "Circle: (" + centar.getX() + "," + centar.getY() + "), outline: "+getHex(getBojaIvice())+", "+"Selected? " + isSelektovan();
		return "Circle: (" + center.getX() + "," + center.getY() + "), radius: "  + getR() + ", outline: " + getHex(getBorderColor())+ ", fill: " + getHex(getFillColor()) + ", Selected? " + isSelected(); 
				
				
	}
	
	
	public boolean equals(Object obj){
		
		if(obj instanceof Circle){
			
			Circle help = (Circle) obj;
		
			if(center.equals(help.center) && r == help.r) {
			
				return true;
			}
				
			else {
				
				return false;
			}
				
		}
		else
			return false;
	}
	public boolean contains(int x, int y){
		
		Point clickPlace = new Point(x, y);
		if(clickPlace.distance(center)<=r)
			return true;
		else
			return false;
		
	}
	
	public void selected(Graphics g) {
		
		g.setColor(Color.BLUE);
		new Line(new Point(center.getX(), center.getY()-r), new Point(center.getX(), center.getY() + r)).selected(g);
		new Line(new Point(center.getX()-r, center.getY()), new Point(center.getX()+r, center.getY())).selected(g);
		setSelected(true);
		

	}
	
	

	public void draw(Graphics g){
		
		g.setColor(getBorderColor());
		g.drawOval(center.getX()-r, center.getY()-r, 2*r, r+r);
		/*if(isSelektovan())
			selektovan(g);*/
	}
	public void fillUp(Graphics g) {
		
		g.setColor(getFillColor());
		g.fillOval(center.getX()-r+1, center.getY()-r+1, 2*r-2, r+r-2);
		
	}
	public int compareTo(Object o) {
		if(o instanceof Circle){
			Circle help = (Circle) o;
			return (int) (this.r - help.r);
		}
		else
			return 0;
	}
	
	
	public Point getCenter() {
		return center;
	}
	public int getR() {
		return r;
	}
	public void setCenter(Point center) {
		this.center = center;
	}
	public void setR(int r) {
		this.r = r;
	}
	

	
	
	
}
