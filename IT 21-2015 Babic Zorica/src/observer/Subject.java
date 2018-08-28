package observer;

import java.util.ArrayList;

public class Subject {

	private ArrayList<javax.swing.JButton> observers = new ArrayList<javax.swing.JButton>();
	   private boolean state;

	  
	   public void attach(javax.swing.JButton btn){
	      observers.add(btn);		
	   }

	   public void notifyAllObservers(){
		   
	      for (javax.swing.JButton observer : observers) {
	         
	    	  observer.setEnabled(state);
	      }
	   }

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
		notifyAllObservers();
	} 	
}
