package stek;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import geometrija.Kvadrat;
import geometrija.Tacka;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class DlgKvadratStek extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtXKoordinataTackeGoreLevo;
	private JTextField txtYKoordinataTackeGoreLevo;
	private JTextField txtDuzinaStranice;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	Stack <Kvadrat> stek = new Stack<Kvadrat>();
	ArrayList<Kvadrat> lista = new ArrayList<Kvadrat>();

	public static void main(String[] args) {
		try {
			
			DlgKvadratStek dialog = new DlgKvadratStek();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgKvadratStek() {
		
		setTitle("Stek");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgKvadratStek.class.getResource("/windows/resources/stack.jpg")));
		setBounds(100, 100, 808, 389);
		contentPanel.setBackground(new Color(135, 206, 235));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel lblXKordinataTackeGoreLevo = new JLabel("X koordinata ta\u010Dke gore levo:\r\n");
		lblXKordinataTackeGoreLevo.setForeground(new Color(128, 0, 128));
		lblXKordinataTackeGoreLevo.setBackground(new Color(128, 0, 128));
		JLabel lblYKoordinataTackeGoreLevo = new JLabel("Y koordinata ta\u010Dke gore levo: ");
		lblYKoordinataTackeGoreLevo.setForeground(new Color(128, 0, 128));
		lblYKoordinataTackeGoreLevo.setBackground(new Color(128, 0, 128));
		lblYKoordinataTackeGoreLevo.setHorizontalAlignment(SwingConstants.LEFT);
		JLabel lblDuzinaStraniceKvadrata = new JLabel("Du\u017Eina stranice:");
		lblDuzinaStraniceKvadrata.setForeground(new Color(128, 0, 128));
		lblDuzinaStraniceKvadrata.setBackground(new Color(128, 0, 128));
		JLabel lblBojaIvice = new JLabel("Boja ivice:");
		lblBojaIvice.setForeground(new Color(128, 0, 128));
		lblBojaIvice.setBackground(new Color(128, 0, 128));
		JLabel lblBojaUnutrasnjosti = new JLabel("Boja unutra\u0161njosti:");
		lblBojaUnutrasnjosti.setForeground(new Color(128, 0, 128));
		lblBojaUnutrasnjosti.setBackground(new Color(128, 0, 128));

		txtXKoordinataTackeGoreLevo = new JTextField();
		txtXKoordinataTackeGoreLevo.setColumns(10);

		txtYKoordinataTackeGoreLevo = new JTextField();
		txtYKoordinataTackeGoreLevo.setColumns(10);

		txtDuzinaStranice = new JTextField();
		txtDuzinaStranice.setText("");
		txtDuzinaStranice.setColumns(10);

		JPanel pnlBojaIvice = new JPanel();
		pnlBojaIvice.setToolTipText("");
		pnlBojaIvice.setBackground(Color.WHITE);
		pnlBojaIvice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color bojaIvice = JColorChooser.showDialog(null, "Izaberi boju ivice: ", Color.WHITE);
				pnlBojaIvice.setBackground(bojaIvice);

			}
		});

		JPanel pnlBojaUnutrasnjosti = new JPanel();
		pnlBojaUnutrasnjosti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color bojaUnutrasnjosti = JColorChooser.showDialog(null, "Izaberi boju ivice: ", Color.WHITE);
				pnlBojaUnutrasnjosti.setBackground(bojaUnutrasnjosti);
			}
		});
		pnlBojaUnutrasnjosti.setBackground(Color.WHITE);

		JButton btnDodajNaStekTrenutneOsobine = new JButton("Dodaj na stek");
		btnDodajNaStekTrenutneOsobine.setForeground(new Color(139, 0, 139));
		btnDodajNaStekTrenutneOsobine.setBackground(new Color(218, 112, 214));


		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setForeground(new Color(139, 0, 139));
		btnOdustani.setBackground(new Color(216, 191, 216));
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

			}
		});

		JButton btnIzuzmiSaSteka = new JButton("Izuzmi sa steka");
		btnIzuzmiSaSteka.setForeground(new Color(139, 0, 139));
		btnIzuzmiSaSteka.setBackground(new Color(218, 112, 214));

		JLabel lblPrikazStanjaNa = new JLabel("Prikaz stanja na steku: ");
		lblPrikazStanjaNa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrikazStanjaNa.setForeground(new Color(139, 0, 139));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(89, Short.MAX_VALUE))
				);

		JScrollPane scrlPrikazSteka = new JScrollPane();

		JLabel lblPrikazPoslednjeg = new JLabel("Osobine obrisanog kvadrata: ");
		lblPrikazPoslednjeg.setVisible(false);
		lblPrikazPoslednjeg.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrikazPoslednjeg.setForeground(new Color(139, 0, 139));
		
		JButton btnNoviNaStek = new JButton("Dodaj novi na stek");
	
		btnNoviNaStek.setForeground(new Color(139, 0, 139));
		btnNoviNaStek.setBackground(new Color(218, 112, 214));

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addComponent(btnDodajNaStekTrenutneOsobine, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnIzuzmiSaSteka, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(316)
					.addComponent(btnOdustani, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblPrikazPoslednjeg)
							.addPreferredGap(ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
							.addComponent(lblPrikazStanjaNa)
							.addGap(113))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblYKoordinataTackeGoreLevo)
								.addComponent(lblXKordinataTackeGoreLevo)
								.addComponent(lblDuzinaStraniceKvadrata)
								.addComponent(lblBojaIvice)
								.addComponent(lblBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
							.addGap(48)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(txtXKoordinataTackeGoreLevo, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtYKoordinataTackeGoreLevo, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDuzinaStranice, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlBojaIvice, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
							.addGap(48)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(btnNoviNaStek)
									.addContainerGap())
								.addComponent(scrlPrikazSteka, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrikazStanjaNa)
						.addComponent(lblPrikazPoslednjeg))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblXKordinataTackeGoreLevo)
								.addComponent(txtXKoordinataTackeGoreLevo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblYKoordinataTackeGoreLevo)
								.addComponent(txtYKoordinataTackeGoreLevo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDuzinaStraniceKvadrata)
								.addComponent(txtDuzinaStranice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlBojaIvice, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBojaIvice))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(11)
									.addComponent(lblBojaUnutrasnjosti))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(8)
									.addComponent(pnlBojaUnutrasnjosti, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(9)
							.addComponent(scrlPrikazSteka, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDodajNaStekTrenutneOsobine)
						.addComponent(btnOdustani)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnIzuzmiSaSteka)
							.addComponent(btnNoviNaStek)))
					.addGap(21))
		);

		JList<String> lstPrikazSteka = new JList<String>();
		lstPrikazSteka.setBackground(Color.WHITE);
		scrlPrikazSteka.setViewportView(lstPrikazSteka);
		lstPrikazSteka.setModel(dlm);

		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setLayout(groupLayout);

		btnDodajNaStekTrenutneOsobine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblPrikazPoslednjeg.setVisible(false);
				
				
				try{
					int x = Integer.parseInt(txtXKoordinataTackeGoreLevo.getText());
					int y = Integer.parseInt(txtYKoordinataTackeGoreLevo.getText());
					int duzinaStranice = Integer.parseInt(txtDuzinaStranice.getText());
					
					if(x>=0 && duzinaStranice>0 && y>0) {

						
						Color bojaIvice = pnlBojaIvice.getBackground();
						Color bojaUnutrasnjosti = pnlBojaUnutrasnjosti.getBackground();
						Kvadrat kv = new Kvadrat(new Tacka(x,y), duzinaStranice, bojaIvice,bojaUnutrasnjosti);
						stek.push(kv);
						dlm.addElement(kv.toString());
						txtXKoordinataTackeGoreLevo.setText(null);
						txtYKoordinataTackeGoreLevo.setText(null);
						txtDuzinaStranice.setText(null);
						pnlBojaIvice.setBackground(Color.WHITE);
						pnlBojaUnutrasnjosti.setBackground(Color.WHITE);
					} else {
						
						JOptionPane.showMessageDialog(null, "Niste dobro uneli osobine kvadrata.");
						/*txtXKoordinataTackeGoreLevo.setText(null);
						txtYKoordinataTackeGoreLevo.setText(null);
						txtDuzinaStranice.setText(null);
						pnlBojaIvice.setBackground(Color.WHITE);
						pnlBojaUnutrasnjosti.setBackground(Color.WHITE);*/
					}
				} catch (NumberFormatException a){

					JOptionPane.showMessageDialog(null, "Niste dobro uneli osobine kvadrata.");
					/*txtXKoordinataTackeGoreLevo.setText(null);
					txtYKoordinataTackeGoreLevo.setText(null);
					txtDuzinaStranice.setText(null);
					pnlBojaIvice.setBackground(Color.WHITE);
					pnlBojaUnutrasnjosti.setBackground(Color.WHITE);*/


				}	catch (NullPointerException a)	{

					
					JOptionPane.showMessageDialog(null, "Niste uneli osobine kvadrata");
					/*txtXKoordinataTackeGoreLevo.setText(null);
					txtYKoordinataTackeGoreLevo.setText(null);
					txtDuzinaStranice.setText(null);
					pnlBojaIvice.setBackground(Color.WHITE);
					pnlBojaUnutrasnjosti.setBackground(Color.WHITE);*/

				}	

			}
		});


		btnIzuzmiSaSteka.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if(stek.isEmpty()){

					
					JOptionPane.showMessageDialog(null, "Stek je prazan");
					lblPrikazPoslednjeg.setVisible(false);
					txtXKoordinataTackeGoreLevo.setText(null);
					txtYKoordinataTackeGoreLevo.setText(null);
					txtDuzinaStranice.setText(null);
					pnlBojaIvice.setBackground(Color.WHITE);
					pnlBojaUnutrasnjosti.setBackground(Color.WHITE);

				}
				else {

					txtXKoordinataTackeGoreLevo.setText(Integer.toString(stek.peek().gettGoreLevo().getX()));
					txtYKoordinataTackeGoreLevo.setText(Integer.toString(stek.peek().gettGoreLevo().getY()));
					txtDuzinaStranice.setText(Integer.toString(stek.peek().getDuzinaStranice()));
					pnlBojaIvice.setBackground(stek.peek().getBojaIvice());
					pnlBojaUnutrasnjosti.setBackground(stek.peek().getBojaUnutrasnjosti());
					lblPrikazPoslednjeg.setVisible(true);
					dlm.remove(getComponentCount()-1);
					stek.pop();

				}
			}
		});
		
		
		btnNoviNaStek.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				lblPrikazPoslednjeg.setVisible(false);
				txtXKoordinataTackeGoreLevo.setText(null);
				txtYKoordinataTackeGoreLevo.setText(null);
				txtDuzinaStranice.setText(null);
				pnlBojaIvice.setBackground(Color.WHITE);
				pnlBojaUnutrasnjosti.setBackground(Color.WHITE);
			}
		});
	}
}
