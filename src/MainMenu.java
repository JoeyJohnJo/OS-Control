import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainMenu extends BorderlessFrame {
	
	int width, height;
	private JLabel title = new JLabel("OS Control");
	private JPanel buttonsPanel = new JPanel();
	private JButton seeMechanicsButton = new JButton("Mecanicos");
	private JButton seeOSButton = new JButton("Ordens de Servico");
	private ImageIcon addIcon = new ImageIcon("lib/assets/addIcon01.png");
	private JButton addButton = new JButton(addIcon);
	JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	private int buttonWidth, buttonHeight;
	
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
	
	
	int getButtonWidth(){
		return buttonWidth;
	}
	int getButtonHeight(){
		return buttonHeight;
	}
	void setButtonWidth(int buttonWidth){
		this.buttonWidth = buttonWidth;
	}
	void setButtonHeight(int buttonHeight){
		this.buttonHeight = buttonHeight;
	}
	
	void setSeeMechanicsButtonProperties(){
		seeMechanicsButton.setFocusable(false);
		seeMechanicsButton.setFont(new Font("Arial Rounded MT", Font.BOLD, 15));
		seeMechanicsButton.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
		seeMechanicsButton.setToolTipText("Mostrar lista de todos os mecanicos no sistema");
		seeMechanicsButton.addActionListener(new SeeMechanicsButtonHandler());
		seeMechanicsButton.setBackground(Color.decode("#1e90ff"));
	}
	void setSeeOSButtonProperties(){
		seeOSButton.setFocusable(false);
		seeOSButton.setFont(new Font("Arial Rounded MT", Font.BOLD, 15));
		seeOSButton.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
		seeOSButton.setToolTipText("Mostrar lista de todos as OSs no sistema");
		seeOSButton.addActionListener(new SeeOSButtonHandler());
		seeOSButton.setBackground(Color.decode("#1e90ff"));
	}
	void setButtonsPanelProperties(){
		buttonsPanel.setLayout(new GridBagLayout());
		buttonsPanel.add(seeMechanicsButton);
		buttonsPanel.add(seeOSButton);
		//buttonsPanel.setBackground(Color.gray);
	}
	void setAddButtonProperties(){
		//addButtonPanel.setBackground(Color.gray);
		addButton.setFocusable(false);
		addButton.setPreferredSize(new Dimension(65,65));
		addButton.setToolTipText("Adicionar OS ou mecanico");
		addButton.addActionListener(new AddButtonActionHandler());
		addButtonPanel.add(addButton);
	}
	
	
	void createFrame(){
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		setTitle("OS Control");
		mb.add(title, BorderLayout.WEST);
		setSize(new Dimension(getWidth(), getHeight()));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(buttonsPanel, BorderLayout.CENTER);
		add(addButtonPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
	
	MainMenu(int width, int height) {
		super();
		setWidth(width);
		setHeight(height);
		setButtonHeight(height / 100 * 16);
		setButtonWidth(width / 100 * 40);
		setSeeMechanicsButtonProperties();
		setSeeOSButtonProperties();
		setButtonsPanelProperties();
		setAddButtonProperties();
		createFrame();
	}

	
}
