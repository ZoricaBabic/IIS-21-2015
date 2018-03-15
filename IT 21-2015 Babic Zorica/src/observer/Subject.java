package observer;

import javax.swing.JButton;

public interface Subject {
	
	void addObserver(JButton btn);
	void removeObserver(JButton btn);
	void notifyAllObservers();

}
