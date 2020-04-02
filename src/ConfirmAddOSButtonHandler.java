import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ConfirmAddOSButtonHandler implements ActionListener 
{	
	public void actionPerformed(ActionEvent arg0)
	{
		
		if(AddOSWindow.OSnumberTextField.getText().trim().length() == 0 )
		{
			JOptionPane alert = new JOptionPane();
			JOptionPane.showMessageDialog(alert,"Completar informacoes para adicionar OS!");
			alert.setLocation(500,500);
			alert.setVisible(true);
		}
		else if(AddConfirmButtonHandler.partCode.size() == 0 || AddConfirmButtonHandler.partName.size() == 0 ||
				AddOSWindow.ownerTextField.getSelectedItem().toString().length() == 0)
		{
			JOptionPane alert = new JOptionPane();
			JOptionPane.showMessageDialog(alert,"Nenhuma peca adicionada.\n Aperte o icone para adicionar.");
			alert.setLocation(500,500);
			alert.setVisible(true);
		}
		else
		{
			try
			{
				ResultSet rs = ServerConnection.createQueryStatement("select * from ordensdeservico where \"Numero\" = " + AddOSWindow.OSnumberTextField.getText());
				if (rs.isBeforeFirst() ) 
				{ 
					JOptionPane alert = new JOptionPane();
					JOptionPane.showMessageDialog(alert,"Esta OS ja esta inserida");
					alert.setLocation(500,500);
					alert.setVisible(true);
				}
				else 
				{
					new OrdemDeServico(Integer.parseInt(AddOSWindow.OSnumberTextField.getText().trim()), 
						  (String)AddOSWindow.ownerTextField.getSelectedItem(), AddConfirmButtonHandler.partCode.size());
		
					JOptionPane alert = new JOptionPane();
					JOptionPane.showMessageDialog(alert,"Ordem de Servico adicionada com sucesso!");
					//alert.setIcon(new ImageIcon("ConfirmPartButton01.png"));
					alert.setLocation(500,500);
					alert.setVisible(true);
        
					
					ServerConnection.SQLStatement("INSERT INTO ordensdeservico VALUES('" + OrdemDeServico.getMechanic() +"', "
							+ OrdemDeServico.getNumber() + ", " + AddConfirmButtonHandler.partCode.size() +")");
					AddConfirmButtonHandler.partCode.clear();
					AddConfirmButtonHandler.partQt.clear();
					AddConfirmButtonHandler.partName.clear();
					AddOSWindow.ownerTextField.setEnabled(true);
				}
			}catch (SQLException e) {e.printStackTrace();}
			
				AddOSWindow.ownerTextField.setEnabled(true);
				AddOSWindow.OSnumberTextField.setEnabled(true);
				AddOSWindow.OSnumberTextField.setText("");
			}
		AddConfirmButtonHandler.partCode.clear();
		AddConfirmButtonHandler.partQt.clear();
		AddConfirmButtonHandler.partName.clear();
		AddOSWindow.ownerTextField.setEnabled(true);
	}
}