package dlg;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import geometrija.Krug;
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

public class DlgOsobineKruga extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int DlgOsobineKruga = 0;
	private JTextField txtXKoordinata;
	private JTextField txtYKoordinata;
	private JTextField txtPoluprecnik;
	private int x;
	private int y;
	private int poluprecnik;
	private Color bojaIvice;
	private Color bojaUnutrasnjosti;
	private Krug kr; 
	private JEditorPane edpBojaIvice;
	private JEditorPane edpBojaUnutrasnjosti;
	private Model model = new Model();
	private  boolean done=false;
	

	/*public static void main(String[] args) {
		try {
			DlgOsobineKruga dialog = new DlgOsobineKruga();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public DlgOsobineKruga() {
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgOsobineKruga.class.getResource("/windows/resources/paleta.png")));
		setTitle("Krug");

		setModal(true);
		setBounds(100, 100, 485, 414);
		setDefaultCloseOperation(DlgOsobineKruga);

		txtXKoordinata = new JTextField();
		txtXKoordinata.setEditable(true);
		txtXKoordinata.setColumns(10);
		txtYKoordinata = new JTextField();
		txtYKoordinata.setEditable(true);
		txtYKoordinata.setColumns(10);
		txtPoluprecnik = new JTextField();
		txtPoluprecnik.setEditable(true);
		txtPoluprecnik.setColumns(10);
		JLabel lblYKoordinata = new JLabel("Y koordinata centra: ");
		lblYKoordinata.setForeground(new Color(139, 0, 139));
		lblYKoordinata.setFont(new Font("Arial", Font.PLAIN, 14));
		JLabel lblDuzinaStranice = new JLabel("Polupre\u010Dnik:");
		lblDuzinaStranice.setForeground(new Color(139, 0, 139));
		lblDuzinaStranice.setFont(new Font("Arial", Font.PLAIN, 14));
		JLabel lblXKoordinata = new JLabel("X koordinata centra:  ");
		lblXKoordinata.setForeground(new Color(139, 0, 139));
		lblXKoordinata.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblBojaIvice = new JLabel("Boja ivice: ");
		lblBojaIvice.setForeground(new Color(139, 0, 139));
		lblBojaIvice.setFont(new Font("Arial", Font.PLAIN, 14));
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
		lblBojaUnutrasnjosti.setForeground(new Color(139, 0, 139));
		lblBojaUnutrasnjosti.setFont(new Font("Arial", Font.PLAIN, 14));

		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setFont(new Font("Arial", Font.BOLD, 12));
		btnPotvrdi.setBackground(new Color(255, 182, 193));
		btnPotvrdi.setForeground(new Color(139, 0, 139));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblXKoordinata)
							.addGap(18)
							.addComponent(txtXKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(276)
							.addComponent(btnPotvrdi))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblYKoordinata)
								.addComponent(lblDuzinaStranice)
								.addComponent(lblBojaIvice)
								.addComponent(lblBojaUnutrasnjosti))
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(edpBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(edpBojaIvice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPoluprecnik, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtYKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(262))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblXKoordinata)
						.addComponent(txtXKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblYKoordinata)
						.addComponent(txtYKoordinata, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDuzinaStranice)
						.addComponent(txtPoluprecnik, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBojaIvice)
						.addComponent(edpBojaIvice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBojaUnutrasnjosti)
						.addComponent(edpBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addComponent(btnPotvrdi)
					.addGap(85))
		);
		getContentPane().setLayout(groupLayout);

		btnPotvrdi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try{
					
					if(x != Integer.parseInt(txtXKoordinata.getText())
							|| y != Integer.parseInt(txtYKoordinata.getText()) || 
							poluprecnik != Integer.parseInt(txtPoluprecnik.getText()) ||
							bojaIvice != edpBojaIvice.getBackground() || 
							bojaUnutrasnjosti != edpBojaUnutrasnjosti.getBackground()){
						
						x = Integer.parseInt(txtXKoordinata.getText());
						y = Integer.parseInt(txtYKoordinata.getText());
						poluprecnik = Integer.parseInt(txtPoluprecnik.getText());
						
						if(x >= 0 && y>=0 && poluprecnik>0){

							bojaIvice = edpBojaIvice.getBackground();
							bojaUnutrasnjosti = edpBojaUnutrasnjosti.getBackground();
				
							setVisible(false);

						} else {

							JOptionPane.showMessageDialog(null, "Niste dobro uneli podatke!");
						}
						
						
						done=true;

								
								
				}
					
					setVisible(false);
					

					
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


	public int getPoluprecnik() {
		return poluprecnik;
	}

	public void setPoluprecnik(int poluprecnik) {
		this.poluprecnik = poluprecnik;
	}

	public Krug getKr() {
		return kr;
	}

	public void setKr(Krug kr) {
		this.kr = kr;
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

	public JTextField getTxtPoluprecnik() {
		return txtPoluprecnik;
	}

	public void setTxtPoluprecnik(JTextField txtPoluprecnik) {
		this.txtPoluprecnik = txtPoluprecnik;
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
