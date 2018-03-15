package observer;

import java.util.ArrayList;

import javax.swing.JButton;

import crtanje.NaslovnaPokretanje;

public class Button implements Subject {
	
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private boolean status;
	private NaslovnaPokretanje frame;
	
	public Button(NaslovnaPokretanje frame) {
		
		this.frame = frame;
		
	}
	
	public Button() {};
	
	@Override
	public void addObserver(JButton btn) {
		
		buttons.add(btn);
		
	}

	@Override
	public void removeObserver(JButton btn) {
		
		buttons.remove(btn);
		
	}

	@Override
	public void notifyAllObservers() {
		
		for(JButton button: buttons) {
			
			button.setEnabled(status);
			
		}
		
		
		
		/*ButtonObserver observer = new ButtonObserver(frame);
		
		observer.setEnable(status);*/
		
		
		
		
	}


	public void setStatus(boolean status) {
		
		System.out.println("Poziva se ova metoda");
		
		this.status = status;
		
		notifyAllObservers();
	}

	public ArrayList<JButton> getObservers() {
		return buttons;
	}

	public void setObservers(ArrayList<JButton> observers) {
		this.buttons = observers;
	}

}
