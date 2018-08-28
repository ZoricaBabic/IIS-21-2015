package geometry;

import java.awt.Color;
import java.awt.Graphics;


public class Line extends Shape{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point start;
	private Point end;
	 
	

	public Line(){

	}
	
	public Line(Point start, Point end){
		this.start = start;
		this.end = end; 
	}
	
	public Line(Point start, Point end, Color borderColor){
		this.start = start;
		this.end = end;
		this.borderColor = borderColor; 
	}

	/*public void pomeriZa(int x, int y){
		start.setX(start.getX()+x);
		start.setY(start.getY()+y);
		end.setX(end.getX()+x);
		end.setY(end.getY()+y);		
	}*/

	public double length(){
		return start.distance(end);
	}
	
	public String toString(){
		
		
		/*return tPocetna+"-->"+tKrajnja + "Selektovan? " + isSelektovan() + ".";
		
		return "Line: startPoint: +", "+"outline: "+getHex(getBojaIvice())+", "+"Selected? " + isSelektovan();";*/
		

		
		return "Line: startPoint (" + start.getX() + "," + start.getY() + "), endPoint (" + end.getX() + "," + end.getY() + "), outline: " + getHex(getBorderColor()) + ", Selected? " + isSelected();
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Line){
			Line help = (Line) obj;
			if(start.equals(help.getStart()) && end.equals(help.getEnd()))
				return true;
			else
				return false;

		}
		else
			return false;
	}
	
	public Point lineMiddle(){
		
		int middleX = (start.getX() + end.getX()) / 2;
		int middleY = (start.getY() + end.getY()) / 2;
		return new Point(middleX, middleY);
	}
	
	public boolean contains(int x, int y){
		Point clickPlace = new Point(x, y);
		if(clickPlace.distance(start)+clickPlace.distance(end)-this.length()<0.5)
			return true;
		else 
			return false;
	}
	
	public void selected(Graphics g){
		g.setColor(Color.BLUE);
		start.selected(g);
		end.selected(g);
		lineMiddle().selected(g);
		setSelected(true);
	}
	
	public void draw(Graphics g){

		g.setColor(getBorderColor());
		g.drawLine(start.getX(), start.getY(), end.getX(), end.getY());
	
		
	}


	public int compareTo(Object o) {
		if(o instanceof Line){
			Line help = (Line) o;
			return (int) (this.length() - help.length());
		}
		else
			return 0;
	}
	
	public Point getStart(){
		return start;
	}
	
	public Point getEnd(){
		return end;
	}
	
	public void setStart(Point start){
		this.start = start;
	}
	
	public void setEnd(Point end){
		this.end = end;
	}
	
	public Color getBorderColor() {
		return borderColor;
	}
	
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

}
