package mvc;

public class App {
	

	public static void main(String[] args) {
		
		Model model = new Model();
		Frame frame = new Frame();
		frame.getView().setModel(model);
		Controller controller = new Controller(model, frame);
		frame.setController(controller);
		frame.setVisible(true);
		
	}
	
}


