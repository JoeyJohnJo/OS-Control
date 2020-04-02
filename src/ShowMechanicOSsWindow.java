import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class ShowMechanicOSsWindow extends BorderlessFrame
{

	public static boolean isOpen;
	public static String ownerName;
	private int width;
	private int height;
	private JTable osTable;
	private JScrollPane sp;
	private JPanel tablePanel = new JPanel(new GridLayout());
	private JLabel title = new JLabel("Lista de OSs por Mecanico");
	static ArrayList<String> number = new ArrayList<>();
	static ArrayList<String> names = new ArrayList<>();
	static ArrayList<String> quantity = new ArrayList<>();
	static String[] namesArray;
	static String[] numberArray;
	static String[]  quantityArray;
	
	void setHeight(int h) {
		height = h;
	}
	void setWidth(int w) {
		width = w;	
	}
	public int getHeight(){return height;}
	public int getWidth(){return width;}
	
	public void setTableProperties(){	
		osTable = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Numero", numberArray);
		model.addColumn("Produtivo", namesArray);
		model.addColumn("Quantidade de Pecas", quantityArray);
		osTable.setModel(model); //adds column to the the table
		osTable.setBounds(30, 40, 200, 300); //table size
		
		osTable.setFont(new Font("Arial Rounded MT", Font.TRUETYPE_FONT, 12));
		osTable.getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		osTable.getTableHeader().setReorderingAllowed(false);
		osTable.setShowHorizontalLines(true);		
		osTable.addMouseListener(new OSListTableHandler());
		
		// adding it to JScrollPane 
		sp = new JScrollPane(osTable);
		//sp.getViewport().setBackground(Color.gray);
		//tablePanel.setBackground(Color.gray);
		tablePanel.add(sp);
	}
	
	void closeOperation()
	{
		close.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
            	number.clear();
            	names.clear();
            	quantity.clear();
				dispose();
				isOpen = false;
            }
		});
	}
	
	private void createFrame() 
	{
		title .setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		mb.add(title, BorderLayout.WEST);
		setTitle("Pecas");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new Dimension(getWidth(), getHeight() ));
		add(tablePanel);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//Prevents the window from being opened multiple times
		 closeOperation();
		
	}
	
	public ShowMechanicOSsWindow(int w, int h) 
	{
		super();
		setWidth(w);
		setHeight(h);
		setTableProperties();
		createFrame();
	}
}
