package mvc;

import javax.swing.JFrame;  

import java.io.*;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

import javax.swing.text.DefaultHighlighter;



import java.awt.Color;
import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class Frame extends JFrame  {

	private static final long serialVersionUID = 1L;
	protected static final Graphics Graphics = null;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int x;
	private int y;
	private int newX;
	private int newY;
	private JPanel pnlFillColor;                 
	private JPanel pnlBorderColor;
	/*public static JTextArea textArea;
	public static boolean done = false;*/
	
	private  JTextArea textArea;
	private  boolean done = false;
	
	
	private ArrayList<String> lines = new ArrayList<String>();
	private JButton btnSave;
	private JButton btnCmdbycmd;
	private JButton btnUndo;
	private JButton btnRedo;
	/*public static JButton btnModify;
	public static JButton btnDelete;
	public static JButton btnSelect;*/
	
	private JButton btnModify;
	private JButton btnDelete;
	private JButton btnSelect;
	
	
	private View pnlForDrawing = new View(); //view
	private Controller controller;
	private JPanel panel;
	private int lineNumber =0; 
	private int index=0;
	private ArrayList<Integer> firstArray = new ArrayList<Integer>();
	private ArrayList<String> strings = new ArrayList<String>();
	private boolean txtOpened = false;


	public View getView() {
		return pnlForDrawing;
	}

	public void setController(Controller controller) {

		this.controller = controller;
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				/*try {
					/*NaslovnaPokretanje window = new NaslovnaPokretanje();
					window.frmPaint.setVisible(true);





					//window.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}*/

			}
		});
	}

	public Frame() {

		initialize();
	}

	private void initialize() {



		this.getContentPane().setBackground(new Color(135, 206, 235));
		this.setBackground(new Color(255, 182, 193));
		this.setFont(new Font("Arial", Font.PLAIN, 15));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource("/windows/resources/paleta.png")));
		this.setTitle("Paint");
		this.setBounds(100, 100, 914, 617);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




		pnlForDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(!btnCmdbycmd.isEnabled()) {
					
					controller.mouseClickedPnl(e.getX(),e.getY());
				}
				
			}

		});


		pnlForDrawing.setBackground(Color.WHITE);
		
		GroupLayout gl_pnlForDrawing = new GroupLayout(pnlForDrawing);
		gl_pnlForDrawing.setHorizontalGroup(
				gl_pnlForDrawing.createParallelGroup(Alignment.LEADING)
				.addGap(0, 907, Short.MAX_VALUE)
				);
		gl_pnlForDrawing.setVerticalGroup(
				gl_pnlForDrawing.createParallelGroup(Alignment.LEADING)
				.addGap(0, 381, Short.MAX_VALUE)
				);
		pnlForDrawing.setLayout(gl_pnlForDrawing);

		panel = new JPanel();

		JPanel pnlLog = new JPanel();

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_pnlLog = new GroupLayout(pnlLog);
		gl_pnlLog.setHorizontalGroup(
				gl_pnlLog.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
				);
		gl_pnlLog.setVerticalGroup(
				gl_pnlLog.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
				);

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 14));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		pnlLog.setLayout(gl_pnlLog);
		
	

		//pnlLog.add(scrollPane);







		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(pnlLog, GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(1)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE))
				.addComponent(pnlForDrawing, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(pnlForDrawing, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlLog, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
				);


		pnlFillColor = new JPanel();
		pnlFillColor.setBackground(Color.WHITE);
		pnlFillColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color c = controller.mouseClickedFillColor();
				pnlFillColor.setBackground(c);


			}
		});


		pnlBorderColor = new JPanel();
		pnlBorderColor.setBackground(Color.BLACK);
		pnlBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color c = controller.mouseClickedBorderColor();
				pnlBorderColor.setBackground(c);


				
			}
		});
		

		btnDelete = new JButton("Delete");

		btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete.setForeground(new Color(139, 0, 139));
		btnDelete.setBackground(new Color(255, 240, 245));



		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.actionPerfomedDelete(e);
			}
		});

		btnModify = new JButton("Modify");


		btnModify.setFont(new Font("Arial", Font.BOLD, 14));

		btnModify.setVisible(true);

		btnModify.setForeground(new Color(139, 0, 139));
		btnModify.setBackground(new Color(255, 240, 245));

		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.actionPerfomedModify(e);


			}
		});





		btnSelect = new JButton("Select");

		btnSelect.setFont(new Font("Arial", Font.BOLD, 14));
		btnSelect.setEnabled(false);
		btnSelect.setEnabled(false);
		btnSelect.setForeground(new Color(139, 0, 139));
		btnSelect.setBackground(new Color(255, 240, 245));

		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


				controller.mouseClickedSelection(e);

				/*if(pnlZaCrtanje.listaObjekata.size() == 0){

											JOptionPane.showMessageDialog(null, "Niste ništa nacrtali!");
										} else {

											pnlZaCrtanje.setOdabranOblik("");

										}*/


			}
		});

		btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Arial", Font.BOLD, 14));
		btnUndo.setForeground(new Color(139, 0, 139));
		btnUndo.setEnabled(false);

		btnUndo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				btnRedo.setEnabled(true);

				if(btnUndo.isEnabled()) {

					controller.undo();
				}



			}
		});

		btnRedo = new JButton("Redo");
		btnRedo.setForeground(new Color(139, 0, 139));
		btnRedo.setFont(new Font("Arial", Font.BOLD, 14));
		btnRedo.setEnabled(false);


		btnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(btnRedo.isEnabled()) {

					controller.redo();
				}


			}
		});

		JButton btnToFront = new JButton("To front");
		btnToFront.setForeground(new Color(139, 0, 139));
		btnToFront.setFont(new Font("Arial", Font.BOLD, 14));
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				controller.moveToFront();
			}
		});

		JButton btnBringToFront = new JButton("Bring to front");
		btnBringToFront.setForeground(new Color(139, 0, 139));
		btnBringToFront.setFont(new Font("Arial", Font.BOLD, 14));
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.bringToFront();
			}
		});

		JButton btnToBack = new JButton("To back");
		btnToBack.setForeground(new Color(139, 0, 139));
		btnToBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				controller.moveToBack();
			}
		});

		JButton btnBringToBack = new JButton("Bring to back");
		btnBringToBack.setForeground(new Color(139, 0, 139));
		btnBringToBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBringToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.bringToBack();
			}
		});

		JLabel lblBojaIvice = new JLabel("Border color:");
		lblBojaIvice.setForeground(new Color(139, 0, 139));
		lblBojaIvice.setFont(new Font("Arial", Font.BOLD, 14));

		JLabel lblAreaColor = new JLabel("Area color:");
		lblAreaColor.setForeground(new Color(139, 0, 139));
		lblAreaColor.setFont(new Font("Arial", Font.BOLD, 14));

		btnSave = new JButton("Save");
		btnSave.setActionCommand("");
		btnSave.setForeground(new Color(139, 0, 139));
		btnSave.setFont(new Font("Arial", Font.BOLD, 14));
		btnSave.setBackground(new Color(255, 240, 245));

		btnSave.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				save();

			}
		});



		JButton btnOpen = new JButton("Open");
		btnOpen.setForeground(new Color(139, 0, 139));
		btnOpen.setFont(new Font("Arial", Font.BOLD, 14));
		btnOpen.setBackground(new Color(255, 240, 245));

		
		btnCmdbycmd = new JButton("cmdBycmd");
		btnCmdbycmd.setForeground(new Color(139, 0, 139));
		btnCmdbycmd.setFont(new Font("Arial", Font.BOLD, 14));
		btnCmdbycmd.setBackground(new Color(255, 240, 245));
		btnCmdbycmd.setEnabled(false);
		
		
		
		btnOpen.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				//pitanje da li zeli da obrise ako ima nesto
				
				txtOpened = false;
				
				if(done==true) {
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to save your previous drawing?","Warning",dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION){

						save();

					}
				}
				


				JFileChooser fs = new JFileChooser(new File("c:\\"));
				fs.setDialogTitle("Open a file");
				fs.addChoosableFileFilter(new FileNameExtensionFilter("Text File", "txt"));
				fs.addChoosableFileFilter(new FileNameExtensionFilter("Bin File", "bin"));

				int result = fs.showOpenDialog(null);

				if(result == JFileChooser.APPROVE_OPTION) {

					String path = fs.getSelectedFile().getAbsolutePath();
					
					try{
						
						if(path.contains(".txt")) {
							
							File file = new File(fs.getSelectedFile().getAbsolutePath());
							controller.openTxt(file);
							btnCmdbycmd.setEnabled(true);
							
						} else if (path.contains(".bin")) {
							
							File file = new File(fs.getSelectedFile().getAbsolutePath());
							controller.loadPainting(file);
							btnCmdbycmd.setEnabled(false);
							if(!controller.getModel().getListOfShapes().isEmpty()) {
								
								btnSelect.setEnabled(true);
							}
							
						}

						
						
						
						



					}catch(Exception ek){
						System.err.println("Error: " + ek.getMessage());
					}

				}




				lines.removeAll(lines);
				
				index=0;
				
				
			
			
			}


		});

	
		
		

	
		btnCmdbycmd.addMouseListener(new MouseAdapter() {
			
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				btnUndo.setEnabled(false);
				btnRedo.setEnabled(false);
				btnBringToBack.setEnabled(false);
				btnBringToFront.setEnabled(false);
				btnToBack.setEnabled(false);
				btnToFront.setEnabled(false);
				btnSelect.setEnabled(false);
				
				
			
				
				firstArray.removeAll(firstArray);
				
				strings.removeAll(strings);
				
				for(String line : textArea.getText().split("\\n")) {
					
					//firstArray.add(textArea.getText().indexOf(line));
					
					firstArray.add(textArea.getText().indexOf(line));
					strings.add(line);
				}
				
				
				
				if(index < firstArray.size()) {
					
					
					

							
							int start = -1;
							int end = -1;
							try {
								
								if(lineNumber < textArea.getLineCount()) {
									
									start = textArea.getLineStartOffset(lineNumber);
									end = textArea.getLineEndOffset(lineNumber);
									
								}
								
								
							} catch (BadLocationException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							
							
							
							
					
				   try {
					   
					   
					   
					   java.awt.Rectangle viewRect = textArea.modelToView(start);
                       // Scroll to make the rectangle visible
                       textArea.scrollRectToVisible(viewRect);
                       // Highlight the text
                       textArea.setCaretPosition(end);
                       textArea.moveCaretPosition(start);
                       // Move the search position beyond the current match
                       
                       
						
						if(start != -1 && end!=-1) {
							
							textArea.getHighlighter().addHighlight(start,
							        end,
							        new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 255, 153)));
							
							
							 textArea.requestFocusInWindow();
							 
						}
						
					
						if(index == 0) {
							
							controller.runCommandByCommand(strings.get(index),null);
						} else {
							
							controller.runCommandByCommand(strings.get(index),strings.get(index-1));
						}
						
						

					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				   
				   
				   
				}
				
				
			
				
				index++;
				lineNumber++;
				
				
			
			
				
				
				

			}
		


		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBojaIvice, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAreaColor, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pnlFillColor, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlBorderColor, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSelect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnModify, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
						.addGap(29)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
										.addGap(34)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
												.addComponent(btnRedo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnUndo, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
										.addGap(18)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(btnBringToFront)
												.addComponent(btnBringToBack))
										.addGap(18)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(btnToBack, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnToFront, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
								.addComponent(btnCmdbycmd, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addContainerGap())
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnUndo)
												.addComponent(btnToFront)
												.addComponent(btnBringToFront)
												.addComponent(btnModify)
												.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																.addComponent(btnRedo)
																.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
																.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
														.addGap(6))
												.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnToBack)
														.addComponent(btnBringToBack)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnDelete)
												.addComponent(btnCmdbycmd, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblBojaIvice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(pnlBorderColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblAreaColor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(pnlFillColor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		panel.setLayout(gl_panel);
		this.getContentPane().setLayout(groupLayout);;

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 239, 213));
		menuBar.setBackground(Color.WHITE);
		this.setJMenuBar(menuBar);

		JMenuItem mntmChooseShape = new JMenuItem("Choose a shape: ");
		mntmChooseShape.setFont(new Font("Arial", Font.BOLD, 12));
		mntmChooseShape.setBackground(Color.WHITE);
		mntmChooseShape.setHorizontalAlignment(SwingConstants.CENTER);
		mntmChooseShape.setForeground(new Color(139, 0, 139));
		menuBar.add(mntmChooseShape);

		JRadioButtonMenuItem rdbtnLine = new JRadioButtonMenuItem("Line");
		rdbtnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.setChosenShape("Line");
			}
		});
		rdbtnLine.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnLine.setBackground(Color.WHITE);

		rdbtnLine.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnLine.setForeground(new Color(139, 0, 139));
		buttonGroup.add(rdbtnLine);
		menuBar.add(rdbtnLine);

		JRadioButtonMenuItem rdbtnRectangle = new JRadioButtonMenuItem("Rectangle");
		rdbtnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.setChosenShape("Rectangle");
			}
		});
		rdbtnRectangle.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnRectangle.setBackground(Color.WHITE);


		rdbtnRectangle.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnRectangle.setForeground(new Color(139, 0, 139));
		buttonGroup.add(rdbtnRectangle);
		menuBar.add(rdbtnRectangle);

		JRadioButtonMenuItem rdbtnCircle = new JRadioButtonMenuItem("Circle");
		rdbtnCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.setChosenShape("Circle");
			}
		});
		rdbtnCircle.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnCircle.setBackground(Color.WHITE);

		rdbtnCircle.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnCircle.setForeground(new Color(139, 0, 139));
		buttonGroup.add(rdbtnCircle);
		menuBar.add(rdbtnCircle);

		JRadioButtonMenuItem rdbtnSquare = new JRadioButtonMenuItem("Square");
		rdbtnSquare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.setChosenShape("Square");
			}
		});
		rdbtnSquare.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnSquare.setBackground(Color.WHITE);


		rdbtnSquare.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnSquare.setForeground(new Color(139, 0, 139));
		buttonGroup.add(rdbtnSquare);
		menuBar.add(rdbtnSquare);

		JRadioButtonMenuItem rdbtnTacka = new JRadioButtonMenuItem("Point");
		rdbtnTacka.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				controller.setChosenShape("Point");
			}
		});
		rdbtnTacka.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnTacka.setBackground(Color.WHITE);

		rdbtnTacka.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnTacka.setForeground(new Color(139, 0, 139));
		buttonGroup.add(rdbtnTacka);
		menuBar.add(rdbtnTacka);

		JRadioButtonMenuItem rdbtnmntmHexagon = new JRadioButtonMenuItem("Hexagon");
		rdbtnmntmHexagon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				controller.setChosenShape("Hexagon");

			}
		});
		rdbtnmntmHexagon.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnmntmHexagon.setForeground(new Color(139, 0, 139));
		rdbtnmntmHexagon.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnmntmHexagon.setBackground(Color.WHITE);
		menuBar.add(rdbtnmntmHexagon);
		buttonGroup.add(rdbtnmntmHexagon);

	}

	public void save() {
		
		txtOpened=false;
		
		if(done==true) {
			
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File", "txt"));
			jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Bin File", "bin"));
			jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));

			int result = jFileChooser.showSaveDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {

				try {

					String path = jFileChooser.getSelectedFile().getAbsolutePath();

					int length = path.length();
					File fileTxt = null;
					File filePng = null;
					File fileBin = null;
					
					if(path.contains(".txt")){
						
						fileTxt = new File(path);
						controller.saveTxt(fileTxt);
						
						/*fileTxt = new File(path);
						String s = path.substring(0, length - 4) + ".png";
						filePng = new File(s);
						String k = path.substring(0, length - 4) + ".bin";
						fileBin = new File(k);*/
						
					} else if(path.contains(".bin")) {
						
						//String k = path.substring(0, length - 4) + ".bin";
						fileBin = new File(path);
						controller.saveBin(fileBin);
						
						
					} else if (!path.contains(".txt") && !path.contains(".png") && !path.contains(".bin")) {
						
						fileTxt = new File(path + ".txt");
						filePng = new File(path + ".png");
						fileBin=new File(path + ".bin");
						controller.saveTxt(fileTxt);
						controller.savePainting(filePng);
						controller.saveBin(fileBin);
					}
					
					
					/*controller.saveTxt(fileTxt);
					controller.savePainting(filePng);
					controller.saveBin(fileBin);*/
				
				

				}catch (Exception exception) {


				}

			

			}
			
			
		}
		
		

	
	}








	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getNewX() {
		return newX;
	}

	public void setNewX(int newX) {
		this.newX = newX;
	}

	public int getNewY() {
		return newY;
	}

	public void setNewY(int newY) {
		this.newY = newY;
	}

	public JPanel getPnlFillColor() {
		return pnlFillColor;
	}

	public void setPnlFillColor(Color c) {
		this.pnlFillColor.setBackground(c);
	}

	public JPanel getPnlBorderColor() {
		return pnlBorderColor;
	}

	public void setPnlBorederColor(Color c) {
		this.pnlBorderColor.setBackground(c);
	}

	public View getPnlForDrawing() {
		return pnlForDrawing;
	}

	public void setPnlForDrawing(View pnlForDrawing) {
		this.pnlForDrawing = pnlForDrawing;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	/*public void setBtnModifikuj(JButton btnModifikuj) {
		this.btnModify = btnModifikuj;
	}*/

	public JButton getBtnDelete() {
		return btnDelete;
	}

	/*public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}*/

	public JButton getBtnSelect() {
		return btnSelect;
	}

	/*public void setBtnSelect(JButton btnSelect) {
		this.btnSelect = btnSelect;
	}*/

	

	/*public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}*/

	public Controller getController() {
		return controller;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnCmdbycmd() {
		return btnCmdbycmd;
	}

	public void setBtnCmdbycmd(JButton btnCmdbycmd) {
		this.btnCmdbycmd = btnCmdbycmd;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public void setBtnSelect(JButton btnSelect) {
		this.btnSelect = btnSelect;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public boolean isTxtOpened() {
		return txtOpened;
	}

	public void setTxtOpened(boolean txtOpened) {
		this.txtOpened = txtOpened;
	}

	
	
}



