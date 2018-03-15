package crtanje;

import java.awt.Graphics;
import java.util.Iterator;

import javax.jws.WebParam.Mode;
import javax.swing.JPanel;

import geometrija.HexagonAdapter;
import geometrija.Krug;
import geometrija.Kvadrat;
import geometrija.Oblik;
import geometrija.PovrsinskiOblik;
import geometrija.Pravougaonik;
import hexagon.Hexagon;



public class ProstorZaCrtanje extends JPanel  {

	/*private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int novoX;
	private int novoY;
	private int xSelekcija;
	private int ySelekcija; 
	private  Color bojaIvice; 
	private  Color bojaUnutrasnjosti;
	private String odabranOblik;
	private int duzinaStranice;
	private boolean dvaKlika = false; 
	private int r; 
	private int duzina;
	private int sirina;
	private boolean selektovan;*/


	//dodato
	private Model model;



	public void setModel(Model model) {

		this.model = model;
	}


	/*ArrayList<Oblik> listaObjekata = new ArrayList<Oblik>();
	Stack<Oblik> stekSelekcija = new Stack<Oblik>();*/


	/*public ProstorZaCrtanje() {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				stekSelekcija.removeAllElements();

				if(getOdabranOblik() == ""){

					xSelekcija = e.getX();
					ySelekcija = e.getY();
					repaint();

				}



				if(getOdabranOblik() == "Tacka" || getOdabranOblik() == null)
				{

					x = e.getX();
					y = e.getY();
					repaint();

					Tacka t = new Tacka(x,y);
					t.setBojaIvice(bojaIvice);
					listaObjekata.add(t);


				}

				if(getOdabranOblik() == "Linija"){

					if(dvaKlika == false){

						x = e.getX();
						y= e.getY();
						dvaKlika=true;
					} else {

						novoX = e.getX();
						novoY = e.getY();
						repaint();
						Linija l = new Linija(new Tacka(x,y), new Tacka(novoX,novoY));
						l.setBojaIvice(bojaIvice);
						listaObjekata.add(l);

						dvaKlika=false;
					}

				}

				if(getOdabranOblik() == "Pravougaonik"){

					String d=JOptionPane.showInputDialog("Unesi duzinu pravougaonika");

					try{
						int duzina = Integer.parseInt(d);
						if(duzina>0){

							setDuzina(duzina);
							String s = JOptionPane.showInputDialog("Unesi sirinu pravougoonika");
							try{

								int sirina = Integer.parseInt(s);
								if(sirina>0){

									setSirina(sirina);
									x = e.getX();
									y = e.getY();
									repaint();
									Pravougaonik p = new Pravougaonik(new Tacka(x,y), duzina,sirina);
									p.setBojaIvice(bojaIvice);
									p.setBojaUnutrasnjosti(bojaUnutrasnjosti);
									listaObjekata.add(p);





								}
								else{
									JOptionPane.showMessageDialog(null, "Niste dobro uneli sirinu pravougaonika!");
								}
							} catch(NumberFormatException a){

								JOptionPane.showMessageDialog(null, "Niste dobro uneli sirinu pravougaonika!");

							} catch(NullPointerException k){

								JOptionPane.showMessageDialog(null, "Niste uneli sirinu pravougaonika");
							}

						} else {

							JOptionPane.showMessageDialog(null, "Niste dobro uneli dužinu pravougaonika!");
						}
					} catch(NumberFormatException a){

						JOptionPane.showMessageDialog(null, "Niste dobro uneli dužinu pravougaonika!");

					} catch(NullPointerException k){

						JOptionPane.showMessageDialog(null, "Niste uneli dužinu pravougaonika!");
					}



				}

				if(getOdabranOblik() == "Kvadrat"){


					String s=JOptionPane.showInputDialog("Unesi duzinu stranice kvadrata");

					try{

						int duzinaStranice = Integer.parseInt(s);
						if(duzinaStranice>0){

							setDuzinaStranice(duzinaStranice);
							x = e.getX();
							y = e.getY();
							repaint();
							Kvadrat kv = new Kvadrat(new Tacka(x,y), duzinaStranice);
							kv.setBojaIvice(bojaIvice);
							kv.setBojaUnutrasnjosti(bojaUnutrasnjosti);
							listaObjekata.add(kv);

						} else {
							JOptionPane.showMessageDialog(null, "Niste  dobro uneli dužinu stranice kvadrata!");
						}

					} catch(NumberFormatException a){

						JOptionPane.showMessageDialog(null, "Niste dobro uneli dužinu stranice kvadrata!");

					} catch(NullPointerException k){

						JOptionPane.showMessageDialog(null, "Niste uneli dužinu stanice kvadrata!");
					}




				}

				if(getOdabranOblik() == "Krug"){

					String s=JOptionPane.showInputDialog("Unesi duzinu poluprecnika kruga");

					try{

						int r = Integer.parseInt(s);
						if(r > 0){

							setR(r);
							x = e.getX();
							y = e.getY();
							repaint();
							Krug kr = new Krug(new Tacka(x,y),r);
							kr.setBojaIvice(bojaIvice);
							kr.setBojaUnutrasnjosti(bojaUnutrasnjosti);
							listaObjekata.add(kr);

						} else {

							JOptionPane.showMessageDialog(null, "Niste dobro uneli polupre�?nik kruga!");
						}

					} catch (NumberFormatException k){

						JOptionPane.showMessageDialog(null, "Niste dobro uneli polupre�?nik kruga!");


					} catch(NullPointerException k){

						JOptionPane.showConfirmDialog(null, "Niste uneli polupre�?nik kruga!");
					}



				}
			}
		});
	}*/



