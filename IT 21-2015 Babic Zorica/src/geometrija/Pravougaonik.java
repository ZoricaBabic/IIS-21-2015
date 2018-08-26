package geometrija;

import java.awt.Color;
import java.awt.Graphics;

public class Pravougaonik extends Kvadrat {


	private int sirina;

	public Pravougaonik(){

	}
	
	public Pravougaonik(Tacka tGoreLevo,int duzina,int sirina){

		super(tGoreLevo,duzina);
		this.sirina = sirina; 
	}

	public Pravougaonik(Tacka tGoreLevo,int duzina,int sirina,Color bojaIvice){

		this(tGoreLevo,duzina,sirina);
		setBojaIvice(bojaIvice);
	}

	public Pravougaonik(Tacka tGoreLevo,int duzina,int sirina,Color bojaIvice, Color bojaUnutrasnjosti){

		this(tGoreLevo,duzina,sirina,bojaIvice);
		setBojaUnutrasnjosti(bojaUnutrasnjosti);
	}
	public double obim(){

		return 2*duzinaStranice + 2*sirina; 
	}
 
	public double povrsina(){

		return duzinaStranice*sirina;
	}

	public void pomeriNa(int x, int y){

		tGoreLevo.pomeriNa(x, y);
	}

	public void pomeriZa(int x, int y){

		tGoreLevo.pomeriZa(x, y);
	}


	public Linija dijagonala(){

		return new Linija(tGoreLevo, new Tacka(tGoreLevo.getX()+duzinaStranice, tGoreLevo.getX()+sirina));
	}

	public Tacka centar(){

		return dijagonala().sredinaLinije();
	}

	public String toString(){

		//return "Tacka gore levo je: " + tGoreLevo + "duzina: " + duzinaStranice + ", sirina: "+sirina + "Selektovan? " + isSelektovan() + ".";
		
		return "Rectangle: (" + tGoreLevo.getX() + ","+tGoreLevo.getY()+"), width: " + duzinaStranice + ", height: " + sirina + ", outline: " + getHex(getBojaIvice())+ ", fill: " + getHex(getBojaUnutrasnjosti()) + ", Selected? " + isSelektovan(); 
	}

	public boolean equals(Object o){
		
	/*	System.out.println(duzinaStranice);
		System.out.println(sirina);
		
		System.out.println(((Kvadrat) o).getDuzinaStranice());
		System.out.println(((Pravougaonik) o).getSirina());*/
		
		if(o instanceof Pravougaonik){
			
			Pravougaonik pomocni = (Pravougaonik) o;
			
			/*((Kvadrat) pomocni).gettGoreLevo();
			
			((Kvadrat) pomocni).getDuzinaStranice();*/
			
			if(tGoreLevo.equals(((Kvadrat) pomocni).gettGoreLevo()) && duzinaStranice == ((Kvadrat) pomocni).getDuzinaStranice() && sirina == pomocni.sirina){
				return true;
			}
			return false;
		}
		return false; 
	}
	
	public boolean sadrzi(int x, int y) {
		if(this.gettGoreLevo().getX()<=x 
				&& x<=(this.gettGoreLevo().getX() + duzinaStranice)
				&& this.gettGoreLevo().getY()<=y 
				&& y<=(this.gettGoreLevo().getY() + sirina))
			return true;
		else 
			return false;

	}
	
	

	public void selektovan(Graphics g){

		g.setColor(Color.BLUE);
		new Linija(tGoreLevo, new Tacka(tGoreLevo.getX()+duzinaStranice, tGoreLevo.getY())).selektovan(g);
		new Linija(new Tacka(tGoreLevo.getX()+duzinaStranice, tGoreLevo.getY()), new Tacka(tGoreLevo.getX()+duzinaStranice, tGoreLevo.getY()+sirina)).selektovan(g);
		new Linija(tGoreLevo, new Tacka(tGoreLevo.getX(), tGoreLevo.getY()+sirina)).selektovan(g);
		new Linija(new Tacka(tGoreLevo.getX(), tGoreLevo.getY()+sirina), new Tacka(tGoreLevo.getX()+duzinaStranice, tGoreLevo.getY()+sirina)).selektovan(g);
		setSelektovan(true);
	}

	public void popuni(Graphics g){

		g.setColor(getBojaUnutrasnjosti());
		g.fillRect(tGoreLevo.getX()+1,tGoreLevo.getY()+1, duzinaStranice-1, sirina-1);
	}

	public void crtajSe(Graphics g){

		g.setColor(getBojaIvice());
		g.drawRect(tGoreLevo.getX(), tGoreLevo.getY(), duzinaStranice, sirina);		
		/*if(isSelektovan()){
			selektovan(g);
		}*/
	}

	public int getSirina() {
		return sirina;
	}
	public void setSirina(int sirina) {
		this.sirina = sirina;
	} 

}
