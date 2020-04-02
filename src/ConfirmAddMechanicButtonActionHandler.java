import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConfirmAddMechanicButtonActionHandler implements ActionListener {
	public void actionPerformed(ActionEvent arg0) {
		String newName = AddMechanicWindow.mechanicNameTextField.getText(); // Retrieves the text from the box
				
        //Do not accept empty strings
        if (newName.trim().length() <= 0) {
            JOptionPane popup = new JOptionPane();
            JOptionPane.showMessageDialog(null, "Nome em branco");
            popup.setLocation(585, 300);
            popup.setVisible(true);
        }

        //Do not add to list if the mechanic already exist
        else
        {	
        	try{
        		ResultSet rs = ServerConnection.createQueryStatement("select * from mechanics where nome = '" + newName + "'");
        		if (rs.isBeforeFirst()) {
        			JOptionPane popup = new JOptionPane();
        			JOptionPane.showMessageDialog(null, "Mecanico ja existe");
        			popup.setLocation(585, 300);
        			popup.setVisible(true);
        		}
        		//Add mechanic if passes the conditions above
                else {
                    JOptionPane popup = new JOptionPane();
                    JOptionPane.showMessageDialog(null, newName + " adicionado.");
                    popup.setLocation(585, 300);
                    popup.setVisible(true);
                    ServerConnection.SQLStatement("insert into mechanics values('" + newName + "')");
                }
        	}catch(SQLException e){e.printStackTrace();}
        }
	}

}
