package database;

import role.Admin;

import java.sql.*;
import java.util.Properties;

import static database.DDL.*;

public class SelectDatabaseContent implements DatabaseSelector {

    private String dbUrl;
    private Properties sqLiteConfig;

    SelectDatabaseContent(String dbUrl, Properties sqLiteConfig) {
        this.dbUrl = dbUrl;
        this.sqLiteConfig = sqLiteConfig;
    }
    @Override
    public Admin getAdmin() throws DatabaseException {
        try (Connection connection = DriverManager.getConnection(dbUrl, sqLiteConfig)) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + ADMIN_TABLE);
            rs.next();
            String email = rs.getString(ADMIN_COLUMN_EMAIL);
            String name = rs.getString(ADMIN_COLUMN_NAME);
            String hashedPassword = rs.getString(ADMIN_COLUMN_HASHED_PASSWORD);
            return new Admin(email, name, hashedPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("database error", e);
        }
    }
}
