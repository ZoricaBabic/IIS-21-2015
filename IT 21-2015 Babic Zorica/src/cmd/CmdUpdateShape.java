package cmd;



import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Square;
import hexagon.Hexagon;
import mvc.Frame;
import mvc.Model;


public class CmdUpdateShape implements Command {

	private Shape original;
	private Shape newState;
	private Shape oldState;
	private Model model;


	public CmdUpdateShape(Shape original,Shape newState,Model model) {


		this.original = original;
		this.newState = newState;
		this.model = model;

	}

	public CmdUpdateShape() {


	}

	@Override
	public void execute() {

		if(original instanceof Point) {


			oldState = new Point();

			((Point) oldState).setX(((Point) original).getX());
			((Point) oldState).setY(((Point) original).getY());
			oldState.setBorderColor(original.getBorderColor());
			oldState.setSelected(original.isSelected());


			((Point) original).setX(((Point) newState).getX());
			((Point) original).setY(((Point) newState).getY());
			original.setBorderColor(newState.getBorderColor());
			original.setSelected(newState.isSelected());

			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);




		} else if(original instanceof Line) {

			oldState = new Line();

			((Line) oldState).setStart(((Line) original).getStart());
			((Line) oldState).setEnd(((Line) original).getEnd());
			oldState.setBorderColor(original.getBorderColor());
			oldState.setSelected(original.isSelected());


			((Line) original).setStart(((Line) newState).getStart());
			((Line) original).setEnd(((Line) newState).getEnd());

			original.setBorderColor(newState.getBorderColor());
			original.setSelected(newState.isSelected());

			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);


		} else if(original instanceof Circle) {

			oldState=new Circle();

			((Circle) oldState).setCenter(((Circle) original).getCenter());
			((Circle) oldState).setR(((Circle) original).getR());
			oldState.setBorderColor(original.getBorderColor());
			((Circle) oldState).setFillColor(((Circle) original).getFillColor());
			oldState.setSelected(original.isSelected());

			((Circle) original).setCenter(((Circle) newState).getCenter());
			((Circle) original).setR(((Circle) newState).getR());
			original.setBorderColor(newState.getBorderColor());
			((Circle) original).setFillColor(((Circle) newState).getFillColor());
			original.setSelected(newState.isSelected());




			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);



		} else if(original instanceof Rectangle) {



			oldState = new Rectangle();

			((Rectangle) oldState).setpUpLeft(((Rectangle) original).getpUpLeft());
			((Rectangle) oldState).setWidth(((Rectangle) original).getWidth());
			((Rectangle) oldState).setSideLength(((Rectangle) original).getSideLength());
			((Rectangle) oldState).setBorderColor(original.getBorderColor());
			((Rectangle) oldState).setFillColor(((Rectangle) original).getFillColor());
			oldState.setSelected(original.isSelected());

			((Rectangle) original).setpUpLeft(((Rectangle) newState).getpUpLeft());
			((Rectangle) original).setWidth(((Rectangle) newState).getWidth());
			((Rectangle) original).setSideLength(((Rectangle) newState).getSideLength());
			((Rectangle) original).setBorderColor(newState.getBorderColor());
			((Rectangle) original).setFillColor(((Rectangle) newState).getFillColor());
			original.setSelected(newState.isSelected());

			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);


		}  else if(original instanceof Square) {

			oldState = new Square();



			((Square) oldState).setpUpLeft(((Square) original).getpUpLeft());
			((Square) oldState).setSideLength(((Square) original).getSideLength());
			((Square) oldState).setBorderColor(original.getBorderColor());
			((Square) oldState).setFillColor(((Square) original).getFillColor());
			oldState.setSelected(original.isSelected());

			((Square) original).setpUpLeft(((Square) newState).getpUpLeft());
			((Square) original).setSideLength(((Square) newState).getSideLength());
			((Square) original).setBorderColor(newState.getBorderColor());
			((Square) original).setFillColor(((Square) newState).getFillColor());
			original.setSelected(newState.isSelected());
			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);


		} else if (original instanceof HexagonAdapter) {



			oldState = new HexagonAdapter();
			((HexagonAdapter) oldState).setHexagon(new Hexagon(((HexagonAdapter) original).getHexagon().getX(),((HexagonAdapter) original).getHexagon().getY(),((HexagonAdapter) original).getHexagon().getR()));
			((HexagonAdapter) oldState).getHexagon().setAreaColor(((HexagonAdapter) original).getHexagon().getAreaColor());
			((HexagonAdapter) oldState).getHexagon().setBorderColor(((HexagonAdapter) original).getHexagon().getBorderColor());
			oldState.setSelected(original.isSelected());

			System.out.println("OLD STATE: " + oldState);


			//menjam original

			((HexagonAdapter) original).setHexagon(((HexagonAdapter) newState).getHexagon());



			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);


		}

		/*if(print == true) {

			Frame.textArea.append("Modified: " + original + "\n");
		}*/



	}

	@Override
	public void unexecute() {

		if(original instanceof Point) {


			((Point) original).setX(((Point) oldState).getX());
			((Point) original).setY(((Point) oldState).getY());
			original.setBorderColor(oldState.getBorderColor());
			original.setSelected(oldState.isSelected());

			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);


		} else if(original instanceof Line) {



			((Line) original).setStart(((Line) oldState).getStart());
			((Line) original).setEnd(((Line) oldState).getEnd());
			original.setBorderColor(oldState.getBorderColor());
			original.setSelected(oldState.isSelected());
			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);

		} else if(original instanceof Circle) {



			((Circle) original).setCenter(((Circle) oldState).getCenter());
			((Circle) original).setR(((Circle) oldState).getR());
			((Circle) original).setBorderColor(oldState.getBorderColor());
			((Circle) original).setFillColor(((Circle) oldState).getFillColor());
			original.setSelected(oldState.isSelected());


			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);





		} else if(original instanceof Rectangle) {



			((Rectangle) original).setWidth(((Rectangle) oldState).getWidth());
			((Rectangle) original).setSideLength(((Rectangle) oldState).getSideLength());
			((Rectangle) original).setpUpLeft(((Rectangle) oldState).getpUpLeft());
			((Rectangle) original).setBorderColor(((Rectangle) oldState).getBorderColor());
			((Rectangle) original).setFillColor(((Rectangle) oldState).getFillColor());
			original.setSelected(oldState.isSelected());

			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);



		} else if(original instanceof Square) {





			((Square) original).setSideLength(((Square) oldState).getSideLength());
			((Square) original).setpUpLeft(((Square) oldState).getpUpLeft());
			((Square) original).setBorderColor(((Square) oldState).getBorderColor());
			((Square) original).setFillColor(((Square) oldState).getFillColor());
			original.setSelected(oldState.isSelected());

			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);

		} else if(original instanceof HexagonAdapter) {

			/*((HexagonAdapter) original).getHexagon().setX(((HexagonAdapter) oldState).getHexagon().getX());
			((HexagonAdapter) original).getHexagon().setY(((HexagonAdapter) oldState).getHexagon().getY());
			((HexagonAdapter) original).getHexagon().setR(((HexagonAdapter) oldState).getHexagon().getR());
			((HexagonAdapter) original).getHexagon().setAreaColor(((HexagonAdapter) oldState).getHexagon().getAreaColor());
			((HexagonAdapter) original).getHexagon().setBorderColor(((HexagonAdapter) oldState).getHexagon().getBorderColor());
			((HexagonAdapter) original).getHexagon().setSelected(((HexagonAdapter) oldState).selektovan);*/

			((HexagonAdapter) original).setHexagon(((HexagonAdapter) oldState).getHexagon());

			model.getListOfShapes().set(model.getListOfShapes().indexOf(original), original);


		}

		

		/*if(print == true) {

			Frame.textArea.append("UNDO >>> Modified: " + original + "\n");
		}*/


	}

	public Shape getOriginal() {
		return original;
	}

	public void setOriginal(Shape original) {
		this.original = original;
	}

	public Shape getNewState() {
		return newState;
	}

	public void setNewState(Shape newState) {
		this.newState = newState;
	}

	public Shape getOldState() {
		return oldState;
	}

	public void setOldState(Shape oldState) {
		this.oldState = oldState;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	



}
