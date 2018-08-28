package dlg;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import geometry.Circle;

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

public class DlgCircleProperties extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int DlgCircleProperties = 0;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtR;
	private int x;
	private int y;
	private int r;
	private Color borderColor;
	private Color fillColor;
	private Circle circle; 
	private JEditorPane edpBorderColor;
	private JEditorPane edpFillColor;
	private  boolean done=false;
	

	public DlgCircleProperties() {
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgCircleProperties.class.getResource("/windows/resources/paleta.png")));
		setTitle("Circle");

		setModal(true);
		setBounds(100, 100, 485, 414);
		setDefaultCloseOperation(DlgCircleProperties);

		txtX = new JTextField();
		txtX.setEditable(true);
		txtX.setColumns(10);
		txtY = new JTextField();
		txtY.setEditable(true);
		txtY.setColumns(10);
		txtR = new JTextField();
		txtR.setEditable(true);
		txtR.setColumns(10);
		JLabel lblY = new JLabel("Y: ");
		lblY.setForeground(new Color(139, 0, 139));
		lblY.setFont(new Font("Arial", Font.PLAIN, 14));
		JLabel lblRadius = new JLabel("Radius: ");
		lblRadius.setForeground(new Color(139, 0, 139));
		lblRadius.setFont(new Font("Arial", Font.PLAIN, 14));
		JLabel lblX = new JLabel("X: ");
		lblX.setForeground(new Color(139, 0, 139));
		lblX.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblBorderColor = new JLabel("Border color: ");
		lblBorderColor.setForeground(new Color(139, 0, 139));
		lblBorderColor.setFont(new Font("Arial", Font.PLAIN, 14));
		edpBorderColor = new JEditorPane();
		edpBorderColor.setEditable(true);
		edpBorderColor.setBackground(Color.WHITE);
		
		
		
		
		edpBorderColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Color currentBorderColor = edpBorderColor.getBackground();
				Color color = JColorChooser.showDialog(null, "Choose the border color", Color.BLACK);
				if(color  == null) {
					
					edpBorderColor.setBackground(currentBorderColor);
				} else {
					
					edpBorderColor.setBackground(color);
					
				}
				
			}
		});
		edpFillColor = new JEditorPane();
		edpFillColor.setEditable(true);
		edpFillColor.setBackground(Color.WHITE);
		edpFillColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Color currentFillColor = edpFillColor.getBackground();

				Color color = JColorChooser.showDialog(null, "Choose the fill color", Color.WHITE);
				if(color  == null) {
					
					edpFillColor.setBackground(currentFillColor);
				} else {
					
					edpFillColor.setBackground(color);
					
				}
			}
		});
		JLabel lblFillColor = new JLabel("Fill color: ");
		lblFillColor.setForeground(new Color(139, 0, 139));
		lblFillColor.setFont(new Font("Arial", Font.PLAIN, 14));

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Arial", Font.BOLD, 12));
		btnSave.setBackground(new Color(255, 182, 193));
		btnSave.setForeground(new Color(139, 0, 139));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblX)
							.addGap(18)
							.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(276)
							.addComponent(btnSave))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblY)
								.addComponent(lblRadius)
								.addComponent(lblBorderColor)
								.addComponent(lblFillColor))
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(edpFillColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(edpBorderColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(262))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRadius)
						.addComponent(txtR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(65)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBorderColor)
						.addComponent(edpBorderColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFillColor)
						.addComponent(edpFillColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addComponent(btnSave)
					.addGap(85))
		);
		getContentPane().setLayout(groupLayout);

		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try{
					
					if(x != Integer.parseInt(txtX.getText())
							|| y != Integer.parseInt(txtY.getText()) || 
							r != Integer.parseInt(txtR.getText()) ||
							borderColor != edpBorderColor.getBackground() || 
							fillColor != edpFillColor.getBackground()){
						
						x = Integer.parseInt(txtX.getText());
						y = Integer.parseInt(txtY.getText());
						r = Integer.parseInt(txtR.getText());
						
						if(x >= 0 && y>=0 && r>0){

							borderColor = edpBorderColor.getBackground();
							fillColor = edpFillColor.getBackground();
				
							setVisible(false);

						} else {

							JOptionPane.showMessageDialog(null, "You did not enter the data well!");
						}
						
						
						done=true;

								
								
				}
					
					setVisible(false);
					

					
				} catch (NumberFormatException k){

					JOptionPane.showMessageDialog(null, "You did not enter the data well!");


				} catch(NullPointerException k){

					JOptionPane.showConfirmDialog(null, "You did not enter the data well!");
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


	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}


	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtR() {
		return txtR;
	}

	public void setTxtR(JTextField txtR) {
		this.txtR = txtR;
	}

	public JEditorPane getEdpBorderColor() {
		return edpBorderColor;
	}

	public void setEdpBorderColor(JEditorPane edpBorderColor) {
		this.edpBorderColor = edpBorderColor;
	}

	public JEditorPane getEdpFillColor() {
		return edpFillColor;
	}

	public void setEdpFillColor(JEditorPane edpFillColor) {
		this.edpFillColor = edpFillColor;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
