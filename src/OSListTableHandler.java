import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;


public class OSListTableHandler implements MouseListener {

	private ResultSet rs;
	@Override
	public void mouseClicked(MouseEvent e) {
		 if (e.getClickCount() == 2) {
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      if (!ShowPartsWindow.isOpen)
		      {
		    	  try
		    	  {
		    		  	ShowPartsWindow.osNumber = (String) target.getValueAt(row, 0);
		    	  		rs = ServerConnection.createQueryStatement("SELECT * FROM os" + ShowPartsWindow.osNumber);
		    	  		while (rs.next())
		    	  		{
		    	  			//loop to add each entry in the table to an array list
		    	  			ShowPartsWindow.names.add(rs.getString("nomes"));
		    	  				
		    	  			if(!ShowPartsWindow.codes.contains(rs.getString("codigo"))){
		    	  				//loop to add each entry in the table to an array list
		    	  				ShowPartsWindow.codes.add(rs.getString("codigo"));
		    	  			}
		    	  			//loop to add each entry in the table to an array list
		    	  			ShowPartsWindow.quantity.add(rs.getString("quantidade"));
		    	  			
		    	  		}
		    	  		//creates an array to put the values from the array list
		    	  		ShowPartsWindow.namesArray = new String[ShowPartsWindow.names.size()];
						for (int iterate = 0; iterate < ShowPartsWindow.names.size(); iterate++){
							
							//loop that iterates through the array list and puts the values in the array
							ShowPartsWindow.namesArray[iterate] = ShowPartsWindow.names.get(iterate);
							System.out.println(ShowPartsWindow.namesArray[iterate]); //prints to the console for testing purposes
						}
						ShowPartsWindow.codesArray = new String[ShowPartsWindow.codes.size()];
						for (int iterate = 0; iterate < ShowPartsWindow.codes.size(); iterate++){
							
							//loop that iterates through the array list and puts the values in the array
							ShowPartsWindow.codesArray[iterate] = ShowPartsWindow.codes.get(iterate);
							System.out.println(ShowPartsWindow.codesArray[iterate]); //prints to the console for testing purposes
						}
						ShowPartsWindow.quantityArray = new String[ShowPartsWindow.quantity.size()];
						for (int iterate = 0; iterate < ShowPartsWindow.quantity.size(); iterate++){
							
							//loop that iterates through the array list and puts the values in the array
							ShowPartsWindow.quantityArray[iterate] = ShowPartsWindow.quantity.get(iterate);
							System.out.println(ShowPartsWindow.quantityArray[iterate]); //prints to the console for testing purposes
						}
						new ShowPartsWindow(750, 400);
		    	  		ShowPartsWindow.isOpen = true;
		    	  		System.out.println("OS Number = " + ShowPartsWindow.osNumber);
		    	  }catch(SQLException a ){a.printStackTrace();}
		      }
		 }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
