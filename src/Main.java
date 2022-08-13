import UserInterface.Dashboard;

public class Main {
	public static void main(String[] agrs) {
		try {
			Dashboard window = new Dashboard();
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
