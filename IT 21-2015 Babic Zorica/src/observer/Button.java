package observer;

public class Button implements Observer {
	
	private boolean state;
	
	public Button(boolean state) {
		
		this.state = state;
	}
	

	@Override
	public void update(boolean state) {
		
		
		System.out.println("Menaj se dugme na: " + state);
		
	}
	
	

}