	@Override
	protected void paintComponent(Graphics g){

		super.paintComponent(g);

		if (model != null) {

			Iterator<Oblik> it = model.getListaObjekata().iterator();
			while(it.hasNext()) {
				it.next().crtajSe(g);
				

			}
			repaint();
		}

		for(Oblik o: model.getListaObjekata()){

			o.crtajSe(g);


			if(o instanceof Pravougaonik){


				((Pravougaonik) o).popuni(g);
			} 
			else if (o instanceof Kvadrat){


				((Kvadrat) o).popuni(g);
			} 
			else if(o instanceof Krug){


				((Krug) o).popuni(g);
				
				
			} 

		}
		
		for(Oblik o: model.getListaObjekata()){
			
		
			if(o.isSelektovan() == true) {
				
				o.selektovan(g);
				
			} else {
				
				o.setSelektovan(false);
			}
		}
		
		
		

		
		/*model.setSelection(false);
		
		if(model.getxSelekcija() != -1 && model.getySelekcija() != -1 && model.getxSelekcija() != -2 && model.getySelekcija() != -2) {
			
			//selektovanje oblika
			for(Oblik o: model.getListaObjekata()){

				//selektovanje oblika na koji je kliknut
				if(o.sadrzi(model.getxSelekcija(), model.getySelekcija())){

					
					model.setSelection(true);
					o.selektovan(g);
					
					model.addToStackSelection(o);
					
					
					//provera da li je još neki oblik selektovan
					for(Oblik k: model.getListaObjekata()) {

						if(k.isSelektovan() == true) {

							k.selektovan(g);

						}
						
					}
				}

			}	
			
			//ako ne sadrzi nijedan oblik onda će se svi odselektovati

			if (model.isSelection() == false) {
				
			

				for(Oblik l: model.getListaObjekata()) {

					if(l.isSelektovan() == true) {

						l.setSelektovan(false);

					}
				}

			}
			

			
	
			
		}
		
		if(model.getxSelekcija() == -2 && model.getySelekcija() == -2)
		{
			
		
			
			for(Oblik k: model.getListaObjekata()) {

				if(k.isSelektovan() == true) {

					k.selektovan(g);

				}
				
			}
			
			/*model.setxSelekcija(-1);
			model.setySelekcija(-1); /*
		
		}*/
		
		

		

	}

}

