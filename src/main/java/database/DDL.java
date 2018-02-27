package database;


import java.sql.*;
import java.util.Properties;


public class DDL {

    static final String ADMIN_TABLE = "admin";
    static final String ADMIN_COLUMN_ADMIN_ID = "admin_id";
    static final String ADMIN_COLUMN_EMAIL = "email";
    static final String ADMIN_COLUMN_NAME = "name";
    static final String ADMIN_COLUMN_HASHED_PASSWORD = "hashed_password";
    static final String CREATE_TABLE_ADMIN = "CREATE TABLE IF NOT EXISTS " + ADMIN_TABLE + "("
            + ADMIN_COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY,"
            + ADMIN_COLUMN_EMAIL + " TEXT NOT NULL,"
            + ADMIN_COLUMN_NAME + " TEXT NOT NULL,"
            + ADMIN_COLUMN_HASHED_PASSWORD + " TEXT NOT NULL);";


    static final String BLOGPOST_TALBE = "blog_post";
    static final String BLOGPOST_COLUMN_ID = "blog_post_id";
    static final String BLOGPOST_COLUMN_TITLE = "blog_post_title";
    static final String BLOGPOST_COLUMN_CONTENT = "blog_post_content";
    static final String BLOGPOST_COLUMN_AUTHOR = "blog_post_author";
    static final String CREATE_TABLE_BLOGPOST = "CREATE TABLE IF NOT EXISTS " + BLOGPOST_TALBE + "("
            + BLOGPOST_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + BLOGPOST_COLUMN_TITLE + " TEXT NOT NULL,"
            + BLOGPOST_COLUMN_CONTENT + " TEXT NOT NULL,"
            + BLOGPOST_COLUMN_AUTHOR + " TEXT NOT NULL);";

    private String dbUrl;
    private Properties sqLiteConfig;

    DDL(String dbUrl, Properties sqLiteConfig) {
        this.dbUrl = dbUrl;
        this.sqLiteConfig = sqLiteConfig;
    }
    void createTablesIfNotExists() {
        try (Connection connection = DriverManager.getConnection(dbUrl, sqLiteConfig)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_TABLE_ADMIN);
            statement.executeUpdate(CREATE_TABLE_BLOGPOST);

            if (!adminUserExists(connection)) {
                createDefaultAdmin(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("database error", e);
        }
    }

    private boolean adminUserExists(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM " + ADMIN_TABLE);
        boolean adminUserExists = rs.next();
        return adminUserExists;
    }

    private void createDefaultAdmin(Connection connection) throws SQLException {
        final String DEFAULT_ADMIN_EMAIL = "admin";
        final String DEFAULT_ADMIN_NAME = "admin";
        final String DEFAULT_ADMIN_PASSWORD =
                "1000:dc9229a6c7c5684628ff08cfa5192d61441595273cf62cd2:3e8c29252f41dd83b1decbd5b343237d4c19fafb879fa5d0";
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO " + ADMIN_TABLE
                        + "(" + ADMIN_COLUMN_EMAIL + ","
                        + ADMIN_COLUMN_NAME + ","
                        + ADMIN_COLUMN_HASHED_PASSWORD + ")"
                        + "VALUES(?, ?, ?)"
        );
        preparedStatement.setString(1, DEFAULT_ADMIN_EMAIL);
        preparedStatement.setString(2, DEFAULT_ADMIN_NAME);
        preparedStatement.setString(3, DEFAULT_ADMIN_PASSWORD);
        preparedStatement.executeUpdate();
    }
}
