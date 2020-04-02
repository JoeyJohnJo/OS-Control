import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


public class AddMechanicWindow extends BorderlessFrame 
{	
	public static boolean isOpen = false;
	private int width, height;
	private JLabel title= new JLabel("Adicionar Mecanico");
	private JLabel mechanicNameLabel = new JLabel("Nome:");
	static JTextField mechanicNameTextField = new JTextField();
	private JPanel innerPanel = new JPanel();
	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel for cancel and confirm
    JButton confirmButton = new JButton("Confirmar");
	
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	void setHeight(int h){height = h;}
	void setWidth(int w){width = w;}
	
	static GridBagConstraints createGbc(int x, int y, int I_GAP)
	{
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;

        gbc.weightx = 0.0;
        gbc.weighty = 0.0; // increase if you want component location to stretch vert.
        // I_GAP is a constant and is the size of the gap around each component
        gbc.insets = new Insets(I_GAP, I_GAP, I_GAP, I_GAP);
        // if the x value is odd, anchor to the left, otherwise if even to the right
        gbc.anchor = x % 2 == 0 ? GridBagConstraints.WEST : GridBagConstraints.EAST;
        return gbc;
    }
	
	void closeOperation(){
		close.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
				dispose();
				isOpen = false;
            }
		});
	}

	void setMechanicNameTextFieldProperties()
	{
		mechanicNameTextField.setPreferredSize(new Dimension(400, 30));
		innerPanel.add(mechanicNameTextField);
	}
	void setMechanicNameLabelProperties()
	{
		 mechanicNameLabel.setBackground(Color.lightGray);
         mechanicNameLabel.setOpaque(true);
         innerPanel.add(mechanicNameLabel);
	}
	void setConfirmButtonProperties()
	{
		confirmButton.addActionListener(new ConfirmAddMechanicButtonActionHandler()); //Does something when confirm is pressed
		confirmButton.setBackground(Color.GREEN);
		confirmButton.setBorderPainted(true);
		confirmButton.setFocusable(false);
        confirmButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        buttonPanel.add(confirmButton);
	}

	void createFrame()
	{
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mb.add(title, BorderLayout.WEST);
		setTitle("Adicionar Mecanico");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(new Dimension(getWidth(), getHeight() ));
		add(innerPanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Prevents the window from being opened multiple times
		 closeOperation();
	}
	
	public AddMechanicWindow(int width, int height) 
	{
		super();
		setWidth(width);
		setHeight(height);
		setMechanicNameLabelProperties();
		setMechanicNameTextFieldProperties();
		setConfirmButtonProperties();
		createFrame();
	}

}
