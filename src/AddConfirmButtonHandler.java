import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AddConfirmButtonHandler implements ActionListener {
	
	ResultSet rs;
	static ArrayList<String> partCode = new ArrayList<>();
	static ArrayList<Integer> partQt = new ArrayList<>();
	static ArrayList<String> partName = new ArrayList<>();
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(AddOSWindow.OSnumberTextField.getText().trim().length() == 0 &&
				AddOSWindow.partCodeTextField.getText().trim().length() == 0)
		{
			JOptionPane alert = new JOptionPane();
			alert.showMessageDialog(alert,"Campos em branco");
			alert.setLocation(500,500);
            alert.setVisible(true);
		}
		else if(AddOSWindow.partCodeTextField.getText().trim().length() == 0)
		{
			JOptionPane alert = new JOptionPane();
			alert.showMessageDialog(alert,"Por favor inserir codigo de peca");
			alert.setLocation(500,500);
            alert.setVisible(true);
		}
		else if(AddOSWindow.OSnumberTextField.getText().trim().length() == 0)
		{
			JOptionPane alert = new JOptionPane();
			alert.showMessageDialog(alert,"Por favor inserir o numero da Ordem de Servico");
			alert.setLocation(500,500);
            alert.setVisible(true);
			
		}
		else
		{
			try
			{
				rs = ServerConnection.createQueryStatement("select * from ordensdeservico where \"Numero\" = " + AddOSWindow.OSnumberTextField.getText());
				if (rs.isBeforeFirst() ) 
				{ 
					JOptionPane alert = new JOptionPane();
					JOptionPane.showMessageDialog(alert,"Esta OS ja esta inserida");
					alert.setLocation(500,500);
					alert.setVisible(true);
				}
				else
				{
					if(!partCode.contains(AddOSWindow.partCodeTextField.getText()))
					{
						try {
							rs = ServerConnection.createQueryStatement("select * from carparts where partcode = '" + AddOSWindow.partCodeTextField.getText() + "'");
							if (!rs.isBeforeFirst() ) 
							{ 
								JOptionPane alert = new JOptionPane();
								int r = JOptionPane.showConfirmDialog(alert,"Peca nao cadastrada, deseja adicionar mesmo assim?", "Peca nao cadastrada", 
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								
								alert.setLocation(500,500);
					            alert.setVisible(true);
					            
					            if (r == alert.YES_OPTION)
					            {
					            	partCode.add(AddOSWindow.partCodeTextField.getText());
									partQt.add((Integer) AddOSWindow.partQtSpinner.getValue());
									partName.add("NAO CADASTRADA/SEM NOME");
									AddOSWindow.partCodeTextField.setText("");
									AddOSWindow.getPartNameField.setText("");
									System.out.println(partCode + "=" + partName + "( " + partQt + ")");
									AddOSWindow.ownerTextField.setEnabled(false);
									AddOSWindow.OSnumberTextField.setEnabled(false);
									
									JOptionPane success = new JOptionPane();
									JOptionPane.showMessageDialog(alert,"Peca adicionada!");
									success.setLocation(500,500);
									success.setVisible(true);
					            }
					            else if (r == alert.NO_OPTION || r == alert.CLOSED_OPTION)
					            {
					            	AddOSWindow.partCodeTextField.setText("");
					    			AddOSWindow.getPartNameField.setText("");
					            }
							} 
							else
							{
								while(rs.next())
								{
									partCode.add(AddOSWindow.partCodeTextField.getText());
									partQt.add((Integer) AddOSWindow.partQtSpinner.getValue());
									partName.add(rs.getString("partname"));
									System.out.println(partCode + "=" + partName + "( " + partQt + ")");
									AddOSWindow.ownerTextField.setEnabled(false);
									AddOSWindow.OSnumberTextField.setEnabled(false);
								
									JOptionPane alert = new JOptionPane();
									JOptionPane.showMessageDialog(alert,"Peca adicionada!");
									alert.setLocation(500,500);
									alert.setVisible(true);
								}
							}
						} catch (SQLException e) {e.printStackTrace();}
					}
					else
					{
						JOptionPane alert = new JOptionPane();
						alert.showMessageDialog(alert,"Esta peca ja esta inserida");
						alert.setLocation(500,500);
			            alert.setVisible(true);
					}
				}
				
			} catch (SQLException e) {e.printStackTrace();}
		}
	}

}