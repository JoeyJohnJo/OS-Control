import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMechanicButtonActionHandler implements ActionListener 
{
	public void actionPerformed(ActionEvent arg0) 
	{
		if(!AddMechanicWindow.isOpen) 
		{
			new AddMechanicWindow(500, 150);
			AddMechanicWindow.isOpen = true;
		}
	}

}
