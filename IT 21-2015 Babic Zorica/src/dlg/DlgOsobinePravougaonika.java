package dlg;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import geometrija.Pravougaonik;
import geometrija.Tacka;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import crtanje.Controller;
import crtanje.Model;

import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;

public class DlgOsobinePravougaonika extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int DlgOsobinePravougaonika = 0;
	private JTextField txtXKoordinata;
	private JTextField txtYKoordinata;
	private JTextField txtDuzina;
	private JTextField txtSirina;
	private int x;
	private int y;
	private int duzina;
	private int sirina; 
	private Color bojaIvice;
	private Color bojaUnutrasnjosti;
	private Pravougaonik p;
	private JEditorPane edpBojaIvice;
	private JEditorPane edpBojaUnutrasnjosti;
	private Model model = new Model();
	private boolean  done = false;
	


	/*public static void main(String[] args) {
		try {
			DlgOsobinePravougaonika dialog = new DlgOsobinePravougaonika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public DlgOsobinePravougaonika() {
		setModal(true);
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 14));
		getContentPane().setForeground(new Color(139, 0, 139));
		setTitle("Pravougaonik");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgOsobinePravougaonika.class.getResource("/windows/resources/paleta.png")));

		setBounds(100, 100, 485, 413);
		JLabel lblOsobineKvadrata = new JLabel(" ");
		setDefaultCloseOperation(DlgOsobinePravougaonika);
		txtXKoordinata = new JTextField();
		txtXKoordinata.setEditable(true);
		txtXKoordinata.setColumns(10);
		txtYKoordinata = new JTextField();
		txtYKoordinata.setEditable(true);
		txtYKoordinata.setColumns(10);
		txtDuzina = new JTextField();
		txtDuzina.setEditable(true);
		txtDuzina.setColumns(10);
		JLabel lblYKoordinata = new JLabel("Y koordinata ta\u010Dke gore levo: ");
		lblYKoordinata.setFont(new Font("Arial", Font.PLAIN, 14));
		lblYKoordinata.setForeground(new Color(139, 0, 139));
		JLabel lblDuzinaStranice = new JLabel("Du\u017Eina:");
		lblDuzinaStranice.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDuzinaStranice.setForeground(new Color(139, 0, 139));
		JLabel lblXKoordinata = new JLabel("X koordinata ta\u010Dke gore levo: ");
		lblXKoordinata.setFont(new Font("Arial", Font.PLAIN, 14));
		lblXKoordinata.setForeground(new Color(139, 0, 139));

		
		
		
		JLabel lblBojaIvice = new JLabel("Boja ivice: ");
		lblBojaIvice.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBojaIvice.setForeground(new Color(139, 0, 139));
		edpBojaIvice = new JEditorPane();
		edpBojaIvice.setEditable(true);
		edpBojaIvice.setBackground(Color.WHITE);
		edpBojaIvice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color currentBojaIvice = edpBojaIvice.getBackground();
				Color boja = JColorChooser.showDialog(null, "Izaberite boju ivice", Color.BLACK);
				if(boja  == null) {
					
					edpBojaIvice.setBackground(currentBojaIvice);
				} else {
					
					edpBojaIvice.setBackground(boja);
					
				}
			}
		});
		edpBojaUnutrasnjosti = new JEditorPane();
		edpBojaUnutrasnjosti.setEditable(true);
		edpBojaUnutrasnjosti.setBackground(Color.WHITE);
		edpBojaUnutrasnjosti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color currentBojaUnutrasnjosti = edpBojaUnutrasnjosti.getBackground();

				Color boja = JColorChooser.showDialog(null, "Izaberi boju unutrašnjosti", Color.WHITE);
				if(boja  == null) {
					
					edpBojaUnutrasnjosti.setBackground(currentBojaUnutrasnjosti);
				} else {
					
					edpBojaUnutrasnjosti.setBackground(boja);
					
				}
			}
		});
		JLabel lblBojaUnutrasnjosti = new JLabel("Boja unutra\u0161njosti: ");
		lblBojaUnutrasnjosti.setFont(new Font("Arial", Font.PLAIN, 14));
		lblBojaUnutrasnjosti.setForeground(new Color(139, 0, 139));

		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setBackground(new Color(255, 182, 193));
		btnPotvrdi.setForeground(new Color(139, 0, 139));
		btnPotvrdi.setFont(new Font("Arial", Font.BOLD, 11));

		JLabel lblSirina = new JLabel("\u0160irina");
		lblSirina.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSirina.setForeground(new Color(139, 0, 139));

		txtSirina = new JTextField();
		txtSirina.setEditable(true);
		txtSirina.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblXKoordinata)
						.addComponent(lblYKoordinata)
						.addComponent(lblDuzinaStranice)
						.addComponent(lblBojaUnutrasnjosti)
						.addComponent(lblBojaIvice)
						.addComponent(lblSirina))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtSirina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(edpBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(edpBojaIvice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtXKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtYKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtDuzina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblOsobineKvadrata)
					.addGap(133))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(309)
					.addComponent(btnPotvrdi)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOsobineKvadrata)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblXKoordinata)
									.addGap(24)
									.addComponent(lblYKoordinata)
									.addGap(24)
									.addComponent(lblDuzinaStranice))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtXKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtYKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtDuzina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(lblSirina))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtSirina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblBojaIvice)
									.addGap(37)
									.addComponent(lblBojaUnutrasnjosti))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(edpBojaIvice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(31)
									.addComponent(edpBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(56)
					.addComponent(btnPotvrdi)
					.addGap(41))
		);
		getContentPane().setLayout(groupLayout);

		btnPotvrdi.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {


				try{
					
					if(x != Integer.parseInt(txtXKoordinata.getText()) ||
							y != Integer.parseInt(txtYKoordinata.getText()) ||
							duzina != Integer.parseInt(txtDuzina.getText()) ||
							sirina != Integer.parseInt(txtSirina.getText()) ||
							bojaIvice != edpBojaIvice.getBackground() ||
							bojaUnutrasnjosti != edpBojaUnutrasnjosti.getBackground()) {
						
						
						x = Integer.parseInt(txtXKoordinata.getText());
						y = Integer.parseInt(txtYKoordinata.getText());
						duzina = Integer.parseInt(txtDuzina.getText());
						sirina = Integer.parseInt(txtSirina.getText());
						if(x >= 0 && y>=0 && duzina>0 && sirina>0){

							bojaIvice = edpBojaIvice.getBackground();
							bojaUnutrasnjosti = edpBojaUnutrasnjosti.getBackground();
				
							setVisible(false);
							done = true;
							
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


	public int getSirina() {
		return sirina;
	}

	public void setSirina(int sirina) {
		this.sirina = sirina;
	}

	public Pravougaonik getP() {
		return p;
	}

	public void setP(Pravougaonik p) {
		this.p = p;
	}

	public int getDuzina() {
		return duzina;
	}

	public void setDuzina(int duzina) {
		this.duzina = duzina;
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

	public JTextField getTxtDuzina() {
		return txtDuzina;
	}

	public void setTxtDuzina(JTextField txtDuzina) {
		this.txtDuzina = txtDuzina;
	}

	public JTextField getTxtSirina() {
		return txtSirina;
	}

	public void setTxtSirina(JTextField txtSirina) {
		this.txtSirina = txtSirina;
	}

	public JEditorPane getEdpBojaIvice() {
		return edpBojaIvice;
	}

	public void setEdpBojaIvice(JEditorPane edpBojaIvice) {
		this.edpBojaIvice = edpBojaIvice;
	}

	public JEditorPane getEdpBojaUnutrasnjosti() {
		return edpBojaUnutrasnjosti;
	}

	public void setEdpBojaUnutrasnjosti(JEditorPane edpBojaUnutrasnjosti) {
		this.edpBojaUnutrasnjosti = edpBojaUnutrasnjosti;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
