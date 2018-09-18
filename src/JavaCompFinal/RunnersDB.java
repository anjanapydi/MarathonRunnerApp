package JavaCompFinal;

import java.util.*;
import java.sql.*;
/**
 * RunnersDB.java - This class is to set connection to Derby database named 'RunnersDB' and 
 * get details of Runners from table 'RunnersStats'.
 */
public class RunnersDB {
		private Connection connection = null; 
		/**
		 * Setup connection to derby Database.
		 * @return connection
		 * @throws SQLException - In case of any connection errors to database
		 */
		public Connection connect() throws SQLException{
        try {
        	String dbDirectory = "Resources/db-derby-for-java-comp/bin/";
            System.setProperty("derby.system.home", dbDirectory);
            String url = "jdbc:derby:RunnersDB";
            String user = "";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            System.err.println("Error loading database driver: " + e);
        }
        return connection;
    } // end connect method
	/**
	 * Method to get all the runners from 'RunnersStats' table.	
	 * @return Arraylist of Runners
	 */
    public List<RunnersThread> getAllRunners() {
    	List<RunnersThread> runners = new ArrayList<RunnersThread>();    	
        try {
            connection = connect();
            String query = "SELECT Name, RunnersSpeed, RestPercentage "
                         + "FROM RunnersStats";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            //get the data in ResultSet
            while(rs.next()) {
            	String name = rs.getString("Name");
                int speed = rs.getInt("RunnersSpeed");
                int rest = rs.getInt("RestPercentage");
                RunnersThread r = new RunnersThread(name, speed, rest);
                runners.add(r);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch(SQLException sqle) {
            sqle.printStackTrace();  // for debugging
        }
        return runners;
    } // end getAllRunners method
  } // end RunnersDB class

