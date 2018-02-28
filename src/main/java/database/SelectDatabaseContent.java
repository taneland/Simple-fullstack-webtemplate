package database;

import data.BlogPost;
import role.Admin;
import role.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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



    @Override
    public List<BlogPost> getAllBlogPosts() throws DatabaseException {
        try (Connection connection = DriverManager.getConnection(dbUrl, sqLiteConfig)) {
            List<BlogPost> allBlogPosts = new ArrayList<>();
            Statement statement = connection.createStatement();
            String sql = "SELECT "
                    + BLOGPOST_COLUMN_TITLE + ","
                    + BLOGPOST_COLUMN_CONTENT + ","
                    + BLOGPOST_COLUMN_AUTHOR
                    + " FROM " + BLOGPOST_TALBE;

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                allBlogPosts.add(new BlogPost(
                        rs.getString(BLOGPOST_COLUMN_TITLE),
                        rs.getString(BLOGPOST_COLUMN_CONTENT),
                        rs.getString(BLOGPOST_COLUMN_AUTHOR)
                ));
            }
            return allBlogPosts;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("database error", e);
        }
    }
}
