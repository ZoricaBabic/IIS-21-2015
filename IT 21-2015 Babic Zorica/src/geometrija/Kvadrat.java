package geometrija;
import java.awt.Color;
import java.awt.Graphics;

public class Kvadrat extends PovrsinskiOblik implements Pomerljiv{
	protected Tacka tGoreLevo;
	protected int duzinaStranice;
	
	
	public Kvadrat(){

	}
	
	public Kvadrat(Tacka tGoreLevo, int duzinaStranice){
		this.tGoreLevo = tGoreLevo;
		this.duzinaStranice = duzinaStranice;
	}
	  
	public Kvadrat(Tacka tGoreLevo, int duzinaStranice,Color bojaIvice,Color bojaUnutrasnjosti){
		this(tGoreLevo,duzinaStranice);
		setBojaIvice(bojaIvice);
		setBojaUnutrasnjosti(bojaUnutrasnjosti);
	}
	
	public void pomeriNa(int x, int y){
		tGoreLevo.setX(x);
		tGoreLevo.setY(y);
	}

	public void pomeriZa(int x, int y){
		tGoreLevo.setX(tGoreLevo.getX()+x);
		tGoreLevo.setY(tGoreLevo.getY()+y);
	}

	public double obim(){
		return 4 * duzinaStranice;
	}
	
	public double povrsina(){
		return duzinaStranice * duzinaStranice;
	}
	
	
	public String toString(){
		//return "Tacka gore levo="+tGoreLevo+", duzina stranice="+duzinaStranice + "Selektovan? " + isSelektovan() + ".";
		
		return "Square: (" + tGoreLevo.getX() + ", "+tGoreLevo.getY()+"), width: " + duzinaStranice + ", outline: " + getHex(getBojaIvice())+ ", fill: " + getHex(getBojaUnutrasnjosti()) + ", Selected? " + isSelektovan(); 
	
				
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Kvadrat){
			Kvadrat pomocni = (Kvadrat) obj;
			if(tGoreLevo.equals(pomocni.tGoreLevo) && duzinaStranice == pomocni.duzinaStranice)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public Linija dijagonala(){
		return new Linija(tGoreLevo, new Tacka(tGoreLevo.getX() + duzinaStranice,tGoreLevo.getY() + duzinaStranice));
	}

	public Tacka centar(){
		return dijagonala().sredinaLinije();
	}
	
	public boolean sadrzi(int x, int y) {
		if(this.gettGoreLevo().getX()<=x 
				&& x<=(this.gettGoreLevo().getX() + duzinaStranice)
				&& this.gettGoreLevo().getY()<=y 
				&& y<=(this.gettGoreLevo().getY() + duzinaStranice))
			return true;
		else 
			return false;

	}
	
	public void selektovan(Graphics g) {
		
		g.setColor(Color.BLUE);
		new Linija(gettGoreLevo(), new Tacka(gettGoreLevo().getX()+duzinaStranice, gettGoreLevo().getY())).selektovan(g);
		new Linija(gettGoreLevo(), new Tacka(gettGoreLevo().getX(), gettGoreLevo().getY()+duzinaStranice)).selektovan(g);
		new Linija(new Tacka(gettGoreLevo().getX()+duzinaStranice, gettGoreLevo().getY()), dijagonala().gettKrajnja()).selektovan(g);
		new Linija(new Tacka(gettGoreLevo().getX(), gettGoreLevo().getY()+duzinaStranice), dijagonala().gettKrajnja()).selektovan(g);
		setSelektovan(true);
		

	}
	
	public void crtajSe(Graphics g){
		
		g.setColor(getBojaIvice());
		g.drawRect(tGoreLevo.getX(), tGoreLevo.getY(), duzinaStranice, duzinaStranice);
		/*if(isSelektovan())
			selektovan(g);*/
	}
	
	public void popuni(Graphics g) {
		g.setColor(getBojaUnutrasnjosti());
		g.fillRect(tGoreLevo.getX()+1, tGoreLevo.getY()+1, duzinaStranice-1, duzinaStranice-1);
		
	}
	
	public int compareTo(Object o) {
		if(o instanceof Kvadrat){
			Kvadrat pomocni = (Kvadrat) o;
			return (int) (this.povrsina() - pomocni.povrsina());
		}
		else
			return 0;
	}
	
	public Tacka gettGoreLevo() {
		return tGoreLevo;
	}
	
	public void settGoreLevo(Tacka tGoreLevo) {
		this.tGoreLevo = tGoreLevo;
	}
	
	public int getDuzinaStranice() {
		return duzinaStranice;
	}
	
	public void setDuzinaStranice(int duzinaStranice) {
		this.duzinaStranice = duzinaStranice;
	}

	
		
}
