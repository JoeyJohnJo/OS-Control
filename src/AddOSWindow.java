import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.text.AttributeSet;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

public class AddOSWindow extends BorderlessFrame{

	private int width;
	private int height;
	public static boolean isOpen = false;
	private JLabel title= new JLabel("Adicionar Ordem de Servico");
	private JLabel ownerLabel = new JLabel("Nome do produtivo:");
	static JComboBox<String> ownerTextField = new JComboBox<>();
	private JLabel OSnumberLabel = new JLabel("Numero da OS:");
	static JFormattedTextField OSnumberTextField;
	private JLabel partQtLabel = new JLabel("Pecas:");
	static JSpinner partQtSpinner;
	static JTextField partCodeTextField;
	private JPanel contentPanel= new JPanel(new BorderLayout());
	private ImageIcon confirmAddPartIcon = new ImageIcon("lib/assets/confirmPartButton01.png");
	private JButton confirmButton = new JButton("Confirmar");
	private JButton cancelButton = new JButton("Cancelar");
	private JButton confirmAddPartButton = new JButton(confirmAddPartIcon);
	static JTextField getPartNameField;
	static JPanel innerPanel;
	JPanel cancelConfirmPanel;

	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public void setWidth(int w){
		width = w;
	}
	public void setHeight(int h){
		height = h;
	}
	void closeOperation(){
		close.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
            	AddConfirmButtonHandler.partCode.clear();
				AddConfirmButtonHandler.partQt.clear();
				AddConfirmButtonHandler.partName.clear();
				ownerTextField.setEnabled(true);
				dispose();
				isOpen = false;
            }
		});
	}
	static GridBagConstraints createGbc(int x, int y, int I_GAP) {

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
	
	void setLabelProperties(){
		
		ownerLabel.setFocusable(false);
		ownerLabel.setFont(new Font("Times New Romans", Font.TRUETYPE_FONT, 12));
		ownerLabel.setBackground(Color.lightGray);
		ownerLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		ownerLabel.setOpaque(true);
		
		partQtLabel.setFocusable(false);
		partQtLabel.setFont(new Font("Times New Romans", Font.TRUETYPE_FONT, 12));
		partQtLabel.setBackground(Color.lightGray);
		partQtLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		partQtLabel.setOpaque(true);
		
		OSnumberLabel.setFocusable(false);
		OSnumberLabel.setFont(new Font("Times New Romans", Font.TRUETYPE_FONT, 12));
		OSnumberLabel.setBackground(Color.lightGray);
		OSnumberLabel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
		OSnumberLabel.setOpaque(true);
	}
	void setOwnerTextFieldProperties(){
		DefaultComboBoxModel mechanicNames = new DefaultComboBoxModel(SeeMechanicsWindow.names.toArray());
		ownerTextField.setEditable(false);
		ownerTextField.setModel(mechanicNames);
		ownerTextField.setPreferredSize(new Dimension(200, 30));
	}
	void setOSNumberTextFieldProperties(){
			
		MaskFormatter numberFormatter;
		try {
			numberFormatter = new MaskFormatter("######");//Format for OS number
			
			OSnumberTextField = new JFormattedTextField(numberFormatter); //OS Number text field
			OSnumberTextField.setEditable(true);
			OSnumberTextField.setPreferredSize(new Dimension(220, 30));
		} catch (ParseException e) {e.printStackTrace();}
	}
	void setPartCodeProperties(){
		
		//Spinner
		int partQt = 1;
		SpinnerModel quantity = new SpinnerNumberModel(partQt,0,99,1);//Initial value, max and min, respectively
		partQtSpinner = new JSpinner(quantity);
		partQtSpinner.setPreferredSize(new Dimension(50,25));
		((DefaultFormatter)((JSpinner.DefaultEditor)partQtSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
		//This line of code allows only for numbers to be entered in the box instead of any character
	
		//Textfield
		
		/*Class that allows only for uppercase text in the textfield, converting everything from lowercase to uppercase*/
		class UppercaseDocumentFilter extends DocumentFilter 
		{
			public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException 
			{
				fb.insertString(offset, text.toUpperCase(), attr);
			}
			public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException 
			{
				fb.replace(offset, length, text.toUpperCase(), attrs);
			}
		}
		
		partCodeTextField = new JTextField();
		DocumentFilter filter = new UppercaseDocumentFilter(); 
		AbstractDocument partCodeTextFieldDoc = (AbstractDocument)partCodeTextField.getDocument(); 
		partCodeTextFieldDoc.setDocumentFilter(filter);
		partCodeTextField.setEditable(true);
		partCodeTextField.setPreferredSize(new Dimension(220, 30));
		partCodeTextField.addFocusListener(new FocusListener(){
			
			ResultSet rs;
			public void focusGained(FocusEvent e)
			{
				
			}
			
			public void focusLost(FocusEvent e)
			{
				/*This method acts like an autofill. The intended behavior is that when a part code is entered in the code field, a connection is made
				 * with the database, the code entered is then queried and the query returns the corresponding name to the code in the part name field below*/
				try 
				{
						rs = ServerConnection.createQueryStatement("select * from carparts where partcode = '" + AddOSWindow.partCodeTextField.getText() + "'");
						while (rs.next())
						{
							getPartNameField.setText(rs.getString("partname"));
						}
				} catch (SQLException e1) {e1.printStackTrace();}
			}
		});
		
		getPartNameField = new JTextField();
		getPartNameField.setEditable(false);
		getPartNameField.setPreferredSize(new Dimension(220, 30));
		getPartNameField.setBackground(Color.lightGray);
			
		//Button
		confirmAddPartButton.setPreferredSize(new Dimension(26, 26));
		confirmAddPartButton.setFocusable(false);
		confirmAddPartButton.addActionListener(new AddConfirmButtonHandler());
	}
	void setCancelConfirmProperties(){
		
		cancelConfirmPanel = new JPanel(new FlowLayout());
		confirmButton.setPreferredSize(new Dimension(120, 25));
		confirmButton.setFocusable(false);
		confirmButton.setBackground(Color.GREEN);
		confirmButton.setBorderPainted(true);
		confirmButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		confirmButton.addActionListener(new ConfirmAddOSButtonHandler());
		
		cancelButton.setPreferredSize(new Dimension(120, 25));
		cancelButton.setFocusable(false);
		cancelButton.setBackground(Color.RED);
		cancelButton.setBorderPainted(true);
		cancelButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ownerTextField.setEnabled(true);
				AddConfirmButtonHandler.partCode.clear();
				AddConfirmButtonHandler.partQt.clear();
				AddConfirmButtonHandler.partName.clear();
				dispose();
				isOpen = false;
			}
			
		});
		
		cancelConfirmPanel.add(confirmButton);
		cancelConfirmPanel.add(cancelButton);
		
	}
	void setContentPanelProperties(){
		innerPanel = new JPanel(new GridBagLayout());
		
		innerPanel.add(ownerLabel, createGbc(0,0,5));
		innerPanel.add(ownerTextField, createGbc(1,0,5));
		innerPanel.add(OSnumberLabel, createGbc(0,1,5));
		innerPanel.add(OSnumberTextField, createGbc(1,1,5));
		innerPanel.add(partQtLabel, createGbc(0,2,5));
		innerPanel.add(partCodeTextField, createGbc(1,2,5));
		innerPanel.add(partQtSpinner, createGbc(2,2,5));
		innerPanel.add(getPartNameField, createGbc(1,3,5));
		innerPanel.add(confirmAddPartButton, createGbc(2,3,5));
		contentPanel.add(innerPanel, BorderLayout.NORTH);
	}
	
	public void createFrame() {
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mb.add(title, BorderLayout.WEST);
		setTitle("Adicionar Ordem de Servico");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new Dimension(getWidth(), getHeight() ));
		add(cancelConfirmPanel, BorderLayout.SOUTH);
		add(contentPanel, BorderLayout.WEST);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Prevents the window from being opened multiple times
		 closeOperation();
	}

	public AddOSWindow(int w, int h) {
		super();
		setWidth(w);
		setHeight(h);
		setLabelProperties();
		setOSNumberTextFieldProperties();
		setOwnerTextFieldProperties();
		setPartCodeProperties();
		setCancelConfirmProperties();
		setContentPanelProperties();
		createFrame();
	}

}
