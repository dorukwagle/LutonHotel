package DataModel;

public class User {
	private int userId;
	private String name;
	private String address;
	
	public User() {
		this.userId = 0;
		this.name = "";
		this.address = "";
	}
	
	public User(int id, String name, String address) {
		this.userId = id;
		this.name = name;
		this.address = address;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
