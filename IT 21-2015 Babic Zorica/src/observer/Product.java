package observer;


import java.util.ArrayList;
import java.util.Observer;
 
public class Product implements Subject{
 
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private boolean state;
  
    
    
	public Product(boolean state) {
		super();
		this.state = state;
	}
	
	
 
	public void setState( boolean state) {
		this.state = state;
		notifyObservers();
	}
 
	public void notifyObservers() {
		
		System.out.println("Notifying to all the subscribers when product became available");
		 for (Observer ob : observers) {
			 
             ob.update(null, state);
      }
 
	}
 
	public void registerObserver(Observer observer) {
		 observers.add(observer);
		
	}
 
	public void removeObserver(Observer observer) {
		 observers.remove(observer);
		
	}
	
	public ArrayList getObservers() {
		return observers;
	}
	public void setObservers(ArrayList observers) {
		this.observers = observers;
	}
	
 
}