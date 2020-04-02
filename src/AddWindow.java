import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class AddWindow extends BorderlessFrame{

	private static int width;
	private static int height;
	private JLabel title= new JLabel("Adicionar");
	private JButton addOSbutton = new JButton("Adicionar OS");
	private JButton addMechanicbutton = new JButton("Adicionar Mecanico");
	JPanel buttonsPanel = new JPanel();
	public static boolean isOpen = false;
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
				dispose();
				isOpen = false;
            }
		});
	}

	void setAddOSButtonProperties(){
		addOSbutton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		addOSbutton.setPreferredSize(new Dimension(150,70));
		addOSbutton.setToolTipText("Adicionar nova OS");
		addOSbutton.setFocusable(false);
		addOSbutton.setBackground(Color.decode("#1e90ff"));
        addOSbutton.addActionListener(new AddOSbuttonActionHandler());
	}
	void setAddMechanicButtonProperties(){
		addMechanicbutton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		addMechanicbutton.setPreferredSize(new Dimension(150,70));
		addMechanicbutton.setToolTipText("Adicionar novo mecanico");
		addMechanicbutton.setFocusable(false);
		addMechanicbutton.setBackground(Color.decode("#1e90ff"));
		addMechanicbutton.addActionListener(new AddMechanicButtonActionHandler());
	}
	void setButtonsPanelProperties(){
		//buttonsPanel.setBackground(Color.gray);
		buttonsPanel.add(addOSbutton);
	    buttonsPanel.add(addMechanicbutton);
	}
	
	public void createFrame() {
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		setTitle("Adicionar");
		mb.add(title, BorderLayout.WEST);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new Dimension(getWidth(),getHeight()));
		setAddOSButtonProperties();
		setAddMechanicButtonProperties();
		setButtonsPanelProperties();
		add(buttonsPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Prevents the window from being opened multiple times
		 closeOperation();
	}
	
	AddWindow(int w, int h){
		super();
		setWidth(w);
		setHeight(h);
		createFrame();
	}
}
