package database;

import role.Admin;

public interface DatabaseSelector {

    Admin getAdmin() throws DatabaseException ;
}
