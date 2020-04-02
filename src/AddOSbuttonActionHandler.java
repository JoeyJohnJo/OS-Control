import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddOSbuttonActionHandler implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {

		if(!AddOSWindow.isOpen) {
			
			new AddOSWindow(750, 400);
			AddOSWindow.isOpen = true;
		}
		
	}

}
