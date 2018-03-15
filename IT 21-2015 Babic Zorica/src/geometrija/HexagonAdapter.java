package geometrija;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends PovrsinskiOblik  {
	
	private Hexagon hexagon;
	private Tacka centar;
	
	
	public HexagonAdapter() {
		
		
	}
	
	public HexagonAdapter(Tacka centar, int r) {
		
		
		hexagon = new Hexagon(centar.getX(), centar.getY(), r);
		this.centar = centar;
		
		
	}
	
	public String toString(){
		
		centar = new Tacka(hexagon.getX(),hexagon.getY());
		
		return "Hexagon: (" + centar.getX() + "," + centar.getY() + "), radius: "  + hexagon.getR() + ", outline: " + getHex(getBojaIvice())+ ", fill: " + getHex(getBojaUnutrasnjosti()) + ", Selected? " + isSelektovan(); 
	}
	public boolean equals(Object obj){
		
		if(obj instanceof Hexagon){
			Hexagon pomocni = (Hexagon) obj;
			Tacka centarPomocni = new Tacka(pomocni.getX(),pomocni.getY());
			if(centar.equals(centarPomocni) && hexagon.getR() == pomocni.getR())
				return true;
			else
				return false;

		}
		else
			return false;
	}
	public boolean sadrzi(int x, int y){
		Tacka mestoKlika = new Tacka(x, y);
		if(mestoKlika.udaljenost(centar)<=hexagon.getR())
			return true;
		else
			return false;
		
	}
	public void selektovan(Graphics g) {
		
		g.setColor(Color.BLUE);
		/*new Linija(new Tacka(centar.getX(), centar.getY()-r), new Tacka(centar.getX(), centar.getY() + r)).selektovan(g);
		new Linija(new Tacka(centar.getX()-r, centar.getY()), new Tacka(centar.getX()+r, centar.getY())).selektovan(g);*/
		hexagon.setSelected(true);
		//setSelektovan(true);
		

	}
	
	

	public void crtajSe(Graphics g){
		
		g.setColor(getBojaIvice());
		//g.drawOval(centar.getX()-r, centar.getY()-r, 2*r, r+r);
		/*if(isSelektovan())
			selektovan(g);*/
		hexagon.paint(g);
	}
	public void popuni(Graphics g) {
		
		g.setColor(getBojaUnutrasnjosti());
		hexagon.setAreaColor(getBojaUnutrasnjosti());
		
	}
	
	public int compareTo(Object o) {
		
		if(o instanceof Hexagon){
			Hexagon pomocni = (Hexagon) o;
			return (int) (this.hexagon.getR() - pomocni.getR());
		}
		else
			return 0;
	}
	
	
	public Tacka getCentar() {
		return centar;
	}
	public void setCentar(Tacka centar) {
		this.centar = centar;
	}

	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	

}
