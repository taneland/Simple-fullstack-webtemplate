package database;

import data.BlogPost;
import role.Admin;
import role.User;

import java.util.List;

public interface DatabaseSelector {

    Admin getAdmin() throws DatabaseException ;

    List<BlogPost> getAllBlogPosts() throws DatabaseException;
}
