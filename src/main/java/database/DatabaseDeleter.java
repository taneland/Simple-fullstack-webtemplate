package database;

public interface DatabaseDeleter {

    void deleteBlogPost(int id) throws DatabaseException;
}
