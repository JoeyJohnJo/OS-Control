import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddButtonActionHandler implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		
		if(!AddWindow.isOpen) {
			
				new AddWindow(400, 150);
				AddWindow.isOpen = true;
		}
	}

}
