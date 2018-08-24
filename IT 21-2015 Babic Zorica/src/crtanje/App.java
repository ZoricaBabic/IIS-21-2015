package crtanje;

import java.awt.Color;


import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

import cmd.CmdUpdateShape;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;



import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;

public class App {
	

	public static void main(String[] args) {
		
		Model model = new Model();
		NaslovnaPokretanje frame = new NaslovnaPokretanje();
		frame.getView().setModel(model);
		Controller controller = new Controller(model, frame);
		CmdUpdateShape.model = model;
		/*Button button = new Button(frame);
		button.addObserver(frame.getBtnModifikuj());
		button.addObserver(frame.getBtnObrisi());
		button.setStatus(false);*/ /////TREBA U CONTROLLER
		
		/*button.addObserver(frame.getBtnModifikuj());
		button.addObserver(frame.getBtnObrisi());
		ButtonObserver observer = new ButtonObserver(frame);

		button.setStatus(false);*/
		frame.setController(controller);
		frame.setVisible(true);
		
	}
	
}


