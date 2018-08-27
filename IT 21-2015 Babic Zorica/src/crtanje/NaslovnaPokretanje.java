package crtanje;

import javax.swing.JFrame;  
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import geometrija.Krug;
import geometrija.Kvadrat;
import geometrija.Linija;
import geometrija.PovrsinskiOblik;
import geometrija.Pravougaonik;
import geometrija.Tacka;
import strategy.Context;
import strategy.LogOperation;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.TextArea;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class NaslovnaPokretanje extends JFrame  {

	private static final long serialVersionUID = 1L;
	protected static final Graphics Graphics = null;
	private static final int ALWAYS_UPDATE = 0;
	private JFrame frmPaint;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private int x;
	private int y;
	private int novoX;
	private int novoY;
	private Kvadrat kv; 
	private Tacka t;
	private Pravougaonik p;
	private Krug kr;
	private Linija l;
	private String s;
	private JPanel pnlBojaUnutrasnjosti;
	private JPanel pnlBojaIvice;
	public static JTextArea textArea;
	public static boolean done = false;
	
	private ArrayList<String> lines = new ArrayList<String>();
	
	
	public static JButton btnSave;
	
	public static JButton btnCmdbycmd;



	private JButton btnUndo;
	private JButton btnRedo;

	public static JButton btnModifikuj;
	public static JButton btnObrisi;

	public static JButton btnSelektuj;

	private ProstorZaCrtanje pnlZaCrtanje = new ProstorZaCrtanje(); //view

	private Controller controller;
	private JPanel panel;
	private boolean isShiftDown = false;
	private Context context;
	
	private int lineNumber =0; 
	
	
	
	
	
	private int index=0;
	private ArrayList indexi = new ArrayList();
	
	private ArrayList firstArray = new ArrayList();
	//private LinkedHashMap<Integer,Integer> firstArray = new LinkedHashMap<Integer,Integer>();
	
	
	
	
	private ArrayList<String> strings = new ArrayList();
	private Set secondSet = new HashSet();
	
	private DefaultHighlighter.DefaultHighlightPainter df = new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 255, 153));
	
	
	


	//tofront, toback observer




	public ProstorZaCrtanje getView() {
		return pnlZaCrtanje;
	}

	public void setController(Controller controller) {

		this.controller = controller;
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					/*NaslovnaPokretanje window = new NaslovnaPokretanje();
					window.frmPaint.setVisible(true);*/





					//window.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public NaslovnaPokretanje() {

		/*KeyAdapter ka = new KeyAdapter() {

			    @Override
			    public void keyReleased(KeyEvent e) {
			        super.keyReleased(e);
			        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			            isShiftDown=false;
			        }
			    }

			    @Override
			    public void keyPressed(KeyEvent e) {
			        super.keyPressed(e);
			        System.out.println("Dugme je pritisnuto!");
			        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			            isShiftDown=true;
			        }
			    }

			};*/


		initialize();
	}

	private void initialize() {


		//frmPaint = new JFrame();
		this.getContentPane().setBackground(new Color(135, 206, 235));
		this.setBackground(new Color(255, 182, 193));
		this.setFont(new Font("Arial", Font.PLAIN, 15));
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(NaslovnaPokretanje.class.getResource("/windows/resources/paleta.png")));
		this.setTitle("crtanje");
		this.setBounds(100, 100, 914, 617);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



		//pnlZaCrtanje = new ProstorZaCrtanje();



		pnlZaCrtanje.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(!btnCmdbycmd.isEnabled()) {
					
					controller.mouseClickedPnl(e.getX(),e.getY());
				}
				
			}

		});


		pnlZaCrtanje.setBackground(Color.WHITE);
		GroupLayout gl_pnlZaCrtanje = new GroupLayout(pnlZaCrtanje);
		gl_pnlZaCrtanje.setHorizontalGroup(
				gl_pnlZaCrtanje.createParallelGroup(Alignment.LEADING)
				.addGap(0, 907, Short.MAX_VALUE)
				);
		gl_pnlZaCrtanje.setVerticalGroup(
				gl_pnlZaCrtanje.createParallelGroup(Alignment.LEADING)
				.addGap(0, 381, Short.MAX_VALUE)
				);
		pnlZaCrtanje.setLayout(gl_pnlZaCrtanje);

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
				.addComponent(pnlZaCrtanje, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(pnlZaCrtanje, GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlLog, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
				);


		pnlBojaUnutrasnjosti = new JPanel();
		pnlBojaUnutrasnjosti.setBackground(Color.WHITE);
		pnlBojaUnutrasnjosti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color c = controller.mouseClickedBojaUnutrasnjosti();
				pnlBojaUnutrasnjosti.setBackground(c);



				/*Color currentBojaUnutrasnjosti = pnlBojaUnutrasnjosti.getBackground();

						Color bojaUnutrasnjosti = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK);

						if(bojaUnutrasnjosti  == null) {

							pnlBojaUnutrasnjosti.setBackground(currentBojaUnutrasnjosti);
						} else {

							pnlBojaUnutrasnjosti.setBackground(bojaUnutrasnjosti);
							pnlZaCrtanje.setBojaUnutrasnjosti(bojaUnutrasnjosti);
						}*/

			}
		});


		pnlBojaIvice = new JPanel();
		pnlBojaIvice.setBackground(Color.BLACK);
		pnlBojaIvice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color c = controller.mouseClickedBojaIvice();
				pnlBojaIvice.setBackground(c);


				/*Color currentBojaIvice = pnlBojaIvice.getBackground();
						Color bojaIvice = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK);

						pnlZaCrtanje.setBojaIvice(bojaIvice);
						System.out.println("boja je:" + pnlZaCrtanje.getBackground());
						if(bojaIvice  == null) {

							pnlBojaIvice.setBackground(currentBojaIvice);
						} else {

							pnlBojaIvice.setBackground(bojaIvice);
							pnlZaCrtanje.setBojaIvice(bojaIvice);
						}*/
			}
		});
		//pnlBojaIvice.setBackground(Color.BLACK);
		//pnlZaCrtanje.setBojaIvice(pnlBojaIvice.getBackground());



		/*btnBojaIvice.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								Color currentBojaIvice = pnlBojaIvice.getBackground();
								Color bojaIvice = JColorChooser.showDialog(null, "Izaberi boju", Color.BLACK);

								pnlZaCrtanje.setBojaIvice(bojaIvice);
								System.out.println("boja je:" + pnlZaCrtanje.getBackground());
								if(bojaIvice  == null) {

									pnlBojaIvice.setBackground(currentBojaIvice);
								} else {

									pnlBojaIvice.setBackground(bojaIvice);
									pnlZaCrtanje.setBojaIvice(bojaIvice);
								}

							}
						});*/






		btnObrisi = new JButton("Delete");

		btnObrisi.setFont(new Font("Arial", Font.BOLD, 14));
		btnObrisi.setForeground(new Color(139, 0, 139));
		btnObrisi.setBackground(new Color(255, 240, 245));



		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.actionPerfomedDelete(e);


				/*if(pnlZaCrtanje.stekSelekcija.isEmpty() == true){

									JOptionPane.showMessageDialog(null, "Niste ništa selektovali");

								} else  {

									int odgovor = JOptionPane.YES_NO_OPTION;
									JOptionPane.showConfirmDialog (null, "Da li želite obrisati oblik koji ste selektovali","Poruka", odgovor);

									if(odgovor == JOptionPane.YES_OPTION){

										if(model.stackPeek() instanceof Pravougaonik){


											pnlZaCrtanje.listaObjekata.remove(pnlZaCrtanje.stekSelekcija.peek());
											pnlZaCrtanje.stekSelekcija.pop();
											pnlZaCrtanje.repaint();


										} else if(pnlZaCrtanje.stekSelekcija.peek() instanceof Tacka){

											pnlZaCrtanje.listaObjekata.remove(pnlZaCrtanje.stekSelekcija.peek());
											pnlZaCrtanje.stekSelekcija.pop();
											pnlZaCrtanje.repaint();

										} else if(pnlZaCrtanje.stekSelekcija.peek() instanceof Linija){

											pnlZaCrtanje.listaObjekata.remove(pnlZaCrtanje.stekSelekcija.peek());
											pnlZaCrtanje.stekSelekcija.pop();
											pnlZaCrtanje.repaint();


										} else if(pnlZaCrtanje.stekSelekcija.peek() instanceof Kvadrat){

											pnlZaCrtanje.listaObjekata.remove(pnlZaCrtanje.stekSelekcija.peek());
											pnlZaCrtanje.stekSelekcija.pop();
											pnlZaCrtanje.repaint();


										} else if (pnlZaCrtanje.stekSelekcija.peek() instanceof Krug) {

											pnlZaCrtanje.listaObjekata.remove(pnlZaCrtanje.stekSelekcija.peek());
											pnlZaCrtanje.stekSelekcija.pop();
											pnlZaCrtanje.repaint();


										} 
									}
								}*/

			}
		});

		btnModifikuj = new JButton("Mofify");


		btnModifikuj.setFont(new Font("Arial", Font.BOLD, 14));

		btnModifikuj.setVisible(true);

		btnModifikuj.setForeground(new Color(139, 0, 139));
		btnModifikuj.setBackground(new Color(255, 240, 245));

		btnModifikuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.actionPerfomedModify(e);

				/*if(pnlZaCrtanje.stekSelekcija.isEmpty() == true){

											JOptionPane.showMessageDialog(null, "Niste ništa selektovali");

										} else {

											if(pnlZaCrtanje.stekSelekcija.peek() instanceof Pravougaonik){


												DlgOsobinePravougaonika osobine = new DlgOsobinePravougaonika(pnlZaCrtanje);
												osobine.setX(((Pravougaonik) pnlZaCrtanje.stekSelekcija.peek()).gettGoreLevo().getX());
												osobine.setY(((Pravougaonik) pnlZaCrtanje.stekSelekcija.peek()).gettGoreLevo().getY());
												osobine.setDuzina(((Pravougaonik) pnlZaCrtanje.stekSelekcija.peek()).getDuzinaStranice());
												osobine.setSirina(((Pravougaonik) pnlZaCrtanje.stekSelekcija.peek()).getSirina());
												osobine.setBojaIvice(pnlZaCrtanje.stekSelekcija.peek().getBojaIvice());
												osobine.setBojaUnutrasnjosti(((PovrsinskiOblik) pnlZaCrtanje.stekSelekcija.peek()).getBojaUnutrasnjosti());

												osobine.getTxtXKoordinata().setText(Integer.toString(osobine.getX()));
												osobine.getTxtYKoordinata().setText(Integer.toString(osobine.getY()));
												osobine.getTxtDuzina().setText(Integer.toString(osobine.getDuzina()));
												osobine.getTxtSirina().setText(Integer.toString(osobine.getSirina()));
												osobine.getEdpBojaIvice().setBackground(osobine.getBojaIvice());
												osobine.getEdpBojaUnutrasnjosti().setBackground(osobine.getBojaUnutrasnjosti());





												osobine.setVisible(true);
												p = osobine.getP();
												pnlZaCrtanje.listaObjekata.remove(pnlZaCrtanje.stekSelekcija.peek());
												pnlZaCrtanje.listaObjekata.add(p);
												pnlZaCrtanje.stekSelekcija.removeAllElements();
												pnlZaCrtanje.repaint();

											} else if(pnlZaCrtanje.stekSelekcija.peek() instanceof Tacka){

												DlgOsobineTacke osobine = new DlgOsobineTacke(pnlZaCrtanje);

												osobine.setX(((Tacka) pnlZaCrtanje.stekSelekcija.peek()).getX());
												osobine.setY(((Tacka) pnlZaCrtanje.stekSelekcija.peek()).getY());
												osobine.setBojaIvice(pnlZaCrtanje.stekSelekcija.peek().getBojaIvice());


												osobine.getTxtXKoordinata().setText(Integer.toString(osobine.getX()));
												osobine.getTxtYKoordinata().setText(Integer.toString(osobine.getY()));
												osobine.getEdpBoja().setBackground(osobine.getBojaIvice());


												osobine.setVisible(true);
												t = osobine.getT();
												pnlZaCrtanje.repaint();
												pnlZaCrtanje.listaObjekata.remove(pnlZaCrtanje.stekSelekcija.peek());
												pnlZaCrtanje.listaObjekata.add(t);
												pnlZaCrtanje.stekSelekcija.removeAllElements();




											} else if(pnlZaCrtanje.stekSelekcija.peek() instanceof Linija){

												DlgOsobineLinije osobine = new DlgOsobineLinije(pnlZaCrtanje);
												osobine.setxPocetna(((Linija) pnlZaCrtanje.stekSelekcija.peek()).gettPocetna().getX());
												osobine.setyPocetna(((Linija) pnlZaCrtanje.stekSelekcija.peek()).gettPocetna().getY());
												osobine.setxKrajnja(((Linija) pnlZaCrtanje.stekSelekcija.peek()).gettKrajnja().getX());
												osobine.setyKrajnja(((Linija) pnlZaCrtanje.stekSelekcija.peek()).gettKrajnja().getY());
												osobine.setBojaIvice(pnlZaCrtanje.stekSelekcija.peek().getBojaIvice());

												osobine.getTxtXKoordinataPocetneTacke().setText(Integer.toString(osobine.getxPocetna()));
												osobine.getTxtYKoordinataPocetneTacke().setText(Integer.toString(osobine.getyPocetna()));
												osobine.getTxtXKoordinataKrajnjeTacke().setText(Integer.toString(osobine.getxKrajnja()));
												osobine.getTxtYKoordinataKrajnjeTacke().setText(Integer.toString(osobine.getyKrajnja()));
												osobine.getEdpBoja().setBackground(osobine.getBojaIvice());


												osobine.setVisible(true);
												l = osobine.getL();
												pnlZaCrtanje.listaObjekata.remove(pnlZaCrtanje.stekSelekcija.peek());
												pnlZaCrtanje.listaObjekata.add(l);
												pnlZaCrtanje.stekSelekcija.removeAllElements();
												pnlZaCrtanje.repaint();




											} else if(pnlZaCrtanje.stekSelekcija.peek() instanceof Kvadrat){

												DlgOsobineKvadrata osobine = new DlgOsobineKvadrata(pnlZaCrtanje);
												osobine.setX(((Kvadrat) pnlZaCrtanje.stekSelekcija.peek()).gettGoreLevo().getX());
												osobine.setY(((Kvadrat) pnlZaCrtanje.stekSelekcija.peek()).gettGoreLevo().getY());
												osobine.setDuzinaStranice(((Kvadrat) pnlZaCrtanje.stekSelekcija.peek()).getDuzinaStranice());
												osobine.setBojaIvice(pnlZaCrtanje.stekSelekcija.peek().getBojaIvice());
												osobine.setBojaUnutrasnjosti(((PovrsinskiOblik) pnlZaCrtanje.stekSelekcija.peek()).getBojaUnutrasnjosti());

												osobine.getTxtXKoordinata().setText(Integer.toString(osobine.getX()));
												osobine.getTxtYKoordinata().setText(Integer.toString(osobine.getY()));
												osobine.getTxtDuzinaStranice().setText(Integer.toString(osobine.getDuzinaStranice()));
												osobine.getEdpBojaIvice().setBackground(osobine.getBojaIvice());
												osobine.getEdpBojaUnutrasnjosti().setBackground(osobine.getBojaUnutrasnjosti());


												osobine.setVisible(true);

												kv = osobine.getKv();
												pnlZaCrtanje.listaObjekata.remove(pnlZaCrtanje.stekSelekcija.peek());
												pnlZaCrtanje.listaObjekata.add(kv);
												pnlZaCrtanje.stekSelekcija.removeAllElements();
												pnlZaCrtanje.repaint();




											} else if (pnlZaCrtanje.stekSelekcija.peek() instanceof Krug) {

												DlgOsobineKruga osobine = new DlgOsobineKruga(pnlZaCrtanje);
												osobine.setX(((Krug) pnlZaCrtanje.stekSelekcija.peek()).getCentar().getX());
												osobine.setY(((Krug) pnlZaCrtanje.stekSelekcija.peek()).getCentar().getY());
												osobine.setPoluprecnik(((Krug) pnlZaCrtanje.stekSelekcija.peek()).getR());
												osobine.setBojaIvice(pnlZaCrtanje.stekSelekcija.peek().getBojaIvice());
												osobine.setBojaUnutrasnjosti(((PovrsinskiOblik) pnlZaCrtanje.stekSelekcija.peek()).getBojaUnutrasnjosti());


												osobine.getTxtXKoordinata().setText(Integer.toString(osobine.getX()));
												osobine.getTxtYKoordinata().setText(Integer.toString(osobine.getY()));
												osobine.getTxtPoluprecnik().setText(Integer.toString(osobine.getPoluprecnik()));
												osobine.getEdpBojaIvice().setBackground(osobine.getBojaIvice());
												osobine.getEdpBojaUnutrasnjosti().setBackground(osobine.getBojaUnutrasnjosti());
												osobine.setVisible(true);

												kr = osobine.getKr();
												pnlZaCrtanje.listaObjekata.remove(pnlZaCrtanje.stekSelekcija.peek());
												pnlZaCrtanje.listaObjekata.add(kr);
												pnlZaCrtanje.stekSelekcija.removeAllElements();
												pnlZaCrtanje.repaint();

											}

										}*/



			}
		});





		btnSelektuj = new JButton("Select");

		btnSelektuj.setFont(new Font("Arial", Font.BOLD, 14));
		btnSelektuj.setEnabled(false);
		btnSelektuj.setEnabled(false);
		btnSelektuj.setForeground(new Color(139, 0, 139));
		btnSelektuj.setBackground(new Color(255, 240, 245));

		btnSelektuj.addMouseListener(new MouseAdapter() {
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

				saveTxt();

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
				
				if(!textArea.getText().isEmpty() && done==true) {
					
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save your previous drawing?","Warning",dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION){

						saveTxt();

					}
				}
				


				JFileChooser fs = new JFileChooser(new File("c:\\"));
				fs.setDialogTitle("Open a file");
				//fs.setFileFilter(new FileTypeFilter(".txt","Text File"));
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
							if(!controller.getModel().getListaObjekata().isEmpty()) {
								
								btnSelektuj.setEnabled(true);
							}
							
						}

						
						
						
						



					}catch(Exception ek){
						System.err.println("Error: " + ek.getMessage());
					}

				}




				lines.removeAll(lines);
				
				index=0;
				
				
				
				
				
				done=false;
			}


		});

	
		
		

	
		btnCmdbycmd.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//String line = null;
				/*boolean exists = false;
				int index = -1;
				int k = -1; */
				
				//firstArray.removeAll(firstArray);
				
			
				
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
								
								start = textArea.getLineStartOffset(lineNumber);
								end = textArea.getLineEndOffset(lineNumber);
								
								
							} catch (BadLocationException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							
							
							
							
						   
				
				  /* int y;
				    Rectangle startIndex;
					try {
						
						startIndex = textArea.modelToView(start);
						y = startIndex.y + (scrollPane.getHeight() - 10);
						
					    textArea.setCaretPosition(textArea.viewToModel(new Point(startIndex.x, y)));
					    scrollPane.scrollRectToVisible(new Rectangle(startIndex.x, y));
					    
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
				
					
				   try {
					   
					
						/*textArea.getHighlighter().addHighlight(pos2,
						        pos2 + strings.get(index).length(),
						        new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 255, 153)));
											//lines.add(line);*/
					   
					   
					   Rectangle viewRect = textArea.modelToView(start);
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
				   
				   
				   
				   /*Rectangle startIndex = null;
					try {
						startIndex = textArea.modelToView(start);
					} catch (BadLocationException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					int y = startIndex.y + (scrollPane.getHeight() - 10);
				    textArea.setCaretPosition(textArea.viewToModel(new Point(startIndex.x, y)));
				    scrollPane.scrollRectToVisible(new Rectangle(startIndex.x, y));*/
				}
				
				
			
				
				index++;
				lineNumber++;
				
				
			
			
				
				
				//System.out.println(index);
				
				
				/*for(String line : textArea.getText().split("\\n")) {
					
					int index = textArea.getText().indexOf(line);
					
					for(int i =0; i<indexi.size(); i++) {
						
						if(!indexi.get(i).equals(index)) {
							
							indexi.add(index);
							
							System.out.println("Pronadjena je linija >>>>> ");
							
						    int y;
						    Rectangle startIndex;
							try {
								
								startIndex = textArea.modelToView(index);
								
								y = startIndex.y + (scrollPane.getHeight() - 10);
								
							    textArea.setCaretPosition(textArea.viewToModel(new Point(startIndex.x, y)));
							    scrollPane.scrollRectToVisible(new Rectangle(startIndex.x, y));
							    
							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							
						   try {

								textArea.getHighlighter().addHighlight(index,
										index + line.length(),
								        new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 255, 153)));
								
								//lines.add(line);
								controller.runCommandByCommand(line);
								

							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
							
							
							break;
						}
					}
					
					if(indexi.isEmpty()) {
						
						indexi.add(index);
						
						System.out.println("Pronadjena je linija >>>>> ");
						
					    int y;
					    Rectangle startIndex;
						try {
							
							startIndex = textArea.modelToView(index);
							
							y = startIndex.y + (scrollPane.getHeight() - 10);
							
						    textArea.setCaretPosition(textArea.viewToModel(new Point(startIndex.x, y)));
						    scrollPane.scrollRectToVisible(new Rectangle(startIndex.x, y));
						    
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						
					   try {

							textArea.getHighlighter().addHighlight(index,
									index + line.length(),
							        new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 255, 153)));
							
							//lines.add(line);
							controller.runCommandByCommand(line);
							

						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
						
						break;
						
						
					}
					
	
					
				
					
						
						
						
					
					
				}*/
				
				
				/*for(String l : textArea.getText().split("\\n")) {
					
					if(lines.isEmpty()) {
						
						line = l;
						lines.add(l);
						
						
						int pos2 = textArea.getText().indexOf(line);
					    int y;
					    Rectangle startIndex;
						try {
							
							startIndex = textArea.modelToView(pos2);
							y = startIndex.y + (scrollPane.getHeight() - 10);
							
						    textArea.setCaretPosition(textArea.viewToModel(new Point(startIndex.x, y)));
						    scrollPane.scrollRectToVisible(new Rectangle(startIndex.x, y));
						    
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						
					   try {

							textArea.getHighlighter().addHighlight(pos2,
							        pos2 + line.length(),
							        new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 255, 153)));
							
							//lines.add(line);
							controller.runCommandByCommand(line);
							

						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
						
						break;
						
					} else {
						
						int n=0;
						for(int i=0; i<lines.size(); i++) {
							
							if(lines.get(i).equals(l)) {
								
								n++;
							}
						}
						
						if(n==0) {
								
							line=l;
							lines.add(l);
							
							int pos2 = textArea.getText().indexOf(line);
						    int y;
						    Rectangle startIndex;
							try {
								
								startIndex = textArea.modelToView(pos2);
								y = startIndex.y + (scrollPane.getHeight() - 10);
								
							    textArea.setCaretPosition(textArea.viewToModel(new Point(startIndex.x, y)));
							    scrollPane.scrollRectToVisible(new Rectangle(startIndex.x, y));
							    
							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							
						   try {

								textArea.getHighlighter().addHighlight(pos2,
								        pos2 + line.length(),
								        new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 255, 153)));
								
								//lines.add(line);
								controller.runCommandByCommand(line);
								

							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
							
							break;
						}
						
					}
				}*/

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
								.addComponent(pnlBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlBojaIvice, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnObrisi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSelektuj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnModifikuj, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
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
												.addComponent(btnModifikuj)
												.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
																.addComponent(btnRedo)
																.addComponent(btnSelektuj, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
																.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
														.addGap(6))
												.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
														.addComponent(btnToBack)
														.addComponent(btnBringToBack)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnObrisi)
												.addComponent(btnCmdbycmd, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblBojaIvice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(pnlBojaIvice, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblAreaColor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
												.addComponent(pnlBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		panel.setLayout(gl_panel);
		this.getContentPane().setLayout(groupLayout);;

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(255, 239, 213));
		menuBar.setBackground(Color.WHITE);
		this.setJMenuBar(menuBar);

		JMenuItem mntmIzaberiOblik = new JMenuItem("Izaberi oblik:");
		mntmIzaberiOblik.setFont(new Font("Arial", Font.BOLD, 12));
		mntmIzaberiOblik.setBackground(Color.WHITE);
		mntmIzaberiOblik.setHorizontalAlignment(SwingConstants.CENTER);
		mntmIzaberiOblik.setForeground(new Color(139, 0, 139));
		menuBar.add(mntmIzaberiOblik);

		JRadioButtonMenuItem rdbtnLinija = new JRadioButtonMenuItem("Linija");
		rdbtnLinija.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.setOdabranOblik("Linija");
			}
		});
		rdbtnLinija.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnLinija.setBackground(Color.WHITE);

		rdbtnLinija.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnLinija.setForeground(new Color(139, 0, 139));
		buttonGroup.add(rdbtnLinija);
		menuBar.add(rdbtnLinija);

		JRadioButtonMenuItem rdbtnPravougaonik = new JRadioButtonMenuItem("Pravougaonik");
		rdbtnPravougaonik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.setOdabranOblik("Pravougaonik");
			}
		});
		rdbtnPravougaonik.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnPravougaonik.setBackground(Color.WHITE);


		rdbtnPravougaonik.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPravougaonik.setForeground(new Color(139, 0, 139));
		buttonGroup.add(rdbtnPravougaonik);
		menuBar.add(rdbtnPravougaonik);

		JRadioButtonMenuItem rdbtnKrug = new JRadioButtonMenuItem("Krug");
		rdbtnKrug.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.setOdabranOblik("Krug");
			}
		});
		rdbtnKrug.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnKrug.setBackground(Color.WHITE);

		rdbtnKrug.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnKrug.setForeground(new Color(139, 0, 139));
		buttonGroup.add(rdbtnKrug);
		menuBar.add(rdbtnKrug);

		JRadioButtonMenuItem rdbtnKvadrat = new JRadioButtonMenuItem("Kvadrat");
		rdbtnKvadrat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				controller.setOdabranOblik("Kvadrat");
			}
		});
		rdbtnKvadrat.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnKvadrat.setBackground(Color.WHITE);


		rdbtnKvadrat.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnKvadrat.setForeground(new Color(139, 0, 139));
		buttonGroup.add(rdbtnKvadrat);
		menuBar.add(rdbtnKvadrat);

		JRadioButtonMenuItem rdbtnTacka = new JRadioButtonMenuItem("Ta\u010Dka");
		rdbtnTacka.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				controller.setOdabranOblik("Tacka");
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

				controller.setOdabranOblik("Hexagon");

			}
		});
		rdbtnmntmHexagon.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnmntmHexagon.setForeground(new Color(139, 0, 139));
		rdbtnmntmHexagon.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnmntmHexagon.setBackground(Color.WHITE);
		menuBar.add(rdbtnmntmHexagon);
		buttonGroup.add(rdbtnmntmHexagon);





		/*pnlZaCrtanje.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						System.out.println("Kliknuto je na panel");

						controller.mouseClickedDrawing(e);

						/*pnlZaCrtanje.stekSelekcija.removeAllElements();

						if(pnlZaCrtanje.getOdabranOblik() == ""){

							pnlZaCrtanje.setxSelekcija(e.getX());
							pnlZaCrtanje.setySelekcija(e.getY());
							repaint();

						}



						if(pnlZaCrtanje.getOdabranOblik() == "Tacka" || pnlZaCrtanje.getOdabranOblik() == null)
						{

							pnlZaCrtanje.setX(e.getX());
							pnlZaCrtanje.setY(e.getY());
							repaint();

							Tacka t = new Tacka(pnlZaCrtanje.getX(),pnlZaCrtanje.getY());
							t.setBojaIvice(pnlZaCrtanje.getBojaIvice());
							pnlZaCrtanje.listaObjekata.add(t);


						}

						if(pnlZaCrtanje.getOdabranOblik() == "Linija"){

							if(pnlZaCrtanje.isDvaKlika() == false){

								pnlZaCrtanje.setX(e.getX());
								pnlZaCrtanje.setY(e.getY());
								pnlZaCrtanje.setDvaKlika(true);
							} else {

								pnlZaCrtanje.setNovoX(e.getX());
								pnlZaCrtanje.setNovoY(e.getY());
								repaint();
								Linija l = new Linija(new Tacka(pnlZaCrtanje.getX(),pnlZaCrtanje.getY()), new Tacka(pnlZaCrtanje.getNovoX(),pnlZaCrtanje.getNovoY()));
								l.setBojaIvice(pnlZaCrtanje.getBojaIvice());
								pnlZaCrtanje.listaObjekata.add(l);

								pnlZaCrtanje.setDvaKlika(false);
							}

						}

						if(pnlZaCrtanje.getOdabranOblik() == "Pravougaonik"){

							pnlZaCrtanje.setX(e.getX());
							pnlZaCrtanje.setY(e.getY());
							repaint();
							Pravougaonik p = new Pravougaonik(new Tacka(pnlZaCrtanje.getX(),pnlZaCrtanje.getY()), pnlZaCrtanje.getDuzina(),pnlZaCrtanje.getSirina());
							p.setBojaIvice(pnlZaCrtanje.getBojaIvice());
							p.setBojaUnutrasnjosti(pnlZaCrtanje.getBojaUnutrasnjosti());
							pnlZaCrtanje.listaObjekata.add(p);

						}

						if(pnlZaCrtanje.getOdabranOblik() == "Kvadrat"){


							pnlZaCrtanje.setX(e.getX());
							pnlZaCrtanje.setY(e.getY());
							repaint();
							Kvadrat kv = new Kvadrat(new Tacka(pnlZaCrtanje.getX(),pnlZaCrtanje.getY()), pnlZaCrtanje.getDuzinaStranice());
							kv.setBojaIvice(pnlZaCrtanje.getBojaIvice());
							kv.setBojaUnutrasnjosti(pnlZaCrtanje.getBojaUnutrasnjosti());
							pnlZaCrtanje.listaObjekata.add(kv);

						}

						if(pnlZaCrtanje.getOdabranOblik() == "Krug"){

							pnlZaCrtanje.setX(e.getX());
							pnlZaCrtanje.setY(e.getY());
							repaint();
							Krug kr = new Krug(new Tacka(pnlZaCrtanje.getX(),pnlZaCrtanje.getY()),pnlZaCrtanje.getR());
							kr.setBojaIvice(pnlZaCrtanje.getBojaIvice());
							kr.setBojaUnutrasnjosti(pnlZaCrtanje.getBojaUnutrasnjosti());
							pnlZaCrtanje.listaObjekata.add(kr);

						}
					}	
				});
			}
		});*/


	}

	public void saveTxt() {
		
		if(done==true) {
			
			JFileChooser jFileChooser = new JFileChooser();
			
			jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File", "txt"));
			jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Bin File", "bin"));
			
			//FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Text file/bin","txt","bin");
			//jFileChooser.setFileFilter(fileNameExtensionFilter);

			int result = jFileChooser.showSaveDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {

				try {

					String path = jFileChooser.getSelectedFile().getAbsolutePath();

					int length = path.length();
					File file = null;
					File filePainting = null;
					File fileBin = null;
					
					if(path.contains(".txt")){
						
						file = new File(path);
						String s = path.substring(0, length - 4) + ".png";
						filePainting = new File(s);
						String k = path.substring(0, length - 4) + ".bin";
						fileBin = new File(k);
						
					} else if(path.contains(".bin")) {
						
						String k = path.substring(0, length - 4) + ".bin";
						fileBin = new File(k);
						
					} else if (!path.contains(".txt") && !path.contains(".png") && !path.contains(".bin")) {
						
						file = new File(path + ".txt");
						filePainting = new File(path + ".png");
						fileBin=new File(path + ".bin");
					}
					
					
					controller.saveTxt(file);
					controller.savePainting(filePainting);
					controller.saveBin(fileBin);
				
				

				}catch (Exception exception) {


				}

				done=false;

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

	public int getNovoX() {
		return novoX;
	}

	public void setNovoX(int novoX) {
		this.novoX = novoX;
	}

	public int getNovoY() {
		return novoY;
	}

	public void setNovoY(int novoY) {
		this.novoY = novoY;
	}

	public JPanel getPnlBojaUnutrasnjosti() {
		return pnlBojaUnutrasnjosti;
	}

	public void setPnlBojaUnutrasnjosti(Color c) {
		this.pnlBojaUnutrasnjosti.setBackground(c);
	}

	public JPanel getPnlBojaIvice() {
		return pnlBojaIvice;
	}

	public void setPnlBojaIvice(Color c) {
		this.pnlBojaIvice.setBackground(c);
	}

	public ProstorZaCrtanje getPnlZaCrtanje() {
		return pnlZaCrtanje;
	}

	public void setPnlZaCrtanje(ProstorZaCrtanje pnlZaCrtanje) {
		this.pnlZaCrtanje = pnlZaCrtanje;
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

	public JButton getBtnModifikuj() {
		return btnModifikuj;
	}

	public void setBtnModifikuj(JButton btnModifikuj) {
		this.btnModifikuj = btnModifikuj;
	}

	public JButton getBtnObrisi() {
		return btnObrisi;
	}

	public void setBtnObrisi(JButton btnObrisi) {
		this.btnObrisi = btnObrisi;
	}

	public JButton getBtnSelektuj() {
		return btnSelektuj;
	}

	public void setBtnSelektuj(JButton btnSelektuj) {
		this.btnSelektuj = btnSelektuj;
	}

	public static JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public Controller getController() {
		return controller;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}
}



