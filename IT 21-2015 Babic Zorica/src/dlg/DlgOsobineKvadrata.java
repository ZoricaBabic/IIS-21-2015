package dlg;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import geometrija.Kvadrat;
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

public class DlgOsobineKvadrata extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int DlgOsobineKvadrata = 0;
	public static Object id;
	private JTextField txtXKoordinata;
	private JTextField txtYKoordinata;
	private JTextField txtDuzinaStranice;
	private int x;
	private int y;
	private int duzinaStranice;
	private Color bojaIvice;
	private Color bojaUnutrasnjosti;
	public String oblik; 
	public Kvadrat kv;
	public JEditorPane edpBojaIvice;
	public JEditorPane edpBojaUnutrasnjosti;
	private Model model = new Model();

	/*public static void main(String[] args) {
		try {
			DlgOsobinaKvadrataGeometrija dialog = new DlgOsobinaKvadrataGeometrija();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/


	public DlgOsobineKvadrata() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgOsobineKvadrata.class.getResource("/windows/resources/paleta.png")));
		setTitle("Kvadrat");

		setModal(true);
		setBounds(100, 100, 495, 400);
		setDefaultCloseOperation(DlgOsobineKvadrata);
		txtXKoordinata = new JTextField();
		txtXKoordinata.setEditable(true);
		txtXKoordinata.setColumns(10);
		txtYKoordinata = new JTextField();
		txtYKoordinata.setEditable(true);
		txtYKoordinata.setColumns(10);
		txtDuzinaStranice = new JTextField();
		txtDuzinaStranice.setEditable(true);
		txtDuzinaStranice.setColumns(10);
		JLabel lblYKoordinata = new JLabel("Y koordinata ta\u010Dke gore levo: ");
		lblYKoordinata.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYKoordinata.setForeground(new Color(139, 0, 139));
		JLabel lblDuzinaStranice = new JLabel("Du\u017Eina stranice: ");
		lblDuzinaStranice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDuzinaStranice.setForeground(new Color(139, 0, 139));
		JLabel lblXKoordinata = new JLabel("X koordinata ta\u010Dke gore levo: ");
		lblXKoordinata.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXKoordinata.setForeground(new Color(139, 0, 139));

		JLabel lblBojaIvice = new JLabel("Boja ivice: ");
		lblBojaIvice.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
		lblBojaUnutrasnjosti.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBojaUnutrasnjosti.setForeground(new Color(139, 0, 139));

		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setFont(new Font("Arial", Font.BOLD, 11));
		btnPotvrdi.setBackground(new Color(255, 182, 193));
		btnPotvrdi.setForeground(new Color(139, 0, 139));



		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(274)
							.addComponent(btnPotvrdi))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblXKoordinata)
								.addComponent(lblYKoordinata)
								.addComponent(lblDuzinaStranice)
								.addComponent(lblBojaUnutrasnjosti)
								.addComponent(lblBojaIvice))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(edpBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(edpBojaIvice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtXKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtYKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDuzinaStranice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
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
							.addComponent(txtDuzinaStranice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBojaIvice)
							.addGap(37)
							.addComponent(lblBojaUnutrasnjosti))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(edpBojaIvice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(edpBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(41)
					.addComponent(btnPotvrdi)
					.addGap(118))
		);
		getContentPane().setLayout(groupLayout);



		btnPotvrdi.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {


				try{

					x = Integer.parseInt(txtXKoordinata.getText());
					y = Integer.parseInt(txtYKoordinata.getText());
					duzinaStranice = Integer.parseInt(txtDuzinaStranice.getText());
					if(x >= 0 && y>=0 && duzinaStranice>0){

						bojaIvice = edpBojaIvice.getBackground();
						bojaUnutrasnjosti = edpBojaUnutrasnjosti.getBackground();
						
						setVisible(false);



					} else {

						JOptionPane.showMessageDialog(null, "Niste dobro uneli podatke!");
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

	public String getOblik() {
		return oblik;
	}

	public void setOblik(String oblik) {
		this.oblik = oblik;
	}

	public Kvadrat getKv() {
		return kv;
	}

	public void setKv(Kvadrat kv) {
		this.kv = kv;
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



	public JTextField getTxtDuzinaStranice() {
		return txtDuzinaStranice;
	}



	public void setTxtDuzinaStranice(JTextField txtDuzinaStranice) {
		this.txtDuzinaStranice = txtDuzinaStranice;
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
}
