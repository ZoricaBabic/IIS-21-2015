package geometrija;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends PovrsinskiOblik {
	
	private Hexagon hexagon;
	private Tacka centar;
	private int r;
	
	
	public HexagonAdapter() {
		
	
		
	}
	
	public HexagonAdapter(Tacka centar, int r,Color bojaIvice,Color bojaUnutrasnjosti) {

		
		hexagon = new Hexagon(centar.getX(), centar.getY(), r);
		hexagon.setBorderColor(bojaIvice);
		hexagon.setAreaColor(bojaUnutrasnjosti);
		setBojaIvice(bojaIvice);
		setBojaUnutrasnjosti(bojaUnutrasnjosti);
		
		this.centar = centar;
		this.r=r;
	
		
		
	}
	
	public String toString(){
		
		centar = new Tacka(hexagon.getX(),hexagon.getY());
	
		return "Hexagon: (" + centar.getX() + "," + centar.getY() + "), radius: "  + hexagon.getR() + ", outline: " + getHex(hexagon.getBorderColor())+ ", fill: " + getHex(hexagon.getAreaColor()) + ", Selected? " + isSelektovan(); 
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
	
	public void  unselect() {
		
		hexagon.setSelected(false);
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
	
	/*public boolean isSelektovan() {
		
		if(hexagon.isSelected() == true) {
			
			return true;
		} else {
			
			return false;
		}
	}*/
	
	
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

	public void setR(int r) {
	
		this.r = r;
		hexagon.setR(r);
		
	}

	public int getR() {
		
		return hexagon.getR();
	}

	


	

}
