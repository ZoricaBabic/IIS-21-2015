package mvc;

import java.awt.Color;

import java.io.Serializable;
import java.util.ArrayList;

import geometry.Shape;



public class Model implements Serializable{
	
	private static final long serialVersionUID = 1L;

	ArrayList<Shape> listOfShapes = new ArrayList<Shape>();


	private int x;
	private int y;
	private int newX;
	private int newY; 
	private int xSelect = -1;
	private int ySelect = -1;
	private  Color borderColor = Color.black;
	private  Color fillColor =  Color.WHITE;
	private String chosenShape = "";
	private int sideLength = -1;
	private boolean twoClicks = false; 
	private int r = -1;
	private int rHexagon;
	private int length = -1;
	private int width = -1;
	private boolean selected;
	private int positionNumber;
	private Shape shape;
	private boolean n = false;
	private int count;
	private int countNumber;
	private boolean selection = false;
	private boolean removeFromSelection = false;
	private boolean undoClicked = false;
	private int position;





	public void add(Shape s) {

		listOfShapes.add(s);
		
	}
	public Shape get(int i) {

		return listOfShapes.get(i);
	}

	public boolean remove(Shape s) {

		return listOfShapes.remove(s);
	}

	public boolean removeAllFromList() {

		return listOfShapes.removeAll(listOfShapes);
	}


	public ArrayList<Shape> getListOfShapes() {
		return listOfShapes;
	}
	public void setListOfShapes(ArrayList<Shape> listOfShapes) {
		this.listOfShapes = listOfShapes;
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

	public int getNewX() {
		return newX;
	}

	public void setNewX(int newX) {
		this.newX = newX;
	}

	public int getNewY() {
		return newY;
	}

	public void setNewY(int newY) {
		this.newY = newY;
	}

	public int getxSelect() {
		return xSelect;
	}

	public void setxSelect(int xSelect) {
		this.xSelect = xSelect;
	}

	public int getySelect() {
		return ySelect;
	}

	public void setYSelect(int ySelect) {
		this.ySelect = ySelect;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public String getChosenShape() {
		return chosenShape;
	}

	public void setChosenShape(String chosenShape) {
		this.chosenShape = chosenShape;
	}

	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}

	public boolean isTwoClicks() {
		return twoClicks;
	}

	public void setTwoClicks(boolean twoClicks) {
		this.twoClicks = twoClicks;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getPositionNumber() {
		return positionNumber;
	}

	public void setPositionNumber(int positionNumber) {
		this.positionNumber = positionNumber;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
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
	


}
