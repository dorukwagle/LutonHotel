import UserInterface.*;
import UserInterface.Window;
import Utility.Values;

import java.awt.*;

public class Main {
	public static void main(String[] agrs) {
		//String path = new Main().getClass().getResource("/src").toString().replace("file:", "");
		Values.initializeValues();
//		new Dashboard().setVisible(true);
		//add the home page for the first time
//		Window.getWindow().add(new HomePage());
//		Window.getWindow().add(new Login());
//		Window.getWindow().add(new IndividualRegistration());
//		Window.getWindow().add(new CorporateRegistration());
		Window.getWindow().add(new ReceptionistDashboard());
	}
}
