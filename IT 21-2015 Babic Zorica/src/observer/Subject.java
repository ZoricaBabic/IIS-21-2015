package observer;

import java.util.Observer;

public interface Subject {
	
     public void registerObserver(Observer observer);
     public void removeObserver(Observer observer);
     public void notifyObservers();
}