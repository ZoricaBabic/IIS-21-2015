package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Square {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;

	public Rectangle(){

	}
	
	public Rectangle(Point pUpLeft,int length,int width){

		super(pUpLeft,length);
		this.width = width; 
	}

	public Rectangle(Point pUpLeft,int length,int width,Color borderColor){

		this(pUpLeft,length,width);
		setBorderColor(borderColor);
	}

	public Rectangle(Point pUpLeft,int length,int width,Color borderColor, Color fillColor){

		this(pUpLeft,length,width,borderColor);
		setFillColor(fillColor);
	}
	
	
	public double volume(){

		return 2*sideLength + 2*width; 
	}
 
	public double surface(){

		return sideLength*width;
	}

	/*public void pomeriNa(int x, int y){

		pUpLeft.pomeriNa(x, y);
	}

	public void pomeriZa(int x, int y){

		pUpLeft.pomeriZa(x, y);
	}*/


	public Line diagonal(){

		return new Line(pUpLeft, new Point(pUpLeft.getX()+sideLength, pUpLeft.getX()+width));
	}

	public Point center(){

		return diagonal().lineMiddle();
	}

	public String toString(){

		//return "Tacka gore levo je: " + tGoreLevo + "duzina: " + duzinaStranice + ", sirina: "+sirina + "Selektovan? " + isSelektovan() + ".";
		
		return "Rectangle: (" + pUpLeft.getX() + ","+pUpLeft.getY()+"), width: " + sideLength + ", height: " + width + ", outline: " + getHex(getBorderColor())+ ", fill: " + getHex(getFillColor()) + ", Selected? " + isSelected(); 
	}

	public boolean equals(Object o){
		
	/*	System.out.println(duzinaStranice);
		System.out.println(sirina);
		
		System.out.println(((Kvadrat) o).getDuzinaStranice());
		System.out.println(((Pravougaonik) o).getSirina());*/
		
		if(o instanceof Rectangle){
			
			Rectangle help = (Rectangle) o;
			
			/*((Kvadrat) pomocni).gettGoreLevo();
			
			((Kvadrat) pomocni).getDuzinaStranice();*/
			
			if(pUpLeft.equals(((Square) help).getpUpLeft()) && sideLength == ((Square) help).getSideLength() && width == help.width){
				return true;
			}
			return false;
		}
		return false; 
	}
	
	public boolean contains(int x, int y) {
		if(this.getpUpLeft().getX()<=x 
				&& x<=(this.getpUpLeft().getX() + sideLength)
				&& this.getpUpLeft().getY()<=y 
				&& y<=(this.getpUpLeft().getY() + width))
			return true;
		else 
			return false;

	}
	
	

	public void selected(Graphics g){

		g.setColor(Color.BLUE);
		new Line(pUpLeft, new Point(pUpLeft.getX()+sideLength, pUpLeft.getY())).selected(g);
		new Line(new Point(pUpLeft.getX()+sideLength, pUpLeft.getY()), new Point(pUpLeft.getX()+sideLength, pUpLeft.getY()+width)).selected(g);
		new Line(pUpLeft, new Point(pUpLeft.getX(), pUpLeft.getY()+width)).selected(g);
		new Line(new Point(pUpLeft.getX(), pUpLeft.getY()+width), new Point(pUpLeft.getX()+sideLength, pUpLeft.getY()+width)).selected(g);
		setSelected(true);
	}

	public void fillUp(Graphics g){

		g.setColor(getFillColor());
		g.fillRect(pUpLeft.getX()+1,pUpLeft.getY()+1, sideLength-1, width-1);
	}

	public void draw(Graphics g){

		g.setColor(getBorderColor());
		g.drawRect(pUpLeft.getX(), pUpLeft.getY(), sideLength, width);		
		/*if(isSelektovan()){
			selektovan(g);
		}*/
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	} 

}
