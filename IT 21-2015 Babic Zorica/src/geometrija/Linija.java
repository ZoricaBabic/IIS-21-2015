package geometrija;

import java.awt.Color;
import java.awt.Graphics;


public class Linija extends Oblik{
	
	private Tacka tPocetna;
	private Tacka tKrajnja;
	 
	

	public Linija(){

	}
	
	public Linija(Tacka tPocetna, Tacka tKrajnja){
		this.tPocetna = tPocetna;
		this.tKrajnja = tKrajnja; 
	}
	
	public Linija(Tacka tPocetna, Tacka tKrajnja, Color bojaIvice){
		this.tPocetna = tPocetna;
		this.tKrajnja = tKrajnja;
		this.bojaIvice = bojaIvice; 
	}

	public void pomeriZa(int x, int y){
		tPocetna.setX(tPocetna.getX()+x);
		tPocetna.setY(tPocetna.getY()+y);
		tKrajnja.setX(tKrajnja.getX()+x);
		tKrajnja.setY(tKrajnja.getY()+y);		
	}

	public double duzina(){
		return tPocetna.udaljenost(tKrajnja);
	}
	
	public String toString(){
		
		
		/*return tPocetna+"-->"+tKrajnja + "Selektovan? " + isSelektovan() + ".";
		
		return "Line: startPoint: +", "+"outline: "+getHex(getBojaIvice())+", "+"Selected? " + isSelektovan();";*/
		

		
		return "Line: startPoint (" + tPocetna.getX() + "," + tPocetna.getY() + "), endPoint (" + tKrajnja.getX() + "," + tKrajnja.getY() + "), outline: " + getHex(getBojaIvice()) + ", Selected? " + isSelektovan();
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Linija){
			Linija pomocna = (Linija) obj;
			if(tPocetna.equals(pomocna.gettPocetna()) && tKrajnja.equals(pomocna.gettKrajnja()))
				return true;
			else
				return false;

		}
		else
			return false;
	}
	
	public Tacka sredinaLinije(){
		int sredinaX = (tPocetna.getX() + tKrajnja.getX()) / 2;
		int sredinaY = (tPocetna.getY() + tKrajnja.getY()) / 2;
		return new Tacka(sredinaX, sredinaY);
	}
	
	public boolean sadrzi(int x, int y){
		Tacka mestoKlika = new Tacka(x, y);
		if(mestoKlika.udaljenost(tPocetna)+mestoKlika.udaljenost(tKrajnja)-this.duzina()<0.5)
			return true;
		else 
			return false;
	}
	
	public void selektovan(Graphics g){
		g.setColor(Color.BLUE);
		tPocetna.selektovan(g);
		tKrajnja.selektovan(g);
		sredinaLinije().selektovan(g);
		setSelektovan(true);
	}
	
	public void crtajSe(Graphics g){

		g.setColor(getBojaIvice());
		g.drawLine(tPocetna.getX(), tPocetna.getY(), tKrajnja.getX(), tKrajnja.getY());
		/*if(isSelektovan())
			selektovan(g);*/
		
	}


	public int compareTo(Object o) {
		if(o instanceof Linija){
			Linija pomocna = (Linija) o;
			return (int) (this.duzina() - pomocna.duzina());
		}
		else
			return 0;
	}
	
	public Tacka gettPocetna(){
		return tPocetna;
	}
	
	public Tacka gettKrajnja(){
		return tKrajnja;
	}
	
	public void settPocetna(Tacka tPocetna){
		this.tPocetna = tPocetna;
	}
	
	public void settKrajnja(Tacka tKrajnja){
		this.tKrajnja = tKrajnja;
	}
	
	public Color getBojaIvice() {
		return bojaIvice;
	}
	
	public void setBojaIvice(Color boja) {
		this.bojaIvice = boja;
	}

}
