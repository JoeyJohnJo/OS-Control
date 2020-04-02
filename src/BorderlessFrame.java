import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class BorderlessFrame extends JFrame {
	JPanel p;
	JMenuBar mb;
	JButton close,min;
	int pX,pY;
	protected static int width;
	protected static int height;

	BorderlessFrame()
	{
		getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.darkGray));
		createAndShowGUI();
	}
	
	void closeOperation(){
	    	close.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent ae)
	            {
	                // terminate program
	                System.exit(0);
	            }
	        });
	    }
	    
	private void createAndShowGUI()
	{
		// Custom look and feel
	    try
	    {
	    	UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	    }catch(Exception e){}
	        
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setUndecorated(true);        

	    // Create JMenuBar
	    mb=new JMenuBar();
	    mb.setLayout(new BorderLayout());
	        
	    // Create panel
	    p=new JPanel();
	    p.setOpaque(false);
	    p.setLayout(new GridLayout(1,3));
	        
	    // Create buttons
	    ImageIcon closeIcon = new ImageIcon("lib/assets/close.png");
	    ImageIcon minIcon = new ImageIcon("lib/assets/min.png");
	    min=new JButton(minIcon);
	    close=new JButton(closeIcon);
	    min.setPreferredSize(new Dimension(15,15));
	    close.setPreferredSize(new Dimension(15,15));
	    
	    min.setOpaque(false); 
	    min.setContentAreaFilled(false); 
	    min.setBorderPainted(false);
	    close.setOpaque(false); 
	    close.setContentAreaFilled(false); 
	    close.setBorderPainted(false);
	    
	        
	    min.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent ae)
	        {
	    		// minimize
	            setState(ICONIFIED);
	        }
	        });
	   
	    closeOperation();
	        
	    min.setFocusPainted(false);
	    close.setFocusPainted(false);
	        
	    // Add buttons
	    p.add(min);
	    p.add(close);
	    mb.add(p,BorderLayout.EAST);
	        
	    // Add mouse listener for JMenuBar mb
	    mb.addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent me)
	    	{
	    		// Get x,y and store them
	    		pX=me.getX();
	            pY=me.getY();
	        }
	    });
	        
	    // Add MouseMotionListener for detecting drag
	    mb.addMouseMotionListener(new MouseAdapter(){
	        public void mouseDragged(MouseEvent me)
	        {
	        	// Set the location
	            // get the current location x-co-ordinate and then get
	            // the current drag x co-ordinate, add them and subtract most recent
	            // mouse pressed x co-ordinate
	        	// do same for y co-ordinate
	        	setLocation(getLocation().x+me.getX()-pX,getLocation().y+me.getY()-pY);
	        }
	    });
	        
	    // Set the menu bar
	    setJMenuBar(mb);
	        
	    // Set size, visibility,shape and center it
	    setBackground(Color.white);
	    setVisible(true);
	    setShape(new java.awt.geom.Rectangle2D.Double(0,0,getWidth(),getHeight()));
	    setLocationRelativeTo(null);
	    }
	    
	void setWidth(int w){
			width = w; 
	}
	void setHeight(int h){
			height = h;
	}
	
}