package dlg;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import geometrija.Linija;
import geometrija.Tacka;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import crtanje.Model;

import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class DlgOsobineLinije extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int DlgOsobineLinije = 0;
	private JTextField txtXKoordinataPocetneTacke;
	private JTextField txtYKoordinataPocetneTacke;
	private JTextField txtXKoordinataKrajnjeTacke;
	private JTextField txtYKoordinataKrajnjeTacke;
	private int xPocetna;
	private int yPocetna;
	private int xKrajnja;
	private int yKrajnja;
	private Color bojaIvice;
	private Linija l;
	private JEditorPane edpBoja;
	private Model model = new Model();
	private boolean done=false;

	/*public static void main(String[] args) {
		try {
			DlgOsobineLinije dialog = new DlgOsobineLinije();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/


	public DlgOsobineLinije() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgOsobineLinije.class.getResource("/windows/resources/paleta.png")));
		setTitle("Linija");
		setModal(true);
		setBounds(100, 100, 499, 335);
		setDefaultCloseOperation(DlgOsobineLinije);
		txtXKoordinataPocetneTacke = new JTextField();
		txtXKoordinataPocetneTacke.setEditable(true);
		txtXKoordinataPocetneTacke.setColumns(10);
		txtYKoordinataPocetneTacke = new JTextField();
		txtYKoordinataPocetneTacke.setEditable(true);
		txtYKoordinataPocetneTacke.setColumns(10);
		JLabel lblYKoordinataPocetneTacke = new JLabel("Y koordinata po\u010Detne ta\u010Dke:");
		lblYKoordinataPocetneTacke.setForeground(new Color(139, 0, 139));
		lblYKoordinataPocetneTacke.setFont(new Font("Arial", Font.PLAIN, 14));
		JLabel lblXKoordinataPocetneTacke = new JLabel("X koordinata po\u010Detne ta\u010Dke: ");
		lblXKoordinataPocetneTacke.setForeground(new Color(139, 0, 139));
		lblXKoordinataPocetneTacke.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblBoja = new JLabel("Boja: ");
		lblBoja.setForeground(new Color(139, 0, 139));
		lblBoja.setFont(new Font("Arial", Font.PLAIN, 14));
		edpBoja = new JEditorPane();
		edpBoja.setEditable(true);
		//edpBoja.setBackground(Color.WHITE);
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

		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setFont(new Font("Arial", Font.BOLD, 11));
		btnPotvrdi.setForeground(new Color(139, 0, 139));
		btnPotvrdi.setBackground(new Color(255, 182, 193));

		JLabel lblXKoordinataKrajnjeTacke = new JLabel("X koordinata krajnje ta\u010Dke:");
		lblXKoordinataKrajnjeTacke.setForeground(new Color(139, 0, 139));
		lblXKoordinataKrajnjeTacke.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblYKoordinataKrajnjeTacke = new JLabel("Y koordinata krajnje ta\u010Dke:");
		lblYKoordinataKrajnjeTacke.setForeground(new Color(139, 0, 139));
		lblYKoordinataKrajnjeTacke.setFont(new Font("Arial", Font.PLAIN, 14));

		txtXKoordinataKrajnjeTacke = new JTextField();
		txtXKoordinataKrajnjeTacke.setEditable(true);
		txtXKoordinataKrajnjeTacke.setColumns(10);

		txtYKoordinataKrajnjeTacke = new JTextField();
		txtYKoordinataKrajnjeTacke.setEditable(true);
		txtYKoordinataKrajnjeTacke.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(314)
							.addComponent(btnPotvrdi))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblXKoordinataPocetneTacke)
									.addGap(45)
									.addComponent(txtXKoordinataPocetneTacke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblYKoordinataPocetneTacke)
										.addComponent(lblXKoordinataKrajnjeTacke)
										.addComponent(lblYKoordinataKrajnjeTacke)
										.addComponent(lblBoja))
									.addGap(48)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(edpBoja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtYKoordinataKrajnjeTacke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtXKoordinataKrajnjeTacke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtYKoordinataPocetneTacke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addGap(210))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblXKoordinataPocetneTacke)
						.addComponent(txtXKoordinataPocetneTacke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblYKoordinataPocetneTacke)
						.addComponent(txtYKoordinataPocetneTacke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblXKoordinataKrajnjeTacke)
							.addGap(18)
							.addComponent(lblYKoordinataKrajnjeTacke))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtXKoordinataKrajnjeTacke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtYKoordinataKrajnjeTacke, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBoja)
						.addComponent(edpBoja, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addComponent(btnPotvrdi))
		);
		getContentPane().setLayout(groupLayout);

		btnPotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



				try{
					
					if(xPocetna != Integer.parseInt(txtXKoordinataPocetneTacke.getText()) ||
					   
							yPocetna != Integer.parseInt(txtYKoordinataPocetneTacke.getText()) ||
							
							xKrajnja != Integer.parseInt(txtXKoordinataKrajnjeTacke.getText()) ||
							
							yKrajnja != Integer.parseInt(txtYKoordinataKrajnjeTacke.getText()) ||
							
							bojaIvice != edpBoja.getBackground()) {
						
						
						
						xPocetna = Integer.parseInt(txtXKoordinataPocetneTacke.getText());
						yPocetna = Integer.parseInt(txtYKoordinataPocetneTacke.getText());
						xKrajnja = Integer.parseInt(txtXKoordinataKrajnjeTacke.getText());
						yKrajnja = Integer.parseInt(txtYKoordinataKrajnjeTacke.getText());
						if(xPocetna >= 0 && yPocetna>=0 && xKrajnja >= 0 && yKrajnja >= 0){
							
							bojaIvice = edpBoja.getBackground();
							
					
							setVisible(false);
							done=true;

						} else {

							JOptionPane.showMessageDialog(null, "Niste dobro uneli podatke!");
						}
						
						
					} else {
						
						setVisible(false);
					}

					

				} catch (NumberFormatException k){

					JOptionPane.showMessageDialog(null, "Niste dobro uneli podatke!");


				} catch(NullPointerException k){

					JOptionPane.showConfirmDialog(null, "Niste dobro uneli podatke!");
				}





			}
		});


	}


	public Color getBojaIvice() {
		return bojaIvice;
	}

	public void setBojaIvice(Color bojaIvice) {
		this.bojaIvice = bojaIvice;
	}

	public int getxPocetna() {
		return xPocetna;
	}

	public void setxPocetna(int xPocetna) {
		this.xPocetna = xPocetna;
	}

	public int getyPocetna() {
		return yPocetna;
	}

	public void setyPocetna(int yPocetna) {
		this.yPocetna = yPocetna;
	}

	public int getxKrajnja() {
		return xKrajnja;
	}

	public void setxKrajnja(int xKrajnja) {
		this.xKrajnja = xKrajnja;
	}

	public int getyKrajnja() {
		return yKrajnja;
	}

	public void setyKrajnja(int yKrajnja) {
		this.yKrajnja = yKrajnja;
	}

	public Linija getL() {
		return l;
	}

	public void setL(Linija l) {
		this.l = l;
	}


	public JTextField getTxtXKoordinataPocetneTacke() {
		return txtXKoordinataPocetneTacke;
	}


	public void setTxtXKoordinataPocetneTacke(JTextField txtXKoordinataPocetneTacke) {
		this.txtXKoordinataPocetneTacke = txtXKoordinataPocetneTacke;
	}


	public JTextField getTxtYKoordinataPocetneTacke() {
		return txtYKoordinataPocetneTacke;
	}


	public void setTxtYKoordinataPocetneTacke(JTextField txtYKoordinataPocetneTacke) {
		this.txtYKoordinataPocetneTacke = txtYKoordinataPocetneTacke;
	}


	public JTextField getTxtXKoordinataKrajnjeTacke() {
		return txtXKoordinataKrajnjeTacke;
	}


	public void setTxtXKoordinataKrajnjeTacke(JTextField txtXKoordinataKrajnjeTacke) {
		this.txtXKoordinataKrajnjeTacke = txtXKoordinataKrajnjeTacke;
	}


	public JTextField getTxtYKoordinataKrajnjeTacke() {
		return txtYKoordinataKrajnjeTacke;
	}


	public void setTxtYKoordinataKrajnjeTacke(JTextField txtYKoordinataKrajnjeTacke) {
		this.txtYKoordinataKrajnjeTacke = txtYKoordinataKrajnjeTacke;
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
