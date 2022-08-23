import BusinessLayer.BLCustomer;
import DataModel.Customer;
import DataModel.LoginDetails;
import UserInterface.*;
import UserInterface.Window;
import Utility.InputException;
import Utility.Values;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
	public static void main(String[] agrs) {
		String path = new Main().getClass().getResource("/src").toString().replace("file:", "");

		System.out.println(isDateValid("6/5/2022"));
		Values.initializeValues();
		//add the home page for the first time
//		Window.getWindow().add(new IndividualDashboard());
		Window.getWindow().add(new HomePage());
	}

	final static String DATE_FORMAT = "dd/MM/yyyy";

	public static boolean isDateValid(String date)
	{
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			System.out.println(df.parse(date));
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
