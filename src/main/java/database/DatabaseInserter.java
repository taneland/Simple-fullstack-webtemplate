package database;

import data.BlogPost;

public interface DatabaseInserter {

     void addNewBlogPost(BlogPost blogPost) throws DatabaseException;
}
