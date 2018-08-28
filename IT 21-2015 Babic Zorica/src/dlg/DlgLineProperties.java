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

import geometry.Line;

import javax.swing.JTextField;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class DlgLineProperties extends JDialog {

	private static final long serialVersionUID = 1L;
	private static final int DlgLineProperties = 0;
	private JTextField txtXStart;
	private JTextField txtYStart;
	private JTextField txtXEnd;
	private JTextField txtYEnd;
	private int xStart;
	private int yStart;
	private int xEnd;
	private int yEnd;
	private Color borderColor;
	private Line l;
	private JEditorPane edpColor;

	private boolean done=false;

	


	public DlgLineProperties() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DlgLineProperties.class.getResource("/windows/resources/paleta.png")));
		setTitle("Line");
		setModal(true);
		setBounds(100, 100, 499, 335);
		setDefaultCloseOperation(DlgLineProperties);
		txtXStart = new JTextField();
		txtXStart.setEditable(true);
		txtXStart.setColumns(10);
		txtYStart = new JTextField();
		txtYStart.setEditable(true);
		txtYStart.setColumns(10);
		JLabel lblYStart = new JLabel("Y start point: ");
		lblYStart.setForeground(new Color(139, 0, 139));
		lblYStart.setFont(new Font("Arial", Font.PLAIN, 14));
		JLabel lblXStart = new JLabel("X start point:  ");
		lblXStart.setForeground(new Color(139, 0, 139));
		lblXStart.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblColor = new JLabel("Color: ");
		lblColor.setForeground(new Color(139, 0, 139));
		lblColor.setFont(new Font("Arial", Font.PLAIN, 14));
		edpColor = new JEditorPane();
		edpColor.setEditable(true);
		//edpBoja.setBackground(Color.WHITE);
		edpColor.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				Color currentBorderColor = edpColor.getBackground();
				Color color = JColorChooser.showDialog(null, "Choose the color", Color.BLACK);
				if(color  == null) {
					
					edpColor.setBackground(currentBorderColor);
				} else {
					
					edpColor.setBackground(color);
					
				}
				
				
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Arial", Font.BOLD, 11));
		btnSave.setForeground(new Color(139, 0, 139));
		btnSave.setBackground(new Color(255, 182, 193));

		JLabel lblXEnd = new JLabel("X end point: ");
		lblXEnd.setForeground(new Color(139, 0, 139));
		lblXEnd.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblYEnd = new JLabel("Y end point");
		lblYEnd.setForeground(new Color(139, 0, 139));
		lblYEnd.setFont(new Font("Arial", Font.PLAIN, 14));

		txtXEnd = new JTextField();
		txtXEnd.setEditable(true);
		txtXEnd.setColumns(10);

		txtYEnd = new JTextField();
		txtYEnd.setEditable(true);
		txtYEnd.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(314)
							.addComponent(btnSave))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblXStart)
									.addGap(45)
									.addComponent(txtXStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblYStart)
										.addComponent(lblXEnd)
										.addComponent(lblYEnd)
										.addComponent(lblColor))
									.addGap(48)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(edpColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtYEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtXEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtYStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
					.addGap(210))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblXStart)
						.addComponent(txtXStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblYStart)
						.addComponent(txtYStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblXEnd)
							.addGap(18)
							.addComponent(lblYEnd))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtXEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtYEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblColor)
						.addComponent(edpColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addComponent(btnSave))
		);
		getContentPane().setLayout(groupLayout);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



				try{
					
					if(xStart != Integer.parseInt(txtXStart.getText()) ||
					   
							yStart != Integer.parseInt(txtYStart.getText()) ||
							
							xEnd != Integer.parseInt(txtXEnd.getText()) ||
							
							yEnd != Integer.parseInt(txtYEnd.getText()) ||
							
							borderColor != edpColor.getBackground()) {
						
						
						
						xStart = Integer.parseInt(txtXStart.getText());
						yStart = Integer.parseInt(txtYStart.getText());
						xEnd = Integer.parseInt(txtXEnd.getText());
						yEnd = Integer.parseInt(txtYEnd.getText());
						if(xStart >= 0 && yStart>=0 && xEnd >= 0 && yEnd >= 0){
							
							borderColor = edpColor.getBackground();
							
					
							setVisible(false);
							done=true;

						} else {

							JOptionPane.showMessageDialog(null, "You did not enter the data well!");
						}
						
						
					} else {
						
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


	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public int getxStart() {
		return xStart;
	}

	public void setxStart(int xStart) {
		this.xStart = xStart;
	}

	public int getyStart() {
		return yStart;
	}

	public void setyStart(int yStart) {
		this.yStart = yStart;
	}

	public int getxEnd() {
		return xEnd;
	}

	public void setxEnd(int xEnd) {
		this.xEnd = xEnd;
	}

	public int getyEnd() {
		return yEnd;
	}

	public void setyEnd(int yEnd) {
		this.yEnd = yEnd;
	}

	public Line getL() {
		return l;
	}

	public void setL(Line l) {
		this.l = l;
	}


	public JTextField getTxtXStart() {
		return txtXStart;
	}


	public void setTxtXStart(JTextField txtXStart) {
		this.txtXStart = txtXStart;
	}


	public JTextField getTxtYStart() {
		return txtYStart;
	}


	public void setTxtYStart(JTextField txtYStart) {
		this.txtYStart = txtYStart;
	}


	public JTextField getTxtXEnd() {
		return txtXEnd;
	}


	public void setTxtXEnd(JTextField txtXEnd) {
		this.txtXEnd = txtXEnd;
	}


	public JTextField getTxtYEnd() {
		return txtYEnd;
	}


	public void setTxtYEnd(JTextField txtYEnd) {
		this.txtYEnd = txtYEnd;
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
