package observer;

import java.awt.Component;

import crtanje.NaslovnaPokretanje;

public class ButtonObserver implements JButton {

	private NaslovnaPokretanje frame;
	private Button btn = new Button();


	public ButtonObserver(NaslovnaPokretanje frame) {

		this.frame = frame;

	}

	public ButtonObserver() {


	}

	/*@Override
	public void update(boolean status) {


		for(int i=0; i<btn.getObservers().size(); i++) {

			btn.getObservers().get(i).setEnabled(status);
			frame.getBtnModifikuj().setEnabled(status);

		}

		/*frame.getBtnModifikuj().setEnabled(status);
		frame.getBtnObrisi().setEnabled(status);

	}*/
	

	@Override
	public void setEnable(boolean status) {

		for(int i=0; i<btn.getObservers().size(); i++) {

			btn.getObservers().get(i).setEnabled(status);
			System.out.println(btn.getObservers().get(i));
			

		}


	}




}
