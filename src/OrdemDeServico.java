import java.sql.SQLException;
import java.util.ArrayList;

public class OrdemDeServico 
{
	static int number;
	static String mechanic;
	static ArrayList<AddPart> parts = new ArrayList<>();
	
	void setNumber(int num)
	{
		number = num;
	}
	void setMechanic(String owner)
	{
		mechanic = owner;
	}
	
	static int getNumber()
	{
		return number;
	}
	static String getMechanic()
	{
		return mechanic;
	}
	
	OrdemDeServico(int osNumber, String mechanic, int numOfParts)
	{
		setNumber(osNumber);
		setMechanic(mechanic);
		for (int i = 0; i < numOfParts; i++)
		{
			parts.add(new AddPart(AddConfirmButtonHandler.partName.get(i),AddConfirmButtonHandler.partCode.get(i), AddConfirmButtonHandler.partQt.get(i)));
		}
		
		System.out.println("Ordem de Servico gerada com dados:");
        System.out.println(OrdemDeServico.getNumber());
        System.out.println(OrdemDeServico.getMechanic());
        
        for (int i = 0; i < parts.size(); i++)
        {
        	System.out.println(parts.get(i).partName);
        	System.out.println(parts.get(i).partCode);
        	System.out.println(parts.get(i).partQuantity);
        }
        
        try {
			ServerConnection.SQLStatement("Create table OS"+ getNumber()  + " ( Nomes varchar(255), Codigo varchar(255), Quantidade int );");
			
			for (int i = 0; i < parts.size(); i++)
	        {	
				ServerConnection.SQLStatement("Insert into OS"+ getNumber() + "( Nomes, Codigo, Quantidade ) values ('" + parts.get(i).partName + "', '" +
						parts.get(i).partCode + "', " + parts.get(i).partQuantity + "  );");
	        }
			parts.clear();
		} catch (SQLException e) {e.printStackTrace();}
	}
}