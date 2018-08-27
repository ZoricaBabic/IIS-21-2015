package dlg;


import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import geometrija.Tacka;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import crtanje.Model;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;


public class DlgOsobineTacke extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int DlgOsobineTacke = 0;
	private JTextField txtXKoordinata;
	private JTextField txtYKoordinata;
	private int x;
	private int y;
	private int duzinaStranice;
	private Color bojaIvice;
	private Color bojaUnutrasnjosti;
	private Tacka t; 
	private JEditorPane edpBoja;
	private Model model = new Model();
	private boolean done=false;


	/*public static void main(String[] args) {
		try {
			DlgOsobineTacke dialog = new DlgOsobineTacke();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public DlgOsobineTacke() {
		
		
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 14));
		setTitle("Ta\u010Dka");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgOsobineTacke.class.getResource("/windows/resources/paleta.png")));
		setDefaultCloseOperation(DlgOsobineTacke);
		setModal(true);
		setBounds(100, 100, 443, 265);

		txtXKoordinata = new JTextField();
		txtXKoordinata.setEditable(true);
		txtXKoordinata.setColumns(10);
		txtYKoordinata = new JTextField();
		txtYKoordinata.setEditable(true);
		txtYKoordinata.setColumns(10);
		JLabel lblYKoordinata = new JLabel("Y koordinata:");
		lblYKoordinata.setFont(new Font("Arial", Font.PLAIN, 14));
		lblYKoordinata.setForeground(new Color(139, 0, 139));
		JLabel lblXKoordinata = new JLabel("X koordinata: ");
		lblXKoordinata.setFont(new Font("Arial", Font.PLAIN, 14));
		lblXKoordinata.setForeground(new Color(139, 0, 139));

		JLabel lblBoja = new JLabel("Boja: ");
		lblBoja.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBoja.setForeground(new Color(139, 0, 139));
		edpBoja = new JEditorPane();
		edpBoja.setEditable(true);
		edpBoja.setBackground(Color.WHITE);
		edpBoja.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color currentBojaIvice = edpBoja.getBackground();
				Color boja = JColorChooser.showDialog(null, "Izaberite boju", Color.BLACK);
				if(boja  == null) {
					
					edpBoja.setBackground(currentBojaIvice);
				} else {
					
					edpBoja.setBackground(boja);
					
				}
			}
		});
		JButton btnPotvrda = new JButton("Potvrdi");
		btnPotvrda.setFont(new Font("Arial", Font.BOLD, 11));
		btnPotvrda.setBackground(new Color(255, 182, 193));
		btnPotvrda.setForeground(new Color(139, 0, 139));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblXKoordinata)
								.addComponent(lblYKoordinata)
								.addComponent(lblBoja))
							.addGap(45)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtXKoordinata)
								.addComponent(txtYKoordinata)
								.addComponent(edpBoja, 0, 0, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(310)
							.addComponent(btnPotvrda)))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblXKoordinata)
							.addGap(24)
							.addComponent(lblYKoordinata))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtXKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtYKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBoja)
						.addComponent(edpBoja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(btnPotvrda)
					.addGap(44))
		);
		getContentPane().setLayout(groupLayout);

		btnPotvrda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					
					if(x != Integer.parseInt(txtXKoordinata.getText()) ||
							y != Integer.parseInt(txtYKoordinata.getText()) ||
							bojaIvice != edpBoja.getBackground()) {
						
						x = Integer.parseInt(txtXKoordinata.getText());
						y = Integer.parseInt(txtYKoordinata.getText());
						if(x >= 0 && y>=0){

							bojaIvice = edpBoja.getBackground();
							Tacka t = new Tacka(x,y,bojaIvice);
							setT(t);
							model.setBojaIvice(bojaIvice);
		
							setVisible(false);
							done=true;




						} else {

							JOptionPane.showMessageDialog(null, "Niste dobro uneli podatke!");
						}
					} else {
						
						setVisible(false);
						done=false;
					}

					

				} catch (NumberFormatException k){

					JOptionPane.showMessageDialog(null, "Niste dobro uneli podatke!");


				} catch(NullPointerException k){

					JOptionPane.showConfirmDialog(null, "Niste dobro uneli podatke!");
				}

			}
		});



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

	public int getDuzinaStranice() {
		return duzinaStranice;
	}

	public void setDuzinaStranice(int duzinaStranice) {
		this.duzinaStranice = duzinaStranice;
	}

	public Color getBojaIvice() {
		return bojaIvice;
	}

	public void setBojaIvice(Color bojaIvice) {
		this.bojaIvice = bojaIvice;
	}

	public Color getBojaUnutrasnjosti() {
		return bojaUnutrasnjosti;
	}

	public void setBojaUnutrasnjosti(Color bojaUnutrasnjosti) {
		this.bojaUnutrasnjosti = bojaUnutrasnjosti;
	}

	public Tacka getT() {
		return t;
	}

	public void setT(Tacka t) {
		this.t = t;
	}

	public JTextField getTxtXKoordinata() {
		return txtXKoordinata;
	}

	public void setTxtXKoordinata(JTextField txtXKoordinata) {
		this.txtXKoordinata = txtXKoordinata;
	}

	public JTextField getTxtYKoordinata() {
		return txtYKoordinata;
	}

	public void setTxtYKoordinata(JTextField txtYKoordinata) {
		this.txtYKoordinata = txtYKoordinata;
	}

	public JEditorPane getEdpBoja() {
		return edpBoja;
	}

	public void setEdpBoja(JEditorPane edpBoja) {
		this.edpBoja = edpBoja;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
