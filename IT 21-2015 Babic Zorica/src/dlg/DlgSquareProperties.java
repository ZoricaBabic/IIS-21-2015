package dlg;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import geometry.Square;

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

public class DlgSquareProperties extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int DlgSquareProperties = 0;
	public static Object id;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtLength;
	private int x;
	private int y;
	private int length;
	private Color borderColor;
	private Color fillColor;
	public String shape; 
	public Square square;
	public JEditorPane edpBorderColor;
	public JEditorPane edpFillColor;
	private boolean done=false;

	public DlgSquareProperties() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgSquareProperties.class.getResource("/windows/resources/paleta.png")));
		setTitle("Square");

		setModal(true);
		setBounds(100, 100, 495, 400);
		setDefaultCloseOperation(DlgSquareProperties);
		txtX = new JTextField();
		txtX.setEditable(true);
		txtX.setColumns(10);
		txtY = new JTextField();
		txtY.setEditable(true);
		txtY.setColumns(10);
		txtLength = new JTextField();
		txtLength.setEditable(true);
		txtLength.setColumns(10);
		JLabel lblY = new JLabel("Y: ");
		lblY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblY.setForeground(new Color(139, 0, 139));
		JLabel lblSideLength = new JLabel("Length ");
		lblSideLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSideLength.setForeground(new Color(139, 0, 139));
		JLabel lblX = new JLabel("X: ");
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblX.setForeground(new Color(139, 0, 139));

		JLabel lblBorderColor = new JLabel("Border color: ");
		lblBorderColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBorderColor.setForeground(new Color(139, 0, 139));
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
		lblFillColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFillColor.setForeground(new Color(139, 0, 139));

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Arial", Font.BOLD, 11));
		btnSave.setBackground(new Color(255, 182, 193));
		btnSave.setForeground(new Color(139, 0, 139));



		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(274)
							.addComponent(btnSave))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblX)
								.addComponent(lblY)
								.addComponent(lblSideLength)
								.addComponent(lblFillColor)
								.addComponent(lblBorderColor))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(edpFillColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(edpBorderColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblX)
							.addGap(24)
							.addComponent(lblY)
							.addGap(24)
							.addComponent(lblSideLength))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBorderColor)
							.addGap(37)
							.addComponent(lblFillColor))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(edpBorderColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(edpFillColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(41)
					.addComponent(btnSave)
					.addGap(118))
		);
		getContentPane().setLayout(groupLayout);



		btnSave.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {


				try{
					
					if( x != Integer.parseInt(txtX.getText()) ||
							y != Integer.parseInt(txtY.getText()) ||
							length != Integer.parseInt(txtLength.getText()) ||
							borderColor != edpBorderColor.getBackground() || 
							fillColor != edpFillColor.getBackground()) {
						
						x = Integer.parseInt(txtX.getText());
						y = Integer.parseInt(txtY.getText());
						length = Integer.parseInt(txtLength.getText());
						if(x >= 0 && y>=0 && length>0){

							borderColor = edpBorderColor.getBackground();
							fillColor = edpFillColor.getBackground();
							
							setVisible(false);
							done=true;



						} else {

							JOptionPane.showMessageDialog(null, "You did not enter the data well!");
							
						}

					} else {
						
						done=false;
						setVisible(false);
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
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



	public JTextField getTxtLength() {
		return txtLength;
	}



	public void setTxtLength(JTextField txtLength) {
		this.txtLength = txtLength;
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
