package dlg;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;


import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Font;

public class DlgRectangleProperties extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int DlgRectangleProperties = 0;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtLength;
	private JTextField txtWidth;
	private int xD;
	private int yD;
	private int length;
	private int widthD; 
	private Color borderColor;
	private Color fillColor;

	private JEditorPane edpBorderColor;
	private JEditorPane edpFillColor;

	private boolean  done = false;
	


	public static void main(String[] args) {
		try {
			DlgRectangleProperties dialog = new DlgRectangleProperties();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DlgRectangleProperties() {
		setModal(true);
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 14));
		getContentPane().setForeground(new Color(139, 0, 139));
		setTitle("Rectangle");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgRectangleProperties.class.getResource("/windows/resources/paleta.png")));

		setBounds(100, 100, 485, 413);
		JLabel lblRectangleProperties = new JLabel(" ");
		setDefaultCloseOperation(DlgRectangleProperties);
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
		lblY.setFont(new Font("Arial", Font.PLAIN, 14));
		lblY.setForeground(new Color(139, 0, 139));
		JLabel lblLength = new JLabel("Length: ");
		lblLength.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLength.setForeground(new Color(139, 0, 139));
		JLabel lblX = new JLabel("X: ");
		lblX.setFont(new Font("Arial", Font.PLAIN, 14));
		lblX.setForeground(new Color(139, 0, 139));

		
		
		
		JLabel lblBorderColor = new JLabel("Border color: ");
		lblBorderColor.setFont(new Font("Arial", Font.PLAIN, 14));
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
		JLabel lblFillColor = new JLabel("Fill color ");
		lblFillColor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblFillColor.setForeground(new Color(139, 0, 139));

		JButton btnSave = new JButton("Save");
		btnSave.setBackground(new Color(255, 182, 193));
		btnSave.setForeground(new Color(139, 0, 139));
		btnSave.setFont(new Font("Arial", Font.BOLD, 11));

		JLabel lblSirina = new JLabel("Width: ");
		lblSirina.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSirina.setForeground(new Color(139, 0, 139));

		txtWidth = new JTextField();
		txtWidth.setEditable(true);
		txtWidth.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblX)
						.addComponent(lblY)
						.addComponent(lblLength)
						.addComponent(lblFillColor)
						.addComponent(lblBorderColor)
						.addComponent(lblSirina))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(edpFillColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(edpBorderColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblRectangleProperties)
					.addGap(133))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(309)
					.addComponent(btnSave)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRectangleProperties)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblX)
									.addGap(24)
									.addComponent(lblY)
									.addGap(24)
									.addComponent(lblLength))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(lblSirina))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblBorderColor)
									.addGap(37)
									.addComponent(lblFillColor))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(edpBorderColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(31)
									.addComponent(edpFillColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(56)
					.addComponent(btnSave)
					.addGap(41))
		);
		getContentPane().setLayout(groupLayout);

		btnSave.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {


				try{
					
					System.out.println(xD);
					System.out.println(Integer.parseInt(txtX.getText()) );
					System.out.println(yD);
					System.out.println(Integer.parseInt(txtY.getText()));
					System.out.println(length);
					System.out.println(Integer.parseInt(txtLength.getText()));
					System.out.println(borderColor);
					System.out.println(edpBorderColor.getBackground());
					System.out.println(fillColor);
					System.out.println(edpFillColor.getBackground());
					if(xD != Integer.parseInt(txtX.getText()) ||
							yD != Integer.parseInt(txtY.getText()) ||
							length != Integer.parseInt(txtLength.getText()) ||
							widthD != Integer.parseInt(txtWidth.getText()) ||
							borderColor != edpBorderColor.getBackground() ||
							fillColor != edpFillColor.getBackground()) {
						
						
						xD = Integer.parseInt(txtX.getText());
						yD = Integer.parseInt(txtY.getText());
						length = Integer.parseInt(txtLength.getText());
						widthD = Integer.parseInt(txtWidth.getText());
						if(xD >= 0 && yD>=0 && length>0 && widthD>0){

							borderColor = edpBorderColor.getBackground();
							fillColor = edpFillColor.getBackground();
				
							setVisible(false);
							done = true;
							
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

	public JTextField getTxtWidth() {
		return txtWidth;
	}

	public void setTxtWidth(JTextField txtWidth) {
		this.txtWidth = txtWidth;
	}

	public int getxD() {
		return xD;
	}

	public void setxD(int xD) {
		this.xD = xD;
	}

	public int getyD() {
		return yD;
	}

	public void setyD(int yD) {
		this.yD = yD;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidthD() {
		return widthD;
	}

	public void setWidthD(int widthD) {
		this.widthD = widthD;
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