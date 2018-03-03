package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static database.DDL.BLOGPOST_COLUMN_ID;
import static database.DDL.BLOGPOST_TALBE;

public class DeleteDatabaseContent implements DatabaseDeleter{

    private String dbUrl;
    private Properties sqLiteConfig;

    DeleteDatabaseContent(String dbUrl, Properties sqLiteConfig) {
        this.dbUrl = dbUrl;
        this.sqLiteConfig = sqLiteConfig;
    }

    @Override
    public void deleteBlogPost(int id) throws DatabaseException {
        try (Connection connection = DriverManager.getConnection(dbUrl, sqLiteConfig)) {
            String sql = "DELETE FROM " + BLOGPOST_TALBE
                    + " WHERE " + BLOGPOST_COLUMN_ID + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("database error", e);
        }
    }
}
