import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class SeeMechanicsWindow extends BorderlessFrame {
	
	int width, height;
	static boolean isOpen = false;
	private JLabel title= new JLabel("Lista de Mecanicos");
	private JTable mechanicListTable;
	private JPanel tablePanel = new JPanel(new GridLayout());
	private JScrollPane sp;
	static List<String> names = new ArrayList<String>();
	static String[] namesArray;
	private JPanel searchPanel = new JPanel(new BorderLayout());
	private JPanel innerPanel = new JPanel(new FlowLayout());
	JTextField searchBar;
	
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
			ArrayList<String> comparer = new ArrayList<String>();
            public void actionPerformed(ActionEvent ae)
            {
            	try {
            		SeeMechanicsButtonHandler.rs = ServerConnection.createQueryStatement("SELECT * FROM mechanics");
    				while (SeeMechanicsButtonHandler.rs.next()){
    					
    					if(!comparer.contains(SeeMechanicsButtonHandler.rs.getString("nome"))){
    					//loop to add each entry in the table to an array list
    						comparer.add(SeeMechanicsButtonHandler.rs.getString("nome"));
    						}
    				}
				} catch (SQLException e) {e.printStackTrace();}
				names.clear();
				names.addAll(comparer);
				
            	dispose();
            	isOpen = false;
            }
		});
	}
	public void createFrame() {
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mb.add(title, BorderLayout.WEST);
		setTitle("Lista de Mecanicos");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new Dimension(getWidth(), getHeight()));
		mechanicListTableProperties();
		setSearchBarProperties();
		add(tablePanel);
		add(searchPanel,BorderLayout.PAGE_END);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Prevents the window from being opened multiple times
		 closeOperation();
		
	}
	public void mechanicListTableProperties(){	
		mechanicListTable = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableModel model = new DefaultTableModel();
		searchBar = RowFilterUtil.createRowFilter(mechanicListTable);
		//mechanicListTable.setBackground(Color.gray);
		model.addColumn("Nomes", namesArray); //Creates a column with title as Nome and lined as the array
		mechanicListTable.setModel(model); //adds column to the the table
		mechanicListTable.setBounds(30, 40, 200, 300); //table size
		
		mechanicListTable.setFont(new Font("Arial Rounded MT", Font.TRUETYPE_FONT, 12));
		mechanicListTable.getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		mechanicListTable.getTableHeader().setReorderingAllowed(false);
		mechanicListTable.setShowHorizontalLines(true);		
		mechanicListTable.addMouseListener(new MechanicListTableHandler());
		
		// adding it to JScrollPane 
		sp = new JScrollPane(mechanicListTable);
		//sp.getViewport().setBackground(Color.gray);
		//tablePanel.setBackground(Color.gray);
		tablePanel.add(sp);
	}
	
        void setSearchBarProperties()
	{
		ImageIcon searchIcon = new ImageIcon("lib/assets/search.png");
		JButton searchButton = new JButton(searchIcon);
		searchButton.setPreferredSize(new Dimension(28,28));
		searchButton.addActionListener(e -> {
			String s = searchBar.getText();
			ArrayList<String> fn = new ArrayList<>();
			fn.addAll(names);
			System.out.println("Initial state: " + fn);
			for (Iterator<String> it = fn.iterator(); it.hasNext(); ) {
				if (!it.next().contains(s)){
					it.remove();
				}
			}
			String[] filteredNames = new String[fn.size()];
			System.out.println("Start of filtered names");
			for (int iterate = 0; iterate < fn.size(); iterate++)
			{
				//loop that iterates through the array list and puts the values in the array
				filteredNames[iterate] = fn.get(iterate);
				System.out.println(filteredNames[iterate]); //prints to the console for testing purposes
			}
			System.out.println("End of filtered names");
			mechanicListTable.invalidate();
			sp.invalidate();
			tablePanel.invalidate();
			tablePanel.remove(sp);
			mechanicListTable = new JTable(){
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			DefaultTableModel model = new DefaultTableModel();
			//mechanicListTable.setBackground(Color.gray);
			model.addColumn("Nomes", filteredNames); //Creates a column with title as Nome and lined as the array
			mechanicListTable.setModel(model); //adds column to the the table
			mechanicListTable.setBounds(30, 40, 200, 300); //table size
			mechanicListTable.setFont(new Font("Arial Rounded MT", Font.TRUETYPE_FONT, 12));
			mechanicListTable.getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 15));
			mechanicListTable.getTableHeader().setReorderingAllowed(false);
			mechanicListTable.setShowHorizontalLines(true);
			mechanicListTable.addMouseListener(new MechanicListTableHandler());

			// adding it to JScrollPane
			sp = new JScrollPane(mechanicListTable);
			//sp.getViewport().setBackground(Color.gray);
			//tablePanel.setBackground(Color.gray);
			tablePanel.add(sp);
			sp.validate();
			tablePanel.validate();
			mechanicListTable.validate();
			mechanicListTable.repaint();
			sp.validate();
			tablePanel.validate();
			sp.repaint();
			tablePanel.repaint();
		});
		searchBar.setPreferredSize(new Dimension(300,28));
		innerPanel.add(searchBar);
		innerPanel.add(searchButton);
		searchPanel.add(innerPanel, BorderLayout.LINE_END);
	}
        
	SeeMechanicsWindow(int w, int h){
		super();
		setWidth(w);
		setHeight(h);
		createFrame();
	}

}
