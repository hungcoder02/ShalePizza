/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Trang Tran
 */
public class DbFactory {

    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "";
    private static final String MYSQL_CONN_STRING = "jdbc:mysql://localhost/eproject2";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                MYSQL_CONN_STRING,
                MYSQL_USERNAME,
                MYSQL_PASSWORD
        );
    }

    public static Connection getConnection(DbType dbt) throws SQLException {
        switch(dbt) {
            case MYSQL:
            default:
                return DriverManager.getConnection(
                        MYSQL_CONN_STRING,
                        MYSQL_USERNAME,
                        MYSQL_PASSWORD
                );
        }
    }
}
