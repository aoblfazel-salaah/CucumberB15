package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtility {
    static Connection conn = null;
    static Statement statement = null;
    private static ResultSet resultSet;
    private static ResultSetMetaData resultSetMetaData;

    //this method creates connection to DB , executes query and return
    // object for result

    public static ResultSet getResultSet(String sqlQuery) {
        try {
            conn = DriverManager.getConnection(
                    ConfigReader.getPropertyValue("urldb"),
                    ConfigReader.getPropertyValue("usernamedb"),
                    ConfigReader.getPropertyValue("passworddb"));

            statement = conn.createStatement();
            resultSet = statement.executeQuery(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static ResultSetMetaData getResultSetMetaData(String query) {

        resultSet = getResultSet(query);
        resultSetMetaData = null;

        //we use this line to get the data in tabular format so that
        // we can use these column keys and values for retrieval operation

        try {
            resultSetMetaData = resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSetMetaData;
    }

    public static List<Map<String, String>> getListOfMapsFromResultSet(String query) {
        resultSetMetaData = getResultSetMetaData(query);

        List<Map<String, String>> mapsOfRset = new ArrayList<>();

        try {

            while (resultSet.next()) {
                Map<String, String> map = new LinkedHashMap<>();

                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String key = resultSetMetaData.getColumnName(i);
                    String value = resultSet.getString(i);

                    map.put(key, value);
                }
                System.out.println(map);
                mapsOfRset.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtility.closeResultSet(resultSet);
            DBUtility.closeStatement(statement);
            DBUtility.closeConnection(conn);
        }
        return mapsOfRset;
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
