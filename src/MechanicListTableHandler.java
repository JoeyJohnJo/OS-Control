import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;


public class MechanicListTableHandler implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		ResultSet rs;
		
		 if (e.getClickCount() == 2) 
		 {
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      if (!ShowMechanicOSsWindow.isOpen)
		      {
		    	  try
		    	  {
		    		  ShowMechanicOSsWindow.ownerName = (String) target.getValueAt(row, 0);
		    	  		rs = ServerConnection.createQueryStatement("SELECT * FROM ordensdeservico WHERE \"Produtivo\" = '" + ShowMechanicOSsWindow.ownerName + "'");
		    	  		while (rs.next())
		    	  		{
		    	  			//loop to add each entry in the table to an array list
		    	  			ShowMechanicOSsWindow.names.add(rs.getString("produtivo"));
		    	  			
		    	  			if(!ShowMechanicOSsWindow.number.contains(rs.getString("numero"))){
		    	  				//loop to add each entry in the table to an array list
		    	  				ShowMechanicOSsWindow.number.add(rs.getString("numero"));
		    	  			}
		    	  			//loop to add each entry in the table to an array list
		    	  			ShowMechanicOSsWindow.quantity.add(rs.getString("quantidade_de_pecas"));
		    	  			
		    	  		}
		    	  		//creates an array to put the values from the array list
		    	  		ShowMechanicOSsWindow.namesArray = new String[ShowMechanicOSsWindow.names.size()];
						for (int iterate = 0; iterate < ShowMechanicOSsWindow.names.size(); iterate++){
							
							//loop that iterates through the array list and puts the values in the array
							ShowMechanicOSsWindow.namesArray[iterate] = ShowMechanicOSsWindow.names.get(iterate);
							System.out.println(ShowMechanicOSsWindow.namesArray[iterate]); //prints to the console for testing purposes
						}
						ShowMechanicOSsWindow.numberArray = new String[ShowMechanicOSsWindow.number.size()];
						for (int iterate = 0; iterate < ShowMechanicOSsWindow.number.size(); iterate++){
							
							//loop that iterates through the array list and puts the values in the array
							ShowMechanicOSsWindow.numberArray[iterate] = ShowMechanicOSsWindow.number.get(iterate);
							System.out.println(ShowMechanicOSsWindow.numberArray[iterate]); //prints to the console for testing purposes
						}
						ShowMechanicOSsWindow.quantityArray = new String[ShowMechanicOSsWindow.quantity.size()];
						for (int iterate = 0; iterate < ShowMechanicOSsWindow.quantity.size(); iterate++){
							
							//loop that iterates through the array list and puts the values in the array
							ShowMechanicOSsWindow.quantityArray[iterate] = ShowMechanicOSsWindow.quantity.get(iterate);
							System.out.println(ShowMechanicOSsWindow.quantityArray[iterate]); //prints to the console for testing purposes
						}
						new ShowMechanicOSsWindow(750, 400);
				    	ShowMechanicOSsWindow.isOpen = true;
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
