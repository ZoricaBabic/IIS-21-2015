package dlg;


import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;

import mvc.Model;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import geometry.Point;

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


public class DlgPointProperties extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int DlgPointProperties = 0;
	private JTextField txtX;
	private JTextField txtY;
	private int x;
	private int y;
	private Color borderColor;
	private JEditorPane edpColor;
	private Model model = new Model();
	private boolean done=false;
	private Point point;


	public DlgPointProperties() {
		
		
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 14));
		setTitle("Point");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgPointProperties.class.getResource("/windows/resources/paleta.png")));
		setDefaultCloseOperation(DlgPointProperties);
		setModal(true);
		setBounds(100, 100, 443, 265);

		txtX = new JTextField();
		txtX.setEditable(true);
		txtX.setColumns(10);
		txtY = new JTextField();
		txtY.setEditable(true);
		txtY.setColumns(10);
		JLabel lblY = new JLabel("Y: ");
		lblY.setFont(new Font("Arial", Font.PLAIN, 14));
		lblY.setForeground(new Color(139, 0, 139));
		JLabel lblX = new JLabel("X:  ");
		lblX.setFont(new Font("Arial", Font.PLAIN, 14));
		lblX.setForeground(new Color(139, 0, 139));

		JLabel lblColor = new JLabel("Color: ");
		lblColor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblColor.setForeground(new Color(139, 0, 139));
		edpColor = new JEditorPane();
		edpColor.setEditable(true);
		edpColor.setBackground(Color.WHITE);
		edpColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Color currentBorderColor = edpColor.getBackground();
				Color color = JColorChooser.showDialog(null, "Choose the color: ", Color.BLACK);
				if(color  == null) {
					
					edpColor.setBackground(currentBorderColor);
				} else {
					
					edpColor.setBackground(color);
					
				}
			}
		});
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Arial", Font.BOLD, 11));
		btnSave.setBackground(new Color(255, 182, 193));
		btnSave.setForeground(new Color(139, 0, 139));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblX)
								.addComponent(lblY)
								.addComponent(lblColor))
							.addGap(45)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtX)
								.addComponent(txtY)
								.addComponent(edpColor, 0, 0, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(310)
							.addComponent(btnSave)))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblX)
							.addGap(24)
							.addComponent(lblY))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblColor)
						.addComponent(edpColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addComponent(btnSave)
					.addGap(44))
		);
		getContentPane().setLayout(groupLayout);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					
					if(x != Integer.parseInt(txtX.getText()) ||
							y != Integer.parseInt(txtY.getText()) ||
							borderColor != edpColor.getBackground()) {
						
						x = Integer.parseInt(txtX.getText());
						y = Integer.parseInt(txtY.getText());
						if(x >= 0 && y>=0){

							borderColor = edpColor.getBackground();
							point = new Point(x,y,borderColor);
							setPoint(point);
							model.setBorderColor(borderColor);
		
							setVisible(false);
							done=true;




						} else {

							JOptionPane.showMessageDialog(null, "You did not enter the data well!");
						}
					} else {
						
						setVisible(false);
						done=false;
					}

					

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

	

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public JTextField getTxtXKoordinata() {
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

	public JEditorPane getEdpColor() {
		return edpColor;
	}

	public void setEdpColor(JEditorPane edpColor) {
		this.edpColor = edpColor;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
