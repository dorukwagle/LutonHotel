import UserInterface.*;
import UserInterface.Window;
import Utility.Values;

import java.awt.*;

public class Main {
	public static void main(String[] agrs) {
		String path = new Main().getClass().getResource("/src").toString().replace("file:", "");

		Values.initializeValues();
		//add the home page for the first time
//		Window.getWindow().add(new IndividualDashboard());
		Window.getWindow().add(new HomePage());
	}
}
