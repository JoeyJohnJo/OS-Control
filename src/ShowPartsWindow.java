import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class ShowPartsWindow extends BorderlessFrame{

	private int width;
	private int height;
	static List<String> names = new ArrayList<String>();
	static String[] namesArray;
	static List<String> codes = new ArrayList<String>();
	static String[] codesArray;
	static List<String> quantity = new ArrayList<String>();
	static String[] quantityArray;
	private JLabel title = new JLabel("Pecas");
	private JTable partsTable;
	private JScrollPane sp;
	private JPanel tablePanel = new JPanel(new GridLayout());
	public static String osNumber;
	protected static boolean isOpen;

	public ShowPartsWindow(int w, int h) {
		super();
		setWidth(w);
		setHeight(h);
		setPartsTableProperties();
		createFrame();
	}

	public void setPartsTableProperties(){	
		partsTable = new JTable(){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Codigo", codesArray);
		model.addColumn("Nome", namesArray);
		model.addColumn("Quantidade de Pecas",quantityArray);
		partsTable.setModel(model); //adds column to the the table
		partsTable.setBounds(30, 40, 200, 300); //table size
		
		partsTable.setFont(new Font("Arial Rounded MT", Font.TRUETYPE_FONT, 12));
		partsTable.getTableHeader().setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		partsTable.getTableHeader().setReorderingAllowed(false);
		partsTable.setShowHorizontalLines(true);		
		
		// adding it to JScrollPane 
		sp = new JScrollPane(partsTable);
		//sp.getViewport().setBackground(Color.gray);
		//tablePanel.setBackground(Color.gray);
		tablePanel.add(sp);
	}
	
	private void createFrame() {
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

	void setHeight(int h) {
		height = h;
	}
	void setWidth(int w) {
		width = w;	
	}
	public int getHeight(){return height;}
	public int getWidth(){return width;}
	
	void closeOperation(){
		close.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
            	names.clear();
            	codes.clear();
            	quantity.clear();
				dispose();
				isOpen = false;
            }
		});
	}
}
