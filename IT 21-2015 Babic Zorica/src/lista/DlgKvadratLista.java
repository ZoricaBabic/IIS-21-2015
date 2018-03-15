package lista;

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
import java.util.Collections;
import java.util.Comparator;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class DlgKvadratLista extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtXKoordinataTackeGoreLevo;
	private JTextField txtYKoordinataTackeGoreLevo;
	private JTextField txtDuzinaStranice;
	DefaultListModel<Object> dlm = new DefaultListModel<Object>();

	ArrayList<Kvadrat> lista = new ArrayList<Kvadrat>();

	public void sortiranje(){

		Collections.sort(lista, new Comparator<Kvadrat>() {
			public int compare(Kvadrat kv1, Kvadrat kv2) {
				return kv1.compareTo(kv2); 
			}
			
		});
		
	}
	public static void main(String[] args) {
		try {
			DlgKvadratLista dialog = new DlgKvadratLista();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public DlgKvadratLista() {
		
		setTitle("Lista");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgKvadratLista.class.getResource("/windows/resources/lista.png")));
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

		JButton btnDodajUListu = new JButton("Dodaj u listu");
		btnDodajUListu.setForeground(new Color(139, 0, 139));
		btnDodajUListu.setBackground(new Color(216, 191, 216));


		JButton btnOdustani = new JButton("Odustani");
		btnOdustani.setForeground(new Color(139, 0, 139));
		btnOdustani.setBackground(new Color(216, 191, 216));
		btnOdustani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

			}
		});

		JLabel lblPrikazStanjaNaListi = new JLabel("Prikaz stanja na listi:");
		lblPrikazStanjaNaListi.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrikazStanjaNaListi.setForeground(new Color(139, 0, 139));
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
		
		JButton btnSortiraj = new JButton("Sortiraj");
		
		btnSortiraj.setForeground(new Color(139, 0, 139));
		btnSortiraj.setBackground(new Color(216, 191, 216));
		
		JButton btnObrisiListu = new JButton("Obri\u0161i listu");
	
		btnObrisiListu.setForeground(new Color(139, 0, 139));
		btnObrisiListu.setBackground(new Color(216, 191, 216));

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(46, Short.MAX_VALUE)
					.addComponent(btnDodajUListu, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSortiraj, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnObrisiListu)
					.addGap(249)
					.addComponent(btnOdustani, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblPrikazPoslednjeg)
							.addPreferredGap(ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
							.addComponent(lblPrikazStanjaNaListi)
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
							.addComponent(scrlPrikazSteka, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrikazStanjaNaListi)
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
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnDodajUListu)
							.addComponent(btnSortiraj)
							.addComponent(btnObrisiListu))
						.addComponent(btnOdustani))
					.addGap(21))
		);

		JList<Object> lstPrikazSteka = new JList<Object>();
		lstPrikazSteka.setBackground(Color.WHITE);
		scrlPrikazSteka.setViewportView(lstPrikazSteka);
		lstPrikazSteka.setModel(dlm);

		contentPanel.setLayout(gl_contentPanel);
		getContentPane().setLayout(groupLayout);

		btnDodajUListu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblPrikazPoslednjeg.setVisible(false);
				
				
				try{

					
					int duzinaStranice = Integer.parseInt(txtDuzinaStranice.getText());
					int x = Integer.parseInt(txtXKoordinataTackeGoreLevo.getText());
					int y = Integer.parseInt(txtYKoordinataTackeGoreLevo.getText());
					if(duzinaStranice>0 && x>=0 && y>=0) {

						
						
						Color bojaIvice = pnlBojaIvice.getBackground();
						Color bojaUnutrasnjosti = pnlBojaUnutrasnjosti.getBackground();
						Kvadrat kv = new Kvadrat(new Tacka(x,y), duzinaStranice, bojaIvice,bojaUnutrasnjosti);
						lista.add(kv);
						dlm.addElement(kv.toString());
						txtXKoordinataTackeGoreLevo.setText(null);
						txtYKoordinataTackeGoreLevo.setText(null);
						txtDuzinaStranice.setText(null);
						pnlBojaIvice.setBackground(Color.WHITE);
						pnlBojaUnutrasnjosti.setBackground(Color.WHITE);
					} else{
						
						JOptionPane.showMessageDialog(null, "Niste dobro uneli osobine kvadrata.");
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
		
		btnSortiraj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				sortiranje();
				dlm.removeAllElements();
				for(Kvadrat i: lista){
					
					dlm.addElement(i);
				}
				
				
			}
		});
		
		btnObrisiListu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				lista.removeAll(lista);
				dlm.removeAllElements();
				/*txtXKoordinataTackeGoreLevo.setText(null);
				txtYKoordinataTackeGoreLevo.setText(null);
				txtDuzinaStranice.setText(null);
				pnlBojaIvice.setBackground(Color.WHITE);
				pnlBojaUnutrasnjosti.setBackground(Color.WHITE);*/
			}
		});
	}
}
