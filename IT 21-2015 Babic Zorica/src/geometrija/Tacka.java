package geometrija;

import java.awt.Color;
import java.awt.Graphics;

public class Tacka extends Oblik {

	private int x;
	private int y;


	public Tacka(){

	}

	public Tacka(int x, int y){
		
		this.x = x;
		this.y = y; 
	}

	public Tacka (int x, int y, Color boja){

		this.x = x;
		this.y = y; 
		setBojaIvice(boja);
	}

	public void pomeriNa(int x, int y){
		setX(this.x);
		setY(this.y);
	}

	public void pomeriZa(int x, int y){
		setX(x+this.x);
		setY(y+this.y);
	}

	public double udaljenost(Tacka t){

		double dx = x-t.getX();
		double dy = y-t.getY();
		double udaljenost = Math.sqrt(dx*dx + dy*dy);
		return udaljenost; 

	}

	public String toString(){
		
		
		return "Point: (" + x +"," +y+")"+", "+"outline: "+getHex(getBojaIvice())+", "+"Selected? " + isSelektovan();
		
		
				
	}

	public boolean equals(Object o){

		if(o instanceof Tacka){
			Tacka pomocna = (Tacka) o;
			if(x == pomocna.x && y == pomocna.y){
				return true;
			}
			return false;
		}
		return false; 
	}

	public int compareTo(Object o){

		if(o instanceof Tacka){
			Tacka pomocna = (Tacka) o;
			return (int) this.udaljenost(new Tacka(0, 0)) - (int)pomocna.udaljenost(new Tacka(0, 0));
		}
		return 0;
	}


	public boolean sadrzi(int x, int y){
		Tacka mestoKlika = new Tacka(x, y);
		if(mestoKlika.udaljenost(this)<=2)
			return true;
		else
			return false;
	}

	public void selektovan(Graphics g){
		
		
		g.setColor(Color.BLUE);
		g.drawRect(x-3, y-3,6, 6);
		setSelektovan(true);

	}



	public void crtajSe(Graphics g){

		g.setColor(getBojaIvice());
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

	public Color getBojaIvice() {
		return bojaIvice;
	}

	public void setBojaIvice(Color bojaIvice) {
		this.bojaIvice = bojaIvice;
	}



}
