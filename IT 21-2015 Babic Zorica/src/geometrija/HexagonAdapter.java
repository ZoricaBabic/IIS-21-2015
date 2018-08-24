package geometrija;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends PovrsinskiOblik {

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



	public boolean sadrzi(int x, int y){
		
		return hexagon.doesContain(x, y);
		/*Tacka mestoKlika = new Tacka(x, y);
		if(mestoKlika.udaljenost(centar)<=hexagon.getR())
			return true;
		else
			return false;*/

	}
	
	
	public void selektovan(Graphics g) {

		hexagon.setSelected(true);
		setSelektovan(true);
		
		
		/*g.setColor(Color.BLUE);
		hexagon.setSelected(true);
		setSelektovan(true);/*/
		
		
		//hexagon.setSelected(true);
		//setSelektovan(true);
		/*g.setColor(Color.BLUE);
		//hexagon.setSelected(true);
		setSelektovan(true);*/
		//selektovan
		
		/*// TODO Auto-generated method stub
				hexagon.setSelected(true);
				setSelektovan(true);
		
		/*System.out.println("Poziva se selektovan!");

		g.setColor(Color.BLUE);
		/*new Linija(new Tacka(centar.getX(), centar.getY()-r), new Tacka(centar.getX(), centar.getY() + r)).selektovan(g);
		new Linija(new Tacka(centar.getX()-r, centar.getY()), new Tacka(centar.getX()+r, centar.getY())).selektovan(g);*/
		/*hexagon.setSelected(true);
		setSelektovan(true);*/
		
		

	}
	
	@Override
	public void setSelektovan(boolean b) {
		super.setSelektovan(b);
		hexagon.setSelected(b);
		
		
	}


	/*public void  unselect() {

		hexagon.setSelected(false);
	}*/


	public boolean equals(Object obj){
		
		

		/*if(obj instanceof HexagonAdapter){
			
			HexagonAdapter pomocni = (HexagonAdapter) obj;
			
			if(hexagon.equals(pomocni.getHexagon())) {
				
				return true;
			} else {
				
				return false;
			}
			

		} else {
			
			return false;
		}*/
		
		if(obj instanceof HexagonAdapter){
			HexagonAdapter pomocna = (HexagonAdapter) obj;
			Tacka t = new Tacka(hexagon.getX(),hexagon.getY());
			Tacka t1 = new Tacka(pomocna.hexagon.getX(),pomocna.hexagon.getY());
			if(t.equals(t1) && hexagon.getR()==pomocna.getHexagon().getR())
				return true;
			else
				return false;

		}
		else
			return false;
		
	}
	

	public void crtajSe(Graphics g){

		//g.setColor(getBojaIvice());
		//g.drawOval(centar.getX()-r, centar.getY()-r, 2*r, r+r);
		/*if(isSelektovan())
			selektovan(g);*/	

		hexagon.paint(g);
	}


	public void popuni(Graphics g) {

		//g.setColor(getBojaUnutrasnjosti());
		hexagon.setAreaColor(getBojaUnutrasnjosti());

	}

	public int compareTo(Object o) {
		
		System.out.println("Poziva se ovo");

		if(o instanceof Hexagon){
			Hexagon pomocni = (Hexagon) o;
			return (int) (this.hexagon.getR() - pomocni.getR());
		}
		else
			return 0;
		

	}

	/*public boolean isSelektovan() {

		if(hexagon.isSelected() == true) {

			return true;
		} else {

			return false;
		}
	}*/


	/*public Tacka getCentar() {

		return centar;
	}
	public void setCentar(Tacka centar) {
		this.centar = centar;
	}*/

	public Hexagon getHexagon() {

		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {

		this.hexagon = hexagon;

	}
	
	public boolean isSelektovan() {
		
	
		return hexagon.isSelected();
	}


	

	/*public void setR(int r) {

		this.r = r;
		hexagon.setR(r);

	}*/

	/*public int getR() {

		return hexagon.getR();
	}*/






}
