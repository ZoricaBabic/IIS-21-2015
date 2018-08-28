package geometry;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends SurfaceShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	/*private Tacka centar;
	private int r;*/


	public HexagonAdapter() {


	}

	
	public HexagonAdapter(Hexagon hexagon) {
		
		this.hexagon = hexagon;
		/*centar.setX(hexagon.getX());
		centar.setY(hexagon.getY());
		setR(hexagon.getR());*/
	}
	


	public String toString(){

		//centar = new Tacka(hexagon.getX(),hexagon.getY());

		return "Hexagon: (" + hexagon.getX() + "," + hexagon.getY() + "), radius: "  + hexagon.getR() + ", outline: " + getHex(hexagon.getBorderColor())+ ", fill: " + getHex(hexagon.getAreaColor()) + ", Selected? " + hexagon.isSelected(); 
	}



	public boolean contains(int x, int y){
		
		return hexagon.doesContain(x, y);

	}
	
	
	public void selected(Graphics g) {

		hexagon.setSelected(true);
		setSelected(true);
		
	}
	
	@Override
	public void setSelected(boolean b) {
		super.setSelected(b);
		hexagon.setSelected(b);
		
		
	}




	public boolean equals(Object obj){
		
		

		
		if(obj instanceof HexagonAdapter){
			
			
			HexagonAdapter help = (HexagonAdapter) obj;
			Point t = new Point(hexagon.getX(),hexagon.getY());
			Point t1 = new Point(help.hexagon.getX(),help.hexagon.getY());
			if(t.equals(t1) && hexagon.getR()==help.getHexagon().getR())
				return true;
			else
				return false;

		}
		else
			return false;
		
	}
	

	public void draw(Graphics g){

		//g.setColor(getBojaIvice());
		//g.drawOval(centar.getX()-r, centar.getY()-r, 2*r, r+r);
		/*if(isSelektovan())
			selektovan(g);*/	

		hexagon.paint(g);
	}


	public void fillUp(Graphics g) {

		//g.setColor(getBojaUnutrasnjosti());
		hexagon.setAreaColor(getFillColor());

	}

	public int compareTo(Object o) {
		
		

		if(o instanceof Hexagon){
			Hexagon help = (Hexagon) o;
			return (int) (this.hexagon.getR() - help.getR());
		}
		else
			return 0;
		

	}



	public Hexagon getHexagon() {

		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {

		this.hexagon = hexagon;

	}
	
	public boolean isSelected() {
		
	
		return hexagon.isSelected();
	}









}
