package DBTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseTest3 {
    public static void main(String[] args) {
        String url="jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";

        String username = "syntax_hrm";
        String password = "syntaxhrm123";

        try {
            Connection conn = DriverManager.getConnection(url,username, password);
            System.out.println("Connection is created for Batch 15");

            Statement statement= conn.createStatement();

            String query= "select * from person;";
            ResultSet rset= statement.executeQuery(query);

            //extract data form resultSet in Java data structure
            List < Map<String, String>> listFromRset= new ArrayList<>();
            ResultSetMetaData rsMetaData = rset.getMetaData();

            while (rset.next()){
                Map <String, String> rowKeysAndValues= new LinkedHashMap<>();
                for (int i=1; i <= rsMetaData.getColumnCount(); i++){
                    String key = rsMetaData.getColumnName(i);
                    String value = rset.getString(key);
                    rowKeysAndValues.put(key, value);
                }
                System.out.println(rowKeysAndValues);
                listFromRset.add(rowKeysAndValues);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
