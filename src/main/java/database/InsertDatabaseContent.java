package database;

import java.util.Properties;

public class InsertDatabaseContent implements DatabaseInserter {

    private String dbUrl;
    private Properties sqLiteConfig;

    InsertDatabaseContent(String dbUrl, Properties sqLiteConfig) {
        this.dbUrl = dbUrl;
        this.sqLiteConfig = sqLiteConfig;
    }
}
