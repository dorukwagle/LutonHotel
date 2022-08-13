package DatabaseLayer;

import DataModel.User;
import Utility.DatabaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author doruk
 *
 */
public class DLUser {
	private User user;
	private DatabaseConnector db;
	private Connection connection;
	
	
	private void init(User user) throws Exception{
		this.user = user;
		try {
			this.db = DatabaseConnector.getInstance();
			this.connection = this.db.getConnection();
		} catch( Exception ex) {
			throw ex;
		}
	}
	public DLUser() throws Exception {
		this.init(new User());
	}
	
	public DLUser(User user) throws Exception{
		this.init(user);
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User save() throws Exception{
		try {
			//prepare for the data to be returned in case of insert
			String generatedColumns[] = {"id"};
			//create statement
			String query = "INSERT INTO users(name, address) VALUES(?,?)";
			PreparedStatement statement = this.connection.prepareStatement(query, generatedColumns);
			statement.setString(1, this.user.getName());
			statement.setString(2, this.user.getAddress());
			//execute the query
			int noOfUpdate = statement.executeUpdate();
			if(noOfUpdate > 0 ) {
				ResultSet rs = statement.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					this.user.setUserId(id);
				}
			}
			return this.user;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public User update() throws Exception{
		try {
			//create statement
			String query = "UPDATE users SET name = ?, address = ? WHERE id = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, this.user.getName());
			statement.setString(2, this.user.getAddress());
			statement.setInt(3, this.user.getUserId());
			//execute the query
			statement.executeUpdate();
			return this.user;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public void delete() throws Exception{
		try {
			//create statement
			String query = "DELETE FROM users WHERE id = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(3, this.user.getUserId());
			//execute the query
			statement.executeUpdate();
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public ArrayList<User> getAllUser() throws Exception{
		try {
			ArrayList<User> users = new ArrayList<User>();
			String query = "SELECT * FROM users ORDER BY name";
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()){
				User u = new User();
				u.setUserId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setAddress(rs.getString("address"));
				users.add(u);
			}
			return users;
		} catch (Exception ex){
			throw ex;
		}
	}

	public ArrayList<User> searchUser(String[] keys, String[] values) throws Exception{
		//SELECT * FROM user WHERE name LIKE '%Hari%' AND address LIKE '%PCPS';
		ArrayList<User> users = new ArrayList<User>();
		try{
			int keyLength = keys.length;
			String where = "";
			for(int i = 0; i<keyLength; ++i) {
				if (i == 0)
					where += " WHERE " + keys[i] + "LIKE '%" + values[i] + "%' ";
				else
					where += " AND " + keys[i] + "LIKE '%" + values[i] + "%' ";
			}
			String query = "SELECT * FROM users" + where + " ORDER BY name";
			Statement statement = this.connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()){
				User u = new User();
				u.setUserId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setAddress(rs.getString("address"));
				users.add(u);
			}
		} catch (SQLException e){
			throw new Exception(e.getMessage());
		}
		return users;
	}
}
