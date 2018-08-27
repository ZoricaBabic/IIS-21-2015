package crtanje;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometrija.HexagonAdapter;
import geometrija.Krug;
import geometrija.Kvadrat;
import geometrija.Linija;
import geometrija.Oblik;
import geometrija.Pravougaonik;
import geometrija.Tacka;


public class Model implements Serializable{
	
	private static final long serialVersionUID = 1L;

	ArrayList<Oblik> listaObjekata = new ArrayList<Oblik>();
	/*Stack<Oblik> stekSelekcija = new Stack<Oblik>();
	ArrayList<Oblik> listaSelekcija = new ArrayList<Oblik>();*/

	Stack <Oblik> stackUndo = new Stack<Oblik>();
	Stack <Oblik> stackRedo = new Stack<Oblik>();
	ArrayList <Oblik> unselectedShapes = new ArrayList<Oblik>();
	private ArrayList<Oblik> commands = new ArrayList<Oblik> ();
	
	
	ArrayList<Oblik> undoRedo = new ArrayList<Oblik>();

	private int x;
	private int y;
	private int novoX;
	private int novoY; 
	private int xSelekcija = -1;
	private int ySelekcija = -1;
	private  Color bojaIvice = Color.black;
	private  Color bojaUnutrasnjosti =  Color.WHITE;
	private String odabranOblik = "";
	private int duzinaStranice = -1;
	private boolean dvaKlika = false; 
	private int r = -1;
	private int rHexagon;
	private int duzina = -1;
	private int sirina = -1;
	private boolean selektovan;
	private int positionNumber;
	private Oblik oblik;
	private boolean n = false;
	private int count;
	private int countNumber;
	private boolean selection = false;
	private boolean removeFromSelection = false;
	private boolean undoClicked = false;
	private int position;





	public void add(Oblik s) {

		listaObjekata.add(s);
		commands.add(s);
	}
	public Oblik get(int i) {

		return listaObjekata.get(i);
	}

	public boolean remove(Oblik s) {

		return listaObjekata.remove(s);
	}

	public boolean removeAllFromList() {

		return listaObjekata.removeAll(listaObjekata);
	}

	/*public void addToStackUndo(Oblik s) {

		stackUndo.push(s);
	}

	public void removeFromStackUndo() {

		stackUndo.pop();
	}

	public Oblik getLastShapeOnStackUndo() {

		return stackUndo.peek();
	}

	public void addToStackRedo(Oblik s) {

		stackRedo.push(s);
	}

	public void removeFromStackRedo() {

		stackRedo.pop();
	}

	public Oblik getLastShapeOnStackRedo() {

		return stackRedo.peek();
	}*/
	
	

	public ArrayList<Oblik> getListaObjekata() {
		return listaObjekata;
	}
	public void setListaObjekata(ArrayList<Oblik> listaObjekata) {
		this.listaObjekata = listaObjekata;
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

	public int getNovoX() {
		return novoX;
	}

	public void setNovoX(int novoX) {
		this.novoX = novoX;
	}

	public int getNovoY() {
		return novoY;
	}

	public void setNovoY(int novoY) {
		this.novoY = novoY;
	}

	public int getxSelekcija() {
		return xSelekcija;
	}

	public void setxSelekcija(int xSelekcija) {
		this.xSelekcija = xSelekcija;
	}

	public int getySelekcija() {
		return ySelekcija;
	}

	public void setySelekcija(int ySelekcija) {
		this.ySelekcija = ySelekcija;
	}

	public Color getBojaIvice() {
		return bojaIvice;
	}

	public void setBojaIvice(Color bojaIvice) {
		this.bojaIvice = bojaIvice;
	}

	public Color getBojaUnutrasnjosti() {
		return bojaUnutrasnjosti;
	}

	public void setBojaUnutrasnjosti(Color bojaUnutrasnjosti) {
		this.bojaUnutrasnjosti = bojaUnutrasnjosti;
	}

	public String getOdabranOblik() {
		return odabranOblik;
	}

	public void setOdabranOblik(String odabranOblik) {
		this.odabranOblik = odabranOblik;
	}

	public int getDuzinaStranice() {
		return duzinaStranice;
	}

	public void setDuzinaStranice(int duzinaStranice) {
		this.duzinaStranice = duzinaStranice;
	}

	public boolean isDvaKlika() {
		return dvaKlika;
	}

	public void setDvaKlika(boolean dvaKlika) {
		this.dvaKlika = dvaKlika;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getDuzina() {
		return duzina;
	}

	public void setDuzina(int duzina) {
		this.duzina = duzina;
	}

	public int getSirina() {
		return sirina;
	}

	public void setSirina(int sirina) {
		this.sirina = sirina;
	}

	public boolean isSelektovan() {
		return selektovan;
	}

	public void setSelektovan(boolean selektovan) {
		this.selektovan = selektovan;
	}

	public int getPositionNumber() {
		return positionNumber;
	}

	public void setPositionNumber(int positionNumber) {
		this.positionNumber = positionNumber;
	}

	public Oblik getOblik() {
		return oblik;
	}

	public void setOblik(Oblik oblik) {
		this.oblik = oblik;
	}

	public boolean isN() {
		return n;
	}

	public void setN(boolean n) {
		this.n = n;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCountNumber() {
		return countNumber;
	}

	public void setCountNumber(int countNumber) {
		this.countNumber = countNumber;
	}

	/*public ArrayList<Oblik> getListaSelekcija() {
		return listaSelekcija;
	}

	public void setListaSelekcija(ArrayList<Oblik> listaSelekcija) {
		this.listaSelekcija = listaSelekcija;
	}*/

	public boolean isSelection() {
		return selection;
	}

	public void setSelection(boolean selection) {
		this.selection = selection;
	}

	public boolean isRemoveFromSelection() {
		return removeFromSelection;
	}

	public void setRemoveFromSelection(boolean removeFromSelection) {
		this.removeFromSelection = removeFromSelection;
	}

	public Stack<Oblik> getStackUndo() {
		return stackUndo;
	}

	public void setStackUndo(Stack<Oblik> stackUndo) {
		this.stackUndo = stackUndo;
	}

	public Stack<Oblik> getStackRedo() {
		return stackRedo;
	}

	public void setStackRedo(Stack<Oblik> stackRedo) {
		this.stackRedo = stackRedo;
	}

	public boolean isUndoClicked() {
		return undoClicked;
	}

	public void setUndoClicked(boolean undoClicked) {
		this.undoClicked = undoClicked;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}


	public int getrHexagon() {
		return rHexagon;
	}

	public void setrHexagon(int rHexagon) {
		this.rHexagon = rHexagon;
	}
	public ArrayList<Oblik> getUndoRedo() {
		return undoRedo;
	}
	public void setUndoRedo(ArrayList<Oblik> undoRedo) {
		this.undoRedo = undoRedo;
	}
	public ArrayList<Oblik> getUnselectedShapes() {
		return unselectedShapes;
	}
	public void setUnselectedShapes(ArrayList<Oblik> unselectedShapes) {
		this.unselectedShapes = unselectedShapes;
	}
	public ArrayList<Oblik> getCommands() {
		return commands;
	}
	public void setCommands(ArrayList<Oblik> commands) {
		this.commands = commands;
	}



}
