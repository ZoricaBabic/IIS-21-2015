package geometry;
import java.awt.Color;
import java.awt.Graphics;

public class Square extends SurfaceShape {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Point pUpLeft;
	protected int sideLength;
	
	
	public Square(){

	}
	
	public Square(Point pUpLeft, int sideLength){
		this.pUpLeft = pUpLeft;
		this.sideLength = sideLength;
	}
	
	public Square(Point pUpLeft, int sideLength,Color borderColor,Color fillColor){
		
		this(pUpLeft,sideLength);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}
	
	/*public void pomeriNa(int x, int y){
		pUpLeft.setX(x);
		pUpLeft.setY(y);
	}

	public void pomeriZa(int x, int y){
		pUpLeft.setX(pUpLeft.getX()+x);
		pUpLeft.setY(pUpLeft.getY()+y);
	}*/

	public double volume(){
		return 4 * sideLength;
	}
	
	public double surface(){
		return sideLength * sideLength;
	}
	
	
	public String toString(){
		//return "Tacka gore levo="+tGoreLevo+", duzina stranice="+duzinaStranice + "Selektovan? " + isSelektovan() + ".";
		
		return "Square: ("+ pUpLeft.getX() + ","+pUpLeft.getY()+"), width: " + sideLength + ", outline: " + getHex(getBorderColor())+ ", fill: " + getHex(getFillColor()) + ", Selected? " + isSelected(); 
	
				
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Square){
			Square help = (Square) obj;
			if(pUpLeft.equals(help.pUpLeft) && sideLength == help.sideLength)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public Line diagonal(){
		return new Line(pUpLeft, new Point(pUpLeft.getX() + sideLength,pUpLeft.getY() + sideLength));
	}

	public Point center(){
		return diagonal().lineMiddle();
	}
	
	public boolean contains(int x, int y) {
		
		if(this.getpUpLeft().getX()<=x 
				&& x<=(this.getpUpLeft().getX() + sideLength)
				&& this.getpUpLeft().getY()<=y 
				&& y<=(this.getpUpLeft().getY() + sideLength))
			return true;
		else 
			return false;

	}
	
	public void selected(Graphics g) {
		
		g.setColor(Color.BLUE);
		new Line(getpUpLeft(), new Point(getpUpLeft().getX()+sideLength, getpUpLeft().getY())).selected(g);
		new Line(getpUpLeft(), new Point(getpUpLeft().getX(), getpUpLeft().getY()+sideLength)).selected(g);
		new Line(new Point(getpUpLeft().getX()+sideLength, getpUpLeft().getY()), diagonal().getEnd()).selected(g);
		new Line(new Point(getpUpLeft().getX(), getpUpLeft().getY()+sideLength), diagonal().getEnd()).selected(g);
		setSelected(true);
		

	}
	
	public void draw(Graphics g){
		
		g.setColor(getBorderColor());
		g.drawRect(pUpLeft.getX(), pUpLeft.getY(), sideLength, sideLength);
		/*if(isSelektovan())
			selektovan(g);*/
	}
	
	public void fillUp(Graphics g) {
		g.setColor(getFillColor());
		g.fillRect(pUpLeft.getX()+1, pUpLeft.getY()+1, sideLength-1, sideLength-1);
		
	}
	
	public int compareTo(Object o) {
		if(o instanceof Square){
			Square help = (Square) o;
			return (int) (this.surface() - help.surface());
		}
		else
			return 0;
	}
	
	public Point getpUpLeft() {
		return pUpLeft;
	}
	
	public void setpUpLeft(Point pUpLeft) {
		this.pUpLeft = pUpLeft;
	}
	
	public int getSideLength() {
		return sideLength;
	}
	
	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}

	
		
}
