import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeeMechanicsButtonHandler implements ActionListener {
	
	static ResultSet rs;
	
public void actionPerformed(ActionEvent e) {
	if(!SeeMechanicsWindow.isOpen) {
		try {
				//SQL code to get the data from mechanics table
				rs = ServerConnection.createQueryStatement("SELECT * FROM 'mechanics'");
				while (rs.next()){
					
					if(!SeeMechanicsWindow.names.contains(rs.getString("nome"))){
					//loop to add each entry in the table to an array list
						SeeMechanicsWindow.names.add(rs.getString("nome"));
						}
				}
				//creates an array to put the values from the array list
				SeeMechanicsWindow.namesArray = new String[SeeMechanicsWindow.names.size()];
				for (int iterate = 0; iterate < SeeMechanicsWindow.names.size(); iterate++){
					
					//loop that iterates through the array list and puts the values in the array
					SeeMechanicsWindow.namesArray[iterate] = SeeMechanicsWindow.names.get(iterate);
					System.out.println(SeeMechanicsWindow.namesArray[iterate]); //prints to the console for testing purposes
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			new SeeMechanicsWindow(750,500);
			SeeMechanicsWindow.isOpen = true;
		}
	}

}
