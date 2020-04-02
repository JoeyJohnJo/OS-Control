import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


public class SeeOSsWindow extends BorderlessFrame {

	int width, height;
	static ArrayList<String> numbers = new ArrayList<String>();
	static ArrayList<String> owners = new ArrayList<String>();
	static ArrayList<String> partQt = new ArrayList<String>();
	static String[] numbersArray;
	static String[] ownersArray;
	static String[] partQtArray;
	private JLabel title= new JLabel("Lista de Ordens de Servico");
	static boolean isOpen = false;
	private JTable osListTable;
	private JPanel tablePanel = new JPanel(new GridLayout());
	private JScrollPane sp;
	private JTextField searchBar = new JTextField();
	private JPanel searchPanel = new JPanel(new BorderLayout());
	private JPanel innerPanel = new JPanel(new FlowLayout());
	
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
            	partQt.clear();
				numbers.clear();
				owners.clear();
				dispose();
				isOpen = false;
            }
		});
	}
	
	void setSearchBarProperties()
	{
		ImageIcon searchIcon = new ImageIcon("lib/assets/search.png");
		JButton searchButton = new JButton(searchIcon);
		searchButton.setPreferredSize(new Dimension(28,28));
		searchButton.addActionListener(e -> {
			String s = searchBar.getText();
			ArrayList<String> fn = new ArrayList<>();
			ArrayList<String> fp = new ArrayList<>();
            ArrayList<String> fq = new ArrayList<>();
			fn.addAll(numbers);
			fp.addAll(owners);
			fq.addAll(partQt);
			System.out.println("Initial state: " + fn);

            Iterator<String> it = fn.iterator();
		for (int i = 0;it.hasNext(); ) {
                    if (!it.next().contains(s)) 
                    {
                        fp.remove(i);
			fq.remove(i);
                        it.remove();
                    }
		}
			String[] filteredNum = new String[fn.size()];
			System.out.println("Start of filtered numbers");
			for (int iterate = 0; iterate < fn.size(); iterate++)
			{
				//loop that iterates through the array list and puts the values in the array
				filteredNum[iterate] = fn.get(iterate);
				System.out.println(filteredNum[iterate]); //prints to the console for testing purposes
			}
			System.out.println("End of filtered numbers");

			String[] filteredNames = new String[fn.size()];
			System.out.println("Start of filtered names");
			for (int iterate = 0; iterate < fp.size(); iterate++)
			{
				//loop that iterates through the array list and puts the values in the array
				filteredNames[iterate] = fp.get(iterate);
				System.out.println(filteredNames[iterate]); //prints to the console for testing purposes
			}
			System.out.println("End of filtered names");
            String[] filteredQ = new String[fq.size()];
            System.out.println("Start of filtered quantities");
            for (int iterate = 0; iterate < fq.size(); iterate++)
            {
                //loop that iterates through the array list and puts the values in the array
                filteredQ[iterate] = fq.get(iterate);
                System.out.println(filteredQ[iterate]); //prints to the console for testing purposes
            }
            System.out.println("End of filtered quantities");

			osListTable.invalidate();
			sp.invalidate();
			tablePanel.invalidate();
			tablePanel.remove(sp);
			osListTable = new JTable(){
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			DefaultTableModel model = new DefaultTableModel();
			//osListTable.setBackground(Color.gray);
			model.addColumn("Numero", filteredNum); //Creates a column with title as Number and lined as the array
			model.addColumn("Produtivo", filteredNames); //Creates a column with title as Produtivo and lined as the array
			model.addColumn("Quantidade de Pecas", filteredQ); //Creates a column with title as Quantidade de Pecas and lined as the array
			osListTable.setModel(model); //adds column to the the table
			osListTable.setBounds(30, 40, 200, 300); //table size

			osListTable.setFont(new Font("Arial Rounded MT", Font.TRUETYPE_FONT, 12));
			osListTable.getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 15));
			osListTable.getTableHeader().setReorderingAllowed(false);
			osListTable.setShowHorizontalLines(true);
			//Event listener to open the table with the part names
			osListTable.addMouseListener(new OSListTableHandler());

			// adding it to JScrollPane
			sp = new JScrollPane(osListTable);
			//sp.getViewport().setBackground(Color.gray);
			//tablePanel.setBackground(Color.gray);
			tablePanel.add(sp);
			sp.validate();
			tablePanel.validate();
			osListTable.validate();
			osListTable.repaint();
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
        
	public void osListTableProperties(){	
		osListTable = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableModel model = new DefaultTableModel();
		//osListTable.setBackground(Color.gray);
		model.addColumn("Numero", numbersArray); //Creates a column with title as Number and lined as the array
		model.addColumn("Produtivo", ownersArray); //Creates a column with title as Produtivo and lined as the array
		model.addColumn("Quantidade de Pecas", partQtArray); //Creates a column with title as Quantidade de Pecas and lined as the array
		osListTable.setModel(model); //adds column to the the table
		osListTable.setBounds(30, 40, 200, 300); //table size
		
		osListTable.setFont(new Font("Arial Rounded MT", Font.TRUETYPE_FONT, 12));
		osListTable.getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		osListTable.getTableHeader().setReorderingAllowed(false);
		osListTable.setShowHorizontalLines(true);		
		
		//Event listener to open the table with the part names
		osListTable.addMouseListener(new OSListTableHandler());
		
		// adding it to JScrollPane 
		sp = new JScrollPane(osListTable);
		//sp.getViewport().setBackground(Color.gray);
		//tablePanel.setBackground(Color.gray);
		tablePanel.add(sp);
	}
	
	public void createFrame() {
		title.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mb.add(title, BorderLayout.WEST);
		setTitle("Lista de Ordens de Servico");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		osListTableProperties();
		setSearchBarProperties();
		add(tablePanel);
		add(searchPanel,BorderLayout.PAGE_END);
		setSize(new Dimension(getWidth(), getHeight() ));
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Prevents the window from being opened multiple times
		 closeOperation();
	}
	
	SeeOSsWindow(int width, int height){
		super();
		setWidth(width);
		setHeight(height);
		createFrame();
	}
}