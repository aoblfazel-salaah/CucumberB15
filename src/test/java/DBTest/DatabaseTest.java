package DBTest;

import java.sql.*;

public class DatabaseTest {
    /*
    we need to build connection with the database
    need 3 things : URL, username, password
     */
    public static void main(String[] args) {
        //providing information::

        /*

        String url="jdbc"+
                // java is trying to connect with the database
                ":mysql"+
                // the database I'm trying to connect with is mysql
                "://3.239.253.255:3306"+
                // the address of the database --:3306 is the port
                "/syntaxhrm_mysql";
        // name of the database

         */
        String url="jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";

        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        //now we need to establish connection to the database

        try {
            Connection conn = DriverManager.getConnection(url,username, password);
            System.out.println("Connection is created for Batch 15");

            //create a statement to send sql queries
            Statement statement= conn.createStatement();

            // by sending query db returns a resultSet (table with row and col)
            ResultSet rset = statement.executeQuery(
                    "select FirstName, LastName " +
                        "from person");
            rset.next();

            String fName = rset.getString("FirstName");
            String lName = rset.getString("LastName");

            System.out.println(fName + " " + lName);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
