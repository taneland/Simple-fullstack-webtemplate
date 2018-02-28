package database;

import data.BlogPost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static database.DDL.*;

public class InsertDatabaseContent implements DatabaseInserter {

    private String dbUrl;
    private Properties sqLiteConfig;

    InsertDatabaseContent(String dbUrl, Properties sqLiteConfig) {
        this.dbUrl = dbUrl;
        this.sqLiteConfig = sqLiteConfig;
    }

    @Override
    public void addNewBlogPost(BlogPost blogPost) throws DatabaseException {
        try (Connection connection = DriverManager.getConnection(dbUrl, sqLiteConfig)) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO " + BLOGPOST_TALBE
                            + "(" + BLOGPOST_COLUMN_TITLE + ","
                            + BLOGPOST_COLUMN_CONTENT + ","
                            + BLOGPOST_COLUMN_AUTHOR
                            + ")"
                            + "VALUES(?, ?, ?)"
            );
            preparedStatement.setString(1, blogPost.getTitle());
            preparedStatement.setString(2, blogPost.getContent());
            preparedStatement.setString(3, blogPost.getAuthor());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("database error", e);
        }
    }
}
