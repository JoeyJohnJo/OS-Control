import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeeOSButtonHandler implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		
		if(!SeeOSsWindow.isOpen) {
			try {
					//SQL code to get the data from ordensdeservico table
					ResultSet rs = ServerConnection.createQueryStatement("SELECT * FROM ordensdeservico");
					while (rs.next()){
						
						if(!SeeOSsWindow.numbers.contains(rs.getString("Numero"))){
						//loop to add each entry in the table to an array list
							SeeOSsWindow.numbers.add(rs.getString("Numero"));
							SeeOSsWindow.owners.add(rs.getString("Produtivo"));
							SeeOSsWindow.partQt.add(rs.getString("Quantidade_de_Pecas"));
							}	
					}
					
					//creates an array to put the values from the array list
					SeeOSsWindow.numbersArray = new String[SeeOSsWindow.numbers.size()];
					for (int iterate = 0; iterate < SeeOSsWindow.numbers.size(); iterate++){
						
						//loop that iterates through the array list and puts the values in the array
						SeeOSsWindow.numbersArray[iterate] = SeeOSsWindow.numbers.get(iterate);
						System.out.println(SeeOSsWindow.numbersArray[iterate]); //prints to the console for testing purposes
					}
					
					//creates an array to put the values from the array list
					SeeOSsWindow.ownersArray = new String[SeeOSsWindow.owners.size()];
					for (int iterate = 0; iterate < SeeOSsWindow.owners.size(); iterate++){
						
						//loop that iterates through the array list and puts the values in the array
						SeeOSsWindow.ownersArray[iterate] = SeeOSsWindow.owners.get(iterate);
						System.out.println(SeeOSsWindow.ownersArray[iterate]); //prints to the console for testing purposes
					}
					//creates an array to put the values from the array list
					SeeOSsWindow.partQtArray = new String[SeeOSsWindow.partQt.size()];
					for (int iterate = 0; iterate < SeeOSsWindow.partQt.size(); iterate++){
						
						//loop that iterates through the array list and puts the values in the array
						SeeOSsWindow.partQtArray[iterate] = SeeOSsWindow.partQt.get(iterate);
						System.out.println(SeeOSsWindow.partQtArray[iterate]); //prints to the console for testing purposes
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				new SeeOSsWindow(750,500);
				SeeOSsWindow.isOpen = true;
		}
	}
}

